package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.datastore.dto.StbFileDTO;
import net.toddsarratt.renttracker.entity.Stb;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class GenericFileDAO<T, ID extends Serializable> {

	private static final String SEQ_FILENAME = "seq.stb";

	Long getIdFromSeqFile(Path filePath) throws IOException {
		List<String> fileContents = Files.readAllLines(filePath);
		return Long.valueOf(fileContents.get(0));
	}

	void save(Stb newInstance, Path filePath) throws IOException {
		StbFileDTO dto = new StbFileDTO(newInstance);
		String stbDir = filePath.toString();
		Path stbFile = Paths.get(stbDir, newInstance.getId().toString() + ".stb");
		Files.write(stbFile, dto.serializeToFile());
		Path seqFile = Paths.get(stbDir, SEQ_FILENAME);
		Files.write(seqFile, newInstance.getId().toString().getBytes());
	}
}
