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
			writeStb(stb);
			Asset asset = new Asset();
			asset.setTitle(dto.getTitle());
			asset.setProvider(dto.getProvider());
			writeAsset(asset);
			AssetLease assetLease = new AssetLease();
			assetLease.setStb(stb);
			assetLease.setAsset(asset);
			assetLease.setDate(dto.getDate());
			assetLease.setRev(dto.getRev());
			assetLease.setViewTime(dto.getViewTime());
			writeAssetLease(assetLease);
		}
	}

	private static Stb writeStb(Stb stb) throws IOException {
		Stb foundStb = stbFileDAO.findByName(stb.getName());
		if ((stb.getId() == null || stbFileDAO.find(stb.getId()) == null) &&
				foundStb == null) {
			System.out.println("Persisting: " + stb);
			stb.setId(stbFileDAO.create(stb));
			return stb;
		}
		System.out.println("STB " + stb + " already persisted.");
		return foundStb;
	}

	private static void writeAsset(Asset asset) throws IOException {
		Asset foundAsset = assetFileDAO.findByTitle(asset.getTitle());
		if ((asset.getId() == null || assetFileDAO.find(asset.getId()) == null) &&
				foundAsset == null) {
			System.out.println("Persisting: " + asset);
			assetFileDAO.create(asset);
		} else {
			System.out.println("Asset " + asset + " already persisted.");
		}
	}

	private static void writeAssetLease(AssetLease assetLease) throws IOException {
		if (assetLeaseFileDAO.find(assetLease.getId()) == null &&
				assetLeaseFileDAO.findByStbAssetDate(
						assetLease.getStb(),
						assetLease.getAsset(),
						assetLease.getDate()) == null) {
			System.out.println("Persisting: " + assetLease);
			assetLeaseFileDAO.create(assetLease);
		} else {
			System.out.println("AssetLease " + assetLease + " already persisted.");
		}
	}
}
