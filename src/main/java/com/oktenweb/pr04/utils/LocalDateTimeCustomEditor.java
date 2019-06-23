package com.oktenweb.pr04.utils;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class LocalDateTimeCustomEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println(text);

        org.joda.time.LocalDateTime time = new LocalDateTime(text);
        setValue(time);
    }
}
