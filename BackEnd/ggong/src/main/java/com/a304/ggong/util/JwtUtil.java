package com.a304.ggong.util;

public class JwtUtil {

    // 유효성 검사
    public Claims valid(String token) throws Exception {
        // getBody() 메소드를 호출하여 Claims 객체를 반환받고, 클레임 정보에 접근할 수 있다.
        // 만약, 유효하지 않은 토큰이라면? -> Claims에 null이 반환된다.
        return Jwts.parser().setSigningKey("SSAFIT".getBytes("UTF-8")).parseClaimsJws(token).getBody();

}
}
