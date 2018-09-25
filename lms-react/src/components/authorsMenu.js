import React from 'react';
import * as db from '../db';

class AuthorsMenu extends React.Component
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
            authorName: document.getElementById('authorNameNew').value
        };

        db.postElement('author', data);
        this.componentDidMount();
    }

    updateElement(id)
    {
        const data =
        {
            authorId: id,
            authorName: document.getElementById('authorName_' + id).value
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
                <tr id={'row_' + obj.authorId} key={obj.authorId}>
                    <th scope="row">{i + 1}</th>
                    <td><input id={'authorName_' + obj.authorId} size="32" type="text" defaultValue={obj.authorName}></input></td>
                    <td>
                        {
                            document.cookie === '0' &&
                            <div className="btn-group">
                                <button type="button" className="btn btn-success" onClick={() => this.updateElement(obj.authorId)}>Update</button>
                                <button type="button" className="btn btn-danger" onClick={() => this.deleteElement('/' + obj.authorId)}>Delete</button>
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
                            <th scope="col" width="15%"></th>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                        {
                            document.cookie === '0' &&
                            <tr id="authorRowNew">
                                <td></td>
                                <td><input id="authorNameNew" size="32" type="text"></input></td>
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

export default AuthorsMenu;