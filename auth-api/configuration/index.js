module.exports = {
    SECRET_KEY: 'xaxopollo1',
    db: 'mongodb://localhost:27017/usersDB',
    expTime: 60 * 60 * 24,
    refreshTokenExpTime: 60 * 60 * 24 * 7,
    SECRET_KEY_REFRESH: 'xaxumTokenizer',
    mysql: {
        config: {
            host: 'localhost',
            user: 'jpizaf',
            password: '123',
            database: 'practica_test2'
        }
    },
    oauth: {
        google: {
            id: '102488523111-o2kkdvo5j4t7isdho5i5h0aipo0hmkdp.apps.googleusercontent.com',
            secret: 'PHGyWUbRV3yjDMPhIJwI1T8b',
            callback: 'http://localhost:3000/token-google/callback',
            clientRedirect: 'http://localhost:8081/#/my-routines/'
        }
    }
};