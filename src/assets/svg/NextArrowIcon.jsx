import React from "react";

const ArrowIcon = ({ width = 16, height = 28, stroke = "#474747" }) => {
  return (
    <svg
      width={width}
      height={height}
      viewBox="0 0 16 28"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M2.25 26L14.25 14L2.25 2"
        stroke={stroke}
        strokeWidth="3"
        strokeLinecap="round"
        strokeLinejoin="round"
      />
    </svg>
  );
};

export default ArrowIcon;
