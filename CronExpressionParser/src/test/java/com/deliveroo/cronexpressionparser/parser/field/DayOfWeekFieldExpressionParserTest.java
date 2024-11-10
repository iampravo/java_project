package com.deliveroo.cronexpressionparser.parser.field;

import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;
import com.deliveroo.cronexpressionparser.utilities.StringUtils;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static com.deliveroo.cronexpressionparser.parser.field.DayOfWeekFieldExpressionParser.DAY_OF_WEEK;

public class DayOfWeekFieldExpressionParserTest extends TestCase {

    public static final String TEST1 = "1,2";
    public static final String TEST2 = "1,,5";
    public static final String TEST3 = "*/2";

    @InjectMocks
    DayOfWeekFieldExpressionParser dayOfWeekFieldExpressionParser;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    public void testValidateFieldExpression() {
        try {
            dayOfWeekFieldExpressionParser.validateFieldExpression(TEST1);
        } catch (ExpressionValidationException ex) {
            fail("Method ValidateFieldExpression failed for input : " + TEST1);
        }
    }

    public void testValidateFieldExpressionWithEx() {
        try {
            dayOfWeekFieldExpressionParser.validateFieldExpression(TEST2);
            fail("The expected ExpressionValidationException was not occurred.");
        } catch (ExpressionValidationException ignored) {
        }
    }

    public void testParseFieldExpression() throws ExpressionValidationException {
        String output = dayOfWeekFieldExpressionParser.parseFieldExpression(TEST1);
        Assert.assertEquals(output, DAY_OF_WEEK + StringUtils.ARROW + "1 2");
    }

    public void testParseAsteriskValue() {
        String output = dayOfWeekFieldExpressionParser.parseAsteriskValue();
        Assert.assertNotNull(output);
    }

    public void testParseStepValues() {
        String output = dayOfWeekFieldExpressionParser.parseStepValues(TEST3);
        Assert.assertNotNull(output);
    }
}