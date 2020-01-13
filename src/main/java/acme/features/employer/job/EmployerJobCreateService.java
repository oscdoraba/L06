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

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EmployerJobRepository repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
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

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "status", "active", "employer", "descriptor", "descriptor.description");
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		assert request != null;

		Job result;

		result = new Job();
		result.setEmployer(this.repository.findOneEmployerById(request.getPrincipal().getActiveRoleId()));
		result.setStatus(Status.DRAFT);
		result.setFinalMode(false);
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<String> spamWords = new ArrayList<>();
		spamWords.add("sex");
		spamWords.add("hard core");
		spamWords.add("viagra");
		spamWords.add("cialis");
		spamWords.add("nigeria");
		spamWords.add("you've won");
		spamWords.add("million dollar");

		boolean esSpam = false, esSpamTitulo = false, textoVacio = false;

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
			//-------------------------------------------------------------------------------------------------------------------
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

		}

	}
	//-------------------------------------------------------------------------------------------------------------------

	// DUTTIES deben sumar el 100%

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Descriptor d = new Descriptor(); // <== Aqui va la query
		// cogemos la descripcion que haya puesto el usuario por pantalla a traves de la pagina
		String descripcion = request.getModel().getString("descriptor.description");

		//se la seteamos al descriptor que hemos sacado a traves de la query
		d.setDescription(descripcion);

		//aplastamos este descriptor sobre el anterior que teniamos en nuestra entity
		entity.setDescriptor(d);
		//lo pasamos a base de datos
		this.repository.save(entity);
	}

}
