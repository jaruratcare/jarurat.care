import React from "react";
import "../../css/home/Button.css"; // Import the external CSS file

const Button = ({ text }) => {
  return (
    <button className="button">
      <span className="button-text">{text}</span>
    </button>
  );
};

export default Button;
