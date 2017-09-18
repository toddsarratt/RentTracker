package net.toddsarratt.renttracker.datastore.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersistenceFileManager {

	private static final byte[] ZERO = new byte[]{'0'};

	public static void createSequenceFilesIfMissing(String persistenceFileRoot) throws IOException {
		// Too magical. Could use Reflection for this, for example
		for (String directory : new String[]{"asset", "stb", "assetLease"}) {
			System.out.println("Looking for \\" + directory);
			createSequenceFileIfMissing(persistenceFileRoot + "\\" + directory);
		}
	}

	private static void createSequenceFileIfMissing(String persistenceFile) throws IOException {
		// Check for files
		String fileName = persistenceFile + "Seq.txt";
		Path persistencePath = Paths.get(persistenceFile);
		// Create files if missing
		if (!Files.exists(persistencePath)) {
			Files.createFile(persistencePath);
		}
		// Write 0 to file
		writeZeroToSequenceFile(persistencePath);
	}

	private static void writeZeroToSequenceFile(Path persistencePath) throws IOException {
		Files.write(persistencePath, ZERO);
	}
}
