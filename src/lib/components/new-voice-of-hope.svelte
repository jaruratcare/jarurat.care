<script>
  import { Splide, SplideSlide, SplideTrack } from "@splidejs/svelte-splide";
  import NewCarouselCard from "./new-carousel-card.svelte";
  import CustomizedRightIcon from "$lib/svg/customized-right-icon.svelte";
  import CustomizeLeftIcon from "$lib/svg/customize-left-icon.svelte";
</script>

<style>
  .abc::before,
  .abc::after {
    content: "";
    position: absolute;
    width: 100%;
    background-color: #DCF2FF;
    border-radius: 0 0 80% 80% / 0 0 100% 100%;

  }

  .abc::before {
    top: 0;
    left: 0;
    height: 50%;
    z-index: -1;
  }

  .abc::after {
    bottom: 0;
    left: 0;
    transform: rotate(180deg);
    height: 30%;
  }

  .splide__arrow {
    background: white; /* Change the color as needed */
    color: black;
    border-radius: 50%;
    font-size: 1.5rem;
    z-index: 20; /* Ensure it's above other content */
    border: 1px solid black;
    width: 3rem; /* Adjust size as needed */
    height: 3rem;
  }

  .splide__arrow--prev {
    left: -1.5rem; /* Adjust positioning */
  }

  .splide__arrow--next {
    right: -1.5rem; /* Adjust positioning */
  }
  
  
  
 
</style>

<div class="relative m-16 p-8 rounded-xl bg-slate-200 z-10 ">
  <div class="abc ">
    <div class="text-center my-20 text-[#0D2561]   ">
      <h1 class="text-2xl font-bold ">Voices Of <span class="text-[#0155BD]">Hope</span></h1>
      <p class="text-lg font-normal text-[#0D2561] ">"Inspiring Journeys from the People We've Supported"</p>
    </div>

    <Splide  hasTrack={false} options={{ rewind: true, perPage: 3, gap: "0rem" }}>
      <SplideTrack >
        <SplideSlide >
          <NewCarouselCard />
        </SplideSlide>
        <SplideSlide>
          <NewCarouselCard />
        </SplideSlide>
        <SplideSlide>
          <NewCarouselCard />
        </SplideSlide>
        <SplideSlide>
          <NewCarouselCard />
        </SplideSlide>
      </SplideTrack>
      <!-- Default Arrows -->
      <div class="splide__arrows">
        <button class="splide__arrow splide__arrow--prev bg-transparent text-black rounded-full text-xl z-20 border border-[#576171]  w-12 h-12">
           <CustomizeLeftIcon />
        </button>
        <button class="splide__arrow splide__arrow--next bg-transparent text-black rounded-full text-xl z-10 border border-[#576171] right-[-2rem]">
                   <CustomizedRightIcon />
        </button>
        
      </div>
    </Splide>

      <div class=" flex my-24 justify-around relative ">
      <iframe class="h-48 w-80 z-20" src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
      <iframe class="w-80 z-20" src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
      <iframe class="w-80 z-20" src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
      <button class="mx-4 z-20  -bottom-20  border-2 absolute left-auto  border-blue-900 px-4 py-2 text-blue-900 rounded-3xl">Submit a story</button>
     
    </div>
  </div>
  
  
</div>

