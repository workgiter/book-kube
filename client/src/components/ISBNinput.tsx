import { Button, TextField, Typography } from "@mui/material";
import { useState } from "react";
import { isbn } from 'luhn-validation';



interface IProps {
    stealBook: (isbn: string) => void
}

const ISBNinput = (props: IProps) => {

    let [bookCode, setBookCode] = useState("")

    const isValidISBN = (bookCode: string) => {
        return isbn(bookCode);
    }

    return (
        <>
            <Typography variant="h2">Add a book</Typography>
            <TextField
                id="outlined-basic"
                label="Please enter your isbn here..."
                variant="outlined"
                fullWidth
                helperText={(isValidISBN(bookCode) || bookCode === "") ? "" : "Invalid ISBN"}
                value={bookCode} onChange={
                    (e) => setBookCode(e.target.value)
                }
            />
            <p></p>
            <Button onClick={() => {
                if (isValidISBN(bookCode)) { props.stealBook(bookCode) }
            }
            }>Submit</Button>
        </>
    )
}

export default ISBNinput;