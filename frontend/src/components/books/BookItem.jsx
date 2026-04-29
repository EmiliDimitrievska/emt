const BookItem = ({ book, onEdit, onDelete }) => {
  return (
    <div>
      <h4>{book.name}</h4>
      <h6>{book.category}</h6>
      <p>{book.author?.name}</p>

      <button onClick={() => onEdit(book)}>Edit</button>
      <button onClick={() => onDelete(book.id)}>Delete</button>
    </div>
  );
}; export default BookItem;