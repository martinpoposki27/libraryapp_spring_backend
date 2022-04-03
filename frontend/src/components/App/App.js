import './App.css';
import React, {Component} from "react";
import Authors from "../Authors/authors";
import Countries from "../Countries/countries";
import Books from "../Books/BookList/books";
import Header from "../Header/header";
import BookEdit from "../Books/BookEdit/bookEdit";
import BookAdd from "../Books/BookAdd/bookAdd";
import Categories from "../Categories/categories";
import {BrowserRouter as Router, Route, Redirect} from 'react-router-dom'
import LibraryService from "../../repository/libraryRepository";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      authors: [],
      books: [],
      categories: [],
      countries: [],
      selectedBook: {}
    }
  }

  render() {
    return (
        <Router>
          <Header/>
          <main>
            <div className="container">
                  <Route path={"/authors"} exact render={() =>
                      <Authors authors={this.state.authors}/>}/>
                  <Route path={"/countries"} exact render={() =>
                      <Countries countries={this.state.countries}/>}/>
                <Route path={"/book/categories"} exact render={() =>
                    <Categories categories={this.state.categories}/>}/>
                  <Route path={"/books/add"} exact render={() =>
                      <BookAdd categories={this.state.categories}
                               authors={this.state.authors}
                               onAddBook={this.addBook}/>}/>
                  <Route path={"/books/edit/:id"} exact render={() =>
                      <BookEdit categories={this.state.categories}
                                authors={this.state.authors}
                                onEditBook={this.editBook}
                                book={this.state.selectedBook}/>}/>
                  <Route path={"/books"} exact render={() =>
                      <Books books={this.state.books}
                                onDelete={this.deleteBook}
                                onEdit={this.getBook}
                                onMark={this.markAsTaken}/>}/>
                <Redirect to={"/books"}/>
            </div>
          </main>
        </Router>
    );
  }

  componentDidMount() {
    this.loadAuthors();
    this.loadCountries();
    this.loadBooks();
    this.loadCategories();
  }

  loadAuthors = () => {
      LibraryService.fetchAuthors()
        .then((data) => {
          this.setState({
            authors: data.data
          })
        });
  }

  loadBooks = () => {
    LibraryService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          })
        });
  }

  loadCountries = () => {
      LibraryService.fetchCountries()
        .then((data) => {
          this.setState({
            countries: data.data
          })
        });
  }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

  deleteBook = (id) => {
      LibraryService.deleteBook(id)
        .then(() => {
          this.loadBooks();
        });
  }

  addBook = (name, category, author, availableCopies) => {
      LibraryService.addBook(name, category, author, availableCopies)
          .then(() => {
              this.loadBooks();
          });
  }

  markAsTaken = (id) => {
      LibraryService.markAsTaken(id)
          .then(() => {
              this.loadBooks();
          });
  }



  getBook = (id) => {
      LibraryService.getBook(id)
        .then((data) => {
          this.setState({
            selectedBook: data.data
          })
        })
  }

  editBook = (id, name, category, author, availableCopies) => {
    LibraryService.editBook(id, name, category, author, availableCopies)
        .then(() => {
          this.loadBooks();
        });
  }
}

export default App;
