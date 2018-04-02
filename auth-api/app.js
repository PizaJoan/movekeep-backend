const app = require('express')()
const routes = require('./router')
const bodyParser = require('body-parser')
const connection = require('express-myconnection')
const passport = require('./configuration/passportConfig')
const config = require('./configuration')
const mysql = require('mysql')

app.use(passport.initialize());

app.use(connection(mysql, config.mysql.config, 'single'))
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
app.use('/', routes)

module.exports = app