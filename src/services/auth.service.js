import http from '../http-commons';

const API_URL = 'http://localhost:8080/';

class AuthService {
    login(user) {
        return http.
        post(API_URL + 'login', {
                email: user.email,
                password: user.password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }
                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        return http.post(API_URL + 'register', {
            email: user.email,
            firstName: user.firstName,
            lastName: user.lastName,
            password: user.password
        });
    }
}

export default new AuthService();