import { Button, Typography } from "@mui/material";
import { useState } from "react";
import { Link } from "react-router-dom";
import BookCard from "./BookCard";
import ISBNinput from "./ISBNinput";

const url = process.env.REACT_APP_SERVER_IP;
const baseBook = {
    bookName: "",
    pageCount: "",
    publisher: "",
    writtenBy: [{ id: "", name: "" }],
    isbn: ""
}

const AddBook = () => {

    let [pageState, setPageState] = useState("input"); //input, loading, success, fail
    let [bookData, setBookData] = useState(baseBook)

    const handleSuccess = (data: any) => {
        console.log(data); setBookData(data); setPageState("success")
    }

    const reset = () => {
        setPageState("input")
        setBookData(baseBook)
    }

    const stealBook = (isbn: string) => {
        console.log(isbn)
        setPageState("loading")
        fetch(`${url}books/steal/${isbn}`)
            .then((response) => {
                if (response.ok) { return response.json() } else {
                    throw new Error("");
                }
            })
            .then((data) => { handleSuccess(data); })
            .catch(e => { console.log(e); setPageState("fail") });
    }


    return (
        <div>
            {
                {
                    'input': <ISBNinput stealBook={stealBook} />,
                    'loading': <Typography variant="h1">Loading...</Typography>,
                    'success':
                        <div>
                            <Typography variant="h1">Book Added!</Typography>
                            <BookCard bookData={bookData} />
                            <Button onClick={() => reset()}>Add Another Book</Button>
                            <Link to="/list"><Button>See All Books</Button></Link>

                        </div>,
                    'fail':
                        <div>
                            <Typography variant="h1">Failed to save</Typography>
                            <Button onClick={() => reset()}>Try to add another book</Button>
                            <Link to="/list"><Button>See All Books</Button></Link>
                        </div>
                }[pageState]
            }
        </div>
    )
}

export default AddBook;