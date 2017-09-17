package net.toddsarratt.renttracker.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewFileImporter {
	// Constants for use in file import
	private static final String DROPBOX_DIRECTORY = "C:\\Code\\IntellijGit\\RentTracker\\dropbox";
	private static final Charset CHARSET = Charset.forName("US-ASCII");

	public List<ViewFileDTO> readit(String fileName) {
		Path path = FileSystems.getDefault().getPath(DROPBOX_DIRECTORY, fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, CHARSET)) {
			String line;
			String header = reader.readLine();
			if (headerValid(header)) {
				List<ViewFileDTO> viewFileDTOS = new ArrayList<>();
				while ((line = reader.readLine()) != null) {
					ViewFileDTO viewFileDTO = convertLineToViewFileDTO(line);
					if (viewFileDTO != null) {
						viewFileDTOS.add(viewFileDTO);
					}
				}
				// Log "OK" message
				return viewFileDTOS;
			} else {
				// Log "Bad header" message
			}
		} catch (IOException ioe) {
			// Log exception
			// Handle exception
		}
		return Collections.emptyList();
	}

	private boolean headerValid(String header) {
		String[] headerColumns = header.split("\\|");
		return headerColumns[0].equals("STB") &&
				headerColumns[1].equals("TITLE") &&
				headerColumns[2].equals("PROVIDER") &&
				headerColumns[3].equals("DATE") &&
				headerColumns[4].equals("REV") &&
				headerColumns[5].equals("VIEW_TIME");
	}

	private ViewFileDTO convertLineToViewFileDTO(String line) {
		ViewFileDTO dto = new ViewFileDTO();
		String[] view = line.split("\\|");
		dto.setStb(view[0]);
		dto.setTitle(view[1]);
		dto.setProvider(view[2]);
		try {
			// Per file definition, date matches ISO_LOCAL_DATE, the default for LocalDate.parse()
			// May throw DateTimeParseException
			dto.setDate(LocalDate.parse(view[3]));
			// May throw NumberFormatException
			dto.setRev(new BigDecimal(view[4]));
			dto.setViewTime(Duration.parse(view[5]));
			// Split VIEW_TIME into hours and minutes to parse to Java Duration class
			String[] viewHoursMinutes = view[5].split(":");
			// See: https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html#parse-java.lang.CharSequence-
			// Example: From view_time "1:30" creates "PT1H30M" which can be parsed to a Duration
			String parsableDuration = "PT" + viewHoursMinutes[0] + "H" + viewHoursMinutes[1] + "M";
			dto.setViewTime(Duration.parse(parsableDuration));
		} catch (DateTimeParseException | NumberFormatException e) {
			// Log exception
			return null;
		}
		return dto;
	}
}
