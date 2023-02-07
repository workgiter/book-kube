import { Button, TextField, Typography } from "@mui/material";
import { useState } from "react";

interface IProps {
    stealBook: (isbn: string) => void
}

const ISBNinput = (props: IProps) => {

    let [bookCode, setBookCode] = useState("")


    return (
        <>
            <Typography variant="h1">Add a book</Typography>
            <TextField
                id="outlined-basic"
                label="Please enter your isbn here..."
                variant="outlined"
                fullWidth
                value={bookCode} onChange={
                    (e) => setBookCode(e.target.value)
                }
            />
            <p></p>
            <Button onClick={() => props.stealBook(bookCode)} >Submit</Button>
        </>
    )
}

export default ISBNinput;