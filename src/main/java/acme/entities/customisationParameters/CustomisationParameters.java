
package acme.entities.customisationParameters;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomisationParameters extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	private ArrayList<String>	spamWords;

	@NotNull
	private Double				threshold;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
