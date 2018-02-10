const express = require('express')
const app = express()
const bodyParser = require('body-parser')

app.use(bodyParser.urlencoded({ extended: false }))

app.post('/login', (req, res) => {

    const user = {
        user: req.body.user,
        password: req.body.password
    }

    res.status(200).send({
        user
    })
})

app.listen(3000, (err) => {
    console.log('Començam...')
})