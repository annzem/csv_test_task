package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.StandardConverter;

import java.io.OutputStream;
import java.util.*;

public class StandardCsvConverter implements StandardConverter {

    private final CsvConverter csvConverter;

    public StandardCsvConverter(CsvConverter csvConverter) {
        this.csvConverter = csvConverter;
    }

    /**
     * Converts given {@link List<Map>} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format. All maps must have the same set of keys
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(List<Map<String, String>> collectionToConvert, OutputStream outputStream) {
        ConvertibleCollection convertibleCollection = toConvertibleCollection(collectionToConvert);
        csvConverter.convert(convertibleCollection, outputStream);
    }

    private ConvertibleCollection toConvertibleCollection(List<Map<String, String>> collectionToConvert) {
        SimpleConvertibleCollection convertibleCollection = new SimpleConvertibleCollection();
        Set<String> headers = new LinkedHashSet<>();
        for (Map<String, String> map : collectionToConvert) {
            for (String header : map.keySet()) {
                headers.add(header);
            }
        }
        convertibleCollection.getHeaders().addAll(headers);
        for (Map<String, String> map : collectionToConvert) {
            String record[] = new String[convertibleCollection.getHeaders().size()];
            int i = 0;
            for (String header : convertibleCollection.getHeaders()) {
                record[i] = map.get(header);
                i++;
            }
            convertibleCollection.addRecord(record);
        }
        return convertibleCollection;
    }

}