package be.abis.casebce.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.abis.casebce.converter.ApiConverter;
import be.abis.casebce.exception.ApiError;
import be.abis.casebce.model.api.Project;
import be.abis.casebce.session.ProjectSessionRemote;

@Path("projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProjectService {

	@EJB(name = "ProjectSession")
	private ProjectSessionRemote session;

	public ProjectService() {
		try {
			session = (ProjectSessionRemote) new InitialContext().lookup(
					"java:global/AbisCaseBCE_App/AbisCaseBCE_EJB/ProjectSession!be.abis.casebce.session.ProjectSessionRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@GET
	public List<Project> getProjects() {
		List<Project> projects = new ArrayList<Project>();
		try {
			session.getProjects().forEach(p -> projects.add(ApiConverter.convert(p)));
		} catch (Exception e) {
			ApiError err = new ApiError("Impossible to get projects", Status.BAD_REQUEST.getStatusCode(),
					e.getMessage());
			Response res = Response.status(Status.BAD_REQUEST).entity(err).build();
			throw new WebApplicationException(err.getTitle(), res);
		}
		return projects;
	}
}
