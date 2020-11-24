import http from '../http-commons';
import authHeader from './auth-header';

class UserService {
    getPublicContent() {
        return http.get('/all');
    }

    getUserBoard() {
        return http.get('/events', { headers: authHeader() });
    }
}

export default new UserService();