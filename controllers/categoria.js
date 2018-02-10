const Categoria = require('./../model/categoria')

function getCategories(req, res) {
    Categoria.find({}, (err, categories) => {
        //Si falla
        if (err) res.status(500).send(`No hem pogut aconseguir les categories: ${err}`)

        //Tornam les categories si va be
        res.send(categories)
    })
}

function insertCategoria(req, res) {
    const categoria = new Categoria({
        titol: req.body.titol,
        descripcio: req.body.descripcio
    }) 
    categoria.save((err, categoriaGuardada) => {
        if (err) res.status(500).send(`No hem pogut guardar la categoria: ${err}`)

        res.send(categoriaGuardada)
    })
}

function updateCategoria(req, res) {
    Categoria.findByIdAndUpdate(req.params.id, req.body, (err, categoria) => {
        if (err) res.status(500).send(`No s'ha pogut actualitzar la categoria: ${err}`)

        res.send(categoria)
    })
}

function deleteCategoria(req, res) {
    res.send('OK')
}


module.exports = {
    getCategories,
    insertCategoria,
    updateCategoria,
    deleteCategoria
}