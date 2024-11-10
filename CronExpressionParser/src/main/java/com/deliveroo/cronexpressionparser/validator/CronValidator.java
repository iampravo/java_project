package com.deliveroo.cronexpressionparser.validator;

import com.deliveroo.cronexpressionparser.utilities.StringUtils;
import java.util.regex.Pattern;

public class CronValidator {
    enum Day {
        MON, TUE, WED, THU, FRI, SAT, SUN
    }

    enum Month {
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
    }

    public static boolean isInvalidField(String cronExpression, String basicFieldRegex) {
        if (cronExpression.equals("")) {
            return true;
        }
        if (cronExpression.equals(StringUtils.ASTERISK)) {
            return false;
        }
        if (CronValidator.validateRegex(basicFieldRegex, cronExpression)) {
            return false;
        }
        if (!cronExpression.contains(StringUtils.COMMA) && !cronExpression.contains(StringUtils.SLASH) && !cronExpression.contains(StringUtils.HYPHEN)) {
            return !CronValidator.validateRegex(basicFieldRegex, cronExpression);
        }
        if (cronExpression.contains(StringUtils.COMMA)) {
            String[] parts = cronExpression.split(StringUtils.COMMA);
            for (String part : parts) {
                if (part.contains(StringUtils.HYPHEN)) {
                    String[] pieces = part.split(StringUtils.HYPHEN);
                    for (String piece : pieces) {
                        if (!CronValidator.validateRegex(basicFieldRegex, piece)) {
                            return true;
                        }
                    }

                } else if (part.contains(StringUtils.SLASH)) {
                    String[] pieces = part.split(StringUtils.SLASH);
                    if (pieces.length != 2) {
                        return true;
                    }
                    if (!CronValidator.validateRegex(basicFieldRegex, pieces[0]) || !CronValidator.validateRegex(basicFieldRegex, pieces[1])) {
                        return true;
                    }
                } else if (!CronValidator.validateRegex(basicFieldRegex, part)) {
                    return true;

                }
            }
            return false;
        }
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
        return false;
    }

    public static String convertToAllNumbers(String cron) {
        for (Day day : Day.values()) {
            if (cron.contains(day.name())) {
                cron = cron.replaceAll(day.name(), "" + (day.ordinal() + 1));
            }
        }
        for (Month month : Month.values()) {
            if (cron.contains(month.name())) {
                cron = cron.replaceAll(month.name(), "" + (month.ordinal() + 1));
            }
        }
        return cron;
    }

    public static boolean validateRegex(String regex, String input) {
        return Pattern.matches(regex, input);
    }

}