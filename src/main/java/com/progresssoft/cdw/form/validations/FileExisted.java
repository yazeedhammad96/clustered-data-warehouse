package com.progresssoft.cdw.form.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.progresssoft.cdw.form.validator.FileExistedValidator;

/**
 * 
 * @author Yazeed
 *
 */
@Constraint(validatedBy = FileExistedValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileExisted {

	String message() default "File is already Imported, It cannot be uploaded again.";
	Class<?>[] groups() default {};
    
	Class<? extends Payload>[] payload() default {};

}
