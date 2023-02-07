
import { Card, CardContent, Typography } from "@mui/material";
import React from "react";
import AuthorCard from "./AuthorCard";

const BookCard = ({ bookData }: any) => {

    return (
        <div style={{ display: 'flex', justifyContent: 'center' }}>
            <Card sx={{ minWidth: 800, maxWidth: 1000, margin: 1 }}>
                <CardContent>
                    <Typography>
                        {bookData.bookName}
                    </Typography>
                    <Typography>
                        {`Page Count: ${bookData.pageCount}`}
                    </Typography>
                    <Typography>
                        {`Publisher: ${bookData.publisher}`}
                    </Typography>
                    <div>
                        {bookData.writtenBy.map((author: any, index: number) => {
                            return <AuthorCard key={index} authorData={author} />
                        })}
                    </div>
                    <Typography>
                        {`ISBN:  ${bookData.isbn}`}
                    </Typography>
                </CardContent>
            </Card>
        </div>
    )
}

export default BookCard