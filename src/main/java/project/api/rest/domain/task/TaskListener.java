package project.api.rest.domain.task;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import project.api.rest.domain.user.AppUser;
import project.api.rest.domain.user.AppUserRepository;

//Essa classe é responsavel por fazer alterações no get antes dele salvar no banco de dados
//quero salvar o id automaticamente do usuario logado, sem que o front me envie o id
@Component
public class TaskListener {
	// injeção de dependencia estatica
	private static AppUserRepository appUserRepository;

	// esse método é o responsavel por fazer alterações antes de salvar
	// nesse método estou salvando o id da sessão
	@PrePersist
	public void onPrePersistHandler(Task task) {
		if (task.getAppUser() == null) {
			// aqui eu consigo pegar quem é o usuario da sessão pelo contexto do spring
			// security
			String username = SecurityContextHolder.getContext().getAuthentication().getName();

			// como eu só peguei o nome do usuario pelo contexo, eu busco o usuario pelo
			// nome
			AppUser appUser = appUserRepository.findByUsername(username);

			if (appUser == null) {
				throw new EntityNotFoundException("O usuário não foi encontrado");
			}

			// seto o novo usuario na task que veio do post do front , e continuo o fluxo
			// pra salvar no banco
			task.setAppUser(appUser);
		}
	}

	@Autowired
	public void init(AppUserRepository appUserRepository) {
		TaskListener.appUserRepository = appUserRepository;
	}
}
