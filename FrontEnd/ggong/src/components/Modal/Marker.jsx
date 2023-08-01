import React, { useState } from 'react';

import icons from '../../global/icons';

import MarkerContent from './MarkerContent';
import IconButton from '../Button/IconButton';

/**
 * 지도 페이지에서 기기 정보를 볼 수 있는 모달 형태의 마커
 *
 * @returns
 */
const Marker = () => {
  // 모달창이 보이는 상태를 저장하는 변수
  const [visible, setVisible] = useState(false);

  // visible의 상태를 토글하는 함수
  const toggleVisible = () => {
    setVisible(!visible);
  };

  return (
    <div>
      {/* 기기 상세를 열 수 있는 버튼 */}
      <IconButton icon={icons.CIGAR} onClick={toggleVisible} />
      {/* visible이 true이면 기기 상세가 보임 */}
      {visible && <MarkerContent open={visible} toggleVisible={toggleVisible} />}
    </div>
  );
};

export default Marker;
