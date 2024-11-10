package com.deliveroo.cronexpressionparser.parser;


import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;
import com.deliveroo.cronexpressionparser.utilities.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.StringTokenizer;

public abstract class AbstractExpressionParser implements ExpressionParser {
    private static final Logger logger = Logger.getLogger(AbstractExpressionParser.class);

    private ExpressionParser nextExpressionParser;

    @Override
    public void parse(StringTokenizer token, List<String> output) throws ExpressionValidationException {
        String tokenStr = token.nextToken();
        logger.info("Started parsing expression : " + tokenStr);
        //validateFieldExpression(tokenStr);
        String parseValue = parseFieldExpression(tokenStr);
        output.add(parseValue);
        logger.info("Completed parsing token parsing expression : " + tokenStr + " Parsed value : " + parseValue);
        if (token.hasMoreTokens())
            nextExpressionParser.parse(token, output);
    }

    @Override
    public void setNextParser(ExpressionParser nextExpressionParser) {
        this.nextExpressionParser = nextExpressionParser;
    }

    protected abstract void validateFieldExpression(String expression) throws ExpressionValidationException;

    protected abstract String parseFieldExpression(String expression) throws ExpressionValidationException;

    // E.g. *
    protected abstract String parseAsteriskValue();

    //E.G. */15
    protected abstract String parseStepValues(String expression);

    // E.g. 1,2
    protected String parseListValues(String expression) {
        String[] rangeParts = expression.split(StringUtils.COMMA);
        return rangeParts[0] + StringUtils.SEPARATOR + rangeParts[1];
    }

    // E.g. 1-3
    protected String parseRangeValues(String expression) {
        String[] rangeParts = expression.split(StringUtils.HYPHEN);
        int start = Integer.parseInt(rangeParts[0]);
        int end = Integer.parseInt(rangeParts[1]);
        if (start > end) {
            return parseOverlappingRange(expression);
        }
        StringBuilder result = new StringBuilder();
        while (start <= end) {
            result.append(start).append(StringUtils.SEPARATOR);
            start++;
        }
        return result.toString();
    }

    /*
     if (cronExpression.contains(StringUtils.SLASH)) {
            String[] parts = cronExpression.split(StringUtils.SLASH);
            if (parts.length != 2) {
                return true;
            }
            if (parts[0].equals("*")) {
                if (!CronValidator.validateRegex(basicFieldRegex, parts[1])) {
                    return true;
                }
            }
            if (!parts[0].equals("*")) {
                if (!CronValidator.validateRegex(basicFieldRegex, parts[0]) || !CronValidator.validateRegex(basicFieldRegex, parts[1])) {
                    return true;
                }
            }
        }
        if (cronExpression.contains(StringUtils.HYPHEN)) {
            String[] pieces = cronExpression.split(StringUtils.HYPHEN);
            for (String piece : pieces) {
                if (!CronValidator.validateRegex(basicFieldRegex, piece)) {
                    return true;
                }
            }
        }
     */
    protected String parseStepRangeValues(String expression) {
        String[] hyphenParts = expression.split(StringUtils.HYPHEN);
        String[] slashParts = hyphenParts[1].split(StringUtils.SLASH);
        StringBuilder result = new StringBuilder();
        int start = Integer.parseInt(hyphenParts[0]);
        int end = Integer.parseInt(slashParts[0]);
        int interval = Integer.parseInt(slashParts[1]);
        for (int i = start; i <= end; i = i + interval) {
            result.append(i).append(StringUtils.SEPARATOR);
        }
        return result.toString();
    }

    protected abstract String parseOverlappingRange(String expression);
}
