package net.toddsarratt.renttracker.datastore.dto;

import net.toddsarratt.renttracker.entity.Stb;

public class StbFileDTO implements FileDTO {

	private static final String FILE_SUFFIX = ".stb";

	private Long id;
	private String name;

	private StbFileDTO() {
	}

	public StbFileDTO(Stb stb) {
		this.id = stb.getId();
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
		Long id;
		// Make sure it's a number!
		if (line[0].matches("^\\d+$")) {
			id = Long.valueOf(line[0]);
		} else {
			return null;
		}
		String stbName = line[1];
		StbFileDTO dto = new StbFileDTO();
		dto.setId(id);
		dto.setName(stbName);
		return dto;
	}

	@Override
	public byte[] serializeToFile() {
		return (id + "|" + name + "\\n").getBytes();
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
