const routes = require('express').Router();
const bookCopy = require('../model/bookCopy');

routes.get('/bookCopies', (req, res) =>
{
    bookCopy.findAll().then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.send(result);
    });
});

routes.post('/bookCopy', (req, res) =>
{
    bookCopy.create({ bookId: req.body.bookId, branchId: req.body.branchId, noOfCopies: req.body.noOfCopies }).then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.status(201);
        res.send(result);
    });
});

routes.put('/bookCopies', (req, res) =>
{
    bookCopy.findOne({ where: { bookId: req.body.bookId, branchId: req.body.branchId } }).then(found =>
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
            found.noOfCopies = req.body.noOfCopies;
            found.save().then(result =>
            {
                res.setHeader('Content-Type', 'application/json');
                res.send(result);
            });
        }
    });
});

routes.delete('/bookCopies/:bookId/:branchId', (req, res) =>
{
    bookCopy.findOne({ where: { bookId: req.params.bookId, branchId: req.params.branchId } }).then(found =>
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