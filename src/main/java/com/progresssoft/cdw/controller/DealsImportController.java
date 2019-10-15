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

	private static final Logger LOG = LoggerFactory.getLogger(DealsImportController.class);

	private static final String GET_SEARCH_FILE = "/search/file";

	private static final String POST_UPLOAD_FILE = "/upload/file";

	private static final String GET_SEARCH = "/search";

	private static final String GET_UPLOAD = "/upload";

	@Autowired
	private ImportDealFacade importDealFacade;


	@RequestMapping(method = RequestMethod.GET, value = GET_UPLOAD)
	public ModelAndView showUpload() {

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("upload-page");
		modelAndView.addObject("csvFileForm", new CSVFileForm());
		
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = GET_SEARCH)
	public ModelAndView showSearch() {

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("upload-summary");

		return modelAndView;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		LOG.error("Failed importing file.", e);

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("upload-page");
		modelAndView.addObject("csvFileForm", new CSVFileForm());
		modelAndView.addObject("error", true);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = POST_UPLOAD_FILE)
	public ModelAndView uploadFile(@ModelAttribute("csvFileForm") @Valid CSVFileForm csvFileForm,
			BindingResult bindingResult, Model model) throws FileNotFoundException, IOException {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("upload-page");
		}

		importDealFacade.importCSVDeals(csvFileForm.getFile(),
				csvFileForm.getIncludeHeader(), csvFileForm.getSeparator());
		UploadSummaryDTO fileSummary = importDealFacade.getFileSummary(csvFileForm.getFile().getOriginalFilename());
		final ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("upload-page");
		modelAndView.addObject("uploadSummary", fileSummary);
		modelAndView.addObject("csvFileForm", new CSVFileForm());
		modelAndView.addObject("success", true);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = GET_SEARCH_FILE)
	public ModelAndView uploadFile(@RequestParam String fileName, Model model)
			throws FileNotFoundException, IOException {
		final ModelAndView modelAndView = new ModelAndView();

		UploadSummaryDTO fileSummary = importDealFacade.getFileSummary(fileName);
		if (fileSummary == null) {
			modelAndView.setViewName("upload-summary");
			modelAndView.addObject("notFound", true);
		}
		modelAndView.setViewName("upload-summary");
		modelAndView.addObject("uploadSummary", fileSummary);
		modelAndView.addObject("success", true);

		return modelAndView;
	}
}
