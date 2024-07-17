<script>
  import { onMount } from "svelte";
 import CrouselCard from "./CrouselCard.svelte"
const cancerPatients = [
  {
    img: "https://www.india.com/wp-content/uploads/2017/11/Cancer-patient.jpg",
    name: "Rajesh Kumar",
    cancerType: "Lung Cancer",
    location: "Mumbai, India",
    description: "Rajesh, 50, courageously battles stage 4 lung cancer with the unwavering support of his family and friends, spreading hope and awareness.",
  },
  {
    img: "https://www.shutterstock.com/image-photo/happy-smiling-indian-recovered-breast-260nw-2346325923.jpg",
    name: "Priya Sharma",
    cancerType: "Breast Cancer",
    location: "Delhi, India",
    description: "Priya, 38, shares her inspiring journey overcoming breast cancer, advocating for early screening and supporting fellow survivors.",
  },
  {
    img: "https://www.shutterstock.com/image-photo/indian-portrait-happy-cancer-patient-260nw-2341118991.jpg",
    name: "Amit Patel",
    cancerType: "Prostate Cancer",
    location: "Bangalore, India",
    description: "Amit, 55, bravely confronts prostate cancer treatment, emphasizing the importance of regular health check-ups for men.",
  },
  {
    img: "https://www.shutterstock.com/image-photo/indian-portrait-happy-cancer-patient-260nw-2341118991.jpg",
    name: "Amit Patel",
    cancerType: "Prostate Cancer",
    location: "Bangalore, India",
    description: "Amit, 55, bravely confronts prostate cancer treatment, emphasizing the importance of regular health check-ups for men.",
  }
];
  let wrapper;
  let carousel;
  let firstCardWidth;
  let arrowBtns;
  let cardPerView;
  let isDragging = false;
  let isAutoPlay = true;
  let startX;
  let startScrollLeft;
  let timeoutId;

  onMount(() => {
    wrapper = document.querySelector(".wrapper");
    carousel = document.querySelector(".carousel");
    firstCardWidth = carousel.querySelector(".card").offsetWidth;
    arrowBtns = document.querySelectorAll(".line button");

    cardPerView = Math.round(carousel.offsetWidth / (carousel.offsetWidth * 0.7)); // Adjust based on 60% width

    const carouselChildrens = Array.from(carousel.children);

    carouselChildrens
      .slice(-cardPerView)
      .reverse()
      .forEach((card) => {
        carousel.insertAdjacentHTML("afterbegin", card.outerHTML);
      });

    carouselChildrens.slice(0, cardPerView).forEach((card) => {
      carousel.insertAdjacentHTML("beforeend", card.outerHTML);
    });

    carousel.classList.add("no-transition");
    carousel.scrollLeft = carousel.offsetWidth;
    carousel.classList.remove("no-transition");

    arrowBtns.forEach((btn) => {
      btn.addEventListener("click", () => {
        carousel.scrollLeft += btn.id == "left" ? -firstCardWidth : firstCardWidth;
      });
    });

    const dragStart = (e) => {
      isDragging = true;
      carousel.classList.add("dragging");
      startX = e.pageX;
      startScrollLeft = carousel.scrollLeft;
    };

    const dragging = (e) => {
      if (!isDragging) return;
      carousel.scrollLeft = startScrollLeft - (e.pageX - startX);
    };

    const dragStop = () => {
      isDragging = false;
      carousel.classList.remove("dragging");
    };

    const infiniteScroll = () => {
      const scrollPosition = carousel.scrollLeft;
      const maxScroll = carousel.scrollWidth - carousel.offsetWidth;

      if (scrollPosition <= 0) {
        carousel.classList.add("no-transition");
        carousel.scrollLeft = maxScroll - cardPerView * firstCardWidth;
        carousel.classList.remove("no-transition");
      } else if (scrollPosition >= maxScroll) {
        carousel.classList.add("no-transition");
        carousel.scrollLeft = cardPerView * firstCardWidth;
        carousel.classList.remove("no-transition");
      }

      clearTimeout(timeoutId);
      if (!wrapper.matches(":hover")) autoPlay();
    };

    const autoPlay = () => {
      if (window.innerWidth < 800 || !isAutoPlay) return;
      timeoutId = setTimeout(() => (carousel.scrollLeft += firstCardWidth), 2500);
    };
    autoPlay();

    carousel.addEventListener("mousedown", dragStart);
    carousel.addEventListener("mousemove", dragging);
    document.addEventListener("mouseup", dragStop);
    carousel.addEventListener("scroll", infiniteScroll);
    wrapper.addEventListener("mouseenter", () => clearTimeout(timeoutId));
    wrapper.addEventListener("mouseleave", autoPlay);
  });
