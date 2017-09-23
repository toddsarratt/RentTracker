package net.toddsarratt.renttracker.datastore.dto;

import net.toddsarratt.renttracker.entity.Asset;

public class AssetFileDTO implements FileDTO {

	private static final String FILE_SUFFIX = ".asset";

	private String id;
	private String title;
	private String provider;

	public AssetFileDTO() {
	}

	public AssetFileDTO(Asset asset) {
		this.id = asset.getId().toString();
		this.title = asset.getTitle();
		this.provider = asset.getProvider();
	}

	/**
	 * Receives a String from a file read of a persisted Asset.
	 * Example incoming String: '1|stb1'
	 *
	 * @param fileContents
	 * @return null if incoming String does not match the expected format
	 */
	public static AssetFileDTO fromFileRead(String fileContents) {
		String[] line = fileContents.split("\\|");
		String title = line[1];
		String provider = line[2];
		AssetFileDTO dto = new AssetFileDTO();
		dto.setId(line[0]);
		dto.setTitle(title);
		dto.setProvider(provider);
		return dto;
	}

	@Override
	public byte[] serializeToFile() {
		return (id + "|" + title + "|" + provider).getBytes();
	}

	@Override
	public String getFileSuffix() {
		return FILE_SUFFIX;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
}
