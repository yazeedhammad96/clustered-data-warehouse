package com.progresssoft.cdw.form.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.progresssoft.cdw.form.validator.NotEmptyFileVaidator;

/**
 * 
 * @author Yazeed
 *
 */
@Constraint(validatedBy = NotEmptyFileVaidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyFile {

	String message() default "File is empty, please upload a valid file";
	Class<?>[] groups() default {};
    
	Class<? extends Payload>[] payload() default {};

}
