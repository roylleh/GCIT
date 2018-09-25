const { Sequelize, sequelize } = require('../db');

const Borrower = sequelize.define('tbl_borrower',
    {
        cardNo:
        {
            primaryKey: true,
            type: Sequelize.INTEGER
        },
        name:
        {
            type: Sequelize.STRING
        },
        address:
        {
            type: Sequelize.STRING
        },
        phone:
        {
            type: Sequelize.STRING
        }
    },
    {
        freezeTableName: true,
        timestamps: false
    }
);

module.exports = Borrower;