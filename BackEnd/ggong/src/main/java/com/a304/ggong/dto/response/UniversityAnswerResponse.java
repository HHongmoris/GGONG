package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Machine;
import com.a304.ggong.entity.Question;

public class UniversityAnswerResponse {

    private String category;
    private String optionA;
    private String optionB;
//    private String answerA;
//    private String answerB;

    public UniversityAnswerResponse(Question entityQ){
        this.category = entityQ.getCategory();
        this.optionA = entityQ.getOptionA();
        this.optionB = entityQ.getOptionB();
    }
}
