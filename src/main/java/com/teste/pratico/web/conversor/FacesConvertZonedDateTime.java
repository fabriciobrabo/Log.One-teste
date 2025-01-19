package com.teste.pratico.web.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@FacesConverter(value = "localZonedDateTimeConverter")
public class FacesConvertZonedDateTime implements Converter {

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
        // Get system default time zone id.
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // Convert Date to Instant.
        Instant instant = date.toInstant();
        // Instant + default time zone.
        ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
        return zonedDateTime;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ZonedDateTime dateValue = (ZonedDateTime) value;

        return DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(dateValue);
    }

}
