import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeCigar } from '../../global/store';
import ProfileCard from '../../components/Card/ProfileCard';

import { Text } from '../../global/colors';
import IconButton from '../../components/Button/IconButton';
import icons from '../../global/icons';
import Button from '../../components/Button/Button';
import Select from '../../components/Select/Select';
import useApi from '../../hooks/useApi';

// 회원 정보 페이지
const UserInfoPage = () => {
  const { userRating, nickname, points, email, gender, ageRange, favoriteCigarette } = useSelector(state => state.user);
  // const [selected, setSelected] = useState(0);
  // const [options, setOptions] = useState([]);

  // const dispatch = useDispatch(); // 디스패치 함수 가져오기

  // // 관심 기기목록에 변동이 생기면 Select 태그로 선택가능한 옵션의 목록과 선택 default 값을 반환합니다.
  // useEffect(() => {
  //   useApi('/', 'GET').then(res => {
  //     const optionList = res.data;
  //     cigarList.forEach(cigar => optionList.push({ sendValue: cigar.cigarNo, optionName: cigar.cigarName }));
  //     setOptions(optionList);
  //     setSelected(cigarList[0]['cigarNo']);
  //     const newCigar = selected;
  //     dispatch(changeCigar({ cigar: newCigar }));
  //   });
  // }, [dispatch]); // dispatch 함수가 변경될 때마다 실행

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
        {/* <div className="flex justify-between py-2">
          <div className="flex flex-col">
            <span>선호 담배</span>
            <span className={Text.GRAY}>{favoriteCigarette}</span>
          </div>
          <Select options={options} selected={selected} setSelected={setSelected} />
          <IconButton icon={icons.PEN} />
        </div> */}
        <div className="flex justify-end py-8 space-x-1">
          <Button value="이전" />
          <Button value="회원탈퇴" />
        </div>
      </div>
    </div>
  );
};

export default UserInfoPage;
