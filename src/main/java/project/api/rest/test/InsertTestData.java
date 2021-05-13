package project.api.rest.test;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.api.rest.domain.user.AppUser;
import project.api.rest.domain.user.AppUserRepository;

@Component
public class InsertTestData {
	
	private AppUserRepository appUSerRepository;
	
	@Autowired
	public InsertTestData( AppUserRepository appUSerRepository) {
		this.appUSerRepository = appUSerRepository;
	}
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		AppUser appUser1 = new AppUser("carolyn", encoder.encode("123"), "Carolyn");
		appUSerRepository.save(appUser1);
		
	}
}*/
