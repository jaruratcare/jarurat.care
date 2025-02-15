import React from "react";
import SendIcon from "../../assets/svg/SendIcon";
import EllipseImage from "../../assets/images/Ellipse64.png";
import Button from "./Button";

const SupportSection = () => {
  const supportData = [
    {
      title: "Patient Advocacy",
      description:
        "We stand by your side, helping navigate treatment options and healthcare systems",
    },
    {
      title: "Emotional Support",
      description:
        "Access to counseling and support groups to help you cope with your diagnosis",
    },
    {
      title: "Education Resources",
      description:
        "Up-to-date information on treatments, clinical trials, and cancer management",
    },
    {
      title: "Cancer Connect",
      description:
        "A community of dedicated caregivers who will help you navigate in your uncertain times",
    },
  ];

  return (
    <div className="relative min-h-screen flex flex-col items-center mt-20 px-6 md:px-12 lg:px-24 mb-10">
      {/* Background Image */}
      <img
        src={EllipseImage}
        alt="Background"
        className="absolute top-0 left-0 w-full h-auto z-[-1]"
      />

      {/* Heading Section */}
      <div className="text-center mb-12 relative z-10 w-full mt-4">
        <h1 className="text-4xl font-bold mb-4 pt-[90px] text-[#0D2561]">
          How We Support You
        </h1>
        <p className="text-xl mb-8 text-[#0D2561]">
          Comprehensive care and support throughout your cancer journey
        </p>
      </div>

      {/* Support Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8 mb-12 w-full">
        {supportData.map((item, index) => (
          <div
            key={index}
            className="group card p-6 border-2 rounded-lg shadow-md bg-white h-96 flex flex-col justify-between items-center text-center relative overflow-hidden border-[#2C72B9] transition-all duration-500 cursor-pointer hover:bg-gradient-to-br hover:from-[#E8DCFF] hover:via-[#D9EDFE] hover:to-[#EBE1FF]"
          >
            <div className="flex justify-center mb-4 mt-4">
              <SendIcon />
            </div>
            <h2 className="text-2xl font-semibold text-[#0D2561]">
              {item.title}
            </h2>
            <p className="text-[#0D2561] text-lg">{item.description}</p>

            {/* Animated Button */}
            <button className="relative inline-block px-4 py-2 text-[#2C72B9] font-bold underline underline-offset-2 rounded-full overflow-hidden transition-all duration-500 hover:bg-[#DBEAFE] hover:scale-105">
              Know More
            </button>
          </div>
        ))}
      </div>

      {/* Main CTA Button */}
      <div className="text-center">
        <Button text={"Know More"} />
      </div>
    </div>
  );
};

export default SupportSection;
