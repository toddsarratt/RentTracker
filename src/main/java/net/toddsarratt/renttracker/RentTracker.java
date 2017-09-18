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
	 * @param args
	 * @throws IOException if any file operations fail then the program fails
	 */
	public void main(String[] args) throws IOException {
		// Check datastore directory structure & create if missing
		System.out.println("Checking for persistence directories");
		PersistenceDirectoryManager.createPersistenceDirectoriesIfMissing(PERSISTENCE_DIRECTORY);
		// Check datastore sequence.txt in each & create if missing
		PersistenceFileManager.createSequenceFilesIfMissing(PERSISTENCE_DIRECTORY);
		// Check dropbox for files
		Path dropboxPath = DropboxHandler.createDropboxDirectoryIfMissing(DROPBOX_DIRECTORY);
		// Read all data from dropbox
		List<ViewFileDTO> allDtosToPersist = ViewFileImporter.importFromDropbox(dropboxPath, CHARSET);
		// Persist data read from dropbox
		PersistImporterDTOs.writeEm(allDtosToPersist);
		// REPL
		QueryHandler.doIt();
	}
}
