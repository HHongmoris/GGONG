package com.a304.ggong.global.oauth2.userinfo;

import java.util.Map;

public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) { this.attributes = attributes; }

    public abstract String getId(); //소셜 식별값: 카카오 - 'id'

    public abstract String getNickname();

    public abstract String getGender();

    public abstract String getAgeRange();
    public abstract String getEmail();
}
