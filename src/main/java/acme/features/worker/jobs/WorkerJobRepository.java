
package acme.features.worker.jobs;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.active = 1 AND 365*YEAR(current_time())+30*MONTH(current_time())+DAY(current_time())-365*YEAR(j.deadline)-30*MONTH(j.deadline)-DAY(j.deadline)<0")
	Collection<Job> findManyAll();

}
