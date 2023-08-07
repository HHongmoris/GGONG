import {
  LiaBarsSolid,
  LiaChartBarSolid,
  LiaCoinsSolid,
  LiaHeart,
  LiaHeartSolid,
  LiaMap,
  LiaPenSolid,
  LiaPlusSolid,
  LiaQrcodeSolid,
  LiaSmokingSolid,
  LiaTimesSolid,
  LiaUserSolid,
  LiaVoteYeaSolid,
  LiaSearchSolid,
} from 'react-icons/lia';

export default {
  PLUS: <LiaPlusSolid />, // + 아이콘
  CANCEL: <LiaTimesSolid />, // X 아이콘

  HEART: <LiaHeart size={24} color="#ef4444" />, // 비워진 하트 아이콘
  HEART_FILL: <LiaHeartSolid size={24} color="#ef4444" />, // 채워진 하트 아이콘
  QR: <LiaQrcodeSolid size={40} />, // QR 아이콘
  PEN: <LiaPenSolid size={20} />, // 수정 아이콘
  SEARCH: <LiaSearchSolid />, // 검색 아이콘

  MENU: <LiaBarsSolid color="#ffffff" size={24} />, // 메뉴바 아이콘
  USER: <LiaUserSolid />, // 회원 아이콘
  MAP: <LiaMap />, // 지도 아이콘
  CIGAR: <LiaSmokingSolid />, // 담배 아이콘
  POINT: <LiaCoinsSolid />, // 포인트 아이콘
  VOTE: <LiaVoteYeaSolid />, // 투표 아이콘
  STAT: <LiaChartBarSolid />, // 통계 아이콘
};
