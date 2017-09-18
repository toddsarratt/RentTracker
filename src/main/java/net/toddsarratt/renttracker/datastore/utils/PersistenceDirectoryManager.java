package net.toddsarratt.renttracker.datastore.utils;

import net.toddsarratt.renttracker.DirectoryManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PersistenceDirectoryManager extends DirectoryManager {

	public static List<Path> createPersistenceDirectoriesIfMissing(String persistenceFileRoot) throws IOException {
		System.out.println("Looking for persistence root directory at: " + persistenceFileRoot);
		createDirectoryIfMissing(persistenceFileRoot);
		List<Path> persistencePaths = new ArrayList<>();
		// Bad form to hardcode directory names in a method. Could use Reflection for this, for example, constants or config file
		for (String directory : new String[]{"asset", "stb", "assetLease"}) {
			System.out.println("Looking for: ..\\" + directory);
			Path persistencePath = createDirectoryIfMissing(persistenceFileRoot + "\\" + directory);
			persistencePaths.add(persistencePath);
		}
		return persistencePaths;
	}
}
