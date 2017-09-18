package net.toddsarratt.renttracker.datastore.dto;

import net.toddsarratt.renttracker.entity.Asset;

public class AssetFileDTO implements FileDTO {

	private static final String FILE_SUFFIX = ".asset";

	private Long id;
	private String title;
	private String provider;

	public AssetFileDTO(Asset asset) {
		this.id = asset.getId();
		this.title = asset.getTitle();
		this.provider = asset.getProvider();
	}

	@Override
	public byte[] serializeToFile() {
		StringBuilder sb = new StringBuilder();
		sb.append(title).append("|")
				.append(provider);
		return sb.toString().getBytes();
	}

	@Override
	public String getFileSuffix() {
		return FILE_SUFFIX;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
