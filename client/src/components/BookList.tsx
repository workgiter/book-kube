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
            .then((data) => { console.log(data); setBooksData(data) });
    }

    useEffect(() => {
        getBookData()
    }, [])

    return (
        <>
            <Link to="/"><Typography variant="h3">Add New Book</Typography></Link>
            {booksData.map((bookData, index) => {
                return <BookCard key={index} bookData={bookData} />
            })}
        </>
    )
}

export default BookList
