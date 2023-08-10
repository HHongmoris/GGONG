import React, { useState, useEffect } from 'react';

import axios from 'axios';
import { useDispatch } from 'react-redux';
import { useSearchParams, useNavigate } from 'react-router-dom';

const AuthProcess = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const navigate = useNavigate();
  const [jwt, setJwt] = useState('');

  let dispatch = useDispatch();

  useEffect(() => {
    const token = searchParams.get('token');
    setJwt('Bearer ' + token);
    console.log(jwt);

    jwt &&
      axios({
        url: 'http://localhost:8080/api/users/jwt-test',
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: jwt,
        },
      })
        .then(res => {
          console.log(res);
          // dispatch(login(response));

          navigate('/');
        })
        .catch(err => console.log(err));
  }, [jwt]);

  return <div></div>;
};

export default AuthProcess;
