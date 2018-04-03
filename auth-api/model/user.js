// const mongoose = require('mongoose')
// const Schema = mongoose.Schema
const encrypt = require('./../authenticate/passVerify')

function checkPassword(password, cb) {
    encrypt.checkPassword(password, this.password, (err, valid) => {
        return cb(err, valid)
    })
}

class User {
    
    constructor({ name, username, password }) {
        this.name = name
        this.username = username
        this.password = password
    }

    save(connection, done) {
        encrypt.encrypt(this.password, (err, hash) => {
            if (err) return done(err)
            this.password = hash
            connection.beginTransaction(err => {
                if (err) throw err
                this._saveIntoUser(connection).then(result => {
                    this._saveIntoUserAccounts(connection).then(result => done(null, this)).catch(err => done(err))
                }).catch(err => done(err))
            })
        })
    }

    _saveIntoUser(connection) {
        return new Promise((resolve, reject) => {
            connection.query('INSERT INTO user SET ?', { 
                create_date: new Date(),
                profile_image: null,
                name: this.name,
                username: this.username
            }, (err, result) => {
                if (err) {
                    return connection.rollback(() => {
                        reject(err)
                    })
                } else {
                    this.id = result.insertId
                    resolve(result)
                }
            })
        })  
    }

    _saveIntoUserAccounts(connection) {
        return new Promise((resolve, reject) => {
            connection.query('INSERT INTO user_account SET ?', {
                user_id: this.id,
                username: this.username,
                password: this.password
            }, (err, result) => {
                delete this.id
                if (err) {
                    return connection.rollback(() => {
                        reject(err)
                    })
                } else {
                    connection.commit(err => {
                        if (err) return connection.rollback(() => {
                            reject(err)
                        })
                    })
                    resolve(result)
                }
            })
        })
    }

    static findOne(user, connection, done) {
        connection.query('SELECT * FROM user_account WHERE username = ?', user.username, (err, user) => {
            if (err || !user[0]) return done (err)
            else {
                user[0].validPassword = checkPassword
                return done(null, user[0])
            }
        })
    }
}

// const UserSchema = new Schema({
//     username: String,
//     password: String,
// })

// UserSchema.methods.validPassword = function (password, cb) {
//     encrypt.checkPassword(password, this.password, (err, valid) => {
//         return cb(err, valid)
//     })
// };

// UserSchema.pre('save', function(next) {
//     let user = this

    // if (!user.isModified('password')) return next()

    // encrypt.encrypt(user.password, (err, hash) => {
    //     if (err) return next(err)

    //     user.password = hash
    //     next()
    // })
// })


module.exports = User