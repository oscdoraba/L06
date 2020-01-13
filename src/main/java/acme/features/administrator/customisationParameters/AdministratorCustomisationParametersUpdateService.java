/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.customisationParameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomisationParametersUpdateService implements AbstractUpdateService<Administrator, CustomisationParameters> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorCustomisationParametersRepository repository;


	@Override
	public boolean authorise(final Request<CustomisationParameters> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<CustomisationParameters> request, final CustomisationParameters entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CustomisationParameters> request, final CustomisationParameters entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWords", "threshold");
	}

	@Override
	public CustomisationParameters findOne(final Request<CustomisationParameters> request) {
		assert request != null;

		CustomisationParameters result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneCustomisationParameterById(id);

		return result;
	}

	@Override
	public void validate(final Request<CustomisationParameters> request, final CustomisationParameters entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<CustomisationParameters> request, final CustomisationParameters entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
