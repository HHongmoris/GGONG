import React, { useState, useEffect } from 'react';

import axios from 'axios';
import { useDispatch } from 'react-redux';
import { useLocation, useNavigate, useSearchParams } from 'react-router-dom';

const AuthPage = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const [jwt, setJwt] = useState('');

  let dispatch = useDispatch();

  useEffect(() => {
    const token = searchParams.get('token');
    setJwt('Bearer ' + token);
    // Perform any logic using the token here

    // useApi('/api/users/jwt-test', 'GET').then(res => {
    //   let response = res.data;
    //   console.log(response);
    //   // dispatch(login(response));

    //   // useNavigate('/');
    // });
    console.log(jwt);
    axios({
      url: 'http://localhost:8080/api/users/jwt-test',
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:3000',
        Authorization: jwt,
      },
    }).then(res => {
      console.log(res);
      // dispatch(login(response));

      // useNavigate('/');
    });
  }, [searchParams]);

  return <div></div>;
};

export default AuthPage;
