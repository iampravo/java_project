package com.deliveroo.cronexpressionparser.validator;

import org.junit.Assert;
import org.junit.Test;

public class CronValidatorTest {

    @Test
    public void testIsInValidField() {

        //Minute
        Assert.assertFalse(CronValidator.isInvalidField("*", "^([0-5]?[0-9])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1", "^([0-5]?[0-9])$"));
        Assert.assertFalse(CronValidator.isInvalidField("59", "^([0-5]?[0-9])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1,2,3", "^([0-5]?[0-9])$"));
        Assert.assertFalse(CronValidator.isInvalidField("*/5", "^([0-5]?[0-9])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1-10", "^([0-5]?[0-9])$"));

        //Hour
        Assert.assertFalse(CronValidator.isInvalidField("*", "^([0-9]|1[0-9]|2[0-3])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1", "^([0-9]|1[0-9]|2[0-3])$"));
        Assert.assertFalse(CronValidator.isInvalidField("12", "^([0-9]|1[0-9]|2[0-3])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1,2,3", "^([0-9]|1[0-9]|2[0-3])$"));
        Assert.assertFalse(CronValidator.isInvalidField("*/5", "^([0-9]|1[0-9]|2[0-3])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1-10", "^([0-9]|1[0-9]|2[0-3])$"));
        
        //Day of Month
        Assert.assertFalse(CronValidator.isInvalidField("*", "^([1-9]|1[0-9]|2[0-9]|3[0-1])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1", "^([1-9]|1[0-9]|2[0-9]|3[0-1])$"));
        Assert.assertFalse(CronValidator.isInvalidField("22", "^([1-9]|1[0-9]|2[0-9]|3[0-1])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1,22,3", "^([1-9]|1[0-9]|2[0-9]|3[0-1])$"));
        Assert.assertFalse(CronValidator.isInvalidField("*/5", "^([1-9]|1[0-9]|2[0-9]|3[0-1])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1-10", "^([1-9]|1[0-9]|2[0-9]|3[0-1])$"));

        //Month
        Assert.assertFalse(CronValidator.isInvalidField("*", "^([1-9]|1[0-2])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1", "^([1-9]|1[0-2])$"));
        Assert.assertFalse(CronValidator.isInvalidField("8", "^([1-9]|1[0-2])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1,2,3", "^([1-9]|1[0-2])$"));
        Assert.assertFalse(CronValidator.isInvalidField("*/5", "^([1-9]|1[0-2])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1-10", "^([1-9]|1[0-2])$"));

        //Day of Week
        Assert.assertFalse(CronValidator.isInvalidField("*", "^([1-7])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1", "^([1-7])$"));
        Assert.assertFalse(CronValidator.isInvalidField("3", "^([1-7])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1,2,3", "^([1-7])$"));
        Assert.assertFalse(CronValidator.isInvalidField("*/5", "^([1-7])$"));
        Assert.assertFalse(CronValidator.isInvalidField("1-3", "^([1-7])$"));
    }

    @Test
    public void testConvertToAllNumbers() {
        Assert.assertEquals("1", CronValidator.convertToAllNumbers("MON"));
        Assert.assertEquals("3", CronValidator.convertToAllNumbers("WED"));
        Assert.assertEquals("1", CronValidator.convertToAllNumbers("JAN"));
        Assert.assertNotEquals("3", CronValidator.convertToAllNumbers("FEB"));
    }

    @Test
    public void validateRegex() {
        Assert.assertTrue(CronValidator.validateRegex("^([1-7])$", "5"));
        Assert.assertFalse(CronValidator.validateRegex("^([1-7])$", "8"));
    }
}