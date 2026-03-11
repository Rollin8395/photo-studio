import { useState } from "react";
import API from "../api/api";

function UploadPage() {

  const [file, setFile] = useState(null);

  const uploadPhoto = async () => {

    const formData = new FormData();
    formData.append("file", file);

    await API.post("/uploads", formData);

    alert("Photo uploaded!");
  };

  return (
    <div>

      <h2>Upload Photo</h2>

      <input
        type="file"
        onChange={(e) => setFile(e.target.files[0])}
      />

      <br/>

      <button onClick={uploadPhoto}>
        Upload Photo
      </button>

    </div>
  );
}

export default UploadPage;