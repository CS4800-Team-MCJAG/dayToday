import http from '../http-commons';

class AuthService {
    login(user) {
        return http.
        post('/login', {
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
        return http.post('/register', {
            email: user.email,
            firstName: user.firstName,
            lastName: user.lastName,
            password: user.password
        });
    }
}

export default new AuthService();