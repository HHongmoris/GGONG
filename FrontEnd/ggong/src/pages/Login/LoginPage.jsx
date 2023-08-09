import Logo from '../../assets/logo.png';
import LoginButton from '../../assets/kakao_login_medium_wide.png';

import { Background } from '../../global/colors';
import { login } from '../../global/store';
import useApi from '../../hooks/useApi';

import React from 'react';
import { useDispatch } from 'react-redux';

import Button from '../../components/Button/Button';
import { Link } from 'react-router-dom';

// 로그인 페이지
const LoginPage = () => {
  // TODO : 로그인 버튼에 로그인 API 연결하기
  let dispatch = useDispatch();

  // const onClickLogin = () => {
  //   useApi('/users/1', 'GET').then(res => {
  //     let response = res.data[0];
  //     console.log(response);
  //     dispatch(login(response));
  //   });
  // };
  // const onClickLogin = () => {
  //   useApi('/oauth2/authorization/kakao', 'GET').then(res => {
  //     let response = res.data[0];
  //     console.log(response);
  //     dispatch(login(response));
  //   });
  // };

  return (
    <div className={`${Background.MAIN} flex flex-col justify-center items-center h-screen space-y-40`}>
      <img src={Logo} className="w-3/4" />
      {/* <img src={LoginButton} onClick={onClickLogin} /> */}
      <a href="http://localhost:8080/oauth2/authorization/kakao">
        <img src={LoginButton} />
      </a>
    </div>
  );
};

export default LoginPage;
