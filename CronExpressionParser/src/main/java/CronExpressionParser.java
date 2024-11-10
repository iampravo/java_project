import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;
import com.deliveroo.cronexpressionparser.parser.ExpressionParser;
import com.deliveroo.cronexpressionparser.parser.field.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.*;

import static com.deliveroo.cronexpressionparser.utilities.StringUtils.SEPARATOR;

public class CronExpressionParser {
    private static final Logger logger = Logger.getLogger(CronExpressionParser.class);

    public static ExpressionParser getHandlerChain() {
        ExpressionParser minuteFieldExpressionParser = new MinuteFieldExpressionParser();
        ExpressionParser hourFieldExpressionParser = new HourFieldExpressionParser();
        ExpressionParser dayFieldExpressionParser = new DayOfMonthFieldExpressionParser();
        ExpressionParser monthDayFieldExpressionParser = new MonthFieldExpressionParser();
        ExpressionParser weekDayFieldExpressionParser = new DayOfWeekFieldExpressionParser();
        ExpressionParser commandFieldExpressionParser = new CommandFieldExpressionParser();

        minuteFieldExpressionParser.setNextParser(hourFieldExpressionParser);
        hourFieldExpressionParser.setNextParser(dayFieldExpressionParser);
        dayFieldExpressionParser.setNextParser(monthDayFieldExpressionParser);
        monthDayFieldExpressionParser.setNextParser(weekDayFieldExpressionParser);
        weekDayFieldExpressionParser.setNextParser(commandFieldExpressionParser);

        return minuteFieldExpressionParser;
    }


    public static void main(String[] args) throws ExpressionValidationException {
        String expression = String.join(SEPARATOR, args);
        validateCronExpression(expression);
        ExpressionParser chain = getHandlerChain();
        StringTokenizer token = new StringTokenizer(expression, SEPARATOR);
        List<String> output = new ArrayList<>();
        chain.parse(token, output);
        System.out.println(String.join("\n", output));
    }


    private static void validateCronExpression(String expression) throws ExpressionValidationException {
        if (null == expression || expression.isEmpty() || StringUtils.isBlank(expression)) {
            logger.error("Empty Cron Expression : " + expression);
            throw new ExpressionValidationException("Empty Cron Expression : " + expression);
        }
        String[] expressionParts = expression.split(" ");
        if (expressionParts.length != 6 && expressionParts.length != 7) {
            logger.error("Incomplete Cron Expression : " + expression);
            throw new ExpressionValidationException("Incomplete Cron Expression : " + expression);
        }
    }
}

