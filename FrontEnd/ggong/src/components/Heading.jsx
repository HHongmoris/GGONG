import React from 'react';

// h1 태그에 속성 부여
const Heading1 = ({ content = 'h1' }) => {
  return (
    <div>
      <h1 className="text-2xl font-bold ml-4">{content}</h1>
    </div>
  );
};

// h2 태그에 속성 부여
const Heading2 = ({ content = 'h2' }) => {
  return (
    <div>
      <h2 className="text-xl ml-4">{content}</h2>
    </div>
  );
};

// h3 태그에 속성 부여
const Heading3 = ({ content = 'h3' }) => {
  return (
    <div>
      <h3 className="text-l ml-4">{content}</h3>
    </div>
  );
};

export { Heading1, Heading2, Heading3 };
