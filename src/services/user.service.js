import http from '../http-commons';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/';

class UserService {
    getPublicContent() {
        return http.get(API_URL + 'all');
    }

    getUserBoard() {
        return http.get(API_URL + 'user', { headers: authHeader() });
    }
}

export default new UserService();