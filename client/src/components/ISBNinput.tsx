import { useState } from "react";

const ISBNinput = () => {

    const REACT_APP_SERVER_IP = process.env.REACT_APP_SERVER_IP
    let [bookCode, setBookCode] = useState("")

    const stealBook = (isbn: string) => {
        //console.log(REACT_APP_SERVER_IP)
        fetch(REACT_APP_SERVER_IP + 'books/steal/' + isbn)
            .then((response) => response.json())
            .then((data) => { console.log(data); })
            .catch(e => console.log(e));
    }

    return (
        <>
            <input type={"text"} value={bookCode} onChange={(e) => setBookCode(e.target.value)}></input>
            <button onClick={() => stealBook(bookCode)}>steal book data</button>
        </>
    )
}

export default ISBNinput;