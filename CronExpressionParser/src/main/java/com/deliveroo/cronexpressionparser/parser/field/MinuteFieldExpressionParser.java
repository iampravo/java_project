package com.deliveroo.cronexpressionparser.parser.field;

import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;
import com.deliveroo.cronexpressionparser.parser.AbstractExpressionParser;
import com.deliveroo.cronexpressionparser.validator.CronValidator;
import org.apache.commons.lang3.StringUtils;

public class MinuteFieldExpressionParser extends AbstractExpressionParser {

    public static final String MINUTE = "minute";
    final String regex = "^([0-5]?[0-9])$";
    private static final int END = 59;

    @Override
    protected void validateFieldExpression(String expression) throws ExpressionValidationException {
        if (CronValidator.isInvalidField(expression, regex)) {
            throw new ExpressionValidationException(" Invalid Minute Field in Cron Expression" + expression);
        }
    }

    @Override
    protected String parseFieldExpression(String minutesExpression) throws ExpressionValidationException {
        String result;
        if (!StringUtils.containsAny(minutesExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.specialChars)) {
            result = minutesExpression.trim();
        } else if (StringUtils.equals(minutesExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.ASTERISK)) {
            result = parseAsteriskValue();
        } else if (StringUtils.contains(minutesExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.HYPHEN)
                && StringUtils.contains(minutesExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.SLASH)) {
            result = parseStepRangeValues(minutesExpression);
        } else if (StringUtils.contains(minutesExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.ASTERISK)
                && StringUtils.contains(minutesExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.SLASH)) {
            result = parseStepValues(minutesExpression);
        } else if (StringUtils.contains(minutesExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.HYPHEN)) {
            result = parseRangeValues(minutesExpression);
        } else if (StringUtils.contains(minutesExpression, com.deliveroo.cronexpressionparser.utilities.StringUtils.COMMA)) {
            result = parseListValues(minutesExpression);
        } else {
            throw new ExpressionValidationException(" Invalid Minute Field in Cron Expression" + minutesExpression);
        }
        return MINUTE + com.deliveroo.cronexpressionparser.utilities.StringUtils.ARROW + result;
    }

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
