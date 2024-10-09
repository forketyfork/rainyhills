package com.forketyfork.rainyhills.faces;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for JSF integer list converter.
 * <p>
 * No tests for erroneous input, because the {@link javax.faces.context.FacesContext} should be mocked
 * for correct error handling, which is not trivial.
 *
 * @see IntegerListConverter
 * <p>
 * Created by Sergey Petunin on 27.03.17.
 */
public class IntegerListConverterTest {

    private IntegerListConverter converter = new IntegerListConverter();

    @Test
    public void whenValueIsCommaSeparatedList_thenResultIsListOfIntegers() {
        List<?> values = (List<?>) converter.getAsObject(null, null, "1,2,3");
        assertEquals(3, values.size());
        assertEquals(1, values.get(0));
        assertEquals(2, values.get(1));
        assertEquals(3, values.get(2));
    }

    @Test
    public void whenValueIsEmpty_thenResultIsEmptyList() {
        List<?> values = (List<?>) converter.getAsObject(null, null, "");
        assertTrue(values.isEmpty());
    }

    @Test
    public void whenValueIsAnArrayOfInts_thenResultIsCommaSeparatedString() {
        String result = converter.getAsString(null, null, Arrays.asList(1, 2, 3));
        assertEquals("1,2,3", result);
    }

    @Test
    public void whenValueIsEmpty_thenResultIsEmptyString() {
        String result = converter.getAsString(null, null, Collections.emptyList());
        assertEquals("", result);
    }

    @Test
    public void whenValueIsNull_thenResultIsEmptyString() {
        String result = converter.getAsString(null, null, null);
        assertEquals("", result);
    }

    /**
     * This is not supposed to happen, but still we're demonstrating that the converter
     * can produce sane results without failing.
     */
    @Test
    public void whenValueIsStringList_thenResultIsCommaSeparatedString() {
        String result = converter.getAsString(null, null, Arrays.asList("a", "b", "c"));
        assertEquals("a,b,c", result);
    }

}