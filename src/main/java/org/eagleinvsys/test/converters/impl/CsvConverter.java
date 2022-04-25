package org.eagleinvsys.test.converters.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvConverter implements Converter {

    private CSVFormat csvFormat;

    public CsvConverter() {
        this.csvFormat = CSVFormat.DEFAULT;
    }

    public CsvConverter(CSVFormat csvFormat) {
        this.csvFormat = csvFormat;
    }

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
        try {
            CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputStream),
                    csvFormat);
            List<String> headerList = new ArrayList<>();
            headerList.addAll(collectionToConvert.getHeaders());
            csvPrinter.printRecord(headerList);

            for (ConvertibleMessage record : collectionToConvert.getRecords()) {
                List<String> elements = new ArrayList<>();
                for (String header : headerList) {
                    String element = record.getElement(header);
                    elements.add(element);
                }
                csvPrinter.printRecord(elements);
            }
            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException("error writing to output stream while converting", e);
        }
    }

}