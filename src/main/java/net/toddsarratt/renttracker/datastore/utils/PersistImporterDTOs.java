package net.toddsarratt.renttracker.datastore.utils;

import net.toddsarratt.renttracker.datastore.dao.AssetFileDAO;
import net.toddsarratt.renttracker.datastore.dao.AssetLeaseFileDAO;
import net.toddsarratt.renttracker.datastore.dao.StbFileDAO;
import net.toddsarratt.renttracker.entity.Asset;
import net.toddsarratt.renttracker.entity.AssetLease;
import net.toddsarratt.renttracker.entity.Stb;
import net.toddsarratt.renttracker.importer.ViewFileDTO;

import java.io.IOException;
import java.util.List;

public class PersistImporterDTOs {

	private static StbFileDAO stbFileDAO = StbFileDAO.getInstance();
	private static AssetFileDAO assetFileDAO = AssetFileDAO.getInstance();
	private static AssetLeaseFileDAO assetLeaseFileDAO = AssetLeaseFileDAO.getInstance();

	public static void persistDTOs(List<ViewFileDTO> dtos) throws IOException {
		for (ViewFileDTO dto : dtos) {
			Stb stb = new Stb();
			stb.setName(dto.getStb());
			Stb persistedStb = writeStb(stb);
			Asset asset = new Asset();
			asset.setTitle(dto.getTitle());
			asset.setProvider(dto.getProvider());
			Asset persistedAsset = writeAsset(asset);
			AssetLease assetLease = new AssetLease();
			assetLease.setStb(persistedStb);
			assetLease.setAsset(persistedAsset);
			assetLease.setDate(dto.getDate());
			assetLease.setRev(dto.getRev());
			assetLease.setViewTime(dto.getViewTime());
			writeAssetLease(assetLease);
		}
	}

	private static Stb writeStb(Stb stb) throws IOException {
		Stb foundStb = stbFileDAO.findByName(stb.getName());
		if (foundStb == null) {
			Long id = stbFileDAO.create(stb);
			stb.setId(id);
			return stb;
		}
		System.out.println("STB " + foundStb + " already persisted.");
		return foundStb;
	}

	private static Asset writeAsset(Asset asset) throws IOException {
		Asset foundAsset = assetFileDAO.findByTitle(asset.getTitle());
		if (foundAsset == null) {
			Long id = assetFileDAO.create(asset);
			asset.setId(id);
			return asset;
		}
		System.out.println("Asset " + foundAsset + " already persisted.");
		return foundAsset;
	}

	private static AssetLease writeAssetLease(AssetLease assetLease) throws IOException {
		AssetLease foundAssetLease = assetLeaseFileDAO.findByStbAssetDate(
				assetLease.getStb(),
				assetLease.getAsset(),
				assetLease.getDate());
		if (foundAssetLease == null) {
			Long id = assetLeaseFileDAO.create(assetLease);
			assetLease.setId(id);
			return assetLease;
		}
		System.out.println("AssetLease " + assetLease + " already persisted.");
		return foundAssetLease;
	}
}
