import React from 'react';
import {
    TextField,
    MenuItem,
    Grid,
    InputAdornment,
    Link,
    Input,
    IconButton,
} from "@material-ui/core";
import Pagination from '@material-ui/lab/Pagination';
import './NicePaging.scss';
import GotoIcon from '@material-ui/icons/ExitToAppOutlined';

export default function NicePagination(props) {
    let { handleChangePage, totalPages, setRowsPerPage, pageSizeOption, t , totalElements, page } = props;
    const [pageSize, setPageSize] = React.useState(props.pageSize);
    const handleChange = (event) => {
        setRowsPerPage(event);
        setPageSize(event.target.value);
    };
    
    // const [page, setPage] = React.useState(1);
    const [pageIndex, setPageIndex] = React.useState(page);
    const handleGo = (event) => {
        if (pageIndex < 1 || pageIndex > totalPages) {
          alert("Hãy nhập số từ 1 dến " + totalPages);
          return;
        }
        handleChangePage(event, Number(pageIndex));
    };
    return (
        <div className="nice-pagination-bar">
            <Grid container spacing={2}>
                <Grid className="row-per-page-selector" item>
                    <p>{t('general.total_rows')}<span className="total-rows">{totalElements}</span></p>
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
                        page={page}
                        // variant="outlined"
                        color="primary"
                        onChange={handleChangePage}
                        boundaryCount={1}
                        siblingCount={1}
                        showFirstButton 
                        showLastButton
                    />
                </Grid>
                {totalPages > 7 && 
                    <Grid className="page-goto" item>
                        <p>{t('general.goto_page')}</p>
                        <TextField
                            className="text-field"
                            type="number"
                            name="pageIndex"
                            value={pageIndex}
                            onChange={(e) => setPageIndex(e.target.value)}
                            onKeyDown={(e) => {
                                if(e.key === "Enter") {
                                    handleGo(e);
                                }
                            }}
                        />
                    </Grid>
                }
            </Grid>
        </div>
    );
}
