const passport = require('passport')
const LocalStrategy = require('passport-local').Strategy
//const BasicStrategy = require('passport-http').BasicStrategy;
const User = require('./../model/user')
const GoogleStrategy = require('passport-google-oauth20').Strategy;
const properties = require('./index')

passport.serializeUser(function(user, done) {
    done(null, user);
});

passport.use(new GoogleStrategy({
        clientID: properties.oauth.google.id,
        clientSecret: properties.oauth.google.secret,
        callbackURL: properties.oauth.google.callback,
        passReqToCallback: true
    },
    function (req, accessToken, refreshToken, profile, done) {
        let username = (profile.name.familyName + profile.name.givenName).toLowerCase()
        req.getConnection((err, connection) => {
            User.findOne({ username: username }, connection, (err, user) => {
                if (err) {
                    return done(err)
                } else if (!user) {
                    let name = profile.displayName
                    let password = profile.id
                    let profile_image = profile.photos[0].value
                    let newUser = new User({ name, username, password, profile_image })
                    return newUser.save(connection, (error, user) => {
                        if (user) return done(null, user)
                        else return done(error, false)
                    })
                } else {
                    return done(null, user)
                }
            })
        })
    }
));

passport.use(new LocalStrategy(
    {
        usernameField: 'username',
        passwordField: 'password',
        passReqToCallback: true
    },
    function(req, username, password, done) {
        req.getConnection((err, connection) => {
            User.findOne({ username: username }, connection, (err, user) => {
                    if (err) {
                        return done(err)
                    } else if (!user) {
                        return done(null, false)
                    } else {
                        user.validPassword(password, (err, isValid) => {
                            if (err || !isValid) return done(err)
                            return done(null, user)
                        })
                    }
                }
            )
        })
    }
))
/*
passport.use(new BasicStrategy(
    function (username, password, done) {
        User.findOne({'username': username}, function (err, user) {
            if (err) {
                return done(err);
            }
            if (!user || !user.validPassword(password)) {
                return done(null, false);
            }
            return done(null, user);
        });
    }
));
*/
module.exports = passport
