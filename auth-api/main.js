const app = require('./app')
const config = require('./configuration')


app.listen(3000, () => {
    console.log('Starting!')
    console.log('Client redirect URL: ', config.oauth.google.clientRedirect)
    console.log('Callback URL: ', config.oauth.google.callback)
})
