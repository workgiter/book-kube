import { Typography } from "@mui/material";
import { useEffect, useState } from "react"
import { Link } from "react-router-dom";
import BookCard from "./BookCard"

const url = process.env.REACT_APP_SERVER_IP;

const BookList = () => {

    let [booksData, setBooksData] = useState([])

    const getBookData = () => {
        fetch(`${url}books/list`)
            .then((response) => response.json())
            .then((data) => { setBooksData(data) });
    }

    useEffect(() => {
        getBookData()
    }, [])

    return (
        <>
            {booksData.map((bookData, index) => {
                return <BookCard key={index} bookData={bookData} />
            })}
            <Link to="/"><Typography variant="h3">Add New Book</Typography></Link>
        </>
    )
}

export default BookList
