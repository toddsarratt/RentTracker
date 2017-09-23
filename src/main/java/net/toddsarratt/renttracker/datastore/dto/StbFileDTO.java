package net.toddsarratt.renttracker.datastore.dto;

import net.toddsarratt.renttracker.entity.Stb;

public class StbFileDTO implements FileDTO {

	private static final String FILE_SUFFIX = ".stb";

	private String id;
	private String name;

	private StbFileDTO() {
	}

	public StbFileDTO(Stb stb) {
		this.id = stb.getId().toString();
		this.name = stb.getName();
	}

	/**
	 * Receives a String from a file read of a persisted STB.
	 * Example incoming String: '1|stb1'
	 *
	 * @param fileContents
	 * @return null if incoming String does not match the expected format
	 */
	public static StbFileDTO fromFileRead(String fileContents) {
		String[] line = fileContents.split("\\|");
		String id = line[0];
		String stbName = line[1];
		StbFileDTO dto = new StbFileDTO();
		dto.setId(id);
		dto.setName(stbName);
		return dto;
	}

	@Override
	public byte[] serializeToFile() {
		return (id + "|" + name).getBytes();
	}

	@Override
	public String getFileSuffix() {
		return FILE_SUFFIX;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
