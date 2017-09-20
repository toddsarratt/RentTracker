package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.datastore.dto.FileDTO;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class GenericFileDAO<T, ID extends Serializable> implements GenericDAO<T, Long> {

	private static final String SEQ_FILE_PREFIX = "seq";
	private static Path filePath;

	Long getIdFromSeqFile(Path filePath) throws IOException {
		List<String> fileContents = Files.readAllLines(filePath);
		return Long.valueOf(fileContents.get(0));
	}

	void save(FileDTO dto, Path filePath) throws IOException {
		String persistenceDir = filePath.toString();
		Path path = Paths.get(persistenceDir, dto.getId().toString() + dto.getFileSuffix());
		Files.write(path, dto.serializeToFile());
		Path seqFile = Paths.get(persistenceDir, SEQ_FILE_PREFIX + "." + dto.getFileSuffix());
		Files.write(seqFile, dto.getId().toString().getBytes());
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}
}
