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
public class UserCigarResponse {

    private String favoriteCigarette;

    public UserCigarResponse(User entity){
        this.favoriteCigarette = entity.getFavoriteCigarette();
    }
}
