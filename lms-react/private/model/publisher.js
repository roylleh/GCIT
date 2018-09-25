const { Sequelize, sequelize } = require('../db');

const Publisher = sequelize.define('tbl_publisher',
    {
        publisherId:
        {
            primaryKey: true,
            type: Sequelize.INTEGER
        },
        publisherName:
        {
            type: Sequelize.STRING
        },
        publisherAddress:
        {
            type: Sequelize.STRING
        },
        publisherPhone:
        {
            type: Sequelize.STRING
        }
    },
    {
        freezeTableName: true,
        timestamps: false
    }
);

module.exports = Publisher;