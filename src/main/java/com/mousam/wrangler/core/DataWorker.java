package com.mousam.wrangler.core;

import com.google.gson.JsonObject;
import com.mousam.wrangler.commons.Format;
import com.mousam.wrangler.exception.FormatterException;
import com.mousam.wrangler.exception.NoPatternMatchedException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataWorker implements StageProcesses {

    protected String regex;
    protected List<String> fields;
    protected Map<String, Type> formatMap = new HashMap<>();
    protected Map<String, Format> formatDateMap = new HashMap<>();
    protected Map<String, Object> extractedFieldsMap = new HashMap<>();
    protected Pattern pattern;
    protected String record;
    protected JsonObject jsonObject;

    @Override
    public void process(String record) throws NoPatternMatchedException, FormatterException, ParseException {
        this.record = record;
        this.jsonObject = new JsonObject();
        if (parse()) {
            if (!format()) {
                throw new FormatterException(FormatterException.formatException);
            }
        } else {
            throw new NoPatternMatchedException(NoPatternMatchedException.noPatternFound);
        }
    }

    @Override
    public boolean parse() {
        Matcher matcher = pattern.matcher(record);
        if (matcher.matches()) {
            for (String field : fields) {
                extractedFieldsMap.put(field, matcher.group(field));
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean format() throws ParseException {
        for (String field : fields) {
            String value = String.valueOf(extractedFieldsMap.get(field));
            if (formatMap.containsKey(field)) {
                Type T = formatMap.get(field);
                if (T.equals(Integer.TYPE)) {
                    jsonObject.addProperty(field, Integer.parseInt(value));
                } else if (T.equals(Long.TYPE)) {
                    jsonObject.addProperty(field, Long.parseLong(value));
                } else if (T.equals(Double.TYPE)) {
                    jsonObject.addProperty(field, Double.parseDouble(value));
                }
            } else if (formatDateMap.containsKey(field)) {
                Date date = new Date();
                Format format = formatDateMap.get(field);
                SimpleDateFormat oldFormat = new SimpleDateFormat(format.getInput());
                date = oldFormat.parse(value);
                SimpleDateFormat newFormat = new SimpleDateFormat(format.getOutput());
                jsonObject.addProperty(field, newFormat.format(date));
            } else{
                jsonObject.addProperty(field, String.valueOf(extractedFieldsMap.get(field)));
            }
        }
        return true;
    }

    @Override
    public Object toJson() {
        return jsonObject;
    }
}
