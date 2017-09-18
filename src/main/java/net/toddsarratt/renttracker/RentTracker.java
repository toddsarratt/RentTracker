package net.toddsarratt.renttracker;

import net.toddsarratt.renttracker.datastore.utils.PersistImporterDTOs;
import net.toddsarratt.renttracker.datastore.utils.PersistenceDirectoryManager;
import net.toddsarratt.renttracker.datastore.utils.PersistenceFileManager;
import net.toddsarratt.renttracker.importer.DropboxHandler;
import net.toddsarratt.renttracker.importer.ViewFileDTO;
import net.toddsarratt.renttracker.importer.ViewFileImporter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;

public class RentTracker {
	// Constants for use in file import
	private static final String ROOT_DIRECTORY = "C:\\Code\\IntellijGit\\RentTracker";
	private static final String PERSISTENCE_DIRECTORY = ROOT_DIRECTORY + "\\persistence";
	private static final Charset CHARSET = Charset.forName("US-ASCII");
	private static final String DROPBOX_DIRECTORY = ROOT_DIRECTORY + "\\dropbox";

	/**
	 * Entry point to the RentTracker application. Run from command line without arguments.
	 *
	 * @param args ignored so far
	 * @throws IOException if any file operations fail then the program fails
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Checking for persistence directories...");
		List<Path> persistencePaths =
				PersistenceDirectoryManager.createPersistenceDirectoriesIfMissing(PERSISTENCE_DIRECTORY);
		System.out.println("Checking for persistence files...");
		PersistenceFileManager.createSequenceFilesIfMissing(persistencePaths);
		System.out.println("Checking for dropbox...");
		Path dropboxPath = DropboxHandler.createDropboxDirectoryIfMissing(DROPBOX_DIRECTORY);
		System.out.println("Looking for dropbox files to import...");
		List<ViewFileDTO> allDtosToPersist = ViewFileImporter.importFromDropbox(dropboxPath, CHARSET);
		PersistImporterDTOs.writeEm(allDtosToPersist);
		System.out.println("Launching REPL...");
		QueryHandler.doIt();
	}
}
