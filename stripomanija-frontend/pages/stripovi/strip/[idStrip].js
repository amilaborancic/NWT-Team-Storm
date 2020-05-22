import React, { Component } from 'react';
import {useRouter} from "next/router";
import NavbarContainer from "../../../components/NavbarContainer/NavbarContainer";
import styles from "./index.module.css";
import cx from "classnames";
import { Row, Col } from 'antd';
import axios from "axios";


class StripDetails extends Component{

    constructor(){
    super()
    this.state={
        comic:[],
        autori:[]
    }
    }
    
    componentDidMount(){
        Promise.all([
            //umjesto 1 ce bit id poslan 
            axios.get('http://localhost:8083/strip?id_strip=1'),
            axios.get('http://localhost:8083/strip/autori/1')
          ])
          .then(([comicResponse, autoriResponse]) => {
                  this.setState({comic : comicResponse.data, autori : autoriResponse.data});
              });
    }

    render(){
         return(
        <NavbarContainer>
            <div className="row">
                <div className={styles.comicPreview}>
                    <img src={this.state.comic.slika} className={styles.img}
                    height="300px"
                    width="200px"></img>
                     <div class="col text-center">
                    <p><p></p>
                    <button className="btn btn-primary btn-sm">Dodaj u katalog</button>
                    </p>
                    </div>
                </div>
                <div className="col-md-4">
                <div className={styles.comicData}>
                    <div className="border md-5 h-200">
                        <h1 className={styles.comicTitle}>{this.state.comic.naziv}</h1>
                        <h3 className={styles.comicEpisode}>#{this.state.comic.izdanje}</h3>
                        <p>By:{" "}
                            {this.state.autori.map(
                                autor=><span className="font-weight-bold">
                                {autor.ime}, {autor.prezime}
                                </span>
                            )}
                        </p>
                        <p className={styles.comicRating}>Ukupni rating: {this.state.comic.ukupniRating}</p>
                        <p className={styles.comicDescription}>{this.state.comic.opis}</p>
                    </div>
                    </div></div>
                    </div>
        </NavbarContainer>
        )
    }
}

export default StripDetails;