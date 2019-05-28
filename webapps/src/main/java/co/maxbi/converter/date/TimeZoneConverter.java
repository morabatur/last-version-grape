package co.maxbi.converter.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Клас для конвертації з одного часового поясу (зони) в інший
 */
public class TimeZoneConverter {


    /**
     * Метод конвертує час по Грінвічу в Московський
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertInMoscowTime(String date) {
        //"2019-03-01T18:02:02Z"
        DateTimeFormatter f = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("Europe/Moscow"));
        ZonedDateTime zdt = ZonedDateTime.parse(date, f);

        return zdt.toLocalDateTime();

    }


    public static LocalDateTime convertToLocalDataTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        //System.out.println(dateTime.toString());
        return dateTime;
    }


    public static LocalDateTime convertInNormalTime(String date) {
        String dateWitoutT = convertInMoscowTime(date).toString().replace('T', ' ');
        return convertToLocalDataTime(dateWitoutT);

    }


}
