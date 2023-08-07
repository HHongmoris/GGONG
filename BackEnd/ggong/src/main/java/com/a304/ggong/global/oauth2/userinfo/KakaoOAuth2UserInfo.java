package com.a304.ggong.global.oauth2.userinfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
    public KakaoOAuth2UserInfo(Map<String, Object> attributes) { super(attributes);  }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getNickname() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("nickname");
    }

    @Override
    public String getGender() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

        String gender = (String) account.get("gender");

        if (account == null) {
            return null;
        }

        if(gender.equals("male")){
            gender = "남성";
        } else {
            gender = "여성";
        }
        return gender;
    }

    @Override
    public String getAgeRange() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

        String ageRange = (String) account.get("age_range");
        if (account == null) {
            return null;
        }

        if (ageRange.equals("0~9") || ageRange.equals("10~19")) {
            ageRange = "10대 이하";
        }
        else if (ageRange.equals("20~29")) {
            ageRange = "20대";
        }
        else if (ageRange.equals("30~39")) {
            ageRange = "30대";
        }
        else if (ageRange.equals("40~49")) {
            ageRange = "40대";
        }
        else if (ageRange.equals("50~59")) {
            ageRange = "50대";
        }
        else {
            ageRange = "60대 이상";
        }

        //return (String) account.get("age_range");
        return ageRange;
    }

    @Override
    public String getEmail() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

        if (account == null) {
            return null;
        }
        return (String) account.get("email");
    }


}
