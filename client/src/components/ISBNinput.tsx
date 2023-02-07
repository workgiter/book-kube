import { useState } from "react";

const ISBNinput = () => {

    let [bookCode, setBookCode] = useState("")

    const stealBook = (isbn: string) => {
        fetch('http://server.test:30011/books/steal/' + isbn)
            .then((response) => response.json())
            .then((data) => { console.log(data); });
    }

    return (
        <>
            <input type={"text"} value={bookCode} onChange={(e) => setBookCode(e.target.value)}></input>
            <button onClick={() => stealBook(bookCode)}>steal book data</button>
        </>
    )
}

export default ISBNinput;