
package acme.features.administrator.challenges;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenges;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChallengesRepository extends AbstractRepository {

	@Query("select a from Challenges a where a.id = ?1")
	Challenges findOneChallengesById(int id);

	@Query("select a from Challenges a")
	Collection<Challenges> findManyAll();

}
