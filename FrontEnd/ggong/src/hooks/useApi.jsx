import axios from 'axios';

// API 서버의 root url
const ROOT_URL = 'http://localhost:8080';

/**
 * 서버와 통신하는 hook 입니다.
 * 전달받은 url, httpMethod로 서버에 요청을 전송합니다.
 *
 * @param {string} url /로 시작하는 url 문자열
 * @param {'GET' | 'POST'} httpMethod 요청 보낼 때 사용할 http메서드
 * @returns API 응답을 포함한 Promise 객체
 */
const useApi = (url, httpMethod = 'GET') =>
  axios({
    // API 서버 URL
    url: ROOT_URL + url,
    // HTTP 메서드
    method: httpMethod,
    // 요청 헤더 설정
    headers: {
      'Content-Type': 'application/json',
    },
  });

export default useApi;
