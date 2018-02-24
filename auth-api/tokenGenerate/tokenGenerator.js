let jwt = require('jsonwebtoken');
const PROPERTIES = require('../configuration');

function generateToken(user) {

    let userData =
        {
            "name": user.name,
        }

    return jwt.sign(userData,PROPERTIES.SECRET_KEY, {expiresIn:PROPERTIES.expTime});
}

function generateRefreshToken(token) {
    let data = {
        "access_token": token
    };
    return jwt.sign(data,PROPERTIES.SECRET_KEY_REFRESH, {expiresIn:PROPERTIES.refreshTokenExpTime});
}

module.exports = {
    access: generateToken,
    refresh: generateRefreshToken
}