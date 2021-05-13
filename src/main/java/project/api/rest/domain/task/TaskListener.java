package project.api.rest.domain.task;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import project.api.rest.domain.user.AppUser;
import project.api.rest.domain.user.AppUserRepository;

//Essa classe � responsavel por fazer altera��es no get antes dele salvar no banco de dados
//quero salvar o id automaticamente do usuario logado, sem que o front me envie o id
@Component
public class TaskListener {
	// inje��o de dependencia estatica
	private static AppUserRepository appUserRepository;

	// esse m�todo � o responsavel por fazer altera��es antes de salvar
	// nesse m�todo estou salvando o id da sess�o
	@PrePersist
	public void onPrePersistHandler(Task task) {
		if (task.getAppUser() == null) {
			// aqui eu consigo pegar quem � o usuario da sess�o pelo contexto do spring
			// security
			String username = SecurityContextHolder.getContext().getAuthentication().getName();

			// como eu s� peguei o nome do usuario pelo contexo, eu busco o usuario pelo
			// nome
			AppUser appUser = appUserRepository.findByUsername(username);

			if (appUser == null) {
				throw new EntityNotFoundException("O usu�rio n�o foi encontrado");
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
