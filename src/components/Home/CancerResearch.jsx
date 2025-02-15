import React, { useState } from "react";
import PrevButtonIcon from "../../assets/svg/PrevButtonIcon"; // Replace with your React icon component
import NextButtonIcon from "../../assets/svg/NextArrowIcon"; // Replace with your React icon component
import SearchIcon from "../../assets/svg/SearchIcon"; // Replace with your React icon component
import WaveGradient from "../../assets/images/Wave-gradient.png"; // Replace with your image path

const ArticlesCarousel = () => {
  const [searchQuery, setSearchQuery] = useState("Breast Cancer");
  const [currentIndex, setCurrentIndex] = useState(0);

  const articles = [
    {
      title: "Global Cancer Incidence and Mortality Rates and Trends",
      description:
        "There are limited published data on recent cancer incidence and mortality trends worldwide...",
      link: "#",
    },
    {
      title: "Measuring cancer evolution from the genome.",
      description:
        "The temporal dynamics of cancer evolution remain elusive, because it is impractical to lon...",
      link: "#",
    },
    {
      title: "The Role of Telomerase in Breast Cancer’s Response to Therapy.",
      description:
        "Currently, breast cancer appears to be the most widespread cancer in the world and the most c...",
      link: "#",
    },
  ];

  const nextSlide = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % articles.length);
  };

  const prevSlide = () => {
    setCurrentIndex(
      (prevIndex) => (prevIndex - 1 + articles.length) % articles.length
    );
  };

  return (
    <div className="relative mb-8">
      <img
        src={WaveGradient}
        alt="Wave Gradient"
        className="absolute top-0 left-0 w-full h-full object-cover z-[-1]"
      />
      <div className="flex flex-col items-center py-10 px-4">
        <h1 className="text-2xl md:text-4xl font-semibold mb-4 text-[#0D2561] text-center">
          Read the Latest in Cancer Research
        </h1>
        <p className="mb-6 text-[#0D2561] text-center">
          Explore the latest studies and breakthroughs in cancer research.
        </p>

        <div className="relative w-full max-w-xl mb-6">
          <div className="relative">
            <input
              type="text"
              placeholder="Search topic"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="w-full pl-12 py-2 border rounded-full bg-[#F4F4F4] text-gray-700 placeholder-[#6A5A58] focus:outline-none"
            />
            <div className="absolute inset-y-0 left-0 flex items-center pl-3">
              <SearchIcon />
            </div>
          </div>
        </div>
        <div className="text-lg mb-10 text-[#04509C] text-sm md:text-base">
          Results for <strong>"{searchQuery}"</strong>
        </div>

        <div className="relative w-full flex flex-col items-center">
          <div className="flex space-x-2 mb-6 ml-auto mr-4 md:mr-12 -mt-6">
            <button
              className="w-[40px] h-[40px] p-0 border rounded-full flex items-center justify-center bg-white"
              onClick={prevSlide}
            >
              <PrevButtonIcon />
            </button>
            <button
              className="w-[40px] h-[40px] p-0 border-2 rounded-full flex items-center justify-center"
              onClick={nextSlide}
            >
              <NextButtonIcon />
            </button>
          </div>

          <div className="w-[80%] overflow-hidden">
            <div
              className="w-full flex transition-transform duration-500"
              style={{ transform: `translateX(-${currentIndex * 100}%)` }}
            >
              {articles.map((article, index) => (
                <div
                  key={index}
                  className="min-w-full md:min-w-[50%] lg:min-w-[33.333%] bg-white p-4 shadow-lg rounded-md mx-2 flex flex-col"
                  style={{ flex: "1 0 0" }}
                >
                  <div className="flex flex-col flex-grow">
                    <h2 className="font-semibold mb-2 text-[#04509C] text-left break-words">
                      {article.title}
                    </h2>
                    <p className="text-sm mb-4 text-[#576171] text-left break-words">
                      {article.description}
                    </p>
                  </div>
                  {/* Updated Button with Animation */}
                  <button
                    className="relative px-4 py-1 bg-[#0155BD] text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center mt-auto overflow-hidden"
                    style={{ alignSelf: "flex-start" }}
                  >
                    <span className="absolute bottom-0 left-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-500 ease-in-out group-hover:w-full group-hover:h-full group-hover:bottom-0 group-hover:left-0 group-hover:right-0"></span>
                    <span className="relative z-10">Read Now</span>
                  </button>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ArticlesCarousel;
