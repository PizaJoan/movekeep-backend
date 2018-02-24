//const verify = require('')

function authenticate(password, user) {
    return user.password === password;
}

module.exports = authenticate