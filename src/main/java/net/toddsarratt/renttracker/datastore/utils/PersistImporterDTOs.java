package net.toddsarratt.renttracker.datastore.utils;

import net.toddsarratt.renttracker.entity.Asset;
import net.toddsarratt.renttracker.entity.AssetLease;
import net.toddsarratt.renttracker.entity.Stb;
import net.toddsarratt.renttracker.importer.ViewFileDTO;

import java.util.List;

public class PersistImporterDTOs {

	public static void writeEm(List<ViewFileDTO> dtos) {
		dtos.forEach(dto -> {
			Stb stb = new Stb();
			stb.setName(dto.getStb());
			Asset asset = new Asset();
			asset.setTitle(dto.getTitle());
			asset.setProvider(dto.getProvider());
			AssetLease assetLease = new AssetLease();
			assetLease.setDate(dto.getDate());
			assetLease.setRev(dto.getRev());
			assetLease.setViewTime(dto.getViewTime());
			// write 'em!
		});
	}
}
