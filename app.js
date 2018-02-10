const express = require('express')
const app = express()
const bodyParser = require('body-parser')
const categoriesRouter = require('./routes/index')

app.use(bodyParser.urlencoded({ extended: false }))
app.use('/categories', categoriesRouter)

module.exports = app