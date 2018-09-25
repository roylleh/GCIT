const { Sequelize, sequelize } = require('../db');

const Book = sequelize.define('tbl_book',
    {
        bookId:
        {
            primaryKey: true,
            type: Sequelize.INTEGER
        },
        title:
        {
            type: Sequelize.STRING
        },
        authorId:
        {
            type: Sequelize.INTEGER
        },
        publisherId:
        {
            type: Sequelize.INTEGER
        }
    },
    {
        freezeTableName: true,
        timestamps: false
    }
);

module.exports = Book;