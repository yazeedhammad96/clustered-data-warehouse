/**
 * 
 */
package com.progresssoft.cdw.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.cdw.dao.ImportDealDao;
import com.progresssoft.cdw.entity.AccumulativeDealsOfCurrency;
import com.progresssoft.cdw.entity.ImportedFile;
import com.progresssoft.cdw.enums.RowType;
import com.progresssoft.cdw.service.AccumulativeDealsOfCurrencyService;
import com.progresssoft.cdw.service.ImportDealService;
import com.progresssoft.cdw.service.ImportedFileService;
import com.progresssoft.cdw.util.CSVLoader;
import com.progresssoft.cdw.validator.CSVDealValidator;

/**
 * @author Yazeed
 *
 */
@Service
public class DefaultImportDealService implements ImportDealService {

	private static final String FILE_PREFIX = "./";

	private final static Logger LOG = LoggerFactory.getLogger(DefaultImportDealService.class);

	@Autowired
	private CSVDealValidator csvDealValidator;

	@Autowired
	private ImportDealDao importDealDao;

	@Autowired
	private ImportedFileService importedFileService;

	@Autowired
	private AccumulativeDealsOfCurrencyService accumulativeDealsOfCurrencyService;

	@Value("${csv.numofcolumns}")
	private Integer numOfCols;

	@Value("${csv.separator}")
	private String defaultSeparator;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void importCSVDeals(MultipartFile multipartFile, Boolean includeHeader, String separator)
			throws FileNotFoundException, IOException {
		long processDuration = System.currentTimeMillis();
		if (separator == null || separator.isEmpty())
			separator = defaultSeparator;

		if (multipartFile == null)
			throw new IllegalArgumentException("multipartFile can't be null");

		byte[] bytes = multipartFile.getBytes();
		String originalFilename = multipartFile.getOriginalFilename();
		Path path = Files.write(Paths.get(FILE_PREFIX + originalFilename), bytes);
		LOG.info(String.format("Start Import CSV file [%s], include header [%s], seprator[%s]", originalFilename,
				includeHeader, separator));

		Map<String, Double> accumulativeMap = new HashMap<String, Double>();
		Map<RowType, List<String[]>> rows = CSVLoader.getAll(path, separator, numOfCols, includeHeader,
				csvDealValidator, (validRow) -> {
					String iso = validRow[1];
					Double amount = Double.parseDouble(validRow[4]);
					if (accumulativeMap.containsKey(iso)) {
						accumulativeMap.put(iso, accumulativeMap.get(iso) + amount);
					} else
						accumulativeMap.put(iso, amount);
				});

		importDealDao.insertCSVDeals(rows, originalFilename);
		processDuration = System.currentTimeMillis() - processDuration;
		Files.deleteIfExists(path);
		LOG.info(String.format("Finish Importing CSV File to Deals, process Duration[%s]", processDuration));
		int validCount = rows.get(RowType.VALID).size();
		int invalidCount = rows.get(RowType.INVALID).size();
		addImportedFile(processDuration, originalFilename, validCount, invalidCount);
		addAccumulativeAmounts(accumulativeMap);

	}

	private void addAccumulativeAmounts(Map<String, Double> accumulativeMap) {
		new Thread(() -> {
			accumulativeMap.keySet().stream().forEach(x -> {
				AccumulativeDealsOfCurrency byCurrencyISOCode = accumulativeDealsOfCurrencyService
						.getByCurrencyISOCode(x);
				if (byCurrencyISOCode == null) {
					byCurrencyISOCode = new AccumulativeDealsOfCurrency();
					byCurrencyISOCode.setCurrencyISOCode(x);
					byCurrencyISOCode.setAmountOfDeals(accumulativeMap.get(x));
					accumulativeDealsOfCurrencyService.add(byCurrencyISOCode);
				} else {
					byCurrencyISOCode.setCurrencyISOCode(x);
					byCurrencyISOCode.setAmountOfDeals(byCurrencyISOCode.getAmountOfDeals() + accumulativeMap.get(x));
					accumulativeDealsOfCurrencyService.update(byCurrencyISOCode);
				}
			});
		}).start();

	}

	private void addImportedFile(long processDuration, String originalFilename, int validCount, int invalidCount) {
		ImportedFile importedFile = new ImportedFile();
		importedFile.setName(originalFilename);
		importedFile.setInvalidDealsImportedCount(invalidCount);
		importedFile.setValidDealsImportedCount(validCount);
		importedFile.setProcessDuration(processDuration);
		importedFileService.add(importedFile);
	}

}
