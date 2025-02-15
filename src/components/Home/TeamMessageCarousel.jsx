import React from "react";
import { Splide, SplideSlide } from "@splidejs/react-splide";
import "@splidejs/react-splide/css";
import TeamMessageCard from "./TeamMessageCard";

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
    <div className="container mx-auto px-4 h-fit">
      <h1 className="text-blue-900 text-3xl font-bold text-center my-16 mb-4">
        Hear From Our Team
      </h1>
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
      <div className="flex justify-center items-center m-8 gap-4">
        <button className="border-2 border-blue-900 px-4 py-2 text-blue-900 rounded-3xl">
          About Us
        </button>
        <button className="border-2 border-blue-900 px-4 py-2 text-white bg-blue-900 rounded-3xl">
          Contact Us
        </button>
      </div>
    </div>
  );
};

export default TeamMessageCarousel;
