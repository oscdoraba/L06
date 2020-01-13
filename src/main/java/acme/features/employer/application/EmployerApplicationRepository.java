
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select ap from Application ap where ap.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select ap from Application ap where ap.job.employer.id = ?1 order by ap.referenceNumber asc")
	Collection<Application> findManyApplicationById(int employerId);

}
