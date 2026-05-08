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
  const [search, setSearch] = useState("");

  const loadBooks = async () => {
    try {
      const data = await bookRepository.findAll();
      setBooks(data.data);
    } catch (error) {
      console.error("Error fetching books:", error);
    }
  };

  useEffect(() => { loadBooks(); }, []);

  const handleAdd = () => { setSelectedBook(null); setShowModal(true); };
  const handleEdit = (book) => { setSelectedBook(book); setShowModal(true); };
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
      loadBooks();
    } catch (error) {
      console.error("Submit error:", error);
    }
  };

  const filtered = books.filter((b) => {
    const q = search.toLowerCase();
    return (
      b.name?.toLowerCase().includes(q) ||
      b.author?.name?.toLowerCase().includes(q) ||
      b.author?.surname?.toLowerCase().includes(q) ||
      b.category?.toLowerCase().includes(q)
    );
  });

  return (
    <div className="bp-root">

      {/* ── HERO ── */}
      <div className="bp-hero">
        <p className="bp-hero-eyebrow">Welcome to your</p>
        <h1 className="bp-hero-title">My <span>Library</span></h1>
        <p className="bp-hero-sub">
          {books.length} book{books.length !== 1 ? "s" : ""} in your collection
        </p>

        {/* Search */}
       <div className="bp-search-bar">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none"
            stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
            <circle cx="11" cy="11" r="8"/>
            <line x1="21" y1="21" x2="16.65" y2="16.65"/>
          </svg>
          <input
            className="bp-search-input"
            type="text"
            placeholder="Search by title, author or category..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
            <button className="bp-search-btn" onClick={() => {}}>
                 Search
             </button>
        </div>
      </div>

      {/* ── TABLE SECTION ── */}
      <div className="bp-section">
        <div className="bp-section-header">
          <h2 className="bp-section-title">All Books</h2>
          <button className="bp-add-btn" onClick={handleAdd}>
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
              <line x1="12" y1="5" x2="12" y2="19"/><line x1="5" y1="12" x2="19" y2="12"/>
            </svg>
            Add Book
          </button>
        </div>

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
              {filtered.length === 0 ? (
                <tr>
                  <td colSpan="4" className="bp-empty">
                    {search ? `No results for "${search}"` : "No books found"}
                  </td>
                </tr>
              ) : (
                filtered.map((b) => (
                  <tr key={b.id}>
                    <td>
                      <div className="bp-book-title">{b.name}</div>
                      <div className="bp-book-author">{b.author?.name} {b.author?.surname}</div>
                    </td>
                    <td><span className="bp-genre-badge">{b.category}</span></td>
                    <td>{b.availableCopies}</td>
                    <td>
                      <div className="bp-actions">
                        <button className="bp-btn-edit" onClick={() => handleEdit(b)}>Edit</button>
                        <button className="bp-btn-del" onClick={() => handleDelete(b.id)}>Delete</button>
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
          <BookForm book={selectedBook} onSubmit={handleSubmit} />
        </Modal>
      )}
    </div>
  );
}

export default BookPage;