import React from 'react';
import * as db from '../db';

class BooksMenu extends React.Component
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
            title: document.getElementById('titleNew').value,
            authorId: document.getElementById('authorIdNew').value,
            publisherId: document.getElementById('publisherIdNew').value
        };

        db.postElement('book', data);
        this.componentDidMount();
    }

    updateElement(id)
    {
        const data =
        {
            bookId: id,
            title: document.getElementById('title_' + id).value,
            authorId: document.getElementById('authorId_' + id).value,
            publisherId: document.getElementById('publisherId_' + id).value
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
                <tr id={'row_' + obj.bookId} key={obj.bookId}>
                    <th scope="row">{i + 1}</th>
                    <td><input id={'title_' + obj.bookId} size="32" type="text" defaultValue={obj.title}></input></td>
                    <td><input id={'authorId_' + obj.bookId} min="1" max="1000000000" type="number" defaultValue={obj.authorId}></input></td>
                    <td><input id={'publisherId_' + obj.bookId} min="1" max="1000000000" type="number" defaultValue={obj.publisherId}></input></td>
                    <td>
                        {
                            document.cookie === '0' &&
                            <div className="btn-group">
                                <button type="button" className="btn btn-success" onClick={() => this.updateElement(obj.bookId)}>Update</button>
                                <button type="button" className="btn btn-danger" onClick={() => this.deleteElement('/' + obj.bookId)}>Delete</button>
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
                            <th scope="col">Title</th>
                            <th scope="col">Author</th>
                            <th scope="col">Publisher</th>
                            <th scope="col" width="15%"></th>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                        {
                            document.cookie === '0' &&
                            <tr id="bookRowNew">
                                <td></td>
                                <td><input id="titleNew" size="32" type="text"></input></td>
                                <td><input id="authorIdNew" min="1" max="1000000000" type="number"></input></td>
                                <td><input id="publisherIdNew" min="1" max="1000000000" type="number"></input></td>
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

export default BooksMenu;