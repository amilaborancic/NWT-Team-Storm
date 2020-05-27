import React, {useEffect, useState} from 'react';
import NavbarContainer from "../../../components/NavbarContainer/NavbarContainer";
import styles from "./index.module.css";
import {authenticatedApi} from "../../../util/url";
import withRouter from "next/dist/client/with-router";
import {routes} from "../../../util/routes";
import BeautyStars from "beauty-stars";
import Comment from "../../../components/Comment/Comment";

const StripDetails = ({ router: { query } })=>{
    const [comic, setComic] = useState({});
    const [ratingReview, setRatingReview] = useState({});
    const [commentList, setCommentList] = useState([]);
    useEffect(()=>{
        if(query.id_strip){
            //fetch details
            fetchComicDetails(query.id_strip, setComic);
            fetchComicReviews(query.id_strip, setCommentList);
        }
    }, [query.id_strip]);

    return(
        <NavbarContainer>
            <div className="d-flex mx-4 mt-2">
                {comic &&
                <>
                    <div className="ml-5">
                        <img src={comic.slika} className={styles.img}/>
                        <div className="d-flex align-items-baseline mt-2">
                            <h4>Ocjena:</h4>
                            <div className="mx-2">
                                <BeautyStars size={20} value={comic.ukupniRating} inactiveColor={"#ccc"}/>
                            </div>
                        </div>
                    </div>
                    <div className="w-50" style={{marginLeft:"7%"}}>
                        <h1 className="display-3">
                            <strong>{comic.naziv}</strong>
                        </h1>
                        <p style={{fontSize: "20px"}}>Autori:
                            {comic.autori && comic.autori.map((autor, index) => {
                                let comma = ",";
                                if (index === comic.autori.length - 1) comma = "";
                                return (<span className="ml-1" key={autor.id}>{`${autor.ime} ${autor.prezime}${comma}`}</span>);
                            })}
                        </p>
                        <h3>Opis</h3>
                        <p className="text-muted" style={{fontSize: "25px"}}>
                            {comic.opis}
                        </p>
                        <h3>Komentari</h3>
                        <div>
                            {comic.ukupnoKomentara === 0 ? "Trenutno nema komentara na ovaj strip." : commentList &&
                                commentList.map(valuePair=>
                                    <div key={valuePair[0]} className="mt-2">
                                        <Comment username={valuePair[0]} commentBody={valuePair[1]} />
                                    </div>
                                )}
                        </div>
                    </div>
                </>
                }
            </div>
        </NavbarContainer>
    )

}

function fetchComicDetails(idStrip, setComic){
    authenticatedApi.get(routes.strip.path, {
        params:{
            id_strip: idStrip
        }
    })
        .then(res=>{
            console.log(res.data);
            setComic(res.data);
        })
        .catch(err=>{
            console.log(err);
        })
}

function fetchComicReviews(stripId, setCommentList){
    authenticatedApi.get(routes.rating.path + routes.rating.komentari.path + stripId)
        .then(res=>{
            let commentList = Object.entries(res.data);
            setCommentList(Object.entries(res.data));
        })
        .catch(err=>{
            console.log(err);
        });
}

export default withRouter(StripDetails);