</script>
<style>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap');

  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Poppins", sans-serif;
  }

  .wrapper {
    width: 100%;
    position: relative;
  }

  .wrapper i {
    top: 50%;
    height: 50px;
    width: 50px;
    cursor: pointer;
    font-size: 1.25rem;
    position: absolute;
    text-align: center;
    line-height: 50px;
    background: #fff;
    border-radius: 50%;
    box-shadow: 0 3px 6px rgba(0, 0, 0, 0.23);
    transform: translateY(-50%);
    transition: transform 0.1s linear;
  }

  .wrapper i:active {
    transform: translateY(-50%) scale(0.85);
  }

  .wrapper i:first-child {
    left: -22px;
  }

  .wrapper i:last-child {
    right: -22px;
  }

  .wrapper .carousel {
    display: grid;
    grid-auto-flow: column;
    grid-auto-columns: calc(60% - 40px);
    overflow-x: auto;
    scroll-snap-type: x mandatory;
    gap: 8rem;
    border-radius: 8px;
    scroll-behavior: smooth;
    padding: 1rem;
  }

  .carousel::-webkit-scrollbar {
    display: none;
  }

  .carousel :where(.card, .img) {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .carousel .card {
    scroll-snap-align: center;
    height: 342px;
    list-style: none;
    background: #fff;
    cursor: pointer;
    flex-direction: column;
    border-radius: 15px;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }

  .card {
    transition: transform 0.3s ease;
  }

  .card:hover {
    transform: scale(1.1);
  }

  .content {
    text-align: center;
    margin: 4rem 0;
    color: darkblue;
  }

  @media screen and (max-width: 900px) {
    .wrapper .carousel {
      grid-auto-columns: calc(90% - 9px);
    }
  }

  @media screen and (max-width: 600px) {
    .wrapper .carousel {
      grid-auto-columns: 100%;
    }
  }

  .line iframe {
    width: 100%;
  }

  .grid {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 2rem;
    position: relative;
  }

  .line {
    margin: 4rem 8rem;
    border-top: 1px solid darkblue;
    padding: 6rem 0rem;
    position: relative;
  }

  .right, .left {
    position: absolute;
    top: 1rem;
    right: 0;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
  }

  .left {
    right: 4rem;
  }

  button {
    display: flex;
    justify-content: center;
    align-items: center;
    color: black;
    border-radius: 50%;
    height: 3rem;
    width: 3rem;
  }

  @media screen and (max-width: 768px) {
    .line {
      margin: 2rem;
      padding: 2rem;
    }

    .grid {
      grid-template-columns: 1fr;
    }
  }
 
</style>
<div class="content">
  <h1>Voices Of Hope</h1>
  <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
</div>
<div class="wrapper" >

  <ul class="carousel">
     {#each cancerPatients as patient }
      <li class="card " id="">
<CrouselCard data={patient} />
      </li>
     {/each}
     
  </ul>

  <div class="line">
    
      <button id="right"   class="right">
        <span class="circle" aria-hidden="true">
        <span class="icon arrow">▶︎</span>
      </button>
      <button id="left"  class="left">
        <span class="circle" aria-hidden="true">
        <span class="icon arrow">◀︎</span>
      </button>
      
    <div class="grid">
     <iframe  src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
     <iframe  src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
     <iframe  src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
  </div>
  </div>
</div>

