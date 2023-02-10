
import { Card, CardContent, Typography } from "@mui/material";


const AuthorCard = ({ authorData }: any) => {

    return (
        <div style={{ display: 'flex', justifyContent: 'center', margin: 3 }}>
            <Typography>
                {authorData.authorName}
            </Typography>
        </div>
    )
}

export default AuthorCard