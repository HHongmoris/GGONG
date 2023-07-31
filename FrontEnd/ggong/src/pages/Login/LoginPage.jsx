import React from 'react';

import Logo from '../../assets/logo.png';
import LoginButton from '../../assets/kakao_login_medium_wide.png';

import { Background } from '../../global/colors';

import Button from '../../components/Button/Button';

// 로그인 페이지
const LoginPage = () => {
  // TODO : 로그인 버튼에 로그인 API 연결하기

  return (
    <div className={`${Background.MAIN} flex flex-col justify-center items-center h-screen space-y-40`}>
      <img src={Logo} className="w-3/4" />
      <Button value={<img src={LoginButton} />} />
    </div>
  );
};

export default LoginPage;
