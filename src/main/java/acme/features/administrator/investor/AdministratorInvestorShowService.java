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

package acme.features.administrator.investor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investor.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorInvestorShowService implements AbstractShowService<Administrator, Investor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorInvestorRepository repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<Investor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Investor> request, final Investor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "statement", "stars");
	}

	@Override
	public Investor findOne(final Request<Investor> request) {
		assert request != null;

		Investor result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneInvestorById(id);

		return result;
	}

}
