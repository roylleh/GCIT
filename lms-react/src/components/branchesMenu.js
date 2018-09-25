import React from 'react';
import * as db from '../db';

class BranchesMenu extends React.Component
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
            branchName: document.getElementById('branchNameNew').value,
            branchAddress: document.getElementById('branchAddressNew').value
        };

        db.postElement('branch', data);
        this.componentDidMount();
    }

    updateElement(id)
    {
        const data =
        {
            branchId: id,
            branchName: document.getElementById('branchName_' + id).value,
            branchAddress: document.getElementById('branchAddress_' + id).value
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
        if (document.cookie === '')
        {
            window.location.replace('/home');
            return <div></div>;
        }

        const array = this.state.collection;

        if (array.length > 0)
        {
            const rows = array.map((obj, i) =>
                <tr id={'row_' + obj.branchId} key={obj.branchId}>
                    <th scope="row">{i + 1}</th>
                    <td><input id={'branchName_' + obj.branchId} size="32" type="text" defaultValue={obj.branchName}></input></td>
                    <td><input id={'branchAddress_' + obj.branchId} size="32" type="text" defaultValue={obj.branchAddress}></input></td>
                    <td>
                        <div className="btn-group">
                            {
                                (document.cookie === '0' || document.cookie === '2') &&
                                <button type="button" className="btn btn-success" onClick={() => this.updateElement(obj.branchId)}>Update</button>
                            }
                            {
                                document.cookie === '0' &&
                                <button type="button" className="btn btn-danger" onClick={() => this.deleteElement('/' + obj.branchId)}>Delete</button>
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
                            <th scope="col">Branch Name</th>
                            <th scope="col">Branch Address</th>
                            <th scope="col" width="15%"></th>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                        {
                            document.cookie === '0' &&
                            <tr id="branchRowNew">
                                <td></td>
                                <td><input id="branchNameNew" size="32" type="text"></input></td>
                                <td><input id="branchAddressNew" size="32" type="text"></input></td>
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

export default BranchesMenu;