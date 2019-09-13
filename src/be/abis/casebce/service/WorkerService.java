package be.abis.casebce.service;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import be.abis.casebce.converter.ApiConverter;
import be.abis.casebce.model.api.ExternalWorker;
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
		Worker worker = ApiConverter.convert(session.getUser(workerId));
		return worker;
	}
}
