package com.progresssoft.cdw.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.progresssoft.cdw.enums.ISOCurrencyCode;

/**
 * 
 * @author Yazeed
 *
 */

@Component("csvDealValidator")
public class CSVDealValidator implements CSVValidator {

	private static final String TIMESTAMP_FORMAT = "yyyy/MM/dd HH:mm";

	private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);

	private static final String NUMERIC_PATTERN = "[-+]?([0-9]*\\.[0-9]+|[0-9]+)";

	private static final Logger LOG = LoggerFactory.getLogger(CSVDealValidator.class);

	@Value("${csv.numofcolumns}")
	private Integer numOfCols;

	@Override
	public boolean validateRow(String[] row) {
		if (row.length != numOfCols) {
			LOG.error("Invalid number of columns");
			return false;
		}

		if (row[0]==null||row[0].isEmpty()) {
			LOG.error(String.format("Deal id [%s] is invalid", row[0]));
			return false;
		}

		if (!ISOCurrencyCode.CURRENCY_CODES.contains(row[1])) {
			LOG.error(String.format("Invalid Ordering Currency Code [%s] for deal with id [%s]",row[1], row[0]));
			return false;
		}

		if (!ISOCurrencyCode.CURRENCY_CODES.contains(row[2])) {
			LOG.error(String.format("Invalid To Currency Code [%s] for deal with id [%s]",row[2], row[0]));
			return false;
		}

		if (!isValidTimestamp(row[3])) {
			LOG.error(String.format("Invalid Timestamp [%s] for deal with id [%s]",row[3], row[0]));
			return false;
		}

		if (!isValidAmount(row[4])) {
			LOG.error(String.format("Invalid Amount [%s] for deal with id [%s]",row[4], row[0]));
			return false;
		}

		return true;
	}

	private boolean isValidTimestamp(String timestamp) {

		try {
			LocalDateTime.parse(timestamp, TIMESTAMP_FORMATTER);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}

	private boolean isValidAmount(String amount) {
		if (!amount.matches(NUMERIC_PATTERN)) {
			return false;
		}
		return true;
	}

	public Integer getNumOfCols() {
		return numOfCols;
	}

	public void setNumOfCols(Integer numOfCols) {
		this.numOfCols = numOfCols;
	}

}
