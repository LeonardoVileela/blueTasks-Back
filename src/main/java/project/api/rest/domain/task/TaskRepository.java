package project.api.rest.domain.task;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

	Task findByDescription(String description);

	//faz com que o usúario só possa ver as tasks dele, se tirar isso todos os úsuarios podem ver todas as tasks
	@Override
	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal}")
	Page<Task> findAll(Pageable pageable);

	//faz com que o usúario só possa ver as tasks dele quando ele busca pelo id da task, se tirar isso todos os úsuarios podem ver todas as tasks
	@Override
	@Query("SELECT t FROM Task t WHERE t.id = ?1 AND t.appUser.username = ?#{principal}")
	Optional<Task> findById(Long id);

}
