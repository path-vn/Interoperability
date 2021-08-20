import React from 'react';
import MaterialTable from 'material-table';
import NicePagination from '../Pagination/NicePagination';

export default function NiceTable(props) {
    const {
        itemList,
        columns,
        t,
        totalPages,
        handleChangePage,
        setRowsPerPage,
        pageSize,
        pageSizeOption,
        totalElements,
        page,

    } = props;
    return (
        <>
            <MaterialTable
                data={itemList}
                columns={columns}
                parentChildData={(row, rows) => {
                    var list = rows.find((a) => a.id === row.parentId)
                    return list
                }}
                options={{
                    selection: "true",
                    actionsColumnIndex: -1,
                    paging: false,
                    search: false,
                    toolbar: false,
                    maxBodyHeight: "440px",
                    headerStyle: {
                        backgroundColor: "#2a80c8",
                        color: "#fff",
                    },
                    rowStyle: (rowData, index) => ({
                        backgroundColor: index % 2 === 1 ? 'rgb(237, 245, 251)' : '#FFF',
                    }),
                    // tableLayout: 'fixed'
                }}
                onSelectionChange={(rows) => {
                    // data = rows;
                    console.log(rows)
                }}
            />
            <NicePagination
                totalPages={totalPages}
                handleChangePage={handleChangePage}
                setRowsPerPage={setRowsPerPage}
                pageSize={pageSize}
                pageSizeOption={pageSizeOption}
                t={t}
                totalElements={totalElements}
                page={page}
            />
        </>
    )
}