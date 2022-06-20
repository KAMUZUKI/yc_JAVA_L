package src.com.mu.project18_util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeExamples2 {
    private static final ThreadLocal<DateFormat> formatters = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日");
        }
    };

    public static void main(String[] args) {

    }

    private static void useOldDate(){
        // Date date = new Date(114,2,18);  //老版的Date缺点：只能表示到毫秒精度，它的起始年份是 1900年，月份从0开始算，返回值有时区信息
        Date date = new Date();  //当前时间
        System.out.println(date);      //Sun Apr 24 20:00:14 CTS 2022
        //  从本地线程中取 一个  日期格式对象 format对象
        DateFormat df = formatters.get();
        System.out.println(df.format(date));
        //Calendar中月份从0开始
        Calendar calender = Calendar.getInstance();  //获取日历，表示当前时间
        calender.set(2014,Calendar.FEBRUARY,18);
        System.out.println(calender);

    }

    private static void useLocalDate(){
        // 日期对象：静态方法 LocalDate.of
        LocalDate date = LocalDate.of(2014,3,18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        System.out.println(date);
        //ChronoField是一个枚举类型，它实现了 TemporalField 接口 枚举中有各种时间字段表示常量
        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);

        //2.时间对象
        LocalTime time = LocalTime.of(13,45,20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(time);

        // 3.时间，日期 都有的对象
        LocalDateTime dt1 = LocalDateTime.of(2014,Month.MARCH,18,13,45,20);
        LocalDateTime dt2 = LocalDateTime.of(date,time);
        LocalDateTime dt3 = date.atTime(13,45,20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt1);

        // 也可以用 toxxxx 方法  来获取 Date，Time
        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1);
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1);

    }

    // 日期格式化类
    public static void useDateFormatter(){
        LocalDate date = LocalDate.of(2022,4,24);
        // 生成线程安全的日期格式化对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));  // 默认格式化
        System.out.println(date.format(formatter));
        System.out.println(date.format(italianFormatter));
        // 自定义一个更复杂的 DateTimeFormatter对象
        // 设计模式： 构建器
        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder().appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ").appendText(ChronoField.MONTH_OF_YEAR).appendLiteral(" ")
                .appendText(ChronoField.YEAR).parseCaseInsensitive().toFormatter(Locale.ITALIAN);

        System.out.println(date.format(complexFormatter));
    }
}


