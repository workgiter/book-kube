
import { Card, CardContent, Typography } from "@mui/material";
import IAuthor from "../interfaces/IAuthor";
import IBookData from "../interfaces/IBookData";
import AuthorCard from "./AuthorCard";


interface IProps {
    bookData: IBookData
}

const BookCard = ({ bookData }: IProps) => {
    const { bookName, pageCount, publisher, coverImg, writtenBy, isbn } = bookData;
    const templateImgURL = "https://covers.openlibrary.org/b/ID/8739161-M.jpg";

    return (
        <div style={{ display: 'flex', justifyContent: 'center' }}>
            <Card sx={{ width: "80%", margin: 1 }}>
                <CardContent>
                    <div style={{ display: "flex" }}>
                        <img
                            width={38}
                            height={58}
                            style={{ margin: 6 }}
                            src={`https://covers.openlibrary.org/b/ID/${coverImg}-S.jpg`}
                            alt="book cover"
                        />
                        <div style={{ width: "60%", margin: 1 }}>
                            <Typography variant="h5">
                                {bookName}
                            </Typography>
                            <div style={{ display: "flex" }}>
                                {writtenBy.map((author: IAuthor, index: number) => {
                                    return <AuthorCard key={index} authorData={author} />
                                })}
                            </div>
                        </div>
                        <div style={{ marginLeft: "auto" }}>
                            <Typography variant="body2">
                                {`Page Count: ${pageCount}`}
                            </Typography>
                            <Typography variant="body2">
                                {`Publisher: ${publisher}`}
                            </Typography>
                            <Typography sx={{ marginTop: "auto" }} variant="body2">
                                {`ISBN:  ${isbn}`}
                            </Typography>
                        </div>
                    </div>
                </CardContent>
            </Card>
        </div>
    )
}

export default BookCard