package com.uahnn.bookmakeeer.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by Robin on 07.01.16
 */
@FacesConverter("com.uahnn.StringToByteArrayConverter")
public class StringToByteArrayConverter implements Converter {

    public byte[] getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == "") {
            return null;
        }

        byte[] valueAsBytes;

        valueAsBytes = value.getBytes();

        return valueAsBytes;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) throws ConverterException {
        if (value == null) return null;
        return value.toString();
    }
}