package net.toddsarratt.renttracker.importer;

import net.toddsarratt.renttracker.DirectoryManager;

import java.io.IOException;
import java.nio.file.Path;

public class DropboxHandler extends DirectoryManager {

	public static Path createDropboxDirectoryIfMissing(String dropboxDirectory) throws IOException {
		Path dropboxPath = createDirectoryIfMissing(dropboxDirectory);
		return dropboxPath;
	}
}
