import React from 'react';

import { Title, Subtitle } from '../../components/Heading';
import Select from '../../components/Select/Select';
import Button from '../../components/Button/Button';

// 회원가입에 필요한 추가 정보를 입력받는 페이지
const AdditionalPage = () => {
  return (
    <div className="flex flex-col justify-around items-center h-screen">
      <div className="flex flex-col items-center justify-end text-center h-1/3 w-3/4">
        <Title content="추가 정보 입력" spacing={true} />
        <Subtitle content="선호하는 담배 종류를 선택해주세요." />
      </div>
      <div className="flex justify-center items-center h-1/3 w-3/4">
        <Select />
      </div>
      <div className="flex justify-end h-1/3 w-3/4">
        <Button value="회원가입" />
      </div>
    </div>
  );
};

export default AdditionalPage;
