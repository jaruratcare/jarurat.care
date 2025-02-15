import React from "react";

const PrevButtonIcon = ({ width = 16, height = 28, color = "#474747" }) => {
  return (
    <svg
      width={width}
      height={height}
      viewBox="0 0 16 28"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M14.25 26L2.25 14L14.25 2"
        stroke={color}
        strokeWidth="3"
        strokeLinecap="round"
        strokeLinejoin="round"
      />
    </svg>
  );
};

export default PrevButtonIcon;
