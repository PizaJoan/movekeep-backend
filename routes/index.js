const express = require('express')
const categoriaRutes = express.Router()
const categoriesController = require('./../controllers/categoria')

categoriaRutes.get('/list', categoriesController.getCategories)
categoriaRutes.post('/insert', categoriesController.insertCategoria)
categoriaRutes.put('/update/:id', categoriesController.updateCategoria)
categoriaRutes.delete('/delete/:id', categoriesController.deleteCategoria)

module.exports = categoriaRutes