package net.toddsarratt.renttracker.datastore.dto;

import net.toddsarratt.renttracker.entity.Stb;

public class StbFileDTO implements FileDTO {

	private static final String FILE_SUFFIX = ".stb";

	private Long id;
	private String name;

	public StbFileDTO(Stb stb) {
		this.id = stb.getId();
		this.name = stb.getName();
	}

	@Override
	public byte[] serializeToFile() {
		return this.name.getBytes();
	}

	@Override
	public String getFileSuffix() {
		return FILE_SUFFIX;
	}

	@Override
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
