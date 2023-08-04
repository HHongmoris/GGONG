import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeCigar } from '../../global/store';
import ProfileCard from '../../components/Card/ProfileCard';

import { Text } from '../../global/colors';
import IconButton from '../../components/Button/IconButton';
import icons from '../../global/icons';
import Button from '../../components/Button/Button';

// 회원 정보 페이지
const UserInfoPage = () => {
  let { userRating, nickname, points, email, gender, ageRange, favoriteCigarette } = useSelector(state => state.user);

  let dispatch = useDispatch();

  return (
    <div>
      <div className="mb-16">
        <ProfileCard grade={userRating} nickname={nickname} point={points} />
      </div>
      <div className="divide-y-2">
        <div className="flex flex-col py-2">
          <span>e-mail</span>
          <span className={Text.GRAY}>{email}</span>
        </div>
        <div className="flex flex-col py-2">
          <span>성별</span>
          <span className={Text.GRAY}>{gender}</span>
        </div>
        <div className="flex flex-col py-2">
          <span>연령대</span>
          <span className={Text.GRAY}>{ageRange}</span>
        </div>
        <div className="flex justify-between py-2">
          <div className="flex flex-col">
            <span>선호 담배</span>
            <span className={Text.GRAY}>{favoriteCigarette}</span>
          </div>
          <IconButton icon={icons.PEN} />
        </div>
        <div className="flex justify-end py-8 space-x-1">
          <Button value="이전" />
          <Button value="회원탈퇴" />
        </div>
      </div>
    </div>
  );
};

export default UserInfoPage;
