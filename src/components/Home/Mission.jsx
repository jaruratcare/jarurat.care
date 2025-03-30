import React from "react";
import medicalRibbon from "../../assets/images/medical-ribbon.png";
import "../../css/home/Mission.css";

const MissionSection = () => {
  return (
    <div className="mission-section">
      <div className="mission-content">
        <p className="mission-subtitle">Who we are</p>
        <h1 className="mission-title">
          Our mission is to support and empower cancer patients and their
          families.
        </h1>
        <h2 className="mission-description">
          Through comprehensive programs, Jarurat Care provides vital support to
          those affected by cancer. We offer emotional, financial, and practical
          assistance to help patients and their families navigate their cancer
          journey.
        </h2>
      </div>
      <img
        src={medicalRibbon}
        alt="Medical Ribbon"
        className="medical-ribbon"
      />
    </div>
  );
};

export default MissionSection;
