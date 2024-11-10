package com.deliveroo.cronexpressionparser.parser.field;

import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;
import com.deliveroo.cronexpressionparser.parser.AbstractExpressionParser;
import com.deliveroo.cronexpressionparser.validator.CronValidator;
import org.apache.commons.lang3.StringUtils;

public class HourFieldExpressionParser extends AbstractExpressionParser {

    public static final String HOUR = "hour";
    final String regex = "^([0-9]|1[0-9]|2[0-3])$";
    private static final int END = 23;

    @Override
    protected void validateFieldExpression(String expression) throws ExpressionValidationException {
        if (CronValidator.isInvalidField(expression, regex)) {
            throw new ExpressionValidationException(" Invalid Hour Field in Cron Expression" + expression);
        }
    }

    @Override
    protected String parseFieldExpression(String hourExpression) throws ExpressionValidationException {
        String result;
        if (!StringUtils.containsAny(hourExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.specialChars)) {
            result = hourExpression.trim();
        } else if (StringUtils.contains(hourExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.HYPHEN)) {
            result = parseRangeValues(hourExpression);
        } else if (StringUtils.equals(hourExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.ASTERISK)) {
            result = parseAsteriskValue();
        } else if (StringUtils.contains(hourExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.ASTERISK)
                && StringUtils.contains(hourExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.SLASH)) {
            result = parseStepValues(hourExpression);
        } else if (StringUtils.contains(hourExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.COMMA)) {
            result = parseListValues(hourExpression);
        } else {
            throw new ExpressionValidationException(" Invalid Hour Field in Cron Expression" + hourExpression);
        }
        return HOUR + com.deliveroo.cronexpressionparser.utilities.StringUtils.ARROW + result;
    }

    @Override
    protected String parseAsteriskValue() {
        StringBuilder result = new StringBuilder();
        int START = 0;
        for (int i = START; i <= END; i++) {
            result.append(i).append(com.deliveroo.cronexpressionparser.utilities.StringUtils.SEPARATOR);
        }
        return result.toString();
    }

    @Override
    protected String parseStepValues(String expression) {
        StringBuilder result = new StringBuilder();
        String[] parts = expression.split(com.deliveroo.cronexpressionparser.utilities.StringUtils.SLASH);
        int start = parts[0].equals(com.deliveroo.cronexpressionparser.utilities.StringUtils.ASTERISK) ? 0 : Integer.parseInt(parts[0]);
        int interval = Integer.parseInt(parts[1]);
        for (int i = start; i <= END; i = i + interval) {
            result.append(i).append(com.deliveroo.cronexpressionparser.utilities.StringUtils.SEPARATOR);
        }
        return result.toString();
    }
    @Override
    protected String parseOverlappingRange(String expression) {
        return null;
    }
}
