import React from "react";
import SendIcon from "../../assets/svg/SendIcon";
import EllipseImage from "../../assets/images/Ellipse64.png";
import Button from "./Button";
import "../../css/home/Support.css";

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
    <div className="support-section">
      {/* Background Image */}
      <img src={EllipseImage} alt="Background" className="support-bg-image" />

      {/* Heading Section */}
      <div className="support-heading">
        <h1 className="support-title">How We Support You</h1>
        <p className="support-subtitle">
          Comprehensive care and support throughout your cancer journey
        </p>
      </div>

      {/* Support Cards */}
      <div className="support-grid">
        {supportData.map((item, index) => (
          <div key={index} className="support-card">
            <div className="support-icon">
              <SendIcon />
            </div>
            <h2 className="support-card-title">{item.title}</h2>
            <p className="support-card-text">{item.description}</p>
            <button className="support-button">Know More</button>
          </div>
        ))}
      </div>

      {/* Main CTA Button */}
      <div className="support-cta">
        <Button text={"Know More"} />
      </div>
    </div>
  );
};

export default SupportSection;
