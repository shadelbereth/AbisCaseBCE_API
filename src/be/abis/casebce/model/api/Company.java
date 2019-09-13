package be.abis.casebce.model.api;

import java.io.Serializable;

public class Company implements Serializable {

	// fields
	private int id;
	private String name;

	// getter and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
