const { Sequelize, sequelize } = require('../db');

const BookCopy = sequelize.define('tbl_book_copies',
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
        noOfCopies:
        {
            type: Sequelize.INTEGER
        }
    },
    {
        freezeTableName: true,
        timestamps: false
    }
);

module.exports = BookCopy;