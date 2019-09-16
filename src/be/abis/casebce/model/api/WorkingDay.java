package be.abis.casebce.model.api;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorkingDay implements Serializable {

	private int id;
	private LocalDateTime start;
	private LocalDateTime end;

	private ExternalWorker worker;

	// constructor
	public WorkingDay() {
	}

	// getter and setters
	public int getId() {
		return id;
	}

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

	public ExternalWorker getWorker() {
		return worker;
	}

	public void setWorker(ExternalWorker worker) {
		this.worker = worker;
	}
}
