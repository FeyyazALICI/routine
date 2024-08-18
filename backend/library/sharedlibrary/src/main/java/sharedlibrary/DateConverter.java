package sharedlibrary;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateConverter {
    
            // convertWeekToDate
    public static String convertWeekToDate(int year, int week) {
        // Create a LocalDate for the first day of the specified week in the given year
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.YEAR, year);
        cld.set(Calendar.WEEK_OF_YEAR, week);
        LocalDate tarih = LocalDate.ofInstant(cld.toInstant(), ZoneId.systemDefault());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String tarihStr = tarih.format(df);
        return tarihStr;
    }
        // convertMonthToDate
    public static String convertMonthToDate(int year, int month) {
        // Create a LocalDate for the first day of the specified month in the given year
        LocalDate tarih = LocalDate.of(year, month, 1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return tarih.format(df);
    }

        // convertYearToDate
    public static String convertYearToDate(int year){
        LocalDate tarih = LocalDate.of(year, 1, 1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return tarih.format(df);
    }
}
