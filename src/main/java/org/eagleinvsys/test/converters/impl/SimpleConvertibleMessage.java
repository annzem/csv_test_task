package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleMessage;
import java.util.HashMap;
import java.util.Map;

public class SimpleConvertibleMessage implements ConvertibleMessage {

    private Map<String, String> elements = new HashMap<>();

    @Override
    public String getElement(String elementId) {
        if (!elements.containsKey(elementId)) {
            throw new RuntimeException("no element with elementId " + elementId);
        }
        return elements.get(elementId);
    }

    public Map<String, String> getElements() {
        return elements;
    }
}
