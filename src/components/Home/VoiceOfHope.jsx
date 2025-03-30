import React from "react";
import { Splide, SplideSlide } from "@splidejs/react-splide";
import "@splidejs/splide/dist/css/themes/splide-default.min.css";
import CarouselCard from "./CarouselCard";
import "../../css/home/VoiceOfHope.css";

const VoicesOfHope = () => {
  const cancerPatients = [
    {
      img: "https://www.india.com/wp-content/uploads/2017/11/Cancer-patient.jpg",
      name: "Rajesh Kumar",
      cancerType: "Lung Cancer",
      location: "Mumbai, India",
      description:
        "Rajesh, 50, courageously battles stage 4 lung cancer with the unwavering support of his family and friends, spreading hope and awareness.",
    },
    {
      img: "https://www.shutterstock.com/image-photo/happy-smiling-indian-recovered-breast-260nw-2346325923.jpg",
      name: "Priya Sharma",
      cancerType: "Breast Cancer",
      location: "Delhi, India",
      description:
        "Priya, 38, shares her inspiring journey overcoming breast cancer, advocating for early screening and supporting fellow survivors.",
    },
    {
      img: "https://www.shutterstock.com/image-photo/indian-portrait-happy-cancer-patient-260nw-2341118991.jpg",
      name: "Amit Patel",
      cancerType: "Prostate Cancer",
      location: "Bangalore, India",
      description:
        "Amit, 55, bravely confronts prostate cancer treatment, emphasizing the importance of regular health check-ups for men.",
    },
  ];

  return (
    <div className="voices-of-hope-container">
      <h1 className="voices-of-hope-heading">Voices Of Hope</h1>
      <p className="voices-of-hope-subheading">
        Lorem ipsum dolor sit amet consectetur adipisicing elit.
      </p>

      {/* Splide Carousel */}
      <Splide
        options={{
          rewind: true,
          perPage: 1,
          breakpoints: {
            1024: {
              perPage: 1,
            },
            768: {
              perPage: 1,
            },
          },
        }}
      >
        {cancerPatients.map((patient, index) => (
          <SplideSlide key={index}>
            <CarouselCard data={patient} />
          </SplideSlide>
        ))}
      </Splide>

      {/* Line and Buttons Section */}
      <div className="voices-of-hope-line">
        {/* Navigation Buttons */}
        <button
          id="left"
          className="voices-of-hope-button voices-of-hope-button-left"
        >
          <span>◀︎</span>
        </button>
        <button
          id="right"
          className="voices-of-hope-button voices-of-hope-button-right"
        >
          <span>▶︎</span>
        </button>

        {/* Video Grid */}
        <div className="voices-of-hope-video-grid">
          <iframe
            className="voices-of-hope-video"
            src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn"
            title="YouTube video player"
            frameBorder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            referrerPolicy="strict-origin-when-cross-origin"
            allowFullScreen
          ></iframe>
          <iframe
            className="voices-of-hope-video"
            src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn"
            title="YouTube video player"
            frameBorder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            referrerPolicy="strict-origin-when-cross-origin"
            allowFullScreen
          ></iframe>
          <iframe
            className="voices-of-hope-video"
            src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn"
            title="YouTube video player"
            frameBorder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            referrerPolicy="strict-origin-when-cross-origin"
            allowFullScreen
          ></iframe>
        </div>
      </div>
    </div>
  );
};

export default VoicesOfHope;
