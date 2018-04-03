const passport = require('passport')
const LocalStrategy = require('passport-local').Strategy
//const BasicStrategy = require('passport-http').BasicStrategy;
const User = require('./../model/user')
//const GoogleStrategy = require('passport-google-oauth20').Strategy;
const properties = require('./index')

/*
passport.serializeUser(function(user, done) {
    done(null, user.googleId);
});

passport.deserializeUser(function(googleId, done) {
    User.findById(googleId, function(err, user) {
        done(err, user);
    });
});


passport.use(new GoogleStrategy({
        clientID: properties.googleId,
        clientSecret: properties.googleSecret,
        callbackURL: "http://localhost:3000/token-google/callback"
    },
    function (accessToken, refreshToken, profile, done) {
        User.findOne({
            'googleId': profile.id
        }, function (err, user) {
            if (err) {
                return done(err);
            }
            if (!user) {
                user = new User({
                    "googleId": profile.id,
                    "name": profile.name.givenName,
                    "username": profile.displayName,
                    "surname": profile.name.familyName,
                    "rols": [{"id": "user"}]
                });
                user.save(function (err, newUser) {
                    if (err) console.log(err);
                    return done(null, newUser);
                });
            } else {
                return done(null, user);
            }
        });
    }
));
*/
passport.use(new LocalStrategy(
    {
        usernameField: 'username',
        passwordField: 'password',
        passReqToCallback: true
    },
    function(req, username, password, done) {
        req.getConnection((err, connection) => {
            User.findOne({ username: username }, connection,(err, user) => {
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
