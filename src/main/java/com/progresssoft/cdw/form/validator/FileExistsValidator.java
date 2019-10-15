package com.progresssoft.cdw.form.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.cdw.entity.ImportedFile;
import com.progresssoft.cdw.form.validations.FileExisted;
import com.progresssoft.cdw.service.ImportedFileService;

/**
 * 
 * @author Yazeed
 *
 */

public class FileExistsValidator implements ConstraintValidator<FileExisted, MultipartFile> {

	@Autowired
	ImportedFileService importedFileService;

	@Override
	public void initialize(FileExisted constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		ImportedFile byName = importedFileService.getByName(value.getOriginalFilename());

		return byName == null;
	}

}