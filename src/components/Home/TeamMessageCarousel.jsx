import React from "react";
import { Splide, SplideSlide } from "@splidejs/react-splide";
import Button from "./Button";
import "@splidejs/react-splide/css";
import TeamMessageCard from "./TeamMessageCard";
import "../../css/home/TeamMessageCarousel.css"; // Import the external CSS file

const memberData = [
  {
    name: "Priyanka",
    designation: "Co-Founder",
    message:
      "At Jaruart Care, our mission is to bring hope and healing to every person battling cancer. We believe in the power of community support, compassion, and innovation to make a real difference in the lives of those we serve.",
  },
  // Add more team members as needed
];

const TeamMessageCarousel = () => {
  return (
    <div className="team-message-carousel-container">
      <h1 className="team-message-heading">Hear From Our Team</h1>
      <Splide
        options={{
          type: "loop",
          perPage: 1,
          perMove: 1,
          gap: "1rem",
          autoplay: true,
          interval: 5000,
          pauseOnHover: true,
          pagination: true,
          arrows: true,
          breakpoints: {
            768: { perPage: 1 },
            1024: { perPage: 1 },
          },
        }}
      >
        {memberData.map((data, index) => (
          <SplideSlide key={index}>
            <TeamMessageCard data={data} />
          </SplideSlide>
        ))}
      </Splide>
      <div className="team-message-button-container">
        <button className="team-message-button team-message-button-about">
          About Us
        </button>

        {/* <Button text="About Us" /> */}
        <button className="team-message-button team-message-button-contact">
          Contact Us
        </button>
      </div>
    </div>
  );
};

export default TeamMessageCarousel;
