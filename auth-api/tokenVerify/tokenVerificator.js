let jwt = require('jsonwebtoken');
let config = require('../configuration');


function verifytoken(token) {
    if (!token.includes('Bearer')) return
    token = token.replace('Bearer ','');
    try {
        let decoded = jwt.verify(token,config.SECRET_KEY);
        //console.log(decode);
        return decoded
    }catch (err){
        return false
    }

}

function verifyRefreshToken(token) {
    try {
        let decoded = jwt.verify(token,config.SECRET_KEY_REFRESH);
        //console.log(decode);
        return decoded
    }catch (err){
        return false
    }

}

module.exports = {
    access: verifytoken,
    refresh: verifyRefreshToken
};
