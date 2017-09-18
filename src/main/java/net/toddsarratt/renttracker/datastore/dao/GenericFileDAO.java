package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.datastore.dto.FileDTO;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class GenericFileDAO<T, ID extends Serializable> {

	private static final String SEQ_FILE_PREFIX = "seq";

	Long getIdFromSeqFile(Path filePath) throws IOException {
		List<String> fileContents = Files.readAllLines(filePath);
		return Long.valueOf(fileContents.get(0));
	}

	void save(FileDTO dto, Path filePath) throws IOException {
		String persistenceDir = filePath.toString();
		Path stbFile = Paths.get(persistenceDir, dto.getId().toString() + dto.getFileSuffix());
		Files.write(stbFile, dto.serializeToFile());
		Path seqFile = Paths.get(persistenceDir, SEQ_FILE_PREFIX + "." + dto.getFileSuffix());
		Files.write(seqFile, dto.getId().toString().getBytes());
	}
}
