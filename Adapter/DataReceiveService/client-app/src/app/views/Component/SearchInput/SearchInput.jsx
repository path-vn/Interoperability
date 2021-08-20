import React, { useEffect } from 'react';
import {
    Input,
    makeStyles,
    InputAdornment,
    Link,
    FormControl
} from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import { useTranslation } from 'react-i18next';
const useStyles = makeStyles((theme) => ({
    formControl: {
        width: '100%'
    }
}));

export default function SearchInput(props) {
    const classes = useStyles();

    const { t } = useTranslation();

    const [keyword, setKeyword] = React.useState('');

    const {
        search,
    } = props;

    const handleKeyDownEnterSearch = (event) => {
        if (event.key === 'Enter') {
            var searchObject = {};
            searchObject.text = keyword

            props.search(searchObject);
        }
    };

    // useEffect(() => {
    //     if (keyword == "") {
    //         props.search('', 'text');
    //     }
    // }, [keyword]);

    return (
        <FormControl fullWidth >
            <Input
                className='mt-10 search_box w-100 stylePlaceholder'
                type="text"
                name="keyword"
                value={keyword}
                onChange={(event) => setKeyword(event.target.value)}
                onKeyDown={handleKeyDownEnterSearch}
                placeholder={t('general.enter_search')}
                id="search_box"
                startAdornment={
                    <InputAdornment >
                        <Link to="#"> <SearchIcon
                            onClick={() => {
                                var searchObject = {};
                                searchObject.text = keyword
                                search(searchObject)
                            }}
                            style={{
                                position: "absolute",
                                top: "0",
                                right: "0"
                            }} /></Link>
                    </InputAdornment>
                }
            />
        </FormControl>
    );

}