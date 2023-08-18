import React, { useState, useEffect } from 'react';

import axios from 'axios';
import { useDispatch } from 'react-redux';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { login } from '../../global/store';

const AuthProcess = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const navigate = useNavigate();
  const [jwt, setJwt] = useState('');

  let dispatch = useDispatch();

  useEffect(() => {
    const token = searchParams.get('token');
    setJwt('Bearer ' + token);

    jwt &&
      axios({
        url: 'http://i9a304.p.ssafy.io:8080/api/users',
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: jwt,
        },
      }).then(res => {
        res.data.token = jwt;
        dispatch(login(res.data));
        // dispatch(login(response));
        navigate('/');
      });
  }, [jwt]);

  return <div></div>;
};

export default AuthProcess;
