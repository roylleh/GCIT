const routes = require('express').Router();
const borrower = require('../model/borrower');

routes.get('/borrowers', (req, res) =>
{
    borrower.findAll().then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.send(result);
    });
});

routes.post('/borrower', (req, res) =>
{
    borrower.create({ name: req.body.name, address: req.body.address, phone: req.body.phone }).then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.status(201);
        res.send(result);
    });
});

routes.put('/borrowers', (req, res) =>
{
    borrower.findById(req.body.cardNo).then(found => 
    {
        if (found === null)
        {
            res.setHeader('Content-Type', 'application/json');
            res.status(404);
            res.send();
        }
        else
        {
            found.name = req.body.name;
            found.address = req.body.address;
            found.phone = req.body.phone;
            found.save().then(result =>
            {
                res.setHeader('Content-Type', 'application/json');
                res.send(result);
            });
        }
    });
});

routes.delete('/borrowers/:id', (req, res) =>
{
    borrower.findById(req.params.id).then(found =>
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