package be.abis.casebce.converter;

import be.abis.casebce.model.api.Activity;
import be.abis.casebce.model.api.Company;
import be.abis.casebce.model.api.ExternalWorker;
import be.abis.casebce.model.api.Project;
import be.abis.casebce.model.api.Worker;

public class ApiConverter {
	
	private ApiConverter() {
		
	}
	
	public static Activity convert(be.abis.casebce.model.Activity activity) {
		Activity activityApi = new Activity();
		activityApi.setActivityId(activity.getActivityId());
		activityApi.setDescription(activity.getDescription());
		activityApi.setEnd(activity.getEnd());
		activityApi.setPerformer(ApiConverter.convert(activity.getPerformer()));
		activityApi.setProject(ApiConverter.convert(activity.getProject()));
		activityApi.setStart(activity.getStart());
		return activityApi;
	}
	
	public static be.abis.casebce.model.Activity convert(Activity activity) {
		be.abis.casebce.model.Activity activityBean = new be.abis.casebce.model.Activity();
		activityBean.setActivityId(activity.getActivityId());
		activityBean.setDescription(activity.getDescription());
		activityBean.setEnd(activity.getEnd());
		activityBean.setPerformer(ApiConverter.convert(activity.getPerformer()));
		activityBean.setProject(ApiConverter.convert(activity.getProject()));
		activityBean.setStart(activity.getStart());
		return activityBean;
	}
	
	public static Worker convert(be.abis.casebce.model.Worker worker) {
		Worker workerApi;
		if (worker instanceof be.abis.casebce.model.ExternalWorker) {
			workerApi = new ExternalWorker();
			((ExternalWorker) workerApi).setHourRate(((be.abis.casebce.model.ExternalWorker)worker).getHourRate());
		} else {
			workerApi = new Worker();
		}
		workerApi.setFirstName(worker.getFirstName());
		workerApi.setId(worker.getId());
		workerApi.setLastName(worker.getLastName());
		workerApi.setLogin(worker.getLogin());
		return workerApi;
	}
	
	public static be.abis.casebce.model.Worker convert(Worker worker) {
		be.abis.casebce.model.Worker workerBean;
		if (worker instanceof ExternalWorker) {
			workerBean = new be.abis.casebce.model.ExternalWorker();
			((be.abis.casebce.model.ExternalWorker) workerBean).setHourRate(((ExternalWorker)worker).getHourRate());
		} else {
			workerBean = new be.abis.casebce.model.Worker();
		}
		workerBean.setFirstName(worker.getFirstName());
		workerBean.setId(worker.getId());
		workerBean.setLastName(worker.getLastName());
		workerBean.setLogin(worker.getLogin());
		return workerBean;
	}
	
	public static Project convert(be.abis.casebce.model.Project project) {
		Project projectApi = new Project();
		projectApi.setClient(ApiConverter.convert(project.getClient()));
		projectApi.setDescription(project.getDescription());
		projectApi.setName(project.getName());
		projectApi.setProjectId(project.getProjectId());
		return projectApi;
	}
	
	public static be.abis.casebce.model.Project convert(Project project) {
		be.abis.casebce.model.Project projectBean = new be.abis.casebce.model.Project();
		projectBean.setClient(ApiConverter.convert(project.getClient()));
		projectBean.setDescription(project.getDescription());
		projectBean.setName(project.getName());
		projectBean.setProjectId(project.getProjectId());
		return projectBean;
	}
	
	public static Company convert(be.abis.casebce.model.Company company) {
		Company companyApi = new Company();
		companyApi.setName(company.getName());
		companyApi.setId(company.getId());
		return companyApi;
	}
	
	public static be.abis.casebce.model.Company convert(Company company) {
		be.abis.casebce.model.Company companyBean = new be.abis.casebce.model.Company();
		companyBean.setName(company.getName());
		companyBean.setId(company.getId());
		return companyBean;
	}
}
