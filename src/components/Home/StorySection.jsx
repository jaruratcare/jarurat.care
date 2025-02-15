import { useState } from "react";
import storyBg from "../../assets/images/Story-bg.png";

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
    <div className="relative w-full lg:h-screen flex justify-center items-center px-4 sm:h-fit sm:mt-10 sm:mb-10">
      <div className="relative w-full max-w-3xl h-[366px] overflow-hidden rounded-xl">
        <div
          className="w-full h-full flex transition-transform duration-500"
          style={{ transform: `translateX(-${currentIndex * 100}%)` }}
        >
          {slides.map((slide, index) => (
            <div
              key={index}
              className="min-w-full min-h-full bg-cover bg-no-repeat bg-center flex justify-center items-center relative"
              style={{ backgroundImage: `url(${slide.background})` }}
            >
              {slide.title ? (
                <h1 className="text-3xl md:text-5xl font-semibold text-white font-serif">
                  {slide.title}
                </h1>
              ) : (
                <div className="w-full h-full bg-black bg-opacity-50 flex justify-center items-center px-6">
                  <p className="text-xs sm:text-sm md:text-base text-gray-300 leading-6 text-center max-w-lg">
                    {slide.overlayText}
                  </p>
                </div>
              )}
              {slide.description && (
                <span className="text-gray-300 absolute bottom-4 left-4 w-[250px] text-xs md:text-sm leading-4">
                  {slide.description}
                </span>
              )}
            </div>
          ))}
        </div>
      </div>
      {/* Navigation Buttons */}
      <div className="absolute bottom-10 flex space-x-4">
        <button
          onClick={handlePrev}
          className="p-2 border-2 border-gray-600 rounded-full"
        >
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
        <button
          onClick={handleNext}
          className="p-2 border-2 border-gray-600 rounded-full"
        >
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
