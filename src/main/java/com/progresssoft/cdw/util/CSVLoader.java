package com.progresssoft.cdw.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.progresssoft.cdw.enums.RowType;
import com.progresssoft.cdw.validator.CSVValidator;

/**
 * @author Yazeed
 *
 */

public class CSVLoader {

	private static final Logger LOG = LoggerFactory.getLogger(CSVLoader.class);

	/**
	 * Load all data from CSV file
	 * 
	 * @return Map<Integer, List<String>> of all data on the csv file
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws CSVException
	 */
	public static Map<RowType, List<String[]>> getAll(Path path, String separator, Integer numOfcols,
			boolean ignoreHeader, CSVValidator csvValidator, Consumer<String[]> consumerRow)
			throws FileNotFoundException, IOException {

		if (path == null) {
			LOG.error("Path can't be null or empty");
			throw new IllegalArgumentException("Path can't be null or empty");
		}

		if (separator == null || separator.isEmpty()) {
			LOG.error("Separator can't be null or empty");
			throw new IllegalArgumentException("Separator can't be null or empty");
		}
		if (numOfcols == null || numOfcols == 0) {
			LOG.error("numOfcols can't be null or zero");
			throw new IllegalArgumentException("NumOfcols can't be null or zero");
		}
		Map<RowType, List<String[]>> csvRecords = loadRecords(path, separator, numOfcols, ignoreHeader, csvValidator,
				consumerRow);
		LOG.info(String.format("Load CSV file [%s] Successfully", path.toString()));
		return csvRecords;
	}

	/**
	 * Load records from a file, then add them to the Map based on validation
	 * results.
	 * 
	 * @param path
	 * @param separator
	 * @param numOfcols
	 * @param includeHeader
	 * @param csvValidator
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static Map<RowType, List<String[]>> loadRecords(Path path, String separator, Integer numOfcols,
			boolean includeHeader, CSVValidator csvValidator, Consumer<String[]> consumerValidRow)
			throws FileNotFoundException, IOException {

		final Map<RowType, List<String[]>> csvRecords = new HashMap<>();

		csvRecords.put(RowType.VALID, new ArrayList<String[]>());
		csvRecords.put(RowType.INVALID, new ArrayList<String[]>());

		try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
			String row;
			if (includeHeader)
				reader.readLine();
			while ((row = reader.readLine()) != null) {
				String[] splittedRow = row.split(separator);
				if (splittedRow.length < numOfcols)
					continue;
				boolean validateRow = csvValidator.validateRow(splittedRow);

				if (validateRow) {
					consumerValidRow.accept(splittedRow);
					csvRecords.get(RowType.VALID).add(splittedRow);
				} else {
					csvRecords.get(RowType.INVALID).add(splittedRow);
				}
			}
		}

		return csvRecords;
	}

}