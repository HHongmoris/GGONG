import Logo from '../../assets/logo.png';
import LoginButton from '../../assets/kakao_login_medium_wide.png';

import { Background } from '../../global/colors';
import { login } from '../../global/store';
import useApi from '../../hooks/useApi';

import React from 'react';
import { useDispatch } from 'react-redux';

import Button from '../../components/Button/Button';

// 로그인 페이지
const LoginPage = () => {
  // TODO : 로그인 버튼에 로그인 API 연결하기
  let dispatch = useDispatch();

  const onClickLogin = () => {
    useApi('/users/1', 'GET').then(res => {
      let response = res.data[0];
      console.log(response);
      dispatch(login(response));
    });
  };

  return (
    <div className={`${Background.MAIN} flex flex-col justify-center items-center h-screen space-y-40`}>
      <img src={Logo} className="w-3/4" />
      <Button value={<img src={LoginButton} onClick={onClickLogin} />} />
    </div>
  );
};

export default LoginPage;
