const { Sequelize, sequelize } = require('../db');

const Author = sequelize.define('tbl_author',
    {
        authorId:
        {
            primaryKey: true,
            type: Sequelize.INTEGER
        },
        authorName:
        {
            type: Sequelize.STRING
        }
    },
    {
        freezeTableName: true,
        timestamps: false
    }
);

module.exports = Author;