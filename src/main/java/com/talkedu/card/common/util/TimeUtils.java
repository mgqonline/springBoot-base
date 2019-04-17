package com.talkedu.card.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Instant getNowRangeTime(long seconds){
        Instant currTime = Instant.now();
        return currTime.plus(Duration.ofSeconds(seconds));
    }

    public static Date getTime(long millLongSecond){
        return new Date(millLongSecond);
    }

    //得到当前月份第一天
    public static String getMonthStartDay(String startDate) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFormat.parse(startDate));
        cal.set(Calendar.DAY_OF_MONTH,1);
        return timeFormat.format(cal.getTime());
    }

    //得到当前月份最后一天
    public static String getMonthEndDay(String endDate) throws ParseException{
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFormat.parse(endDate));
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return timeFormat.format(cal.getTime());
    }

    //flag=1表示当天时间起始时间点，2表示当天最后一秒时间点 其它不处理
    public static String getCurrentTimeConvert(String currdate,int flag) throws ParseException{
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFormat.parse(currdate));
        if(flag == 1) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND,0);
        }else if(flag ==2){
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND,59);
        }
        return timeFormat.format(cal.getTime());
    }
}
