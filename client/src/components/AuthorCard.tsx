
import { Card, CardContent, Typography } from "@mui/material";


const AuthorCard = ({ authorData }: any) => {

    return (
        <div style={{ display: 'flex', justifyContent: 'center' }}>
            <Card sx={{ minWidth: 200, maxWidth: 600 }}>
                <CardContent>
                    <Typography>
                        {authorData.authorName}
                    </Typography>
                </CardContent>
            </Card>
        </div>
    )
}

export default AuthorCard