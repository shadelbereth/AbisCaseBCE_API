package be.abis.casebce.service;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.abis.casebce.converter.ApiConverter;
import be.abis.casebce.exception.ApiError;
import be.abis.casebce.model.api.Login;
import be.abis.casebce.model.api.Worker;
import be.abis.casebce.session.WorkerSessionRemote;

@Path("workers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkerService {

	@EJB(name = "WorkerSession")
	private WorkerSessionRemote session;

	public WorkerService() {
		try {
			session = (WorkerSessionRemote) new InitialContext().lookup(
					"java:global/AbisCaseBCE_App/AbisCaseBCE_EJB/WorkerSession!be.abis.casebce.session.WorkerSessionRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Path("{id}")
	@GET
	public Worker getWorker(@PathParam("id") int workerId) {
		Worker worker = null;
		try {
			worker = ApiConverter.convert(session.getUser(workerId));
		} catch (Exception e) {
			ApiError err = new ApiError("Impossible to get worker with id " + workerId,
					Status.BAD_REQUEST.getStatusCode(), e.getMessage());
			Response res = Response.status(Status.BAD_REQUEST).entity(err).build();
			throw new WebApplicationException(err.getTitle(), res);
		}
		return worker;
	}

	@Path("login")
	@POST
	public Worker login(Login login) {
		Worker worker = null;
		try {
			System.out.println("login with " + login.getLogin() + " and " + login.getPassword());
			worker = new Worker();
			worker.setLogin(login.getLogin());
			worker.setFirstName(login.getLogin());
			worker.setId(-1);
			worker.setLastName(login.getLogin());
		} catch (Exception e) {
			ApiError err = new ApiError("Impossible to login", Status.BAD_REQUEST.getStatusCode(), e.getMessage());
			Response res = Response.status(Status.BAD_REQUEST).entity(err).build();
			throw new WebApplicationException(err.getTitle(), res);
		}
		return worker;
	}
}
