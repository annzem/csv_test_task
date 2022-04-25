package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.SimpleConvertibleCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;

class CsvConverterTests {

    @Test
    public void test() {
        SimpleConvertibleCollection collection = new SimpleConvertibleCollection();
        collection.getHeaders().add("ID");
        collection.getHeaders().add("name");
        collection.getHeaders().add("surname");
        collection.getHeaders().add("age");

        collection.addRecord("123", "name1", "surname1", "22");
        collection.addRecord("234", "name2", "surname2", "33");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CsvConverter csvConverter = new CsvConverter();
        csvConverter.convert(collection, outputStream);

        Assertions.assertEquals("abc", new String(outputStream.toByteArray()));
    }

}