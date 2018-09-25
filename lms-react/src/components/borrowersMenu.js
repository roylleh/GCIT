import React from 'react';
import * as db from '../db';

class BorrowersMenu extends React.Component
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
            name: document.getElementById('nameNew').value,
            address: document.getElementById('addressNew').value,
            phone: document.getElementById('phoneNew').value
        };

        db.postElement('borrower', data);
        this.componentDidMount();
    }

    updateElement(id)
    {
        const data =
        {
            cardNo: id,
            name: document.getElementById('name_' + id).value,
            address: document.getElementById('address_' + id).value,
            phone: document.getElementById('phone_' + id).value
        };

        db.putElement(this.props.activeNav, id, data);
        this.componentDidMount();
    }

    deleteElement(id)
    {
        db.deleteElement(this.props.activeNav + id);
        this.componentDidMount();
    }

    render()
    {
        if (document.cookie === '1')
        {
            window.location.replace('/home');
            return <div></div>;
        }

        const array = this.state.collection;

        if (array.length > 0)
        {
            const rows = array.map((obj, i) =>
                <tr id={'row_' + obj.cardNo} key={obj.cardNo}>
                    <th scope="row">{i + 1}</th>
                    <td><input id={'name_' + obj.cardNo} size="32" type="text" defaultValue={obj.name}></input></td>
                    <td><input id={'address_' + obj.cardNo} size="32" type="text" defaultValue={obj.address}></input></td>
                    <td><input id={'phone_' + obj.cardNo} size="32" type="text" defaultValue={obj.phone}></input></td>
                    <td>
                        {
                            document.cookie === '0' &&
                            <div className="btn-group">
                                <button type="button" className="btn btn-success" onClick={() => this.updateElement(obj.cardNo)}>Update</button>
                                <button type="button" className="btn btn-danger" onClick={() => this.deleteElement('/' + obj.cardNo)}>Delete</button>
                            </div>
                        }
                    </td>
                </tr>
            );

            return (
                <table className="table table-striped" border="2">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Address</th>
                            <th scope="col">Phone</th>
                            <th scope="col" width="15%"></th>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                        {
                            document.cookie === '0' &&
                            <tr id="borrowerRowNew">
                                <td></td>
                                <td><input id="nameNew" size="32" type="text"></input></td>
                                <td><input id="addressNew" size="32" type="text"></input></td>
                                <td><input id="phoneNew" size="32" type="text"></input></td>
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

export default BorrowersMenu;