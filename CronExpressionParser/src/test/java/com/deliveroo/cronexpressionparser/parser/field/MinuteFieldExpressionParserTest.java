package com.deliveroo.cronexpressionparser.parser.field;

import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;
import com.deliveroo.cronexpressionparser.utilities.StringUtils;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static com.deliveroo.cronexpressionparser.parser.field.MinuteFieldExpressionParser.MINUTE;

public class MinuteFieldExpressionParserTest extends TestCase {
    public static final String TEST1 = "1,15";
    public static final String TEST2 = "1,,15";
    public static final String TEST3 = "*/15";

    @InjectMocks
    MinuteFieldExpressionParser minuteFieldExpressionParser;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public void testValidateFieldExpression() {
        try {
            minuteFieldExpressionParser.validateFieldExpression(TEST1);
        } catch (ExpressionValidationException ex) {
            fail("Method ValidateFieldExpression failed for input : " + TEST1);
        }
    }

    public void testValidateFieldExpressionWithEx() {
        try {
            minuteFieldExpressionParser.validateFieldExpression(TEST2);
            fail("The expected ExpressionValidationException was not occurred.");
        } catch (ExpressionValidationException ignored) {
        }
    }

    public void testParseFieldExpression() throws ExpressionValidationException {
        String output = minuteFieldExpressionParser.parseFieldExpression(TEST1);
        Assert.assertEquals(output, MINUTE + StringUtils.ARROW + "1 15");
    }

    public void testParseAsteriskValue() {
        String output = minuteFieldExpressionParser.parseAsteriskValue();
        Assert.assertNotNull(output);
    }

    public void testParseStepValues() {
        String output = minuteFieldExpressionParser.parseStepValues(TEST3);
        Assert.assertNotNull(output);
    }

}