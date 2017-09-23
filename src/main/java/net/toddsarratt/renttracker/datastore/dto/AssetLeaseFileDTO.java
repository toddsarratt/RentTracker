package net.toddsarratt.renttracker.datastore.dto;

import net.toddsarratt.renttracker.entity.AssetLease;

public class AssetLeaseFileDTO implements FileDTO {

	private static final String FILE_SUFFIX = ".assetLease";

	private String id;
	private String stbId;
	private String assetId;
	private String date;
	private String rev;
	private String viewTime;

	public AssetLeaseFileDTO() {
	}

	public AssetLeaseFileDTO(AssetLease assetLease) {
		this.id = assetLease.getId().toString();
		this.stbId = assetLease.getStb().getId().toString();
		this.assetId = assetLease.getAsset().getId().toString();
		this.date = assetLease.getDate().toString();
		this.rev = assetLease.getRev().toPlainString();
		this.viewTime = assetLease.getViewTime().toString();
	}

	/**
	 * Receives a String from a file read of a persisted STB.
	 * Example incoming String: '1|stb1|the matrix|warner bros|2014-04-01|4.00|1:30'
	 *
	 * @param fileContents
	 * @return null if incoming String does not match the expected format
	 */
	public static AssetLeaseFileDTO fromFileRead(String fileContents) {
		String[] line = fileContents.split("\\|");
		String id = line[0];
		String stbId = line[1];
		String assetId = line[2];
		String date = line[3];
		String rev = line[4];
		String viewTime = line[5];
		AssetLeaseFileDTO dto = new AssetLeaseFileDTO();
		dto.setId(id);
		dto.setStbId(stbId);
		dto.setAssetId(assetId);
		dto.setDate(date);
		dto.setRev(rev);
		dto.setViewTime(viewTime);
		return dto;
	}

	@Override
	public byte[] serializeToFile() {
		return (id + "|" +
				stbId + "|" +
				assetId + "|" +
				date.toString() + "|" +
				rev + "|" +
				viewTime).getBytes();
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

	public String getStbId() {
		return stbId;
	}

	public void setStbId(String stbId) {
		this.stbId = stbId;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public String getViewTime() {
		return viewTime;
	}

	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}
}
