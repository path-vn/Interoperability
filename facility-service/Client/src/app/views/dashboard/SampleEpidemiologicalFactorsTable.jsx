import React from "react";
import {
  Table,
  TableHead,
  TableCell,
  TableBody,
  IconButton,
  Icon,
  TableRow
} from "@material-ui/core";

const data = [
  {
    name: "john doe",
    total: 0,
    positive: 0,
    totalF1: 0,
    totalF2: 0
  },
  {
    name: "kessy bryan",
    total: 0,
    positive: 0,
    totalF1: 0,
    totalF2: 0
  },
  {
    name: "james cassegne",
    total: 0,
    positive: 0,
    totalF1: 0,
    totalF2: 0
  },
  {
    name: "lucy brown",
    total: 0,
    positive: 89000,
    totalF1: 0,
    totalF2: 0
  },
  {
    name: "lucy brown",
    total: 0,
    positive: 89000,
    totalF1: "0",
    totalF2: "0"
  },
  {
    name: "lucy brown",
    total: 100,
    positive: 0,
    totalF1: "0",
    totalF2: "0"
  }
];

const SimpleTable = () => {
  return (
    <div className="mb-28 w-100 overflow-auto">
      <Table style={{ whiteSpace: "pre" }}>
        <TableHead>
          <TableRow>
            <TableCell className="px-0">Stt</TableCell>
            <TableCell className="px-0">Tên</TableCell>
            <TableCell className="px-0">Tổng số mẫu</TableCell>
            <TableCell className="px-0">Dương tính</TableCell>
            <TableCell className="px-0">F1</TableCell>
            <TableCell className="px-0">F2</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data.map((sample, index) => (
            <TableRow key={index}>
              <TableCell className="px-0 capitalize" align="left">
                {index}
              </TableCell>
              <TableCell className="px-0 capitalize" align="left">
                {sample.name}
              </TableCell>
              <TableCell className="px-0 capitalize" align="left">
                {sample.total}
              </TableCell>
              <TableCell className="px-0 capitalize" align="left">
                {sample.positive}
              </TableCell>
              <TableCell className="px-0 capitalize">
                {sample.totalF1}
              </TableCell>
              <TableCell className="px-0 capitalize">
                {sample.totalF2}
              </TableCell>
              {/* <TableCell className="px-0">
                <IconButton>
                  <Icon color="error">close</Icon>
                </IconButton>
              </TableCell> */}
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default SimpleTable;
