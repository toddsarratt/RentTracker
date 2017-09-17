package net.toddsarratt.renttracker.entity;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public class AssetLease {
	Long id;
	Stb stb;
	Asset asset;
	LocalDate date;
	BigDecimal rev;
	Duration viewTime;
}
