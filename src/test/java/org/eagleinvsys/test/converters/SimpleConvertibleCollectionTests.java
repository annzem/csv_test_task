package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.SimpleConvertibleCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleConvertibleCollectionTests {

    @Test
    public void testSimpleConvertibleCollection() {
        SimpleConvertibleCollection collection = new SimpleConvertibleCollection();
        collection.getHeaders().add("ID");
        collection.getHeaders().add("name");
        collection.getHeaders().add("age");

        Assertions.assertAll(() -> collection.addRecord("1", "name1", "22"));

        RuntimeException exExtraRecords = Assertions.assertThrows(RuntimeException.class, () -> {
            collection.addRecord("1", "name1", "surname1", "22");
        });
        Assertions.assertEquals("elements quantity in a record must be equals the headers quantity",
                exExtraRecords.getMessage());


        RuntimeException exExtraHeaders = Assertions.assertThrows(RuntimeException.class, () -> {
            collection.addRecord("name1");
        });
        Assertions.assertEquals("elements quantity in a record must be equals the headers quantity",
                exExtraHeaders.getMessage());
    }

}
