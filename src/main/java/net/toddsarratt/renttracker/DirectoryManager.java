package net.toddsarratt.renttracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryManager {

	protected static Path createDirectoryIfMissing(String directory) throws IOException {
		// Check for directory
		Path path = Paths.get(directory);
		// Create directory if missing
		if (!Files.exists(path)) {
			Files.createDirectory(path);
		}
		if (!Files.isDirectory(path)) {
			throw new IOException("A file named " + directory + " exists where a directory should exist.");
		}
		return path;
	}
}
