import { useEffect, useState } from "react";
import API from "../api/api";

function GalleryPage(){

  const [photos,setPhotos] = useState([]);

  useEffect(()=>{

    API.get("/photos")
      .then(res => setPhotos(res.data))
      .catch(err => console.error(err));

  },[]);

  return(

    <div>

      <h2>Photo Gallery</h2>

      <div className="gallery">

        {photos.map(photo => (

          <div className="photo-card" key={photo.id}>

            <img
              src={`http://localhost:8080/${photo.path}`}
              alt="photo"
            />

          </div>

        ))}

      </div>

    </div>

  )

}

export default GalleryPage;