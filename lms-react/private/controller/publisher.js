const routes = require('express').Router();
const publisher = require('../model/publisher');

routes.get('/publishers', (req, res) =>
{
    publisher.findAll().then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.send(result);
    });
});

routes.post('/publisher', (req, res) =>
{
    publisher.create({ publisherName: req.body.publisherName, publisherAddress: req.body.publisherAddress, publisherPhone: req.body.publisherPhone }).then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.status(201);
        res.send(result);
    });
});

routes.put('/publishers', (req, res) =>
{
    publisher.findById(req.body.publisherId).then(found => 
    {
        if (found === null)
        {
            res.setHeader('Content-Type', 'application/json');
            res.status(404);
            res.send();
        }
        else
        {
            found.publisherName = req.body.publisherName;
            found.publisherAddress = req.body.publisherAddress;
            found.publisherPhone = req.body.publisherPhone;
            found.save().then(result =>
            {
                res.setHeader('Content-Type', 'application/json');
                res.send(result);
            });
        }
    });
});

routes.delete('/publishers/:id', (req, res) =>
{
    publisher.findById(req.params.id).then(found =>
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