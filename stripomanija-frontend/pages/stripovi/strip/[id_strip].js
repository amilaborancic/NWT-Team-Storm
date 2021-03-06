import React, {useEffect, useState} from 'react';
import NavbarContainer from "../../../components/NavbarContainer/NavbarContainer";
import styles from "./index.module.css";
import {authenticatedApi} from "../../../util/url";
import withRouter from "next/dist/client/with-router";
import {routes} from "../../../util/routes";
import BeautyStars from "beauty-stars";
import Comment from "../../../components/Comment/Comment";
import GenericModal from "../../../components/GenericModal/GenericModal";
import ToastMessage from "../../../components/ToastMessage/ToastMessage";
import cx from "classnames";
import {useWindowDimensions} from "../../../components/hooks/useWindowDimensions";
import {handleFieldChange} from "../../index";

const StripDetails = ({ router: { query } })=>{
    const [comic, setComic] = useState({});
    const [commentList, setCommentList] = useState([]);
    const [isRatingModalOpen, setIsRatingModalOpen] = useState(false);
    const [windowDimensions, setWindowDimensions] = useState({});
    useWindowDimensions(windowDimensions, setWindowDimensions);
    const [toast, setToast] = useState({});
    const [isToastOpen, setIsToastOpen] = useState(false);
    const [newRateReview, setNewRateReview] = useState({
        korisnik: {
            id:null
        },
        strip: {
            id:null
        },
        ocjena:null,
        komentar:""
    });

    useEffect(()=>{
        if(query.id_strip){
            //fetch details
            fetchComicDetails(query.id_strip, setComic, setToast, setIsToastOpen);
            fetchComicReviews(query.id_strip, setCommentList, setToast, setIsToastOpen);
            setNewRateReview(prevState => ({...prevState,
                strip: {id:parseInt(query.id_strip)}
            }));
        }
    }, [query.id_strip]);
    useEffect(()=>{
        fetchComicReviews(query.id_strip, setCommentList, setToast, setIsToastOpen);
    }, [commentList]);
    return(
        <NavbarContainer>
            <div className={styles.wrapper}>
                <div className={cx("d-flex mt-2", styles.container)}>
                    {comic &&
                    <>
                        <LeftPart comic={comic}/>
                        <RightPart commentList={commentList} comic={comic}  setIsRatingModalOpen={setIsRatingModalOpen} windowDimensions={windowDimensions}/>
                    </>}
                </div>
                {windowDimensions.width >= 750 && windowDimensions.width <= 1024 &&
                <div className={styles.commentSection}>
                    <CommentSection comic={comic} commentList={commentList} setIsRatingModalOpen={setIsRatingModalOpen} />
                </div>}
                <NewRateReviewModal isRatingModalOpen={isRatingModalOpen} setIsRatingModalOpen={setIsRatingModalOpen}
                                    newRateReview={newRateReview}
                                    setNewRateReview={setNewRateReview}
                                    setIsToastOpen={setIsToastOpen}
                                    setToast={setToast}
                />
                <ToastMessage setIsOpen={setIsToastOpen} isOpen={isToastOpen} type={toast.type} message={toast.message}/>
            </div>
        </NavbarContainer>
    )

}

const RightPart = ({comic, commentList, setIsRatingModalOpen, windowDimensions})=>{
    return(
        <div className={cx(styles.descriptionContainer)}>
            <h2 className="display-4">
                <strong>{comic.naziv}</strong>
            </h2>
            <p style={{fontSize: "20px"}}>Autori:
                {comic.autori && comic.autori.map((autor, index) => {
                    let comma = ",";
                    if (index === comic.autori.length - 1) comma = "";
                    return (<span className="ml-1" key={autor.id}>{`${autor.ime} ${autor.prezime}${comma}`}</span>);
                })}
            </p>
            <h3>Opis</h3>
            <p className="text-muted" style={{fontSize: "20px"}}>
                {comic.opis}
            </p>
            {(windowDimensions.width > 1024 || windowDimensions.width <= 749) && <CommentSection comic={comic} commentList={commentList} setIsRatingModalOpen={setIsRatingModalOpen} />}
        </div>
    );
}

