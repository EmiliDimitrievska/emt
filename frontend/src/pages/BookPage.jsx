import React, { useEffect, useState } from "react";
import bookRepository from "../repository/bookRepository";

function BookPage() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const data = await bookRepository.findAll(); 
        console.log(data); 
        console.log("TYPE:", typeof data.data);
        setBooks(data.data);
      } catch (error) {
        console.error("Error fetching books:", error);
      }
    };

    fetchBooks();
  }, []);


  return (
    <div>
      <h1>Book Page</h1>
      <ul>
        {books.map((book) => (
          <li key={book.id}>{book.name}</li>
        ))}
        
      </ul>
    </div>
  );
}

export default BookPage;