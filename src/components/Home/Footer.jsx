import { FaInstagram, FaTwitter, FaFacebook } from "react-icons/fa";

const Footer = () => {
  const scrollToTop = () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  return (
    <footer className="bg-custom-blue font-sans py-5 px-4 sm:px-6 md:px-10 lg:px-24">
      <div className="flex flex-col md:flex-row items-center justify-between text-center md:text-left">
        <div className="logo mb-4 md:mb-0">
          <img src="logo/image.webp" alt="logo" className="h-16 md:h-20" />
        </div>
        <div>
          <h1 className="text-white text-xl sm:text-2xl md:text-3xl font-bold">
            Jaruratcare <span className="text-[#D8E74E]">Foundation</span>
          </h1>
          <p className="text-white text-sm md:text-base">
            Jaisi Jarurat, Vaisi Care
          </p>
        </div>
        <div className="flex flex-col sm:flex-row space-y-3 sm:space-y-0 sm:space-x-4 mt-4 md:mt-0">
          <button className="bg-[#D8E74E] text-black font-bold py-2 px-3 sm:px-4 md:px-5 rounded-full cursor-pointer">
            Seek Support
          </button>
          <button className="bg-[#2C72B9] text-white font-bold py-2 px-3 sm:px-4 md:px-5 rounded-full cursor-pointer">
            Donate
          </button>
        </div>
      </div>

      <hr className="my-5 border-gray-300" />

      <div className="flex flex-col md:flex-row text-white text-sm justify-between py-5 gap-5 text-center md:text-left">
        <div className="flex flex-col space-y-2">
          <p>Home</p>
          <p>Mission</p>
          <p>Volunteer</p>
          <p>Locate Hospitals</p>
          <p>Terms & Conditions</p>
        </div>
        <div className="flex flex-col space-y-2">
          <p>About Us</p>
          <p>Blogs</p>
          <p>News</p>
          <p>Contact Us</p>
        </div>
        <div className="flex flex-col space-y-2">
          <p>Connect with us</p>
          <div className="flex justify-center md:justify-start space-x-4">
            <FaInstagram className="h-6 cursor-pointer" />
            <FaTwitter className="h-6 cursor-pointer" />
            <FaFacebook className="h-6 cursor-pointer" />
          </div>
        </div>
      </div>

      <hr className="my-5 border-gray-300" />

      <div className="flex flex-col md:flex-row text-[#204D80] justify-between py-5 text-center md:text-left">
        <div className="flex flex-col sm:flex-row space-x-2 cursor-pointer mb-2 md:mb-0">
          <p>Privacy policy</p>
          <p>Terms of service</p>
        </div>
        <div>
          <p>&copy;2024 HANDSCART LCC. All rights reserved</p>
        </div>
        <div className="text-[#D8E74E] cursor-pointer" onClick={scrollToTop}>
          <p>Back to Top &uarr;</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
