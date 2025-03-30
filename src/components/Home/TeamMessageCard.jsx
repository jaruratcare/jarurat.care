import React from "react";
import "../../css/home/TeamMessageCard.css"; // Import the external CSS file

const TeamMessageCard = ({ data }) => {
  return (
    <div className="team-message-card-container">
      <div className="team-message-card">
        {/* Icon */}
        <div className="team-message-icon">
          <svg viewBox="0 0 32 32" transform="rotate(180)">
            <g id="SVGRepo_iconCarrier">
              <g>
                <g id="right_x5F_quote">
                  <g>
                    <path d="M0,4v12h8c0,4.41-3.586,8-8,8v4c6.617,0,12-5.383,12-12V4H0z" />
                    <path d="M20,4v12h8c0,4.41-3.586,8-8,8v4c6.617,0,12-5.383,12-12V4H20z" />
                  </g>
                </g>
              </g>
            </g>
          </svg>
        </div>

        {/* Message Content */}
        <p className="team-message-content">{data.message}</p>
        <p className="team-message-name">{data.name}</p>
        <p className="team-message-designation">{data.designation}</p>
      </div>
    </div>
  );
};

export default TeamMessageCard;
