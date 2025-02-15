const Button = ({ text }) => {
  return (
    <button className="relative z-10 mt-8 px-6 py-3 bg-blue-700 text-lg md:text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease-in-out">
      <span className="absolute bottom-0 left-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-500 ease-in-out group-hover:w-full group-hover:h-full group-hover:bottom-0 group-hover:left-0 group-hover:right-0"></span>
      <span className="relative z-10">{text}</span>
    </button>
  );
};

export default Button;
