package tbgt.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private static ThreadLocal threadLocal = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };

    public static DateFormat getDateFormat() {
        return (DateFormat) threadLocal.get();
    }

    public static Date parse(String textDate) {
        Date date ;
        try {
            date = getDateFormat().parse(textDate);

        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    public static String format(Date date) {
        String format ;
        try {
            format = getDateFormat().format(date);

        } catch (Exception e) {
            format = "0000-00-00";
        }
        return format;
    }
}
