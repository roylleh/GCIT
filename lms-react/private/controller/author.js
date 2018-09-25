const routes = require('express').Router();
const author = require('../model/author');

routes.get('/authors', (req, res) =>
{
    author.findAll().then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.send(result);
    });
});

routes.post('/author', (req, res) =>
{
    author.create({ authorName: req.body.authorName }).then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.status(201);
        res.send(result);
    });
});

routes.put('/authors', (req, res) =>
{
    author.findById(req.body.authorId).then(found => 
    {
        if (found === null)
        {
            res.setHeader('Content-Type', 'application/json');
            res.status(404);
            res.send();
        }
        else
        {
            found.authorName = req.body.authorName;
            found.save().then(result =>
            {
                res.setHeader('Content-Type', 'application/json');
                res.send(result);
            });
        }
    });
});

routes.delete('/authors/:id', (req, res) =>
{
    author.findById(req.params.id).then(found =>
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