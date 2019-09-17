package be.abis.casebce.model.api;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import be.abis.casebce.converter.LocalDateTimeXmlAdapter;

public class Activity implements Serializable {

	private int activityId;
	@XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
	private LocalDateTime start;
	@XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
	private LocalDateTime end;
	private String description;

	private Project project;
	private Worker performer;

	// constructor

	public Activity() {
		super();
	}

	public Activity(LocalDateTime start, LocalDateTime end, String description, Project project, Worker performer) {
		super();
		this.start = start;
		this.end = end;
		this.description = description;
		this.project = project;
		this.performer = performer;
	}

	// getter and setters

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Worker getPerformer() {
		return performer;
	}

	public void setPerformer(Worker performer) {
		this.performer = performer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
}
