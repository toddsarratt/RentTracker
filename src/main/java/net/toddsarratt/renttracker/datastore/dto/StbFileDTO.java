package net.toddsarratt.renttracker.datastore.dto;

import net.toddsarratt.renttracker.entity.Stb;

public class StbFileDTO {

	private Long id;
	private String name;

	public StbFileDTO(Stb stb) {
		this.id = stb.getId();
		this.name = stb.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
