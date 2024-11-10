package com.deliveroo.cronexpressionparser.parser.field;

import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;
import com.deliveroo.cronexpressionparser.utilities.StringUtils;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class CommandFieldExpressionParserTest extends TestCase {

    public static final String TEST = "test";
    @InjectMocks
    CommandFieldExpressionParser commandFieldExpressionParser;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public void testValidateFieldExpression() {
        String input = TEST;
        try {
            commandFieldExpressionParser.validateFieldExpression(input);
        } catch (ExpressionValidationException ex) {
            fail("Method ValidateFieldExpression failed for input : " + input);
        }
    }

    public void testValidateFieldExpressionWithEx() {
        try {
            commandFieldExpressionParser.validateFieldExpression("");
            fail("The expected ExpressionValidationException was not occurred.");
        } catch (ExpressionValidationException ignored) {
        }
    }

    public void testParseFieldExpression() {
        String input = TEST;
        String output = CommandFieldExpressionParser.NAME + StringUtils.ARROW + input;
        Assert.assertEquals(output, commandFieldExpressionParser.parseFieldExpression(input));
    }

    public void testParseAsteriskValue() {
        Assert.assertNull(commandFieldExpressionParser.parseAsteriskValue());
    }

    public void testParseStepValues() {
        Assert.assertNull(commandFieldExpressionParser.parseStepValues(TEST));
    }
}