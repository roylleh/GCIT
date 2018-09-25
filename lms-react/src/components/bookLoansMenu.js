import React from 'react';
import * as db from '../db';

class BookLoansMenu extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = { collection: [] };
    }

    componentDidMount()
    {
        if (this.interval === undefined)
            this.interval = setInterval(() => db.getCollection(this.props.activeNav).then(array => this.setState({ collection: array })).catch(), 500);
    }

    componentDidUpdate()
    {
        db.getCollection(this.props.activeNav).then(array =>
        {
            if (this.state.collection.length !== array.length)
                this.setState({ collection: array });
        });
    }

    componentWillMount()
    {
        db.getCollection(this.props.activeNav).then(array => this.setState({ collection: array }));
    }

    componentWillUnmount()
    {
        clearInterval(this.interval);
    }

    componentWillUpdate()
    {
        db.getCollection(this.props.activeNav).then(array =>
        {
            if (this.state.collection.length !== array.length)
                this.setState({ collection: array });
        });
    }

    addElement()
    {
        const data =
        {
            bookId: document.getElementById('bookIdNew').value,
            branchId: document.getElementById('branchIdNew').value,
            cardNo: document.getElementById('cardNoNew').value,
            dateOut: document.getElementById('dateOutNew').value,
            dueDate: document.getElementById('dueDateNew').value
        };

        db.postElement('bookLoan', data);
        this.componentDidMount();
    }

    updateElement(bookId, branchId, cardNo)
    {
        const data =
        {
            bookId: bookId,
            branchId: branchId,
            cardNo: cardNo,
            dateOut: document.getElementById('dateOut_' + bookId + '_' + branchId + '_' + cardNo).value,
            dueDate: document.getElementById('dueDate_' + bookId + '_' + branchId + '_' + cardNo).value
        };

        db.putElement(this.props.activeNav, bookId + '_' + branchId + '_' + cardNo, data);
        this.componentDidMount();
    }

    deleteElement(id)
    {
        db.deleteElement(this.props.activeNav + id);
        this.componentDidMount();
    }

    render()
    {
        if (document.cookie === '')
        {
            window.location.replace('/home');
            return <div></div>;
        }

        const array = this.state.collection;

        if (array.length > 0)
        {
            const rows = array.map((obj, i) =>
                <tr id={'row_' + obj.bookId + '_' + obj.branchId + '_' + obj.cardNo} key={obj.bookId + '_' + obj.branchId + '_' + obj.cardNo}>
                    <th scope="row">{i + 1}</th>
                    <td>{obj.bookId}</td>
                    <td>{obj.branchId}</td>
                    {(document.cookie === '0' || document.cookie === '2') && <td>{obj.cardNo}</td>}
                    <td><input id={'dateOut_' + obj.bookId + '_' + obj.branchId + '_' + obj.cardNo} size="32" type="text" defaultValue={obj.dateOut}></input></td>
                    <td><input id={'dueDate_' + obj.bookId + '_' + obj.branchId + '_' + obj.cardNo} size="32" type="text" defaultValue={obj.dueDate}></input></td>
                    <td>
                        {
                            document.cookie === '0' &&
                            <div className="btn-group">
                                <button type="button" className="btn btn-success" onClick={() => this.updateElement(obj.bookId, obj.branchId, obj.cardNo)}>Update</button>
                                <button type="button" className="btn btn-danger" onClick={() => this.deleteElement('/' + obj.bookId + '/' + obj.branchId + '/' + obj.cardNo)}>Delete</button>
                            </div>
                        }
                        {
                            document.cookie === '1' &&
                            <button type="button" className="btn btn-info" onClick={() => this.deleteElement('/' + obj.bookId + '/' + obj.branchId + '/' + obj.cardNo)}>Return</button>
                        }
                    </td>
                </tr>
            );

            return (
                <table className="table table-striped" border="2">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Book ID</th>
                            <th scope="col">Branch ID</th>
                            {(document.cookie === '0' || document.cookie === '2') && <th scope="col">Card #</th>}
                            <th scope="col">Date Out</th>
                            <th scope="col">Due Date</th>
                            <th scope="col" width="15%"></th>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                        {
                            document.cookie === '0' &&
                            <tr id="bookCopyRowNew">
                                <td></td>
                                <td><input id="bookIdNew" min="1" max="1000000000" type="number"></input></td>
                                <td><input id="branchIdNew" min="1" max="1000000000" type="number"></input></td>
                                <td><input id="cardNoNew" min="1" max="1000000000" type="number"></input></td>
                                <td><input id="dateOutNew" size="32" type="text"></input></td>
                                <td><input id="dueDateNew" size="32" type="text"></input></td>
                                <td><button type="button" className="btn btn-warning" onClick={() => this.addElement()}>Add</button></td>
                            </tr>
                        }
                    </tbody>
                </table>
            );
        }

        return <div id="loading">Loading...</div>;
    }
}

export default BookLoansMenu;