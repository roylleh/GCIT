const { Sequelize, sequelize } = require('../db');

const BookLoan = sequelize.define('tbl_book_loans',
    {
        bookId:
        {
            primaryKey: true,
            type: Sequelize.INTEGER
        },
        branchId:
        {
            primaryKey: true,
            type: Sequelize.INTEGER
        },
        cardNo:
        {
            primaryKey: true,
            type: Sequelize.INTEGER
        },
        dateOut:
        {
            type: Sequelize.STRING
        },
        dueDate:
        {
            type: Sequelize.STRING
        }
    },
    {
        freezeTableName: true,
        timestamps: false
    }
);

module.exports = BookLoan;