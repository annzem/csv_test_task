package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.SimpleConvertibleCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class CsvConverterTests {
    private SimpleConvertibleCollection collection = new SimpleConvertibleCollection();
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private CsvConverter csvConverter = new CsvConverter();

    @BeforeEach
    public void clear() {
        collection.getHeaders().clear();
        ((List<ConvertibleMessage>) collection.getRecords()).clear();
    }

    @Test
    public void testCsvConverterEquality() {
        collection.getHeaders().add("ID");
        collection.getHeaders().add("name");
        collection.getHeaders().add("age");

        collection.addRecord("1", "name1", "22");
        collection.addRecord("2", "name2", "33");
        collection.addRecord("3", "name3", "44");

        csvConverter.convert(collection, outputStream);

        Assertions.assertEquals("ID,name,age\r\n" +
                "1,name1,22\r\n" +
                "2,name2,33\r\n" +
                "3,name3,44\r\n", new String(outputStream.toByteArray()));
    }

    @Test
    void writingExceptionTest() throws IOException {
        collection.getHeaders().add("ID");
        collection.getHeaders().add("name");
        collection.addRecord("1", "name1");

        ByteArrayOutputStream outputStreamMock = mock(ByteArrayOutputStream.class);
        doThrow(IOException.class)
                .when(outputStreamMock)
                .write(any(byte[].class), anyInt(), anyInt());

        RuntimeException writingError = Assertions.assertThrows(RuntimeException.class, () -> {
            csvConverter.convert(collection, outputStreamMock);
        });

        Assertions.assertEquals("Error writing to output stream while converting",
                writingError.getMessage());
    }

    @Test
    public void testNullCollection() {
        RuntimeException collectionIsNullError = Assertions.assertThrows(RuntimeException.class, () -> {
            csvConverter.convert(null, outputStream);
        });

        Assertions.assertEquals("Collection can't be null",
                collectionIsNullError.getMessage());
    }

    @Test
    public void testNullHeaders() {
        collection.getHeaders().add(null);
        collection.getHeaders().add("name");

        collection.addRecord("1", "name1");
        collection.addRecord("2", "name2");

        RuntimeException exNullHeaders = Assertions.assertThrows(RuntimeException.class, () -> {
            csvConverter.convert(collection, outputStream);
        });
        Assertions.assertEquals("Headers can't be null or empty",
                exNullHeaders.getMessage());
    }

    @Test
    public void testEmptyHeaders() {
        collection.getHeaders().add("");
        collection.getHeaders().add("name");

        collection.addRecord("1", "name1");
        collection.addRecord("2", "name2");

        RuntimeException exNullHeaders = Assertions.assertThrows(RuntimeException.class, () -> {
            csvConverter.convert(collection, outputStream);
        });
        Assertions.assertEquals("Headers can't be null or empty",
                exNullHeaders.getMessage());
    }


    @Test
    public void testEmptyRecords() {
        collection.getHeaders().add("id");
        collection.getHeaders().add("name");
        csvConverter.convert(collection, outputStream);

        Assertions.assertEquals("id,name\r\n", new String(outputStream.toByteArray()));
    }


}