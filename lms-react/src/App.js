import React from 'react';
import { Link, Redirect, Route, Switch } from 'react-router-dom';
import AuthorsMenu from './components/authorsMenu';
import BookCopiesMenu from './components/bookCopiesMenu';
import BookLoansMenu from './components/bookLoansMenu';
import BooksMenu from './components/booksMenu';
import BorrowersMenu from './components/borrowersMenu';
import BranchesMenu from './components/branchesMenu';
import PublishersMenu from './components/publishersMenu';
import logo from './logo.svg';
import './App.css';

class Header extends React.Component
{
    render()
    {
        const activeNav = this.props.activeNav;
        let navs =
        {
            home: <Link to="home" className="nav-item nav-link">Home</Link>,
            authors: <Link to="authors" className="nav-item nav-link">Authors</Link>,
            bookCopies: <Link to="bookCopies" className="nav-item nav-link">Book Copies</Link>,
            bookLoans: <Link to="bookLoans" className="nav-item nav-link">Book Loans</Link>,
            books: <Link to="books" className="nav-item nav-link">Books</Link>,
            borrowers: <Link to="borrowers" className="nav-item nav-link">Borrowers</Link>,
            branches: <Link to="branches" className="nav-item nav-link">Branches</Link>,
            publishers: <Link to="publishers" className="nav-item nav-link">Publishers</Link>
        };
        navs[activeNav] = <Link to={activeNav} className="nav-item nav-link active">{activeNav[0].toUpperCase() + activeNav.slice(1)}</Link>;

        return (
            <nav className="navbar fixed-top navbar-expand-md navbar-dark bg-dark">
                <span className="navbar-brand">
                    <img alt="" className="react-logo" src={logo} />
                    Library Management
                </span>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon" />
                </button>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div className="navbar-nav mr-auto">
                        {navs.home}
                        {document.cookie !== '' && navs.authors}
                        {document.cookie !== '' && navs.books}
                        {document.cookie !== '' && navs.bookCopies}
                        {document.cookie !== '' && navs.bookLoans}
                        {(document.cookie === '0' || document.cookie === '2') && navs.borrowers}
                        {document.cookie !== '' && navs.branches}
                        {document.cookie !== '' && navs.publishers}
                    </div>
                    <Link className="nav-item nav-text" to="login">Login</Link>
                </div>
            </nav>
        );
    }
}

class Home extends React.Component
{
    setUser(userId)
    {
        document.cookie = userId;
        window.location.reload(true);
    }

    render()
    {
        const userId = document.cookie;
        const buttons = Array(3).fill('btn btn-secondary');
        buttons[userId] = 'btn btn-secondary active';

        return (
            <div>
                <h1>Hello, what kind of user are you?</h1>
                <br></br>
                <div className="btn-group btn-group-toggle" data-toggle="buttons">
                    <label className={buttons[0]} onClick={() => this.setUser(0)}>
                        <input type="radio" name="options" id="option1" autoComplete="off"></input>Administrator
                    </label>
                    <label className={buttons[1]} onClick={() => this.setUser(1)}>
                        <input type="radio" name="options" id="option2" autoComplete="off"></input>Borrower
                    </label>
                    <label className={buttons[2]} onClick={() => this.setUser(2)}>
                        <input type="radio" name="options" id="option3" autoComplete="off"></input>Librarian
                    </label>
                </div>
            </div >
        );
    }
}

class App extends React.Component
{
    render()
    {
        return (
            <div className="container-fluid" >
                <Switch>
                    <Route exact path="/" render={(props) => <Redirect {...props} to="/home" />} />
                    <Route exact path="/home" render={(props) => <Header {...props} activeNav="home" />} />
                    <Route exact path="/authors" render={(props) => <Header {...props} activeNav="authors" />} />
                    <Route exact path="/books" render={(props) => <Header {...props} activeNav="books" />} />
                    <Route exact path="/bookCopies" render={(props) => <Header {...props} activeNav="bookCopies" />} />
                    <Route exact path="/bookLoans" render={(props) => <Header {...props} activeNav="bookLoans" />} />
                    <Route exact path="/borrowers" render={(props) => <Header {...props} activeNav="borrowers" />} />
                    <Route exact path="/branches" render={(props) => <Header {...props} activeNav="branches" />} />
                    <Route exact path="/publishers" render={(props) => <Header {...props} activeNav="publishers" />} />
                </Switch>
                <div id="lms-container">
                    <Switch>
                        <Route exact path="/" render={(props) => <Redirect {...props} to="/home" />} />
                        <Route exact path="/home" render={(props) => <Home {...props} activeNav="home" />} />
                        <Route exact path="/authors" render={(props) => <AuthorsMenu {...props} activeNav="authors" />} />
                        <Route exact path="/books" render={(props) => <BooksMenu {...props} activeNav="books" />} />
                        <Route exact path="/bookCopies" render={(props) => <BookCopiesMenu {...props} activeNav="bookCopies" />} />
                        <Route exact path="/bookLoans" render={(props) => <BookLoansMenu {...props} activeNav="bookLoans" />} />
                        <Route exact path="/borrowers" render={(props) => <BorrowersMenu {...props} activeNav="borrowers" />} />
                        <Route exact path="/branches" render={(props) => <BranchesMenu {...props} activeNav="branches" />} />
                        <Route exact path="/publishers" render={(props) => <PublishersMenu {...props} activeNav="publishers" />} />
                    </Switch>
                </div>
            </div>
        );
    }
}

export default App;