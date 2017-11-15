package io.mirko.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

public class TestFirstCapStringValidator {
    private FirstCapStringValidator sut;
    @Before
    public void before() {
        sut = new FirstCapStringValidator();
    }
    @Test
    public void test() {
        final ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);
        Assert.assertTrue(sut.isValid("John", context));
        Assert.assertFalse(sut.isValid("JOhn", context));
        Assert.assertTrue(sut.isValid("John Legend", context));
        Assert.assertFalse(sut.isValid("John legend", context));
    }
}
