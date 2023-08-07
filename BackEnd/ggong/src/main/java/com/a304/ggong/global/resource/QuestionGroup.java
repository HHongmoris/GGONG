package com.a304.ggong.global.resource;

import java.time.LocalDate;

public class QuestionGroup {

    LocalDate now = LocalDate.now();

    public int getThisWeekGroupNum(){
        // 한 달에 31일로 가정
        // 7일 간격으로 questionGroup이 바뀜
        // 첫째주는 1 (1~7)
        // 둘째주는 2 (8~14)
        // 셋째주는 3 (15~21)
        // 넷째주는 4 (22~28)
        // 다섯째주는 5 (29~31)
        int dayOfMonth = now.getDayOfMonth();

        if(dayOfMonth >= 1 && dayOfMonth <= 7){
            return 1;
        }else if(dayOfMonth >= 8 && dayOfMonth <= 14){
            return 2;
        }else if(dayOfMonth >= 15 && dayOfMonth <= 21){
            return 3;
        }else if(dayOfMonth >= 22 && dayOfMonth <= 28){
            return 4;
        }else {
            return 5;
        }
    }

    public int getLastWeekGroupNum(){
        // 첫째주는 5
        // 둘째주는 1
        // 셋째주는 2
        // 넷째주는 3
        // 다섯째주는 4
        int dayOfMonth = now.getDayOfMonth();

        if(dayOfMonth >= 1 && dayOfMonth <= 7){
            return 5;
        }else if(dayOfMonth >= 8 && dayOfMonth <= 14){
            return 1;
        }else if(dayOfMonth >= 15 && dayOfMonth <= 21){
            return 2;
        }else if(dayOfMonth >= 22 && dayOfMonth <= 28){
            return 3;
        }else {
            return 4;
        }
    }
}
