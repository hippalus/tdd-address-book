package com.addressbook.domain.validation;

import com.addressbook.domain.exceptions.BeanValidationException;
import com.addressbook.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.FieldError;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeanValidatorTest {

    @AllArgsConstructor
    @Setter@Getter
    public static class BeanToValidate{
        @NotNull
        @Size(min = 5,max = 50)
        private String name;
        @Min(18)@Max(90)
        private int age;
    }

    private BeanValidator validator;

    @BeforeEach
    void init(){
        this.validator=new BeanValidator();
    }
    @AfterEach
    void terminate(){
        this.validator.close();
    }

    @Test
    void should_validate_bean_with_short_name() {
        BeanToValidate invalidBean=new BeanToValidate("test",25);
        assertThrows(BeanValidationException.class,() -> validator.validate(invalidBean));

    }
    @Test
    void should_validate_bean_with_invalid_age() {
        BeanToValidate invalidBean=new BeanToValidate("Martin",5);
        assertThrows(BeanValidationException.class,() -> validator.validate(invalidBean));

    }
    @Test
    void should_attach_invalid_field_details(){
        BeanToValidate invalidBean = new BeanToValidate("fff", 5);
        List<FieldError> fieldErrors=null;
        try {
            validator.validate(invalidBean);
        } catch (BeanValidationException e) {
            fieldErrors=e.getFieldErrors();
        }
        assertNotNull(fieldErrors);
        assertEquals(2,fieldErrors.size());
        assertTrue(fieldErrors.contains(new FieldError("BeanToValidate", "name", "size must be between 5 and 50")));
        assertTrue(fieldErrors.contains(new FieldError("BeanToValidate", "age", "must be greater than or equal to 18")));

    }





}
