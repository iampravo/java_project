package com.deliveroo.cronexpressionparser.parser;

import com.deliveroo.cronexpressionparser.exception.ExpressionValidationException;

import java.util.List;
import java.util.StringTokenizer;

public interface ExpressionParser {
    void parse(StringTokenizer request, List<String> output) throws ExpressionValidationException;

    void setNextParser(ExpressionParser expressionParser);
}