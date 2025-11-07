package com.forketyfork.rainyhills.faces;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility methods for JSF converters.
 * <p>
 * Created by Forketyfork on 26.03.17.
 */
public final class ConverterUtils {

    /**
     * The default message bundle name.
     */
    private static final String BUNDLE_NAME = "com.forketyfork.rainyhills.messages";

    /**
     * Private constructor for an utility class.
     */
    private ConverterUtils() {
    }

    /**
     * Creates an instance of the {@link FacesMessage} class corresponding to the supplied message key
     * in the {@link #BUNDLE_NAME} bundle. Also takes into account the current user's locale.
     * The summary message is retrieved from the bundle with the messageKey parameter.
     * The details message is retrieved by the key of the same name with "_detail" appended.
     *
     * @param context    the JSF context containing the user's locale
     * @param messageKey the message key
     * @param params     the substitution parameters for the detail message
     * @return an instance of the {@link FacesMessage} class for presenting to the user
     */
    public static FacesMessage createFacesMessage(FacesContext context, String messageKey, Object... params) {
        Locale locale = context.getViewRoot().getLocale();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale, loader);
        return new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                bundle.getString(messageKey),
                MessageFormat.format(bundle.getString(messageKey + "_detail"), params));
    }

}
