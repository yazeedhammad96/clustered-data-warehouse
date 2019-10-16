package com.progresssoft.cdw.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.progresssoft.cdw.dto.UploadSummaryDTO;
import com.progresssoft.cdw.facade.ImportDealFacade;
import com.progresssoft.cdw.form.CSVFileForm;

/**
 * 
 * @author Yazeed
 *
 */

@Controller
@EnableWebMvc
public class DealsImportController {

	private static final String NOT_FOUND = "notFound";

	private static final String SUCCESS = "success";

	private static final String CSV_FILE_FORM = "csvFileForm";

	private static final String UPLOAD_SUMMARY_VIEW = "upload-summary";

	private static final String UPLOAD_SUMMARY_FORM = "uploadSummary";

	private static final String UPLOAD_PAGE_VIEW = "upload-page";

	private static final String GET_SEARCH_FILE = "/search/file";

	private static final String POST_UPLOAD_FILE = "/upload/file";

	private static final String GET_SEARCH = "/search";

	private static final String GET_UPLOAD = "/upload";
	
	private static final Logger LOG = LoggerFactory.getLogger(DealsImportController.class);


	@Autowired
	private ImportDealFacade importDealFacade;


	/**
	 * Get method to show the upload page 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET, value = GET_UPLOAD)
	public ModelAndView showUpload() {

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(UPLOAD_PAGE_VIEW);
		modelAndView.addObject(CSV_FILE_FORM, new CSVFileForm());
		
		return modelAndView;
	}

	/**
	 * Get method to show the search page 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET, value = GET_SEARCH)
	public ModelAndView showSearch() {

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(UPLOAD_SUMMARY_VIEW);

		return modelAndView;
	}

	/**
	 * Post method to let the client upload a file, then proccess it, and show him the result of the uploading a CSV file.
	 * 
	 * @param csvFileForm
	 * @param bindingResult
	 * @param model
	 * @return ModelAndView 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST, value = POST_UPLOAD_FILE)
	public ModelAndView uploadFile(@ModelAttribute(CSV_FILE_FORM) @Valid CSVFileForm csvFileForm,
			BindingResult bindingResult, Model model) throws FileNotFoundException, IOException {

		if (bindingResult.hasErrors()) {
			return new ModelAndView(UPLOAD_PAGE_VIEW);
		}

		importDealFacade.importCSVDeals(csvFileForm.getFile(),
				csvFileForm.getIncludeHeader(), csvFileForm.getSeparator());
		UploadSummaryDTO fileSummary = importDealFacade.getFileSummary(csvFileForm.getFile().getOriginalFilename());
		final ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName(UPLOAD_PAGE_VIEW);
		modelAndView.addObject(UPLOAD_SUMMARY_FORM, fileSummary);
		modelAndView.addObject(CSV_FILE_FORM, new CSVFileForm());
		modelAndView.addObject(SUCCESS, true);

		return modelAndView;
	}

	/**
	 * Get method to let the user inquire about the result of any imported file.
	 * 
	 * @param fileName
	 * @param model
	 * @return ModelAndView
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = GET_SEARCH_FILE)
	public ModelAndView uploadFile(@RequestParam String fileName, Model model)
			throws FileNotFoundException, IOException {
		final ModelAndView modelAndView = new ModelAndView();

		UploadSummaryDTO fileSummary = importDealFacade.getFileSummary(fileName);
		if (fileSummary == null) {
			modelAndView.setViewName(UPLOAD_SUMMARY_VIEW);
			modelAndView.addObject(NOT_FOUND, true);
		}
		modelAndView.setViewName(UPLOAD_SUMMARY_VIEW);
		modelAndView.addObject(UPLOAD_SUMMARY_FORM, fileSummary);
		modelAndView.addObject(SUCCESS, true);

		return modelAndView;
	}
	
	/**
	 * To handle any exception that throwing during the importing process.
	 * 
	 * @param exception
	 * @return ModelAndView
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception exception) {
		LOG.error(String.format("Failed importing file, exeption message[%s].", exception.getMessage()));

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(UPLOAD_PAGE_VIEW);
		modelAndView.addObject(CSV_FILE_FORM, new CSVFileForm());
		modelAndView.addObject("error", true);

		return modelAndView;
	}
}
