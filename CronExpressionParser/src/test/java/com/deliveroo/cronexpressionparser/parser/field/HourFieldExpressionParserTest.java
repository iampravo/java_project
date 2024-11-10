package com.deliveroo.cronexpressionparser.parser.field;

import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;
import com.deliveroo.cronexpressionparser.utilities.StringUtils;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class HourFieldExpressionParserTest extends TestCase {
    public static final String TEST1 = "1,15";
    public static final String TEST2 = "1,,15";
    public static final String TEST3 = "*/15";

    @InjectMocks
    HourFieldExpressionParser hourFieldExpressionParser;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public void testValidateFieldExpression() {
        try {
            hourFieldExpressionParser.validateFieldExpression(TEST1);
        } catch (ExpressionValidationException ex) {
            fail("Method ValidateFieldExpression failed for input : " + TEST1);
        }
    }

    public void testValidateFieldExpressionWithEx() {
        try {
            hourFieldExpressionParser.validateFieldExpression(TEST2);
            fail("The expected ExpressionValidationException was not occurred.");
        } catch (ExpressionValidationException ignored) {
        }
    }

    public void testParseFieldExpression() throws ExpressionValidationException {
        String output = hourFieldExpressionParser.parseFieldExpression(TEST1);
        Assert.assertEquals(output, HourFieldExpressionParser.HOUR + StringUtils.ARROW + "1 15");
    }

    public void testParseAsteriskValue() {
        String output = hourFieldExpressionParser.parseAsteriskValue();
        Assert.assertNotNull(output);
    }

    public void testParseStepValues() {
        String output = hourFieldExpressionParser.parseStepValues(TEST3);
        Assert.assertNotNull(output);
    }
}