import React from 'react';
import {
    TextField,
    MenuItem,
    Grid
} from "@material-ui/core";
import Pagination from '@material-ui/lab/Pagination';
import './NicePaging.scss';

export default function NicePagination(props) {
    let { handleChangePage, totalPages, setRowsPerPage, pageSizeOption, t } = props;
    const [pageSize, setPageSize] = React.useState(props.pageSize);
    const handleChange = (event) => {
        setRowsPerPage(event);
        setPageSize(event.target.value);
    };
    return (
        <div className="nice-pagination-bar">
            <Grid container spacing={2}>
                {/* <Grid item md={5}></Grid> */}
                <Grid className="row-per-page-selector" item>
                    <p>{t('general.rows_per_page')}</p>
                    <TextField
                        select
                        value={pageSize}
                        className="text-field"
                        onChange={handleChange}
                    >
                        {pageSizeOption.map((option, index) => (
                            <MenuItem value={option}>
                                {option}
                            </MenuItem>
                        ))}
                    </TextField>
                </Grid>
                <Grid className="page-selector" item>
                    <Pagination
                        count={totalPages}
                        shape="rounded"
                        // variant="outlined"
                        color="primary"
                        onChange={handleChangePage}
                        boundaryCount={1}
                        siblingCount={1}
                        showFirstButton 
                        showLastButton
                    />
                </Grid>
            </Grid>
        </div>
    );
}
