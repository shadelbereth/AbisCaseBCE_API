package be.abis.casebce.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.abis.casebce.converter.ApiConverter;
import be.abis.casebce.exception.ApiError;
import be.abis.casebce.model.api.Activity;
import be.abis.casebce.session.ActivitySessionRemote;

@Path("activities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActivityService {

	@EJB(name = "ActivitySession")
	private ActivitySessionRemote session;

	public ActivityService() {
		try {
			session = (ActivitySessionRemote) new InitialContext().lookup(
					"java:global/AbisCaseBCE_App/AbisCaseBCE_EJB/ActivitySession!be.abis.casebce.session.ActivitySessionRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@GET
	public List<Activity> getActivities(@QueryParam("worker-id") int performerId) {
		List<Activity> activities = new ArrayList<Activity>();
		try {
			session.getActivities(performerId).forEach(a -> activities.add(ApiConverter.convert(a)));
		} catch (Exception e) {
			ApiError err = new ApiError("Impossible to get activities", Status.BAD_REQUEST.getStatusCode(),
					e.getMessage());
			Response res = Response.status(Status.BAD_REQUEST).entity(err).build();
			throw new WebApplicationException(err.getTitle(), res);
		}
		return activities;
	}

	@GET
	@Path("{id}")
	public Activity getActivity(@PathParam("id") int id) {
		Activity activity = null;
		try {
			activity = ApiConverter.convert(session.reuploadActivity(id));
		} catch (Exception e) {
			ApiError err = new ApiError("Impossible to get activity with id " + id, Status.BAD_REQUEST.getStatusCode(),
					e.getMessage());
			Response res = Response.status(Status.BAD_REQUEST).entity(err).build();
			throw new WebApplicationException(err.getTitle(), res);
		}
		return activity;
	}
	
	@PUT
	@Path("{id}")
	public void updateActivity(@PathParam("id") int id, Activity activity) {
		try {
			if (id != activity.getActivityId()) {
				throw new Exception("Try to modify activity with wrong id");
			}
			session.updateActivity(ApiConverter.convert(activity));
		} catch (Exception e) {
			ApiError err = new ApiError("Impossible to update activity with id " + id, Status.BAD_REQUEST.getStatusCode(),
					e.getMessage());
			Response res = Response.status(Status.BAD_REQUEST).entity(err).build();
			throw new WebApplicationException(err.getTitle(), res);
		}
	}
}
