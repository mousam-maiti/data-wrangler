package com.mousam.wrangler.core;

import com.mousam.wrangler.commons.Format;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Wrangler extends DataWorker{


    private Wrangler(WranglerBuilder wranglerBuilder) {
        this.regex = wranglerBuilder.getRegex();
        this.fields = wranglerBuilder.getFields();
        pattern = Pattern.compile(wranglerBuilder.getRegex());
        this.formatMap = wranglerBuilder.getFormatMap();
        this.formatDateMap = wranglerBuilder.getFormatDateMap();
    }


    public static class WranglerBuilder {

        private String regex;
        private List<String> fields = new ArrayList<>();
        private Map<String,Type> formatMap = new HashMap<>();
        private Map<String, Format> formatDateMap = new HashMap<>();

/*        public WranglerBuilder(File file){

        }*/

        public WranglerBuilder(String regex){
            this.regex = regex;
        }

        public WranglerBuilder extractField(String field){
            this.fields.add(field);
            return this;
        }

        public WranglerBuilder formatField(String field, Type T){
            this.formatMap.put(field, T);
            return this;
        }

        public WranglerBuilder formatDateField(String field, String inputFormat, String outputFormat) throws Exception {
            Format format = new Format();
            if(inputFormat==null || outputFormat==null){
                throw new Exception("Formats can't be NULL");
            }else{
                format.setInput(inputFormat);
                format.setOutput(outputFormat);
                this.formatDateMap.put(field,format);
            }
            return this;
        }

        public Wrangler build() {
            return new Wrangler(this);
        }

        private String getRegex() {
            return regex;
        }

        private List<String> getFields() {
            return fields;
        }

        private Map<String, Type> getFormatMap() {
            return formatMap;
        }

        private Map<String, Format> getFormatDateMap() {
            return formatDateMap;
        }
    }

}
