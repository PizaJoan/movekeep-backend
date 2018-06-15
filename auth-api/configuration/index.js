module.exports = {
    SECRET_KEY: 'UJ4iJtsbopnNrlGCnp0U',
    SECRET_KEY_REFRESH: 'SFdwJRqtWwNhxSkZsnFq',
    expTime: 60 * 60 * 24,
    refreshTokenExpTime: 60 * 60 * 24 * 7,
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
            callback: process.env.GOOGLECALLBACK || 'http://localhost:3000/token-google/callback',
            clientRedirect: process.env.CLIENTREDIRECT || 'http://localhost:8081/#/my-routines/'
        }
    }
};