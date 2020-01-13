
package acme.entities.companyRecords;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompanyRecord extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				ceoName;

	@NotBlank
	private String				activities;

	@URL
	@NotBlank
	private String				web;

	@Pattern(regexp = "(\\+\\d\\d\\d) (\\(\\d\\d\\d\\d\\)) (\\d\\d\\d\\d\\d\\d(\\d)?(\\d)?(\\d)?)")
	@NotBlank
	private String				phone;

	@Email
	@NotBlank
	private String				email;

	@NotNull
	private Boolean				incorporated;

	@Min(0)
	@Max(5)
	private Integer				stars;

	@Transient
	private String				nameExtra;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------


	//Aux

	public String getNameExtra() {

		String res = "";

		if (this.incorporated == true) {
			res = this.name + ", Inc.";
		} else if (this.incorporated == false) {
			res = this.name + ", LLC.";
		}

		return res;
	}

}
