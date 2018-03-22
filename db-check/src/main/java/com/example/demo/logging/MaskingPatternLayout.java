package com.example.demo.logging;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.lang.String;

@Component("maskingPatternLayout")
public class MaskingPatternLayout extends PatternLayout {

	private String patternsProperty;
	private Optional<Pattern> pattern;

	public String getPatternsProperty() {
	    return patternsProperty;
	}

	public void setPatternsProperty(String patternsProperty) {
	    this.patternsProperty = patternsProperty;
	    if (this.patternsProperty != null) {
	        this.pattern = Optional.of(Pattern.compile(patternsProperty, Pattern.MULTILINE));
	    } else {
	        this.pattern = Optional.empty();
	    }
	}

	    @Override
	    public String doLayout(ILoggingEvent event) {
	        final StringBuilder message = new StringBuilder(super.doLayout(event));

	        if (pattern.isPresent()) {
	            Matcher matcher = pattern.get().matcher(message);
	            while (matcher.find()) {
	                int group = 1;
	                while (group <= matcher.groupCount()) {
	                    if (matcher.group(group) != null) {
	                        for (int i = matcher.start(group); i < matcher.end(group); i++) {
	                            message.setCharAt(i, '*');
	                        }
	                    }
	                    group++;
	                }
	            }
	        }
	        return message.toString();
	    }
	    
	    
	    private String mask(String message) {
	        StringBuffer buffer = new StringBuffer();
	        Matcher matcher = URL_PATTERN.matcher(message)
	        
	        while (matcher.find()) {
	            matcher.appendReplacement(buffer, URL_REPLACEMENT_REGEX)
	        }
	        
	        matcher.appendTail(buffer)
	        
	        return buffer.toString()
	    }

	}


