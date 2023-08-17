import React from 'react';

import QRModal from '../Modal/QRModal';

// 프로필 카드 컴포넌트 : 등급 이미지, 닉네임, 현재 포인트, 큐알 정보, 큐알을 노출시킬지 여부를 항목으로 갖는다.
const ProfileCard = ({ grade, nickname = '닉네임', point, qr = 'qr', isVisible = false }) => {
  return (
    <div className="card bg-white border border-zinc-400">
      <div className="flex justify-between ml-2 mr-5">
        <div className="flex items-center">
          <div className="card-body" style={{ '--padding-card': '1rem' }}>
            <div className="flex space-x-3">
              <img
                src={`${process.env.PUBLIC_URL}/grade/${grade ? grade : 'seed'}.png`} // 등급 이미지의 경로 (이미지 찾아서 폴더에 넣어놓으면 그걸로 경로 바꿔주면 될 듯)
                alt="Grade"
                className="w-7 h-7 object-contain rounded-xl" // 이미지 크기와 마진을 설정 (contain : 이미지의 가로세로비를 유지하면서, 카드 내부에 들어가도록 크기를 맞춤 조절)
              />
              <h2 className="card-title">{nickname}</h2>
            </div>
            <div>
              <span>보유 포인트 : </span>
              <span>{point ? point : 0} p</span>
            </div>
          </div>
        </div>
        <div className={`flex items-center ${isVisible ? '' : 'invisible'}`}>
          {/* 아래는 모달창 컴포넌트 */}
          <QRModal qr={qr} />
        </div>
      </div>
    </div>
  );
};

export default ProfileCard;
