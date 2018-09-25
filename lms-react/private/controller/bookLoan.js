const routes = require('express').Router();
const bookLoan = require('../model/bookLoan');

routes.get('/bookLoans', (req, res) =>
{
    bookLoan.findAll().then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.send(result);
    });
});

routes.get('/bookLoans/cardNo/:cardNo', (req, res) =>
{
    bookLoan.findAll({ where: { cardNo: req.params.cardNo } }).then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.send(result);
    });
});

routes.post('/bookLoan', (req, res) =>
{
    bookLoan.create({ bookId: req.body.bookId, branchId: req.body.branchId, cardNo: req.body.cardNo, dateOut: req.body.dateOut, dueDate: req.body.dueDate }).then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.status(201);
        res.send(result);
    });
});

routes.put('/bookLoans', (req, res) =>
{
    bookLoan.findOne({ where: { bookId: req.body.bookId, branchId: req.body.branchId, cardNo: req.body.cardNo } }).then(found =>
    {
        if (found === null)
        {
            res.setHeader('Content-Type', 'application/json');
            res.status(404);
            res.send();
        }
        else
        {
            found.bookId = req.body.bookId;
            found.branchId = req.body.branchId;
            found.cardNo = req.body.cardNo;
            found.dateOut = req.body.dateOut;
            found.dueDate = req.body.dueDate;
            found.save().then(result =>
            {
                res.setHeader('Content-Type', 'application/json');
                res.send(result);
            });
        }
    });
});

routes.delete('/bookLoans/:bookId/:branchId/:cardNo', (req, res) =>
{
    bookLoan.findOne({ where: { bookId: req.params.bookId, branchId: req.params.branchId, cardNo: req.params.cardNo } }).then(found =>
    {
        if (found === null)
        {
            res.setHeader('Content-Type', 'application/json');
            res.status(404);
            res.send();
        }
        else
        {
            found.destroy().then(() =>
            {
                res.setHeader('Content-Type', 'application/json');
                res.status(204);
                res.send();
            });
        }
    });
});

module.exports = routes;