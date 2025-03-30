import React from "react";
import "../../css/home/CarouselCard.css";

const CarouselCard = ({ data }) => {
  return (
    <div className="carousel-card">
      <img className="carousel-card-img" src={data?.img} alt={data?.name} />
      <div className="content">
        <div className="avatar">
          <img src={data?.img} alt={data?.name} />
          <h2>{data?.name}</h2>
          <span>
            {data?.cancerType}, {data?.location}
          </span>
        </div>
        <div className="story">
          <svg
            className="quote-icon"
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
          <p>{data?.description}</p>
        </div>
      </div>
    </div>
  );
};

export default CarouselCard;
