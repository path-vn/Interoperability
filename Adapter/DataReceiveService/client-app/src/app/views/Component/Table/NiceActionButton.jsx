import React from 'react';
import {
    IconButton,
    Icon
} from "@material-ui/core";


export default function NiceActionButton(props) {

    const {
        item,
        size,
        onSelect,
        fontSize,
        color,
        icon

    } = props;
    return (
        <IconButton size={size} onClick={() => onSelect(item, 0)}>
            <Icon fontSize={fontSize} color={color}>{icon}</Icon>
        </IconButton>
    )
}