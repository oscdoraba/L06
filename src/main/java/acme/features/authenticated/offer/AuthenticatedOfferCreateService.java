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

package acme.features.authenticated.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedOfferCreateService implements AbstractCreateService<Authenticated, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedOfferRepository repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "minMoney", "maxMoney", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;

		result = new Offer();

		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted, isMinEuro, isMaxEuro, isMenorMoney, isDuplicated;

		if (!errors.hasErrors("minMoney")) { //Comprobamos la currencys de ambos valores de dinero
			String minCurrency = entity.getMinMoney().getCurrency();
			isMinEuro = minCurrency.equals("€") || minCurrency.equals("EUR");
			errors.state(request, isMinEuro, "minMoney", "consumer.offer.error.min-euro");
		}

		if (!errors.hasErrors("maxMoney")) {
			String maxCurrency = entity.getMaxMoney().getCurrency();
			isMaxEuro = maxCurrency.equals("€") || maxCurrency.equals("EUR");
			errors.state(request, isMaxEuro, "maxMoney", "consumer.offer.error.max-euro");
		}

		if (!errors.hasErrors("maxMoney") && !errors.hasErrors("minMoney")) {
			Double minAmount = entity.getMinMoney().getAmount();
			Double maxAmount = entity.getMaxMoney().getAmount();
			isMenorMoney = minAmount < maxAmount;
			errors.state(request, isMenorMoney, "maxMoney", "consumer.offer.error.must-be-less");
		}

		if (!errors.hasErrors("ticker")) {
			isDuplicated = this.repository.findOneByTicker(entity.getTicker()) != null;
			errors.state(request, !isDuplicated, "ticker", "consumer.offer.error.duplicated");
		}

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.requests.error.must-accept");

	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		Date moment;

		Money m = new Money();
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		//		m.setAmount(amount);

		//		m.setCurrency(currency);

		this.repository.save(entity);
	}

}
