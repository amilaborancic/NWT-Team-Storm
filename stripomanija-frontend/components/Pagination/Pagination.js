import React, {useState} from "react";
import styles from "./Pagination.module.css";
import cx from "classnames";

const Pagination = ({numberOfPages})=>{
    const [isLastPageActive, setIsLastPageActive] = useState(false);
    const [isFirstPageActive, setIsFirstPageActive] = useState(true);
    const [activePageNumber, setActivePageNumber] = useState(1);

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
                    <li className={cx("page-item", {"disabled":isFirstPageActive})}>
                        <a className="page-link" href="#">&laquo;</a>
                    </li>
                    {dummyArray.map(page=>
                        <li className={cx("page-item", {"active": activePageNumber === page.pageLabel})} key={page.pageLabel}>
                            <a className="page-link" href="#"
                               onClick={()=>onPageClick(setActivePageNumber, page.pageLabel, setIsFirstPageActive, setIsLastPageActive, numberOfPages)}>{page.pageLabel}
                            </a>
                        </li>
                    )}
                    <li className={cx("page-item", {"disabled": isLastPageActive})}>
                        <a className="page-link" href="#">&raquo;</a>
                    </li>
                </ul>
            </div>
        </div>
    );
}

function onPageClick(setActivePageNumber, pageLabel, setIsFirstPageActive, setIsLastPageActive, totalPages) {
    setActivePageNumber(pageLabel);
    if(pageLabel > 1) setIsFirstPageActive(false);
    else if(pageLabel === 1) setIsFirstPageActive(true);
    if(pageLabel < totalPages) setIsLastPageActive(false);
    else if(pageLabel === totalPages) setIsLastPageActive(true);
}

export default Pagination;