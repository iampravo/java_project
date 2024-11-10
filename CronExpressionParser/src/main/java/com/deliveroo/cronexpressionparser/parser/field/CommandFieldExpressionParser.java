package com.deliveroo.cronexpressionparser.parser.field;

import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;
import com.deliveroo.cronexpressionparser.parser.AbstractExpressionParser;
import org.apache.commons.lang3.StringUtils;

public class CommandFieldExpressionParser extends AbstractExpressionParser {
protected  static final String NAME = "command";
    @Override
    protected void validateFieldExpression(String expression) throws ExpressionValidationException {
        if (StringUtils.isBlank(expression)) {
            throw new ExpressionValidationException("Invalid command Field in Cron Expression" + expression);
        }
    }

    @Override
    protected String parseFieldExpression(String hourExpression) {
        return NAME + com.deliveroo.cronexpressionparser.utilities.StringUtils.ARROW + hourExpression.trim();
    }

    @Override
    protected String parseAsteriskValue() {
        return null;
    }

    @Override
    protected String parseStepValues(String expression) {
        return null;
    }
    @Override
    protected String parseOverlappingRange(String expression) {
        return null;
    }
}
