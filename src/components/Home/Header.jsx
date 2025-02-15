import { useState } from "react";
import { FaBars, FaTimes } from "react-icons/fa";
import logo from "../../assets/logo/image.webp";

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <nav className="w-full h-[72px] flex justify-between items-center bg-white p-4 px-6 md:px-12 lg:px-16 relative">
      <div className="logo">
        <img src={logo} alt="logo" className="h-12" />
      </div>

      {/* Desktop Menu */}
      <ul className="lg:flex hidden list-none m-0 p-0 gap-6 md:gap-8">
        {[
          { name: "Home", path: "/" },
          { name: "About Us", path: "/about" },
          { name: "News and Blogs", path: "/blogs" },
          { name: "Get Involved", path: "/getInvolved" },
          { name: "Contact Us", path: "/contactUs" },
        ].map((item) => (
          <li key={item.path}>
            <a
              href={item.path}
              className="text-blue-900 no-underline hover:font-bold"
            >
              {item.name}
            </a>
          </li>
        ))}
      </ul>

      {/* Mobile & Medium Menu Button */}
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="lg:hidden text-blue-900 text-2xl z-50"
      >
        {isOpen ? <FaTimes /> : <FaBars />}
      </button>

      {/* Mobile Menu (Dropdown from Top) */}
      <div
        className={`lg:hidden fixed top-0 left-0 w-full bg-white shadow-md z-40 transform ${
          isOpen ? "translate-y-0" : "-translate-y-full"
        } transition-transform duration-300 flex flex-col items-center py-6`}
      >
        <ul className="flex flex-col items-center gap-6 w-full">
          {[
            { name: "Home", path: "/" },
            { name: "About Us", path: "/about" },
            { name: "News and Blogs", path: "/blogs" },
            { name: "Get Involved", path: "/getInvolved" },
            { name: "Contact Us", path: "/contactUs" },
          ].map((item) => (
            <li key={item.path} className="w-full text-center">
              <a
                href={item.path}
                className="text-blue-900 text-xl font-semibold block py-2"
                onClick={() => setIsOpen(false)}
              >
                {item.name}
              </a>
            </li>
          ))}
        </ul>
      </div>

      {/* Language & Donate Selects */}
      <div className="hidden lg:flex gap-3 mr-7">
        <div className="relative flex items-center justify-between px-3 text-blue-800 rounded-full border-2 border-blue-800">
          <select className="w-full p-2 cursor-pointer bg-transparent outline-none">
            <option value="en">English</option>
            <option value="hi">हिन्दी</option>
          </select>
        </div>
        <div className="relative flex items-center justify-between px-3 bg-blue-800 text-white rounded-full border-2 border-blue-800">
          <select className="w-full p-2 cursor-pointer bg-transparent outline-none">
            <option value="donate">Donate</option>
          </select>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
