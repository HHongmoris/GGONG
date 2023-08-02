package com.a304.ggong.dto.response;

import com.a304.ggong.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgeStatResponse {
    private Long twenty;    //20대 사용자 수
    private Long thirty;    //30대 사용자 수
    private Long forty;     //40대 사용자 수
    private Long fifty;     //50대 사용자 수
    private Long etc;       //그 외 사용자 수

    //이거 사용자 수를 카운트해서 가져오기

}
