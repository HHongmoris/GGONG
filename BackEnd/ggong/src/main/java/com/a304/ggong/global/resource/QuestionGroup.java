package com.a304.ggong.global.resource;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class QuestionGroup {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // 오늘이 몇째주인지에 따라서 그룹 넘버가 바뀌도록!

    public int getThisWeekGroupNum(){

        int thisWeek = getWeekOfYear(sdf.format(new Date()));
        thisWeek = thisWeek % 10;

        if(thisWeek == 0){
            return 10;
        }
        return thisWeek;
    }

    // 오늘이 몇째주인지 구하는 메소드
    private int getWeekOfYear(String date){
        Calendar calendar = Calendar.getInstance();
        String[] dates = date.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);
        calendar.set(year, month-1, day);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public int getLastWeekGroupNum(){
        int thisWeek = this.getThisWeekGroupNum();

        int lastWeek = thisWeek -1;

        switch (lastWeek){
            case 0:
                return 10;
        }
        return lastWeek;
    }
}
