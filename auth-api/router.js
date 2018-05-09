const express = require('express');
const cors = require('cors');
const router = express.Router();
router.use(cors({ credentials: true, origin: '*' }));
const verifyToken = require('./tokenVerify/tokenVerificator');
const tokenGenerator = require ('./tokenGenerate/tokenGenerator');
const passport = require('./configuration/passportConfig');
const User = require('./model/user');

/*
router.post('/token-basic', passport.authenticate('basic', {session: false}), function (req, res) {
    res.json(tokenGenerator.access(req.user));
});*/

router.use((req, res, next) => {
    res.header('Access-Control-Expose-Headers', 'Authorization')
    next()
})

router.post('/create-user', (req, res) => {
    req.getConnection((err, connection) => {
        User.findOne({ username: req.body.username }, connection, (err, user) => {
            if (user) res.status(409).json({ message: 'Usuari ja existeix' })
            else {
                let newUser = new User(req.body)
                newUser.save(connection, (err, user) => {
                    if (err) res.status(500).json({ message: 'Alguna cosa no ha funcionat' })
                    else {
                        let token = tokenGenerator.access(user)
                        let refresh = tokenGenerator.refresh(token)
                        res.header("Authorization", "Bearer " + token)
                        res.json(refresh)
                    }
                })
            }
        })
    })
})

router.post('/token-local', passport.authenticate('local', {session: false}), function (req, res) {
    let token = tokenGenerator.access(req.user)
    let refresh = tokenGenerator.refresh(token)
    res.header("authorization", "Bearer " + token)
    res.json(refresh);
});

//router.get('/token-google', passport.authenticate('google', {scope: ['profile']}));

/*router.get('/token-google/callback', passport.authenticate('google', {}), function (req, res) {
    let token = tokenGenerator.access(req.user)
    let refresh = tokenGenerator.refresh(token)
    res.header("Authorization", "Bearer " + token)
    res.json(refresh);
});*/

router.post('/verify-token', function (req, res) {
    let message = verifyToken.access(req.headers.authorization);

    if(!message){
        res.status(401).json('Unauthorized')
    }else {
        res.json('OK');
    }
});

router.post('/refresh-token', function (req, res) {
    if (!req.headers.authorization || !req.body.refreshtoken) res.sendStatus(404)
    else req.getConnection((err, connection) => {
        if (!verifyToken.access(req.headers.authorization)){
            let user = verifyToken.refresh(req.body.refresh);
            if (user) {
                user = JSON.parse(Buffer.from(user.access_token.split(".")[1], 'base64').toString("ascii"))
                User.findOne({ username:user.name }, connection, (err, user) => {
                    if(user){
                        let token = tokenGenerator.access(user)
                        let refresh = tokenGenerator.refresh(token)
                        res.header("Authorization", "Bearer " + token)
                        res.json(refresh);
                    } else {
                        res.sendStatus(401)
                    }
                })
            } else {
                res.sendStatus(401)
            }
        } else {
            res.header("Authorization", req.headers.authorization)
            res.json(req.body.refreshtoken);
        }
    })
});

module.exports = router;

