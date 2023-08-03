package com.a304.ggong.dto.response;
import com.a304.ggong.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyAnswerResponse {
    private String companyName; // Service에서 쿼리문 주기
    private String optionA;
    private String optionB;

    // Service에서 연산해서 넣어주기
    private String answerA;
    private String answerB;

    public CompanyAnswerResponse(Question entity){
        this.optionA = entity.getOptionA();
        this.optionB = entity.getOptionB();
    }
}
