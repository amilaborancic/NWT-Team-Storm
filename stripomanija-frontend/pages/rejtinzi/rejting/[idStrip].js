import React,{useEffect,useState} from "react";
import {useRouter} from "next/router";
import NavbarContainer from "../../../components/NavbarContainer/NavbarContainer";
import styles from "./index.module.css";
import Pagination from "../../../components/Pagination/Pagination";
import axios from "axios";
import RatingDetails from "../../../components/RatingDetails/RatingDetails";

const Rating=()=>{
    
  const router=useRouter();
  const {idStrip}=router.query;

  const [korisnici, setKorisnici]=useState([]);
  const [rejtinzi, setRejtinzi]=useState([]);

  useEffect(()=>{
    Promise.all([
      axios.get('http://localhost:8081/korisnici-stripa/1'),
      axios.get('http://localhost:8081/rating/stripa/1')
       ]).then(([korisniciResponse, rejtinziResponse]) => {
          setKorisnici(korisniciResponse.data);
          setRejtinzi(rejtinziResponse.data);
      });

  });

    return(
      <NavbarContainer>
            {
              rejtinzi.map((rejting, index)=>{
              const korisnik=korisnici[index];
              return(
                <RatingDetails ocjena={rejting.ocjena} komentar={rejting.komentar} korisnik={korisnik} id={idStrip}/>
              )
              })
            }
      </NavbarContainer>
      );
  }
  
export default Rating;