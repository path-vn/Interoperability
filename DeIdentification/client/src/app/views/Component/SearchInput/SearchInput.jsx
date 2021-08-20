import React from 'react';
import {
    Input,
    InputAdornment,
    Link,
    FormControl
} from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
class SearchInput extends React.Component {
    state = {
        keyword: ''
    }

    handleTextChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        });
        if (event.target.value == "") {
            this.props.search();
        }
        console.log(event.target.value);
    };
    handleKeyDownEnterSearch = (event) => {
        if (event.key === 'Enter') {
            this.props.search(this.state.keyword);
        }
    };

    render() {
        let { search, t, } = this.props;
        let { keyword } = this.state;
        return (
            <FormControl fullWidth >
                <Input
                    className='mt-10 search_box w-100 stylePlaceholder'
                    type="text"
                    name="keyword"
                    value={keyword}
                    onChange={this.handleTextChange}
                    onKeyDown={this.handleKeyDownEnterSearch}
                    placeholder={t('general.enter_search')}
                    id="search_box"
                    startAdornment={
                        <InputAdornment >
                            <Link to="#"> <SearchIcon
                                onClick={() => search(keyword)}
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
}
export default SearchInput;