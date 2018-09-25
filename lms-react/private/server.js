const bodyParser = require('body-parser');
const express = require('express');
const app = express();

app.use((req, res, next) =>
{
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
    next();
});

app.use(bodyParser.json());

app.use(require('./controller/author'));
app.use(require('./controller/book'));
app.use(require('./controller/bookCopy'));
app.use(require('./controller/bookLoan'));
app.use(require('./controller/borrower'));
app.use(require('./controller/branch'));
app.use(require('./controller/publisher'));

app.listen(8081);