import React, { useState } from 'react';
import { Subtitle } from '../Heading';
import Button from '../Button/Button';

const ModalContent = ({ open, toggleVisible }) => {
  // TODO: 상품 구매로직 추가하기
  const buyProduct = () => {};

  return (
    <div>
      {open && (
        <div className="absolute left-0 top-0 w-full h-full flex justify-center items-center">
          <div className="absolute left-0 top-0 w-full h-full opacity-50 bg-gray-400"></div>
          {/* 구매 안내문 영역 */}
          <div className="card w-80 bg-white">
            <div className="card-body">
              {/* 안내문 */}
              <Subtitle content="상품권 구매" />
              <p className="mt-3 mb-7">해당 상품권을 구매하시겠습니까?</p>
              {/* 구매, 취소 버튼 */}
              <div className="card-actions justify-end">
                <Button value="구매" handleClick={buyProduct} />
                <Button value="취소" handleClick={toggleVisible} color="LIGHT" />
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ModalContent;
