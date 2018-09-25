const routes = require('express').Router();
const branch = require('../model/branch');

routes.get('/branches', (req, res) =>
{
    branch.findAll().then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.send(result);
    });
});

routes.post('/branch', (req, res) =>
{
    branch.create({ branchName: req.body.branchName, branchAddress: req.body.branchAddress }).then(result =>
    {
        res.setHeader('Content-Type', 'application/json');
        res.status(201);
        res.send(result);
    });
});

routes.put('/branches', (req, res) =>
{
    branch.findById(req.body.branchId).then(found => 
    {
        if (found === null)
        {
            res.setHeader('Content-Type', 'application/json');
            res.status(404);
            res.send();
        }
        else
        {
            found.branchName = req.body.branchName;
            found.branchAddress = req.body.branchAddress;
            found.save().then(result =>
            {
                res.setHeader('Content-Type', 'application/json');
                res.send(result);
            });
        }
    });
});

routes.delete('/branches/:id', (req, res) =>
{
    branch.findById(req.params.id).then(found =>
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