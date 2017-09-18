package net.toddsarratt.renttracker.datastore.dto;

import net.toddsarratt.renttracker.entity.AssetLease;

public class AssetLeaseFileDTO {
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

	public byte[] serializeToFile() {
		StringBuilder sb = new StringBuilder();
		sb.append(stbId).append("|")
				.append(assetId).append("|")
				.append(date).append("|")
				.append(rev).append("|")
				.append(viewTime);
		return sb.toString().getBytes();
	}
}