const CommentSection = ({commentList, comic, setIsRatingModalOpen})=>{
    return(
        <>
            <h3>Komentari</h3>
            {comic.ukupnoKomentara === 0 ? "Trenutno nema komentara na ovaj strip." : commentList &&
                commentList.map(valuePair=>
                    <div key={valuePair[0]} className="mt-2">
                        <Comment username={valuePair[0]} commentBody={valuePair[1]} />
                    </div>
                )}
            <div className="d-flex w-100 justify-content-end mt-2">
                <button type="button" className="btn btn-info" onClick={()=>setIsRatingModalOpen(true)}>Ostavite recenziju</button>
            </div>
        </>
    );
}

const LeftPart = ({comic})=>{
    return(
        <div className={styles.leftPart}>
            <img src={comic.slika} className={styles.img}/>
            <div className="d-flex align-items-baseline mt-3">
                <h3>Ocjena:</h3>
                <div className="mx-2">
                    <BeautyStars size={20} value={comic.ukupniRating} inactiveColor={"#ccc"}/>
                </div>
            </div>
        </div>
    );
}

const NewRateReviewModal = ({setIsRatingModalOpen, isRatingModalOpen, newRateReview, setNewRateReview, setIsToastOpen, setToast})=>{
    const [ocjena, setOcjena] = useState(0);
    return(
        <GenericModal showModal={isRatingModalOpen}
                      closeModal={()=>setIsRatingModalOpen(false)}
                      modalTitle={"Rate & review"}
        >
            <div className="form-group">
                <label>Ocjena</label>
                <BeautyStars editable inactiveColor={"#ccc"} size={20} value={ocjena} onChange={(value)=>setOcjena(value)}/>
            </div>
            <div className="form-group">
                <label>Komentar</label>
                <textarea className="form-control" name={"komentar"} placeholder={"Kakvi su vam utisci?"} rows="3"
                          onChange={(e)=>handleFieldChange(e, newRateReview, setNewRateReview)} />
            </div>
            <div className="d-flex w-100 justify-content-end">
                <button type="button" className="btn btn-info" onClick={()=>rateReview(ocjena, newRateReview, setNewRateReview, setIsRatingModalOpen, setIsToastOpen, setToast)}>Ok</button>
            </div>
        </GenericModal>
    );
}

//leave a review
function rateReview(ocjena, newRateReview, setNewRateReview, setIsModalOpen, setIsToastOpen, setToast){
    let obj = newRateReview;
    obj.ocjena = ocjena;
    setNewRateReview(obj);
    //fetch user
    authenticatedApi.get(routes.user.path + routes.user.single.path + localStorage.getItem("username"))
        .then(res=>{
            let newObj = newRateReview;
            newObj.korisnik = {id:res.data.id};
            setNewRateReview(newObj);
            //send post for new rating
            authenticatedApi.post(routes.rating.path + routes.rating.novi.path, newRateReview).then(res=>{
                //close modal, show toast
                setIsModalOpen(false);
                setIsToastOpen(true);
                setToast({
                    type:"success",
                    message:"Uspješno ste ostavili recenziju!"
                });
                //reset state
                setNewRateReview({
                    korisnik:{id:newRateReview.korisnik.id},
                    strip: {id:null},
                    ocjena: null,
                    komentar: ""
                });
            }).catch(err=>{
                setIsModalOpen(false);
                setIsToastOpen(true);
                setToast({
                    type: "danger",
                    message: err.response.data.message
                });
            })
        })
        .catch(err=>{
            console.log(err);
            setIsModalOpen(false);
            setIsToastOpen(true);
            setToast({
                type:"danger",
                message:err.response.data.message
            });
        });
}


function fetchComicDetails(idStrip, setComic, setToast, setIsToastOpen){
    authenticatedApi.get(routes.strip.path, {
        params:{
            id_strip: idStrip
        }
    })
        .then(res=>{
            setComic(res.data.strip);
        })
        .catch(err=>{
            console.log(err);
            setToast({
                type:"danger",
                message:"Došlo je do greške prilikom dobavljanja detalja o stripu."
            });
            setIsToastOpen(true);
        })
}

function fetchComicReviews(stripId, setCommentList, setIsToastOpen, setToast){
    authenticatedApi.get(routes.rating.path + routes.rating.komentari.path + stripId)
        .then(res=>{
            setCommentList(Object.entries(res.data.komentari));
        })
        .catch(err=>{
            console.log(err);
            setToast({
                type:"danger",
                message:"Došlo je do greške prilikom dobavljanja informacija o recenzijama stripa."
            });
            setIsToastOpen(true);
        });
}


export default withRouter(StripDetails);