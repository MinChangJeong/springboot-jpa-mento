package tuk.mentor.global.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {
    public LocalDate convertStringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }
    public LocalDateTime convertStringToLocalDateTime(String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ISO_DATE_TIME);
    }
}
