import React from 'react';
import Button from '../Button/Button';
import { Background } from '../../global/colors';

/**
 * 모달창의 내용 컴포넌트를 반환하는 함수
 *
 * @param {boolean} open 현재 컴포넌트가 보이는지 여부
 * @param {Function} toggleVisible open의 상태를 변경하는 함수
 * @returns 모달창 내용 컴포넌트
 */
const QRModal = ({ open, toggleVisible, qr }) => {
  const bgColor = Background['WHITE'];

  return (
    <div>
      {open && (
        <div className="absolute left-[-21px] top-[-5rem] w-screen h-screen flex justify-center items-center">
          {/* 그림자 영역 */}
          <div className={'absolute w-full h-full opacity-70 bg-black z-40'} onClick={toggleVisible} />
          {/* QR 영역 */}
          <div className={`card w-80 ${bgColor} z-40`}>
            <div className="card-body">
              <img
                src={`https://chart.apis.google.com/chart?cht=qr&chs=250x250&chl=${qr}`}
                // 큐알 api 주소 이용해서 적용시켜 놓음. qr을 원하는 값으로 바꾸면 될 듯
              />
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default QRModal;
