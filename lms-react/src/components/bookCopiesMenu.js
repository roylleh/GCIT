import React from 'react';
import * as db from '../db';

class BookCopiesMenu extends React.Component
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
            noOfCopies: document.getElementById('noOfCopiesNew').value
        };

        db.postElement('bookCopy', data);
        this.componentDidMount();
    }

    updateElement(bookId, branchId)
    {
        const data =
        {
            bookId: bookId,
            branchId: branchId,
            noOfCopies: document.getElementById('noOfCopies_' + bookId + '_' + branchId).value
        };

        db.putElement(this.props.activeNav, bookId + '_' + branchId, data);
        this.componentDidMount();
    }

    deleteElement(id)
    {
        db.deleteElement(this.props.activeNav + id);
        this.componentDidMount();
    }

    checkout(bookId, branchId)
    {
        let now = new Date();
        let then = new Date();
        then.setDate(now.getDate() + 7);

        const data =
        {
            bookId: bookId,
            branchId: branchId,
            cardNo: 1,
            dateOut: now.toISOString(),
            dueDate: then.toISOString()
        };

        db.postElement('bookLoan', data);
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
                <tr id={'row_' + obj.bookId + '_' + obj.branchId} key={obj.bookId + '_' + obj.branchId}>
                    <th scope="row">{i + 1}</th>
                    <td>{obj.bookId}</td>
                    <td>{obj.branchId}</td>
                    <td><input id={'noOfCopies_' + obj.bookId + '_' + obj.branchId} min="1" max="1000000000" type="number" defaultValue={obj.noOfCopies}></input></td>
                    <td>
                        <div className="btn-group">
                            {
                                (document.cookie === '0' || document.cookie === '2') &&
                                <button type="button" className="btn btn-success" onClick={() => this.updateElement(obj.bookId, obj.branchId)}>Update</button>
                            }
                            {
                                document.cookie === '0' &&
                                <button type="button" className="btn btn-danger" onClick={() => this.deleteElement('/' + obj.bookId + '/' + obj.branchId)}>Delete</button>
                            }
                            {
                                document.cookie === '1' &&
                                <button type="button" className="btn btn-success" onClick={() => this.checkout(obj.bookId, obj.branchId)}>Checkout</button>
                            }
                        </div>
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
                            <th scope="col">Copies</th>
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
                                <td><input id="noOfCopiesNew" min="1" max="1000000000" type="number"></input></td>
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

export default BookCopiesMenu;