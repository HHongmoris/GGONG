package com.a304.ggong.dto.request;

import com.a304.ggong.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCigarRequest {

    private String favoriteCigarette;

    @Builder
    public User toEntity(){
        return User.builder().favoriteCigarette(favoriteCigarette).build();
    }
}
