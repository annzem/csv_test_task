package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.SimpleConvertibleCollection;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StandardCsvConverterTests {

    @Test
    public void test() {
        StandardCsvConverter converter = new StandardCsvConverter(new CsvConverter());
        List<Map<String, String>> collectionToConvert = new ArrayList<>();

        Map<String, String> rec = new HashMap<>();
        rec.put("id", "123");
        rec.put("name", "qwe");
        collectionToConvert.add(rec);


        collectionToConvert.add(rec);



        rec = new HashMap<>();
        rec.put("type", "sometype");
        rec.put("name", "asd");
        collectionToConvert.add(rec);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        converter.convert(collectionToConvert, outputStream);

        System.out.println();



    }

}