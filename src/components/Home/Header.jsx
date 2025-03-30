import { useState } from "react";
import { FaBars, FaTimes } from "react-icons/fa";
import logo from "../../assets/logo/image.webp";
import "../../css/home/Header.css";

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <nav className="navbar">
      <div className="logo">
        <img src={logo} alt="logo" />
      </div>

      {/* Desktop Menu */}
      <ul className="desktop-menu">
        {[
          { name: "Home", path: "/" },
          { name: "About Us", path: "/about" },
          { name: "News and Blogs", path: "/blogs" },
          { name: "Get Involved", path: "/getInvolved" },
          { name: "Contact Us", path: "/contactUs" },
        ].map((item) => (
          <li key={item.path}>
            <a href={item.path}>{item.name}</a>
          </li>
        ))}
      </ul>

      {/* Mobile & Medium Menu Button */}
      <button onClick={() => setIsOpen(!isOpen)} className="mobile-menu-button">
        {isOpen ? <FaTimes /> : <FaBars />}
      </button>

      {/* Mobile Menu (Dropdown from Top) */}
      <div className={`mobile-menu ${isOpen ? "open" : ""}`}>
        <ul>
          {[
            { name: "Home", path: "/" },
            { name: "About Us", path: "/about" },
            { name: "News and Blogs", path: "/blogs" },
            { name: "Get Involved", path: "/getInvolved" },
            { name: "Contact Us", path: "/contactUs" },
          ].map((item) => (
            <li key={item.path}>
              <a href={item.path} onClick={() => setIsOpen(false)}>
                {item.name}
              </a>
            </li>
          ))}
        </ul>
      </div>

      {/* Language & Donate Selects */}
      <div className="language-donate-container">
        <div className="language-select">
          <select>
            <option value="en">English</option>
            <option value="hi">हिन्दी</option>
          </select>
        </div>
        <div className="donate-select">
          <select>
            <option value="donate">Donate</option>
          </select>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
