package com.progresssoft.cdw.form.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.progresssoft.cdw.form.validator.FileFormatValidator;

/**
 * 
 * @author Yazeed
 *
 */

@Constraint(validatedBy = FileFormatValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileFormat {

	String message() default "File must be a CSV file.";
	Class<?>[] groups() default {};
    
	Class<? extends Payload>[] payload() default {};
}
