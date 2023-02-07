import IAuthor from "./IAuthor";


export default interface IBookData {
    bookName: string,
    pageCount: string,
    publisher: string,
    writtenBy: IAuthor[],
    isbn: string
}