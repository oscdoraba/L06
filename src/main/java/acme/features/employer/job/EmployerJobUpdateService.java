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

package acme.features.employer.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = job.isFinalMode() || !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "finalMode", "status", "active", "descriptor.description");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean dutiesCien = false, esSpam = false, esSpamTitulo = false, algunaDuty;

		List<String> spamWords = new ArrayList<>();
		spamWords.add("sex");
		spamWords.add("hard core");
		spamWords.add("viagra");
		spamWords.add("cialis");
		spamWords.add("nigeria");
		spamWords.add("you've won");
		spamWords.add("million dollar");

		if (!errors.hasErrors("job")) {

			String[] palabras = entity.getDescriptor().getDescription().split(" ");
			for (int i = 0; i < palabras.length; i++) {
				for (String w : spamWords) {
					if (w.trim().equals(palabras[i].trim())) {
						esSpam = true;
					}
				}

				if (i <= palabras.length - 2) {
					if (palabras[i].equals("hard") && palabras[i + 1].equals("core")) {
						esSpam = true;
					} else if (palabras[i].equals("you've") && palabras[i + 1].equals("won")) {
						esSpam = true;
					} else if (palabras[i].equals("million") && palabras[i + 1].equals("dollar")) {
						esSpam = true;
					}
				}

			}

			errors.state(request, !esSpam, "descriptor.description", "employer.job.error.descriptor.description");
			//------------------------------------------------------------------------------------------------------------------

			String[] palabrasTitulo = entity.getTitle().split(" ");
			for (int i = 0; i < palabrasTitulo.length; i++) {
				for (String w : spamWords) {
					if (w.trim().equals(palabrasTitulo[i].trim())) {
						esSpamTitulo = true;
					}
				}

				if (i <= palabrasTitulo.length - 2) {
					if (palabrasTitulo[i].equals("hard") && palabrasTitulo[i + 1].equals("core")) {
						esSpamTitulo = true;
					} else if (palabrasTitulo[i].equals("you've") && palabrasTitulo[i + 1].equals("won")) {
						esSpamTitulo = true;
					} else if (palabrasTitulo[i].equals("million") && palabrasTitulo[i + 1].equals("dollar")) {
						esSpamTitulo = true;
					}
				}

			}

			errors.state(request, !esSpamTitulo, "title", "employer.job.error.titulo");
			//------------------------------------------------------------------------------------------------------------------
			algunaDuty = this.repository.countDuty(entity.getDescriptor().getId()) > 0;
			if (algunaDuty) {
				dutiesCien = this.repository.sumPercentage(entity.getDescriptor().getId()) == 100.;
			}
			//comprobamos que los duties sumen 100
			errors.state(request, !(!dutiesCien && entity.isFinalMode()), "descriptor.description", "employer.job.error.descriptor.dutiesCien");

			//------------------------------------------------------------------------------------------------------------------

			errors.state(request, !entity.isFinalMode(), "descriptor.description", "employer.job.error.finalmode");

			errors.state(request, !esSpamTitulo, "title", "employer.job.error.titulo");

			//			String text = entity.getNuevo().getText();
			//			String textoNuevo = request.getModel().getString("nuevo.text");
			//			String link = entity.getNuevo().getLink();
			//			textoVacio = (link != null || link != "") && (text == null || text.equals(""));

			//			errors.state(request, !textoVacio, "nuevo.texto", "employer.job.error.nuevo.text");
			//
			//			dejarEnBlanco = (!text.equals("") || !text.equals(null)) && (textoNuevo.equals("") || textoNuevo.equals(null));
			//			errors.state(request, !dejarEnBlanco, "nuevo.texto", "employer.tex", arguments);

			//			String text = request.getModel().getString("nuevo.text");
			//			String link = request.getModel().getString("nuevo.link");
			//			textoVacio = (link != null || !link.isEmpty() || link != "") && (text == null || text.isEmpty() || text.equals(""));
			//
			//			errors.state(request, !textoVacio, "nuevo.text", "employer.job.error.nuevo.text");

		}

	}
	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		boolean dutiesCien = false;
		boolean algunaDuty = this.repository.countDuty(entity.getDescriptor().getId()) > 0;
		if (algunaDuty) {
			dutiesCien = this.repository.sumPercentage(entity.getDescriptor().getId()) == 100.;
		}

		if (dutiesCien) {
			entity.setFinalMode(true);
		}

		this.repository.save(entity);
	}

}
