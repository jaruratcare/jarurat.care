import React from "react";

const TeamMessageCard = ({ data }) => {
  return (
    <div className="flex items-center justify-center w-full px-4 m-2 mb-4 mt-2">
      <div className="relative md:w-3/5 bg-white shadow-lg p-6 md:p-10 rounded-md min-h-[250px] flex flex-col justify-center">
        <div className="absolute -left-6 md:-left-12 top-8 md:top-10 bg-white rounded-full flex items-center justify-center shadow-md overflow-hidden">
          <svg
            className="h-10 w-10 md:h-16 md:w-16 p-2 md:p-3 bg-green-600 rounded-full"
            viewBox="0 0 32 32"
            fill="white"
            transform="rotate(180)"
          >
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
        <p className="text-gray-800 text-lg md:text-xl leading-relaxed">
          {data.message}
        </p>
        <p className="mt-4 font-bold text-gray-900">{data.name}</p>
        <p className="text-sm text-gray-600">{data.designation}</p>
      </div>
    </div>
  );
};

export default TeamMessageCard;
