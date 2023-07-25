module.exports = {
  root: true,
  env: {
    browser: true,
    es6: true,
  },
  extends: [],
  //lintrc
  parserOptions: {
    ecmaVersion: 6,
    sourceType: 'module',
    ecmaFeatures: {
      jsx: true,
    },
  },
  rules: {
    indent: ['error', 2],
    quotes: ['error', 'single'],
    semi: ['error', 'always'],
    'linebreak-style': 0,
    'no-unused-vars': 'off',
  },
};
