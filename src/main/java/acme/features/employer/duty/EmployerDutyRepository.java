
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select j from Duty j where j.id = ?1")
	Duty findOneDutyById(int id);

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> findManyDutiesByDescriptorId(int descriptorId);

	@Query("select d from Descriptor d where d.id = ?1")
	Descriptor findOneDescriptorById(int descriptorId);

	@Query("select j from Job j where j.descriptor.id = ?1")
	Job findOneJobDescriptorById(int id);

	@Query("select sum (percentage) from Duty d where d.descriptor.id = ?1")
	Double sumPercentage(int descriptorId);

	@Query("select count(*) from Duty d where d.descriptor.id = ?1")
	Double countDuty(int descriptorId);

}
