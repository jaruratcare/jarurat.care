<script>
  import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
  import '@splidejs/svelte-splide/css';
  import img1 from '../../../assets/images/Frame 1171275557.png';
  import img2 from '../../../assets/images/Frame 1171275557(1).png';
  import img3 from '../../../assets/images/Frame 1171275557(2).png';
  import PrevIcon from "$lib/svg/prev-button-icon.svelte"
  import NextIcon from "$lib/svg/next-button-icon.svelte"

  const slides = [
    { id: 1, img: img1, text: 'Medical Supplies and Equipment' },
    { id: 2, img: img2, text: 'Educational Programs' },
    { id: 3, img: img3, text: 'Emergency Relief' },
    { id: 4, img: img1, text: 'Medical Supplies and Equipment' },
    { id: 5, img: img2, text: 'Educational Programs' },
    { id: 6, img: img3, text: 'Emergency Relief' }
  ];
</script>

<div class="flex flex-col justify-center  bg-[#132442]">
  <div class="text-center my-20">
    <p class="text-[2.2rem] text-white font-semibold" style="font-family: 'Rubik', sans-sarif;">
      Where Your Money Goes
    </p>
    <p class="text-[1.1rem] text-white" style="font-family: 'Manrope', sans-sarif;">
      See the Difference Your Support Makes
    </p>
  </div>

  <div class="w-full flex items-center justify-center mt-3.5 mb-36">
    <Splide
      hasTrack={false}
      options={{
        perPage: 3.3,
        perMove: 1,
        focus: 'center',
        pagination: false,
        trimSpace: false,
        breakpoints: {
          600: {
            perPage: 2
          }
        }
      }}
      aria-label="slider"
    >
      <SplideTrack>
        {#each slides as slide}
          <SplideSlide>
            <div class=" mr-4 flex flex-col items-center">
              <img src={slide?.img} alt="slides" />
              <p class="text-[#DBEDFD] text-[1.2rem] mt-2 text-center">
                {slide?.text}
              </p>
            </div>
          </SplideSlide>
        {/each}
      </SplideTrack>
      
    </Splide>
  </div>
</div>
