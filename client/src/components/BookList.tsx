import { useEffect, useState } from "react"
import BookCard from "./BookCard"

const url = process.env.REACT_APP_SERVER_IP;

const BookList = () => {

    let [booksData, setBooksData] = useState([])

    const getBookData = () => {
        fetch(`${url}books/list`)
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
