package net.toddsarratt.renttracker.entity;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public class AssetLease extends Entity {
	private Stb stb;
	private Asset asset;
	private LocalDate date;
	private BigDecimal rev;
	private Duration viewTime;

	public Stb getStb() {
		return stb;
	}

	public void setStb(Stb stb) {
		this.stb = stb;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getRev() {
		return rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

	public Duration getViewTime() {
		return viewTime;
	}

	public void setViewTime(Duration viewTime) {
		this.viewTime = viewTime;
	}

	@Override
	public String toString() {
		return "AssetLease{" +
				"stb=" + stb +
				", asset=" + asset +
				", date=" + date +
				", rev=" + rev +
				", viewTime=" + viewTime +
				", id=" + id +
				'}';
	}
}
