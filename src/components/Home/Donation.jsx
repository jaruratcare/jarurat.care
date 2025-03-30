import React from "react";
import HeartDonateIcon from "../../assets/svg/HeartDonateIcon.jsx";
import DonateBg from "../../assets/svg/Donatebg.jsx";
import SeekSupportIcon from "../../assets/svg/SeekSupportIcon.jsx";
import MemberIcon from "../../assets/svg/MemberIcon.jsx";
import Button from "./Button.jsx";
import "../../css/home/Donation.css";

const SupportDonateMember = () => {
  return (
    <div className="support-donate-member-container">
      {/* Background */}
      <div className="support-donate-member-bg">
        <DonateBg />
      </div>

      {/* Content Grid */}
      <div className="support-donate-member-grid">
        {/* Seek Support */}
        <div className="support-donate-member-section">
          <span className="support-donate-member-icon">
            <SeekSupportIcon />
          </span>
          <h2 className="support-donate-member-heading">Seek Support</h2>
          <p className="support-donate-member-paragraph">
            Our NGO is here to help you through your cancer journey.{" "}
            <span>Reach out to us today.</span>
          </p>
          <Button text="Seek Support" />
        </div>

        {/* Donate */}
        <div className="support-donate-member-section middle">
          <span className="support-donate-member-icon">
            <HeartDonateIcon />
          </span>
          <h2 className="support-donate-member-heading">Donate</h2>
          <p className="support-donate-member-paragraph">
            One Life at a Time. <span>Donate</span> today and be the light that
            brings hope and healing to cancer patients.
          </p>
          <Button text="Donate Now" />
        </div>

        {/* Become a Member */}
        <div className="support-donate-member-section">
          <span className="support-donate-member-icon">
            <MemberIcon />
          </span>
          <h2 className="support-donate-member-heading">Become a Member</h2>
          <p className="support-donate-member-paragraph">
            Be a hero. <span>Volunteer</span> with us and provide support and
            hope to those affected by cancer.
          </p>
          <Button text="Get Started" />
        </div>
      </div>
    </div>
  );
};

export default SupportDonateMember;
