package com.shc.north;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author shaohch
 * @date 2021/8/11
 */

public class Utils {
    public static final int DURATION = 11;
    
    public static String msgTime(long when) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf3 = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        String nowTime1 =  sdf1.format(calendar.getTime());
        String whenTime1 =  sdf1.format(new Date(when));

        String nowTime2 =  sdf.format(calendar.getTime());
        String whenTime2 =  sdf.format(new Date(when));

        if(nowTime1.equals(whenTime1)){
            //是当天
            return sdf2.format(new Date(when));
        }else{
            if(nowTime2.equals(whenTime2)){
                //是当年
                return sdf3.format(new Date(when));
            }else {
                //非当年
                return sdf1.format(new Date(when));
            }
        }
    }

    public static long getTimeS(String date) {
        if (TextUtils.isEmpty(date)) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss",
                Locale.CHINA);
        try {
            Date ckDate = sdf.parse(date);
            return ckDate.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getCurTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String t=format.format(new Date());
        return t;
    }
}
