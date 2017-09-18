package net.toddsarratt.renttracker.datastore.utils;

import net.toddsarratt.renttracker.DirectoryManager;

import java.io.IOException;

public class PersistenceDirectoryManager extends DirectoryManager {

	public static void createPersistenceDirectoriesIfMissing(String persistenceFileRoot) throws IOException {
		// Bad form to hardcode directory names in a method. Could use Reflection for this, for example, constants or config file
		for (String directory : new String[]{"asset", "stb", "assetLease"}) {
			System.out.println("Looking for \\" + directory);
			createDirectoryIfMissing(persistenceFileRoot + "\\" + directory);
		}
	}
}
