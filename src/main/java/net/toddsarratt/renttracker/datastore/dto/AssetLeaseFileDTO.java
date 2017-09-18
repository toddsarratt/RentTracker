package net.toddsarratt.renttracker.datastore.dto;

import net.toddsarratt.renttracker.entity.AssetLease;

public class AssetLeaseFileDTO implements FileDTO {

	private static final String FILE_SUFFIX = ".assetLease";

	private Long id;
	private Long stbId;
	private Long assetId;
	private String date;
	private String rev;
	private String viewTime;

	public AssetLeaseFileDTO(AssetLease assetLease) {
		this.id = assetLease.getId();
		this.stbId = assetLease.getStb().getId();
		this.assetId = assetLease.getAsset().getId();
		this.date = assetLease.getDate().toString();
		this.rev = assetLease.getRev().toPlainString();
		this.viewTime = assetLease.getViewTime().toString();
	}

	@Override
	public byte[] serializeToFile() {
		StringBuilder sb = new StringBuilder();
		sb.append(stbId).append("|")
				.append(assetId).append("|")
				.append(date).append("|")
				.append(rev).append("|")
				.append(viewTime);
		return sb.toString().getBytes();
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

	public Long getStbId() {
		return stbId;
	}

	public void setStbId(Long stbId) {
		this.stbId = stbId;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
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
