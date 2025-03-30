import { useState } from "react";
import storyBg from "../../assets/images/Story-bg.png";
import "../../css/home/StorySection.css";

const StorySection = () => {
  const [currentIndex, setCurrentIndex] = useState(0);

  const slides = [
    {
      title: "Where it all started",
      background: storyBg,
      description:
        "Rekha's last trip before she started her battle with Cholangiocarcinoma and succumbed on 25th December 2023",
    },
    {
      background: storyBg,
      overlayText:
        "Our story commenced in December 2023, marking a pivotal moment in our lives. It was then that we faced the profound loss of our cherished mother, who bravely battled Cholangiocarcinoma, a form of bile duct cancer, for seven months. Motivated by our experiences and driven by the same determination any devoted son or daughter would possess, we established the Jarurat Care Foundation with the intent to rectify these systemic shortcomings and improve the healthcare landscape for others.",
    },
  ];

  const handlePrev = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === 0 ? slides.length - 1 : prevIndex - 1
    );
  };

  const handleNext = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === slides.length - 1 ? 0 : prevIndex + 1
    );
  };

  return (
    <div className="story-section">
      <div className="slider-container">
        <div
          className="slides-wrapper"
          style={{ transform: `translateX(-${currentIndex * 100}%)` }}
        >
          {slides.map((slide, index) => (
            <div
              key={index}
              className="slide"
              style={{ backgroundImage: `url(${slide.background})` }}
            >
              {slide.title ? (
                <h1 className="slide-title">{slide.title}</h1>
              ) : (
                <div className="slide-overlay">
                  <p className="slide-text">{slide.overlayText}</p>
                </div>
              )}
              {slide.description && (
                <span className="slide-description">{slide.description}</span>
              )}
            </div>
          ))}
        </div>
      </div>
      {/* Navigation Buttons */}
      <div className="navigation-buttons">
        <button onClick={handlePrev} className="nav-button">
          <svg
            width="16"
            height="28"
            viewBox="0 0 16 28"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M14.25 26L2.25 14L14.25 2"
              stroke="#474747"
              strokeWidth="3"
              strokeLinecap="round"
              strokeLinejoin="round"
            />
          </svg>
        </button>
        <button onClick={handleNext} className="nav-button">
          <svg
            width="16"
            height="28"
            viewBox="0 0 16 28"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M2.25 26L14.25 14L2.25 2"
              stroke="#474747"
              strokeWidth="3"
              strokeLinecap="round"
              strokeLinejoin="round"
            />
          </svg>
        </button>
      </div>
    </div>
  );
};

export default StorySection;
