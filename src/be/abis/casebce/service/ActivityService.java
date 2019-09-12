package be.abis.casebce.service;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.abis.casebce.model.Activity;
import be.abis.casebce.session.ActivitySessionRemote;

@Path("activities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ActivityService {

	@EJB(name = "ActivitySession")
	private ActivitySessionRemote session;
	
	public ActivityService() {
		try {
			session = (ActivitySessionRemote) new InitialContext().lookup("java:global/AbisCaseBCE_App/AbisCaseBCE_EJB/ActivitySession!be.abis.casebce.session.ActivitySessionRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@GET
	public Response getActivities(@QueryParam("worker-id") int performerId) {
		Response res;
		try {
			List<Activity> activities = session.getActivities(performerId);
			res = Response.status(Status.OK).entity(activities).build();
		} catch (Exception e) {
			e.printStackTrace();
			res = Response.status(Status.BAD_REQUEST).entity(e).build();
		}
		return res;
	}
}
