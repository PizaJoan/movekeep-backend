const app = require('express')()
const routes = require('./router')
const bodyParser = require('body-parser')
const passport = require('./configuration/passportConfig')

app.use(passport.initialize());

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
app.use('/', routes)

module.exports = app