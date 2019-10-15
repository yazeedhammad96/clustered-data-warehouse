package com.progresssoft.cdw.form.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.cdw.form.validations.FileFormat;

/**
 * 
 * @author Yazeed
 *
 */
public class FileFormatValidator implements ConstraintValidator<FileFormat, MultipartFile> {

	@Override
	public void initialize(FileFormat fileFormat) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext validatorContext) {

		return multipartFile.getOriginalFilename().toLowerCase().endsWith(".csv");
	}

}
