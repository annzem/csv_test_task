package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.*;

class StandardCsvConverterTests {

    @Test
    public void testStandardCsvConverter() {
        StandardCsvConverter standardCsvConverter = new StandardCsvConverter(new CsvConverter());
        List<Map<String, String>> collectionToConvert = new ArrayList<>();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Map<String, String> record1 = new LinkedHashMap<>();
        record1.put("id", "1");
        record1.put("name", "name1");
        record1.put("age", "22");
        collectionToConvert.add(record1);

        Map<String, String> record2 = new LinkedHashMap<>();
        record2.put("id", "2");
        record2.put("name", "name2");
        record2.put("age", "33");
        collectionToConvert.add(record2);

        Map<String, String> record3 = new LinkedHashMap<>();
        record3.put("id", "3");
        record3.put("name", "name3");
        record3.put("age", "44");
        collectionToConvert.add(record3);

        standardCsvConverter.convert(collectionToConvert, outputStream);

        Assertions.assertEquals("id,name,age\r\n" +
                "1,name1,22\r\n" +
                "2,name2,33\r\n" +
                "3,name3,44\r\n", new String(outputStream.toByteArray()));

    }



}