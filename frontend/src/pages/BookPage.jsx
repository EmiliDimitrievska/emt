import React, { useEffect, useState } from "react";
import bookRepository from "../repository/bookRepository";
import BookList from "../components/books/BookList";
import BookForm from "../components/books/BookForm";
import Modal from "../components/common/Modal";
import "./BookPage.css";

function BookPage() {
  const [books, setBooks] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedBook, setSelectedBook] = useState(null);


  const loadBooks = async () => {
    try {
      const data = await bookRepository.findAll();
      setBooks(data.data);
    } catch (error) {
      console.error("Error fetching books:", error);
    }
  };
  useEffect(() => {
    loadBooks();
  }, []);

  

  const handleAdd = () => {
    setSelectedBook(null);
    setShowModal(true);
  };

  const handleEdit = (book) => {
    setSelectedBook(book);
    setShowModal(true);
  };

  const handleDelete = async (id) => {
    try {
      await bookRepository.delete(id);
      setBooks(books.filter((b) => b.id !== id));
    } catch (error) {
      console.error("Delete error:", error);
    }
  };

 
  const handleSubmit = async (formData) => {
    try {
      if (selectedBook) {
        await bookRepository.update(selectedBook.id, formData);
      } else {
        await bookRepository.create(formData);
      }

      setShowModal(false);
      loadBooks(); // refresh list
    } catch (error) {
      console.error("Submit error:", error);
    }
  };

return (
  <div className="bp-root">
    <div className="bp-header">
      <div>
        <h1 className="bp-title">
          My <span>Library</span>
        </h1>
        <p className="bp-count">
          {books.length} book{books.length !== 1 ? "s" : ""} in collection
        </p>
      </div>

      <button className="bp-add-btn" onClick={handleAdd}>
        Add Book
      </button>
    </div>

    <div className="bp-wrapper">
      <div className="bp-table-wrap">
        <table className="bp-table">
          <thead>
            <tr>
              <th>Title / Author</th>
              <th>Category</th>
              <th>Copies</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {books.length === 0 ? (
              <tr>
                <td colSpan="4">No books found</td>
              </tr>
            ) : (
              books.map((b) => (
                <tr key={b.id}>
                  <td>
                    <div className="bp-book-title">{b.name}</div>
                    <div className="bp-book-author">
                       {b.author?.name} {b.author?.surname}
                    </div>
                  </td>

                  <td>
                    <span className="bp-genre-badge">
                      {b.category}
                    </span>
                  </td>

                  <td>{b.availableCopies}</td>

                  <td>
                    <div className="bp-actions">
                      <button
                        className="bp-btn-edit"
                        onClick={() => handleEdit(b)}
                      >
                        Edit
                      </button>

                      <button
                        className="bp-btn-del"
                        onClick={() => handleDelete(b.id)}
                      >
                        Delete
                      </button>
                    </div>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>

    {showModal && (
      <Modal onClose={() => setShowModal(false)}>
        <BookForm
          book={selectedBook}
          onSubmit={handleSubmit}
        />
      </Modal>
    )}
  </div>
);
}

export default BookPage;