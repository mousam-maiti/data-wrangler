package com.mousam.wrangler.core;

import com.mousam.wrangler.exception.FormatterException;
import com.mousam.wrangler.exception.NoPatternMatchedException;

import java.text.ParseException;

public interface StageProcesses {

    void process(String record) throws NoPatternMatchedException, FormatterException, ParseException;
    boolean parse();
    boolean format() throws ParseException;
    Object toJson();

}
