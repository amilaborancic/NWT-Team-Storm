import React from "react";
import NavbarContainer from "../../components/NavbarContainer/NavbarContainer";
import Link from "next/link";
import RatingDetails from "../../components/RatingDetails/RatingDetails";
import StripRating from "../../components/StripRating/StripRating";

const Rejtinzi = ()=>{
    const id=1;
    const naziv="strip";
    //ovo jos moram sredit i povezat sa springom
      return(
        <NavbarContainer>
              {
                  <StripRating naziv={naziv} id={id}/>
                                  
               })
              }
        </NavbarContainer>
        );

}

export default Rejtinzi;