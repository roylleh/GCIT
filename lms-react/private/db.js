const Sequelize = require('sequelize');
const sequelize = new Sequelize('mysql://root:password@mysql.cv82vp5r6wmd.us-east-1.rds.amazonaws.com:3306/library', { operatorsAliases: false });

module.exports = { Sequelize, sequelize };