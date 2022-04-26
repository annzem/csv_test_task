package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleConvertibleCollection implements ConvertibleCollection {

    public List<String> headers = new ArrayList<>();
    public List<ConvertibleMessage> records = new ArrayList<>();

    @Override
    public Collection<String> getHeaders() {
        return headers;
    }

    @Override
    public Iterable<ConvertibleMessage> getRecords() {
        return records;
    }

    public void addRecord(String... elements) {
        if (elements.length != headers.size()) {
            throw new RuntimeException("elements quantity in a record must be equals the headers quantity");
        }
        SimpleConvertibleMessage message = new SimpleConvertibleMessage();
        for (int i = 0; i < elements.length; i++) {
            message.getElements().put(headers.get(i), elements[i]);
        }
        records.add(message);
    }
}
