import { configureStore, createSlice } from '@reduxjs/toolkit';

// 로그인한 유저의 정보를 저장하고 있는 객체입니다.
let user = createSlice({
  name: 'user',
  initialState: {
    userNo: -1,
    email: '',
    nickname: '',
    userRating: '',
    ageRange: '',
    gender: '',
    favoriteCigarette: '',
    QR: '',
    points: 0,
  },
  // login과 changeCigar, chnagePoint를 통해 user 객체의 데이터를 변경할 수 있습니다.
  reducers: {
    login(state, action) {
      state.userNo = action.payload.userNo;
      state.email = action.payload.email;
      state.nickname = action.payload.nickname;
      state.userRating = action.payload.userRating;
      state.ageRange = action.payload.ageRange;
      state.gender = action.payload.gender;
      state.favoriteCigarette = action.payload.favoriteCigarette;
      state.QR = action.payload.QR;
      state.points = action.payload.points;
    },
    changeCigar(state, action) {
      state.favoriteCigarette = action.payload.favoriteCigarette;
    },
    changePoint(state, action) {
      state.points = action.payload.points;
    },
  },
});

export let { login, changeCigar, changePoint } = user.actions;

export default configureStore({
  reducer: {
    user: user.reducer,
  },
});
