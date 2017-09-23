package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.datastore.dto.StbFileDTO;
import net.toddsarratt.renttracker.entity.Stb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StbFileDAO extends GenericFileDAO<Stb, Long> {

	private static StbFileDAO instance = null;

	/**
	 * Do not allow instantiation of this singleton
	 */
	private StbFileDAO() {
	}

	public static StbFileDAO getInstance() {
		if (instance == null) {
			instance = new StbFileDAO();
		}
		return instance;
	}

	@Override
	public Long create(Stb newInstance) throws IOException {
		Path sequenceFilePath = Paths.get(getFilePath().toString(), "seq.stb");
			Long lastId = getIdFromSeqFile(sequenceFilePath);
			newInstance.setId(lastId + 1L);
			StbFileDTO dto = new StbFileDTO(newInstance);
		save(dto, getFilePath());
		return newInstance.getId();
	}

	@Override
	public Stb find(Long id) {
		/*
		Get connection
		Search by ID
		Return STB
		 */
		return null;
	}

	public Stb findByName(String name) throws IOException {
		Stb foundStb = null;

		File dir = new File(getFilePath().toString());
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (!file.getName().contains("seq")) {
					System.out.println("Checking file: \'" + file.getName() + "\' for Stb name: \'" + name + "\'");
					List<String> fileContents = Files.readAllLines(file.toPath());
					StbFileDTO dto = StbFileDTO.fromFileRead(fileContents.get(0));
					if (dto != null) {
						if (dto.getName().equals(name)) {
							foundStb = new Stb();
							foundStb.setId(Long.valueOf(dto.getId()));
							foundStb.setName(dto.getName());
							break;
						}
					}
				}
			}
		}
		// If no STB is found this will return null
		return foundStb;
	}

	@Override
	public void update(Stb transientObject) {
		/*
		Get connection
		Find record
		Rewrite record
		 */
	}

	@Override
	public void delete(Stb persistentObject) {
		/*
		Get connection
		Find record
		Delete record
		 */
	}
}
