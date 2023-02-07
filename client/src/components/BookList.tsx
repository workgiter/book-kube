import { useEffect, useState } from "react"
import BookCard from "./BookCard"

const BookList = () => {

    let [booksData, setBooksData] = useState([])

    const getBookData = () => {//http://localhost:8080/books/list
        console.log(process.env.REACT_APP_SERVER_IP)
        fetch(process.env.REACT_APP_SERVER_IP + 'books/list')
            .then((response) => response.json())
            .then((data) => { console.log(data); setBooksData(data) });
    }

    useEffect(() => {
        getBookData()
    }, [])

    return (
        <>
            {booksData.map((bookData, index) => {
                return <BookCard key={index} bookData={bookData} />
            })}
        </>
    )
}

export default BookList
