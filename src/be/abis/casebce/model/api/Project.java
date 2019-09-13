package be.abis.casebce.model.api;

import java.io.Serializable;

public class Project implements Serializable {

	private int projectId;
	private String name;
	private String description;

	private Company client;

	// getters and setters
	public Company getClient() {
		return client;
	}

	public void setClient(Company client) {
		this.client = client;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Override
	public int hashCode() {
		return 100 + (this.getName() + " " + (this.getClient() != null ? this.getClient().getName() : "")).hashCode();
	}

}
