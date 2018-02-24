let jwt = require('jsonwebtoken');
let config = require('../configuration');


function verifytoken(token, decrypt) {
    token = token.replace('Bearer ','');
    try {
        let decode = jwt.verify(token,config.SECRET_KEY);
        console.log(decode);
        return decode
    }catch (err){
        return false
    }

}

function verifyRefreshToken(token) {
    try {
        let decode = jwt.verify(token,config.SECRET_KEY_REFRESH);
        console.log(decode);
        return decode
    }catch (err){
        return false
    }

}

module.exports = {
    access: verifytoken,
    refresh: verifyRefreshToken
};
