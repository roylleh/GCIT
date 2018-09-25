const routes = require('express').Router();
const book = require('../model/book');

routes.get('/books', (req, res) =>
{
    book.findAll().then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.send(result);
    });
});

routes.post('/book', (req, res) =>
{
    book.create({ title: req.body.title, authorId: req.body.authorId, publisherId: req.body.publisherId }).then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.status(201);
        res.send(result);
    });
});

routes.put('/books', (req, res) =>
{
    book.findById(req.body.bookId).then(found => 
    {
        if (found === null)
        {
            res.setHeader('Content-Type', 'application/json');
            res.status(404);
            res.send();
        }
        else
        {
            found.title = req.body.title;
            found.authorId = req.body.authorId;
            found.publisherId = req.body.publisherId;
            found.save().then(result =>
            {
                res.setHeader('Content-Type', 'application/json');
                res.send(result);
            });
        }
    });
});

routes.delete('/books/:id', (req, res) =>
{
    book.findById(req.params.id).then(found =>
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