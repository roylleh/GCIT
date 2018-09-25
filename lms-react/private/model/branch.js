const { Sequelize, sequelize } = require('../db');

const Branch = sequelize.define('tbl_library_branch',
    {
        branchId:
        {
            primaryKey: true,
            type: Sequelize.INTEGER
        },
        branchName:
        {
            type: Sequelize.STRING
        },
        branchAddress:
        {
            type: Sequelize.STRING
        }
    },
    {
        freezeTableName: true,
        timestamps: false
    }
);

module.exports = Branch;