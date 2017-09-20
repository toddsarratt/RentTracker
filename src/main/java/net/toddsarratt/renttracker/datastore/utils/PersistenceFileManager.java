package net.toddsarratt.renttracker.datastore.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PersistenceFileManager {

	private static final byte[] ZERO = new byte[]{'0'};

	public static void createSequenceFilesIfMissing(List<Path> persistencePaths) throws IOException {
		for (Path persistencePath : persistencePaths) {
			System.out.println("Looking for sequence file in: " + persistencePath);
			createSequenceFileIfMissing(persistencePath);
		}
	}

	private static void createSequenceFileIfMissing(Path persistencePath) throws IOException {
		String pathName = persistencePath.toString();
		String entityName = pathName.substring(pathName.lastIndexOf("\\") + 1, pathName.length());
		// Check for files
		String fileName = "seq." + entityName;
		// Create files if missing
		Path sequenceFileFullPath = Paths.get(pathName, fileName);
		if (!Files.exists(sequenceFileFullPath)) {
			System.out.println("Creating: " + sequenceFileFullPath);
			Files.createFile(sequenceFileFullPath);
			writeZeroToSequenceFile(sequenceFileFullPath);
		} else {
			System.out.println("Found: " + sequenceFileFullPath);
		}
	}

	private static void writeZeroToSequenceFile(Path sequenceFileFullPath) throws IOException {
		Files.write(sequenceFileFullPath, ZERO);
	}
}
