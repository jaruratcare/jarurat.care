import React from "react";
import image2 from "../../assets/images/care-visual.png";
import imageBlur from "../../assets/images/image-blur.png";

const MentorSection = () => {
  return (
    <div className="relative bg-white rounded-lg overflow-hidden shadow-lg max-w-6xl mx-auto mt-10 mb-10 p-4 lg:p-6">
      {/* Dark Blue Background */}
      <div className="absolute inset-0 flex items-center">
        <div className="w-full md:w-1/2 h-full bg-[#132442]"></div>
      </div>

      {/* Content Section */}
      <div className="relative z-10 flex flex-col md:flex-row items-center md:items-start">
        <div className="p-6 md:p-8 w-full md:w-1/2 text-center md:text-left">
          <h1 className="text-3xl md:text-4xl font-bold text-white leading-tight">
            Find a Mentor for Your <br />
            <span className="text-[#D6B7FF]">Cancer</span> Journey
          </h1>
          <p className="mt-4 text-white text-lg md:text-xl">
            You Don't Have to Face Cancer Alone
          </p>
          <p className="mt-2 text-white">
            Connect with a compassionate mentor who understands your challenges
            and can offer personal support and guidance.
          </p>
          <button className="mt-6 px-6 py-3 font-semibold text-[#132442] rounded-full bg-[#D6B7FF] hover:bg-[#b89fe6] transition-all cursor-pointer">
            Get a Mentor
          </button>
        </div>
      </div>

      {/* Blurred Image Section - Full Height & Hidden on Small/Medium */}
      <div className="absolute inset-0 w-1/2 h-full hidden md:hidden lg:block">
        <img
          src={imageBlur}
          alt="Blurred effect"
          className="object-cover w-full h-full blur-lg"
        />
      </div>

      {/* Doctor with Patient Image */}
      <div className="absolute inset-y-0 right-0 w-full md:w-1/2 h-full hidden md:block">
        <img
          src={image2}
          alt="Doctor with patient"
          className="object-cover w-full h-full"
        />
      </div>
    </div>
  );
};

export default MentorSection;
