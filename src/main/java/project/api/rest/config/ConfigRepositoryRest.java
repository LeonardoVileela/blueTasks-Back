package project.api.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import project.api.rest.domain.task.Task;

//classe para configura��es
@Configuration
public class ConfigRepositoryRest implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(Task.class);
		// Liberar api para front end que est� em outro
		// resolu��o do problema do projeto da fabrica
		cors.addMapping("/**")// end points liberados
				.allowedOrigins("*")// posso restringir a ip ou algum client
				.allowedMethods("GET", "POST", "PUT", "DELETE");// M�todos liberados
	}

}


