import React, { useState } from "react";
import PrevButtonIcon from "../../assets/svg/PrevButtonIcon";
import NextButtonIcon from "../../assets/svg/NextArrowIcon";
import SearchIcon from "../../assets/svg/SearchIcon";
import WaveGradient from "../../assets/images/Wave-gradient.png";
import "../../css/home/CancerResearch.css";

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
    <div className="articles-carousel-container">
      <img
        src={WaveGradient}
        alt="Wave Gradient"
        className="wave-gradient-background"
      />
      <div className="articles-carousel-content">
        <h1 className="articles-carousel-heading">
          Read the Latest in Cancer Research
        </h1>
        <p className="articles-carousel-subheading">
          Explore the latest studies and breakthroughs in cancer research.
        </p>

        <div className="search-container">
          <div className="relative">
            <input
              type="text"
              placeholder="Search topic"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="search-input"
            />
            <div className="search-icon">
              <SearchIcon />
            </div>
          </div>
        </div>
        <div className="results-text">
          Results for <strong>"{searchQuery}"</strong>
        </div>

        <div className="relative w-full flex flex-col items-center">
          <div className="carousel-buttons">
            <button className="carousel-button" onClick={prevSlide}>
              <PrevButtonIcon />
            </button>
            <button
              className="carousel-button carousel-button-next"
              onClick={nextSlide}
            >
              <NextButtonIcon />
            </button>
          </div>

          <div className="carousel-items-container">
            <div
              className="carousel-items"
              style={{ transform: `translateX(-${currentIndex * 100}%)` }}
            >
              {articles.map((article, index) => (
                <div key={index} className="carousel-item">
                  <div className="flex flex-col flex-grow">
                    <h2 className="carousel-item-title">{article.title}</h2>
                    <p className="carousel-item-description">
                      {article.description}
                    </p>
                  </div>
                  <button className="read-now-button">
                    <span>Read Now</span>
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
