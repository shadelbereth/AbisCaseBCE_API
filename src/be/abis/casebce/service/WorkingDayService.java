package be.abis.casebce.service;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.abis.casebce.converter.ApiConverter;
import be.abis.casebce.exception.ApiError;
import be.abis.casebce.model.api.WorkingDay;
import be.abis.casebce.session.WorkingDaySessionRemote;

@Path("working-days")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkingDayService {

	@EJB(name = "WorkingDaySession")
	private WorkingDaySessionRemote session;

	public WorkingDayService() {
		try {
			session = (WorkingDaySessionRemote) new InitialContext().lookup(
					"java:global/AbisCaseBCE_App/AbisCaseBCE_EJB/WorkingDaySession!be.abis.casebce.session.WorkingDaySessionRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Path("current/{workerId}")
	@GET
	public WorkingDay getActivity(@PathParam("workerId") int workerId) {
		WorkingDay workingDay = null;
		try {
			workingDay = ApiConverter.convert(session.getCurrentWorkingDay(workerId));
		} catch (Exception e) {
			ApiError err = new ApiError("lastWorkingDayErrorMessage", Status.BAD_REQUEST.getStatusCode(),
					e.getMessage());
			Response res = Response.status(Status.BAD_REQUEST).entity(err).build();
			throw new WebApplicationException(err.getTitle(), res);
		}
		return workingDay;
	}

	@Path("start")
	@PUT
	public WorkingDay startWorkingDay(WorkingDay workingDay) {
		try {
			workingDay = ApiConverter.convert(session.startWorkingDay(ApiConverter.convert(workingDay)));
		} catch (Exception e) {
			ApiError err = new ApiError("startWorkingDayErrorMessage", Status.BAD_REQUEST.getStatusCode(),
					e.getMessage());
			Response res = Response.status(Status.BAD_REQUEST).entity(err).build();
			throw new WebApplicationException(err.getTitle(), res);
		}
		return workingDay;
	}

	@Path("close")
	@PUT
	public WorkingDay closeWorkingDay(WorkingDay workingDay) {
		try {
			workingDay = ApiConverter.convert(session.closeWorkingDay(ApiConverter.convert(workingDay)));
		} catch (Exception e) {
			ApiError err = new ApiError("closeWorkingDayErrorMessage", Status.BAD_REQUEST.getStatusCode(),
					e.getMessage());
			Response res = Response.status(Status.BAD_REQUEST).entity(err).build();
			throw new WebApplicationException(err.getTitle(), res);
		}
		return workingDay;
	}
}
