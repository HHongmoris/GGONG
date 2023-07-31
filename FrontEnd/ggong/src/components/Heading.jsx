import React from 'react';

// h1 태그에 속성 부여
const Title = ({ content = '제목입니다' }) => {
  return <h1 className="text-3xl font-bold">{content}</h1>;
};

// h2 태그에 속성 부여
const Subtitle = ({ content = '부제목입니다' }) => {
  return <h2 className="text-xl font-bold">{content}</h2>;
};

export { Title, Subtitle };
