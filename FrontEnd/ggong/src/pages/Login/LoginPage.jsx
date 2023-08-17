import Logo from '../../assets/logo.png';
import LoginButton from '../../assets/kakao_login_medium_wide.png';

import { Background } from '../../global/colors';

import React from 'react';
import { useDispatch } from 'react-redux';

// 로그인 페이지
const LoginPage = () => {
  // TODO : 로그인 버튼에 로그인 API 연결하기
  let dispatch = useDispatch();

  return (
    <div className={`${Background.MAIN} flex flex-col justify-center items-center h-screen space-y-40`}>
      <img src={Logo} className="w-3/4" />
      <a href="http://i9a304.p.ssafy.io:8080/oauth2/authorization/kakao">
        <img src={LoginButton} />
      </a>
    </div>
  );
};

export default LoginPage;
