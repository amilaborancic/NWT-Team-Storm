import React, {useState} from "react";
import styles from "./Pagination.module.css";
import cx from "classnames";

const Pagination = ({numberOfPages, currentPage, setCurrentPage, onPageChange})=>{
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
                        <a className="page-link" onClick={()=>onPrev(setCurrentPage, currentPage, setIsFirstPageActive, setIsLastPageActive, numberOfPages)}>&laquo;</a>
                    </li>
                    {dummyArray.map(page=>
                        <li className={cx("page-item", {"active": currentPage === page.pageLabel - 1}, styles.cursor)} key={page.pageLabel}>
                            <a className="page-link"
                               onClick={()=>{
                                   onPageClick(setCurrentPage, page.pageLabel - 1, setIsFirstPageActive, setIsLastPageActive, numberOfPages);
                                   onPageChange();
                               }}>{page.pageLabel}
                            </a>
                        </li>
                    )}
                    <li className={cx("page-item", {"disabled": isLastPageActive}, styles.cursor)}>
                        <a className="page-link" onClick={()=>onNext(setCurrentPage, currentPage, setIsFirstPageActive, setIsLastPageActive, numberOfPages)}>&raquo;</a>
                    </li>
                </ul>
            </div>
        </div>
    );
}

function onPageClick(setCurrentPage, pageLabel, setIsFirstPageActive, setIsLastPageActive, totalPages) {
    setCurrentPage(pageLabel);
    setDisabled(pageLabel, setIsFirstPageActive, setIsLastPageActive, totalPages);
}

function onNext(setCurrentPage, activePageNumber, setIsFirstPageActive, setIsLastPageActive, totalPages){
    let pageLabel = activePageNumber + 1;
    setCurrentPage(pageLabel);
    setDisabled(pageLabel, setIsFirstPageActive, setIsLastPageActive, totalPages);
}

function onPrev(setCurrentPage, activePageNumber, setIsFirstPageActive, setIsLastPageActive, totalPages){
    const pageLabel = activePageNumber - 1;
    setCurrentPage(pageLabel);
    setDisabled(pageLabel, setIsFirstPageActive, setIsLastPageActive, totalPages)
}

function setDisabled(pageLabel, setIsFirstPageActive, setIsLastPageActive, totalPages){
    if(pageLabel > 0) setIsFirstPageActive(false);
    else if(pageLabel === 0) setIsFirstPageActive(true);
    if(pageLabel < totalPages-1) setIsLastPageActive(false);
    else if(pageLabel === totalPages-1) setIsLastPageActive(true);
}

export default Pagination;