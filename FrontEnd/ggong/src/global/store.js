import { configureStore, createSlice } from '@reduxjs/toolkit';

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
  },
});

export let { login, changeCigar } = user.actions;

export default configureStore({
  reducer: {
    user: user.reducer,
  },
});
