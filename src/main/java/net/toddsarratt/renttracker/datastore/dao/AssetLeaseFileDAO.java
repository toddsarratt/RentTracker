package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.datastore.dto.AssetLeaseFileDTO;
import net.toddsarratt.renttracker.entity.Asset;
import net.toddsarratt.renttracker.entity.AssetLease;
import net.toddsarratt.renttracker.entity.Stb;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class AssetLeaseFileDAO extends GenericFileDAO<AssetLease, Long> {

	private static AssetLeaseFileDAO instance;
	private static StbFileDAO stbFileDAO = StbFileDAO.getInstance();
	private static AssetFileDAO assetFileDAO = AssetFileDAO.getInstance();

	/**
	 * Do not allow instantiation of this singleton
	 */
	private AssetLeaseFileDAO() {
	}

	public static AssetLeaseFileDAO getInstance() {
		if (instance == null) {
			instance = new AssetLeaseFileDAO();
		}
		return instance;
	}

	@Override
	public Long create(AssetLease newInstance) throws IOException {
		Path sequenceFilePath = Paths.get(getFilePath().toString(), "seq.assetLease");
		Long lastId = getIdFromSeqFile(sequenceFilePath);
			newInstance.setId(lastId + 1L);
			AssetLeaseFileDTO dto = new AssetLeaseFileDTO(newInstance);
		save(dto, getFilePath());
		return newInstance.getId();
	}

	@Override
	public AssetLease find(Long id) {
		/*
		Get connection
		Search by ID
		Return AssetLease
		 */
		return null;
	}

	public AssetLease findByStbAssetDate(Stb stb, Asset asset, LocalDate date) throws IOException {
		AssetLease foundAssetLease = null;

		File dir = new File(getFilePath().toString());
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (!file.getName().contains("seq")) {
					System.out.println("Checking file: \'" + file.getName() + "\' for AssetLease of : " +
							"\'" + stb + "\'" +
							"\'" + asset + "\'" +
							"\'" + date + "\'");
					List<String> fileContents = Files.readAllLines(file.toPath());
					AssetLeaseFileDTO dto = AssetLeaseFileDTO.fromFileRead(fileContents.get(0));
					if (dto != null) {
						if (dto.getStbId().equals(stb.getId()) &&
								dto.getAssetId().equals(asset.getId()) &&
								dto.getDate().equals(date)) {
							foundAssetLease = new AssetLease();
							foundAssetLease.setId(Long.valueOf(dto.getId()));
							foundAssetLease.setStb(stbFileDAO.find(Long.valueOf(dto.getStbId())));
							foundAssetLease.setAsset(assetFileDAO.find(Long.valueOf(dto.getAssetId())));
							foundAssetLease.setDate(LocalDate.parse(dto.getDate()));
							foundAssetLease.setRev(new BigDecimal(dto.getRev()));
							foundAssetLease.setViewTime(Duration.parse(dto.getViewTime()));
							break;
						}
					}
				}
			}
		}
		// If no STB is found this will return null
		return foundAssetLease;
	}

	@Override
	public void update(AssetLease transientObject) {
		/*
		Get connection
		Find record
		Rewrite record
		 */
	}

	@Override
	public void delete(AssetLease persistentObject) {
		/*
		Get connection
		Find record
		Delete record
		 */
	}
}
