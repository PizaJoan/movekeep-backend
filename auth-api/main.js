const app = require('./app');
// const mongoose = require('mongoose');
const config = require('./configuration');
// const User = require('./model/user')

// mongoose.connect(config.db).then(() => {
   app.listen(3000, () => {
  /* let user = new User({
        username: 'jpizaf',
        password: 'hola'
    }) */
   })
//     user.save((err, user) => {
// */
//      console.log('Authentication module listening on port 3000!');
//    //})
//     });
// }, err => {
//     console.log(err)
// });

