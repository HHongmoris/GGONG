import React from 'react';

// 프로필 카드 컴포넌트 : 등급 이미지, 닉네임, 현재 포인트, 큐알 정보를 항목으로 갖는다.
const ProfileCard = ({ grade = '등급', nickname = '닉네임', point = '현재 포인트', qr = 'qr' }) => {
  return (
    <div className="card bg-base-100 shadow-xl flex justify-between border border-zinc-400">
      <div className="flex items-center">
        <img
          // src={`./${grade}`} // 등급 이미지의 경로 (이미지 찾아서 폴더에 넣어놓으면 그걸로 경로 바꿔주면 될 듯)
          src="https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png" // 이건 임시로 그냥 넣어둔 이미지
          alt="Grade"
          className="w-1/6 h-10 object-contain" // 이미지 크기와 마진을 설정 (contain : 이미지의 가로세로비를 유지하면서, 카드 내부에 들어가도록 크기를 맞춤 조절)
        />
        <div className="card-body" style={{ '--padding-card': '1rem' }}>
          <h2 className="card-title">{nickname}</h2>
          <p>{point}</p>
        </div>
        <a
          href="https://chart.apis.google.com/chart?cht=qr&chs=150x150&chl={qr}"
          className="justify-end w-1/6" // 큐알 api 주소 이용해서 적용시켜 놓음. qr을 원하는 값으로 바꾸면 될 듯
        >
          큐알
        </a>
      </div>
    </div>
  );
};

export default ProfileCard;
