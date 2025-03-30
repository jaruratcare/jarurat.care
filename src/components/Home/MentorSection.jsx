import React from "react";
import image2 from "../../assets/images/care-visual.png";
import imageBlur from "../../assets/images/image-blur.png";
import "../../css/home/MentorSection.css";

const MentorSection = () => {
  return (
    <div className="mentor-section">
      {/* Dark Blue Background */}
      <div className="background-blue">
        <div></div>
      </div>

      {/* Content Section */}
      <div className="content-section">
        <div className="text-content">
          <h1 className="heading">
            Find a Mentor for Your <br />
            <span className="highlight">Cancer</span> Journey
          </h1>
          <p className="subheading">You Don't Have to Face Cancer Alone</p>
          <p className="description">
            Connect with a compassionate mentor who understands your challenges
            and can offer personal support and guidance.
          </p>
          <button className="mentor-button">Get a Mentor</button>
        </div>
      </div>

      {/* Blurred Image Section - Full Height & Hidden on Small/Medium */}
      <div className="blurred-image">
        <img src={imageBlur} alt="Blurred effect" />
      </div>

      {/* Doctor with Patient Image */}
      <div className="doctor-image">
        <img src={image2} alt="Doctor with patient" />
      </div>
    </div>
  );
};

export default MentorSection;
