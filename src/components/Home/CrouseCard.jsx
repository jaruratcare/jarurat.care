import React from "react";

const CarouselCard = ({ data }) => {
  return (
    <div
      style={{
        boxShadow: "0 2rem 3rem rgba(132, 139, 200, 0.18)",
        border: "2px solid rgba(132, 139, 200, 0.18)",
      }}
      className="grid grid-cols-1 md:grid-cols-[1fr_1.5fr] border border-black max-w-4xl min-h-[20rem] mx-auto rounded-lg transform transition-transform hover:scale-105"
    >
      <img
        className="w-full h-full object-cover rounded-t-lg md:rounded-l-lg md:rounded-tr-none"
        src={data?.img}
        alt={data?.name}
      />
      <div className="content p-4 flex flex-col items-center md:items-start">
        <div className="avatar flex flex-col items-center md:items-start m-4">
          <img
            className="h-16 w-16 rounded-full"
            src={data?.img}
            alt={data?.name}
          />
          <h2 className="text-darkblue mt-2 text-lg font-medium">
            {data?.name}
          </h2>
          <span className="text-darkblue mt-1 text-sm">
            {data?.cancerType}, {data?.location}
          </span>
        </div>
        <div className="story flex items-start relative mt-4">
          <svg
            className="h-10 w-10 ml-4 md:ml-8 flex-shrink-0"
            height="40px"
            width="40px"
            version="1.1"
            id="Capa_1"
            xmlns="http://www.w3.org/2000/svg"
            xmlnsXlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 32 32"
            xmlSpace="preserve"
            fill="#000000"
            transform="rotate(180)"
          >
            <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
            <g
              id="SVGRepo_tracerCarrier"
              strokeLinecap="round"
              strokeLinejoin="round"
            ></g>
            <g id="SVGRepo_iconCarrier">
              <g>
                <g id="right_x5F_quote">
                  <g>
                    <path
                      style={{ fill: "#2d9a4e" }}
                      d="M0,4v12h8c0,4.41-3.586,8-8,8v4c6.617,0,12-5.383,12-12V4H0z"
                    ></path>
                    <path
                      style={{ fill: "#2d9a4e" }}
                      d="M20,4v12h8c0,4.41-3.586,8-8,8v4c6.617,0,12-5.383,12-12V4H20z"
                    ></path>
                  </g>
                </g>
              </g>
            </g>
          </svg>
          <p className="text-darkblue ml-4 md:ml-12 mr-4 md:mr-8 flex-grow text-sm md:text-base">
            {data?.description}
          </p>
        </div>
      </div>
    </div>
  );
};

export default CarouselCard;
