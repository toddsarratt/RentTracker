package net.toddsarratt.renttracker.importer;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Data Transfer Object for reading in asset lease / view data from disk
 */
public class ViewFileDTO {
	private String stb;
	private String title;
	private String provider;
	private LocalDate date;
	private BigDecimal rev;
	private Duration viewTime;

	public String getStb() {
		return stb;
	}

	public void setStb(String stb) {
		this.stb = stb;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
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
}
