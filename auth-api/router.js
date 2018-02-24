let express = require('express');
let cors = require('cors');
let router = express.Router();
router.use(cors({credentials: true, origin: '*'}));
let verifyToken = require('./tokenVerify/tokenVerificator');
let tokenGenerator = require ('./tokenGenerate/tokenGenerator');
let passport = require('./configuration/passportConfig');
let User = require('./model/user');


router.post('/token-basic', passport.authenticate('basic', {session: false}), function (req, res) {
    res.json(tokenGenerator.access(req.user));
});

router.post('/token-local', passport.authenticate('local', {session: false}), function (req, res) {
    let token = tokenGenerator.access(req.user)
    let refresh = tokenGenerator.refresh(token)
    res.header("Authorization", "Bearer " + token)
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

    //if(!verifyToken.access(req.headers.authorization)){
    if(!message){

        res.sendStatus(401)
    }else {
        res.json(message);
    }
});

router.post('/refresh-token', function (req, res) {
    if (!verifyToken.access(req.headers.authorization)){
        let user = verifyToken.refresh(req.body.refreshtoken);
        user = JSON.parse(Buffer.from(user.access_token.split(".")[1], 'base64').toString("ascii"))
       User.findOne({username:user.username},function (err, user) {
           if(user){
               console.log(user)
               let token = tokenGenerator.access(user)
               let refresh = tokenGenerator.refresh(token)
               res.header("Authorization", "Bearer " + token)
               res.json(refresh);
           }else{
               res.sendStatus(401)
           }
        })
    }else{
        res.header("Authorization", req.headers.authorization)
        res.json(req.body.refreshtoken);
    }
});

module.exports = router;

