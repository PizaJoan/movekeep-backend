const mongoose = require('mongoose')
const Schema = mongoose.Schema
const authenticator = require('../authenticate/authenticator')
const encrypt = require('./../authenticate/passVerify')

const UserSchema = new Schema({
    username: String,
    password: String,
})

UserSchema.methods.validPassword = function (password, cb) {
    encrypt.checkPassword(password, this.password, (err, valid) => {
        return cb(err, valid)
    })
};

UserSchema.pre('save', function(next) {
    let user = this

    if (!user.isModified('password')) return next()

    encrypt.encrypt(user.password, (err, hash) => {
        if (err) return next(err)

        user.password = hash
        next()
    })
})


module.exports = mongoose.model('User', UserSchema)
