
package acme.features.administrator.customisationParameters;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomisationParametersRepository extends AbstractRepository {

	@Query("select c from CustomisationParameters c")
	Collection<CustomisationParameters> findManyAll();

	@Query("select a from CustomisationParameters a where a.id = ?1")
	CustomisationParameters findOneCustomisationParameterById(int id);

}
