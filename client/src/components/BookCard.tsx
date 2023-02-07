
import { Card, CardContent, Typography } from "@mui/material";
import AuthorCard from "./AuthorCard";

interface IAuthor {
    id: string,
    name: string
}

interface IBookData {
    bookName: string,
    pageCount: string,
    publisher: string,
    writtenBy: IAuthor[],
    isbn: string
}

interface IProps {
    bookData: IBookData
}

const BookCard = ({ bookData }: IProps) => {
    const { bookName, pageCount, publisher, writtenBy, isbn } = bookData;

    return (
        <div style={{ display: 'flex', justifyContent: 'center' }}>
            <Card sx={{ minWidth: 800, maxWidth: 1000, margin: 1 }}>
                <CardContent>
                    <Typography>
                        {bookName}
                    </Typography>
                    <Typography>
                        {`Page Count: ${pageCount}`}
                    </Typography>
                    <Typography>
                        {`Publisher: ${publisher}`}
                    </Typography>
                    <div>
                        {writtenBy.map((author: IAuthor, index: number) => {
                            return <AuthorCard key={index} authorData={author} />
                        })}
                    </div>
                    <Typography>
                        {`ISBN:  ${isbn}`}
                    </Typography>
                </CardContent>
            </Card>
        </div>
    )
}

export default BookCard