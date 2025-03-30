import { FaInstagram, FaTwitter, FaFacebook } from "react-icons/fa";
import "../../css/home/Footer.css"; // Import external CSS file

const Footer = () => {
  const scrollToTop = () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  return (
    <footer className="footer-container">
      {/* Top Section */}
      <div className="footer-top">
        <div className="footer-logo">
          <img src="logo/image.webp" alt="logo" className="footer-logo-img" />
        </div>
        <div className="footer-title">
          <h1>
            Jaruratcare <span className="highlight">Foundation</span>
          </h1>
          <p>Jaisi Jarurat, Vaisi Care</p>
        </div>
        <div className="footer-buttons">
          <button className="support-btn">Seek Support</button>
          <button className="donate-btn">Donate</button>
        </div>
      </div>

      {/* Divider */}
      <hr className="footer-divider" />

      {/* Middle Section */}
      <div className="footer-middle">
        <div className="footer-links">
          <p>Home</p>
          <p>Mission</p>
          <p>Volunteer</p>
          <p>Locate Hospitals</p>
          <p>Terms & Conditions</p>
        </div>
        <div className="footer-links">
          <p>About Us</p>
          <p>Blogs</p>
          <p>News</p>
          <p>Contact Us</p>
        </div>
        <div className="footer-social">
          <p>Connect with us</p>
          <div className="social-icons">
            <FaInstagram className="icon" />
            <FaTwitter className="icon" />
            <FaFacebook className="icon" />
          </div>
        </div>
      </div>

      {/* Divider */}
      <hr className="footer-divider" />

      {/* Bottom Section */}
      <div className="footer-bottom">
        <div className="footer-policy">
          <p>Privacy policy</p>
          <p>Terms of service</p>
        </div>
        <div>
          <p>
            &copy;{new Date().getFullYear()} HANDSCART LCC. All rights reserved
          </p>
        </div>
        <div className="back-to-top" onClick={scrollToTop}>
          <p>Back to Top &uarr;</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
