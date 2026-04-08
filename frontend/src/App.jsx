import './App.css'
import HomePage from "./pages/HomePage.jsx";
import CountryPage from "./pages/CountryPage.jsx";
import AuthorPage from "./pages/AuthorPage.jsx";
import BookPage from "./pages/BookPage.jsx";
import LoginPage from "./pages/LoginPage.jsx";
import {Routes, Route} from 'react-router-dom'
function App() {

  return (
  
    <Routes>
      <Route path='/' element={<HomePage/>}/>
      <Route path='/countries' element={<CountryPage/>}/>
      <Route path='/books' element={<BookPage/>}/>
      <Route path='authors' element={<AuthorPage/>}/>
    </Routes>
  )
}

export default App
