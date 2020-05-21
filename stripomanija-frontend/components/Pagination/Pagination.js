import React, {useState} from "react";
import styles from "./Pagination.module.css";
import axios from "axios";
import cx from "classnames";

const Pagination = ({numberOfPages, currentPage, setCurrentPage, url, params, setSearchResults})=>{
    const [isLastPageActive, setIsLastPageActive] = useState(false);
    const [isFirstPageActive, setIsFirstPageActive] = useState(true);

    //for mapping pages
    let dummyArray = new Array(numberOfPages);
    for(let i=0; i<numberOfPages; i++)
        dummyArray[i] = Object.create({
            pageLabel: i + 1
        });

    return(
        <div className={styles.paginationContainer}>
            <div>
                <ul className="pagination">
                    <li className={cx("page-item", {"disabled":isFirstPageActive}, styles.cursor)}>
                        <a className="page-link"
                           onClick={()=>onNext(setCurrentPage, currentPage, setIsFirstPageActive, setIsLastPageActive, numberOfPages, url, params,false, setSearchResults)}>&laquo;</a>
                    </li>
                    {dummyArray.map(page=>
                        <li className={cx("page-item", {"active": currentPage === page.pageLabel - 1}, styles.cursor)} key={page.pageLabel}>
                            <a className="page-link"
                               onClick={()=>onPageClick(setCurrentPage, page.pageLabel - 1, setIsFirstPageActive, setIsLastPageActive, numberOfPages, url, params, setSearchResults)}>{page.pageLabel}
                            </a>
                        </li>
                    )}
                    <li className={cx("page-item", {"disabled": isLastPageActive}, styles.cursor)}>
                        <a className="page-link"
                           onClick={()=>onNext(setCurrentPage, currentPage, setIsFirstPageActive, setIsLastPageActive, numberOfPages, url, params, true, setSearchResults)}>&raquo;</a>
                    </li>
                </ul>
            </div>
        </div>
    );
}

function onPageClick(setCurrentPage, pageLabel, setIsFirstPageActive, setIsLastPageActive, totalPages, url, params, setSearchResults) {
    setCurrentPage(pageLabel);
    setDisabled(pageLabel, setIsFirstPageActive, setIsLastPageActive, totalPages);
    params.brojStranice = pageLabel;
    fetchNextPage(url, params, setSearchResults);
}

function onNext(setCurrentPage, activePageNumber, setIsFirstPageActive, setIsLastPageActive, totalPages, url, params, next, setSearchResults){
    let pageLabel = activePageNumber;
    if(next) pageLabel+=1;
    else pageLabel-=1;
    setCurrentPage(pageLabel);
    setDisabled(pageLabel, setIsFirstPageActive, setIsLastPageActive, totalPages);
    params.brojStranice = pageLabel;
    fetchNextPage(url, params, setSearchResults);
}


function setDisabled(pageLabel, setIsFirstPageActive, setIsLastPageActive, totalPages){
    if(pageLabel > 0) setIsFirstPageActive(false);
    else if(pageLabel === 0) setIsFirstPageActive(true);
    if(pageLabel < totalPages-1) setIsLastPageActive(false);
    else if(pageLabel === totalPages-1) setIsLastPageActive(true);
}

function fetchNextPage(url, params, setSearchResults){
    console.log(params)
    axios.get(url, {
        params: params
    }).then(res=>{
        setSearchResults(res.data.stripovi);
        console.log(res.data)
    }).catch(err=>{
        console.log(err);
    })
}

export default Pagination;