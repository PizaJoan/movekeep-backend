const mongoose = require('mongoose')
const Schema = mongoose.Schema

const Categoria = new Schema({
    titol: String,
    descripcio: String
})

module.exports = mongoose.model('Categoria', Categoria)

