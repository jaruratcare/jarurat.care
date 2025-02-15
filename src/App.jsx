import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Footer from "./components/Home/Footer";
import Navbar from "./components/Home/Header";
import About from "./pages/About";
import Blogs from "./pages/Blogs";
import ContactUs from "./pages/ContactUs";
import Donate from "./pages/Donate";
import GetInvolved from "./pages/GetInvolved";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/blogs" element={<Blogs />} />
        <Route path="/contactUs" element={<ContactUs />} />
        <Route path="/donate" element={<Donate />} />
        <Route path="/getInvolved" element={<GetInvolved />} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
