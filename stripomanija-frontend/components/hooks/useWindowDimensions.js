import React, {useEffect} from "react";

export const useWindowDimensions = (windowDimensions, setWindowDimensions)=>{
    useEffect(() => {
        const handleResize = () => setWindowDimensions({width: window.innerWidth, height: window.innerHeight});
        handleResize();
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, []);
    return {windowDimensions};
}


