package net.toddsarratt.renttracker.datastore.dto;

public interface FileDTO {
	String getId();

	byte[] serializeToFile();

	String getFileSuffix();
}
