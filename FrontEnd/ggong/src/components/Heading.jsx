import React from 'react';

const Heading1 = ({ content = 'h1' }) => {
  return (
    <div>
      <h1 className="text-2xl font-bold ml-4">{content}</h1>
    </div>
  );
};

const Heading2 = ({ content = 'h2' }) => {
  return (
    <div>
      <h2 className="text-xl ml-4">{content}</h2>
    </div>
  );
};

const Heading3 = ({ content = 'h3' }) => {
  return (
    <div>
      <h3 className="text-l ml-4">{content}</h3>
    </div>
  );
};

export { Heading1, Heading2, Heading3 };
