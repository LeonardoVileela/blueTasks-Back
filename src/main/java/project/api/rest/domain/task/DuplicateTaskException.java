package project.api.rest.domain.task;
//classe que trata dados duplicados
@SuppressWarnings("serial")
public class DuplicateTaskException extends Exception {

	public DuplicateTaskException(String message) {
		super(message);
	}

}
