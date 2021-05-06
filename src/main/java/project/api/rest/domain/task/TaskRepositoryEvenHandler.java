package project.api.rest.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
//classe que trata dados duplicados
@Component
@RepositoryEventHandler(Task.class)
public class TaskRepositoryEvenHandler {

	private TaskRepository taskRepository;

	@Autowired
	public TaskRepositoryEvenHandler(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@HandleBeforeSave
	@HandleBeforeCreate
	public void handle(Task task) throws DuplicateTaskException {
		Task taskDB = this.taskRepository.findByDescription(task.getDescription());
		boolean duplicate = false;

		if (taskDB != null) {
			if ((task.getId() == null || task.getId() == 0) && task.getDescription().equals(taskDB.getDescription())) {
				duplicate = true;
			}
			if (task.getId() != null && task.getId() > 0 && task.getId().equals(taskDB.getId())) {
				duplicate = true;
			}
		}
		if (duplicate) {
			throw new DuplicateTaskException("there is already a task with that description");
		}

	}

}
