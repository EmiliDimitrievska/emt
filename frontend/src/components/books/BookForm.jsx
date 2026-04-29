import { useState, useEffect } from "react";
import "./BookForm.css"

const BookForm = ({ book, onSubmit }) => {
  const [form, setForm] = useState({
    name: "",
    category: "",
    author: "",
    availableCopies: 0
  });

  useEffect(() => {
  if (!book) return;
  setForm({
      name: book.name || "",
      category: book.category || "",
      author: book.author?.id?.toString() || "",
      availableCopies: book.availableCopies || 0
  
  });
  
}, [book]);

  const handleChange = (e) => {
    const numericFields = ["availableCopies"];
    const { name, value } = e.target;

    setForm({
      ...form,
      [name]: numericFields.includes(name)
      ?Number(value)
      :value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!form.name || !form.category || !form.author) {
      alert("Please fill all fields");
      return;
    }
    onSubmit({
      ...form,
      author: Number(form.author),
      availableCopies : Number(form.availableCopies),
      category: form.category 

    });
  };

  return (
    <form className="book-form" onSubmit={handleSubmit}>
      <h3 className="form-title">
       Add <span className="book-text">Book</span>
      </h3>
      <input
        name="name"
        value={form.name}
        onChange={handleChange}
        placeholder="Name"
      />

      <select
        name="category"
        value={form.category}
        onChange={handleChange}
        required
      >
        <option value="">Select Category</option>
        <option value="NOVEL">Novel</option>
        <option value="THRILLER">Thriller</option>
        <option value="HISTORY">History</option>
        <option value="FANTASY">Fantasy</option>
        <option value="BIOGRAPHY">Biography</option>
      </select> 

       <input
      name="author"
      value={form.author}
      onChange={handleChange}
      placeholder="Author ID"
     /> 
     <input
      type="number"
      name="availableCopies"
      value={form.availableCopies}
      onChange={handleChange}
      placeholder="Available Copies"
    />


      <button type="submit">Save</button>
    </form>
  );
};

export default BookForm;