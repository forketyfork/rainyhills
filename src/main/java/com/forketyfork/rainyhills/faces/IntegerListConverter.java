package com.forketyfork.rainyhills.faces;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * The JSF converter for the comma-separated list of integers:
 * "1,2,3" <=> ArrayList(1,2,3)
 * <p>
 * Created by Sergey Petunin on 26.03.17.
 */
@FacesConverter("com.forketyfork.rainyhills.faces.IntegerListConverter")
public class IntegerListConverter implements Converter {

    /**
     * A precompiled regex for splitting the list.
     */
    private static final Pattern SPLIT_BY_COMMA = Pattern.compile(",");

    /**
     * Converts the user-input String (comma-separated list of integers) to a List of Integer values.
     *
     * @param context   JSF context
     * @param component the input component
     * @param value     the input value, a comma-separated list of integers: "1,2,3"
     * @return a List&lt;String&gt; instance containing the numbers
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return Arrays.stream(SPLIT_BY_COMMA.split(value))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        throw new ConverterException(
                                ConverterUtils.createFacesMessage(context, "indexConversionError", s), e);
                    }
                })
                .collect(toList());
    }

    /**
     * Converts a List&lt;String&gt; instance to a String.
     *
     * @param context   JSF context
     * @param component the input component
     * @param value     the object value, an instance of List&lt;String&gt;
     * @return comma-separated list of integers in a String.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        List<?> list = (List<?>) value;
        return list.stream()
                .map(Object::toString)
                .collect(joining(","));
    }

}
