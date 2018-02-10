const app = require('./app')
const mongoose = require('mongoose')
const config = require('./config')

mongoose.connect(config.dataBase).then(() => {
    app.listen(config.port, (err) => {
        console.log(`Començam dins el port ${config.port}`)
    })
}, err => {
    console.log(err)
})