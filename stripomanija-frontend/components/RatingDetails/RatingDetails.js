import React from "react";
import cx from "classnames";
import {routes} from "../../util/routes";
import Link from "next/link";
import Card from "react-bootstrap/Card";
import Rater from 'react-rater'

const RatingDetails = ({ocjena, komentar,korisnik, id})=>{
    const routeToRejting = "http://localhost:3000/rejtinzi/rejting/";
    
    
    return(
        <Link href={`${routeToRejting}[idStrip]`} as={`${routeToRejting}${id}`}>
            <Card>
                <Card.Header>Ocjena: 
                <Rater total={5} rating={ocjena} />;
                </Card.Header>
                <Card.Body>
                <blockquote className="blockquote mb-0">
                    <p>
                    {''}
                   {komentar}
                    {''}
                    </p>
                    <footer className="blockquote-footer text-primary">
                    {korisnik}
                    </footer>
                </blockquote>
                </Card.Body>
            </Card>
        </Link>
    );
}

export default RatingDetails;