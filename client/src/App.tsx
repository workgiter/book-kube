import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import BookList from './components/BookList';
import AddBook from './components/AddBook';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<AddBook />} />
        <Route path='/list' element={<BookList />} />
      </Routes>
    </BrowserRouter>
  );
}


export default App;
