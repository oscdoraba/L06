
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select sum (percentage) from Duty d where d.descriptor.id = ?1")
	Double sumPercentage(int descriptorId);

	@Query("select count(*) from Duty d where d.descriptor.id = ?1")
	Double countDuty(int descriptorId);

	@Query("select d from Descriptor d where d.id = ?1")
	Descriptor findOneDescriptorById(int descriptorId);

	@Query("select e from Employer e where e.id = ?1")
	Employer findOneEmployerById(int employerId);

	@Query("select ap from Application ap where ap.job.id = ?1")
	Collection<Application> findOneApplicationByJodId(int jobId);
}
