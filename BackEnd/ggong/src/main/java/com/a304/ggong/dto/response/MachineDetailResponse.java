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
public class MachineDetailResponse {

    private Long machineNo;
    private String name;
    // 혼잡도 계산은 어디서? Controller나 service에서 계산하고 넣어줘야하나?
//    private int[] userCount = new int[24]; // 임의로 24 줌
    private String content;
    // 얘도 계산 어디서..?
//    private Long answerA;
//    private Long answerB;
    private String optionA;
    private String optionB;

    public MachineDetailResponse (Machine entityM, Question entityQ){
        this.machineNo = entityM.getMachineNo();
        this.name = entityM.getName();
        this.content = entityQ.getContent();
        this.optionA = entityQ.getOptionA();
        this.optionB = entityQ.getOptionB();
    }

}
