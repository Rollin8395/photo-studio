import UploadPage from "./pages/UploadPage";
import OrdersPage from "./pages/OrdersPage";
import GalleryPage from "./pages/GalleryPage";
import Navbar from "./components/Navbar";

function App(){

  return(

    <div className="app-container">

      <Navbar/>

      <UploadPage/>

      <GalleryPage/>

      <OrdersPage/>

    </div>

  )

}

export default App;