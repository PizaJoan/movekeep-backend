const encrypt = require('./../authenticate/passVerify')

function checkPassword(password, cb) {
    encrypt.checkPassword(password, this.password, (err, valid) => {
        return cb(err, valid)
    })
}

class User {
    
    constructor({ name, username, password, profile_image = null }) {
        this.name = name
        this.username = username
        this.password = password
        this.profile_image = profile_image
    }

    save(connection, done) {
        encrypt.encrypt(this.password, (err, hash) => {
            if (err) return done(err)
            this.password = hash
            connection.beginTransaction(async err => {  
                try {
                    if (err) throw err
                    let userSaved = await this._saveIntoUser(connection)
                    let userAccountSaved = await this._saveIntoUserAccounts(connection)
                    done(null, this)
                } catch (e) {
                    done(err)
                }
            })
        })
    }

    _saveIntoUser(connection) {
        return new Promise((resolve, reject) => {
            connection.query('INSERT INTO user SET ?', { 
                create_date: new Date(),
                profile_image: this.profile_image,
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
                username: this.username,
                password: this.password
            }, (err, result) => {
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

module.exports = User