import BookItem from "./BookItem";

const BookList = ({ books, onEdit, onDelete }) => {
  return (
    <div>
      {books.map((b) => (
        <BookItem
          key={b.id}
          book={b}
          onEdit={onEdit}
          onDelete={onDelete}
        />
      ))}
    </div>
  );
};

export default BookList;
