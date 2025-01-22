package com.teste.pratico.web.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@FacesConverter(value = "localDateTimeConverter")
public class FacesConvertLocalDateTime implements Converter {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Date date = null;
        try {
            date = formatter.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        // Convert Date to LocalDateTime.
        LocalDateTime localDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return localDateTime;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LocalDateTime dateValue = (LocalDateTime) value;
        return DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm").format(dateValue);
    }
}