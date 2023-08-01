package com.a304.ggong.dto.response;
import com.a304.ggong.entity.Machine;
import com.a304.ggong.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaAnswerResponse {

    private String areaGu;
    private String optionA;
    private String optionB;
//    private String answerA;
//    private String answerB;

    public AreaAnswerResponse (Machine entityM, Question entityQ){
        this.areaGu = entityM.getAreaGu();
        this.optionA = entityQ.getOptionA();
        this.optionB = entityQ.getOptionB();
    }
}
