package com.a304.ggong.global.oauth2;

import com.a304.ggong.entity.Role;
import com.a304.ggong.entity.SocialType;
import com.a304.ggong.entity.User;
import com.a304.ggong.global.oauth2.userinfo.KakaoOAuth2UserInfo;
import com.a304.ggong.global.oauth2.userinfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private String nameAttributeKey;
    private OAuth2UserInfo oauth2UserInfo;

    @Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    public static OAuthAttributes of(SocialType socialType, String userNameAttributeName, Map<String, Object> attributes) {
        return ofKakao(userNameAttributeName, attributes);

    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    public User toEntity(SocialType socialType, OAuth2UserInfo oauth2UserInfo) {
        System.out.println("나이 범위 : "+oauth2UserInfo.getAgeRange());
        return User.builder()
                .socialType(socialType)
                .socialId(oauth2UserInfo.getId())
                .email(oauth2UserInfo.getEmail())
                .name(oauth2UserInfo.getNickname())
                .gender(oauth2UserInfo.getGender())
                .ageRange(oauth2UserInfo.getAgeRange())
                .role(Role.GUEST)
                //.QR("qrurl")
                //.favoriteCigarette("mal")
                //.userRating("0")
                .build();
    }
}
