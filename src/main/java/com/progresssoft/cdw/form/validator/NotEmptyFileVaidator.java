package com.progresssoft.cdw.form.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.cdw.form.validations.NotEmptyFile;

/**
 * 
 * @author Yazeed
 *
 */

public class NotEmptyFileVaidator implements ConstraintValidator<NotEmptyFile, MultipartFile> {

	@Override
	public void initialize(NotEmptyFile notEmptyFile) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext validatorContext) {

		return !multipartFile.isEmpty();
	}

}
