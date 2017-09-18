package net.toddsarratt.renttracker.datastore.dto;

public interface FileDTO {
	public Long getId();

	public byte[] serializeToFile();

	public String getFileSuffix();
}
