<script>

import '@splidejs/splide/dist/css/themes/splide-default.min.css';
  import { Splide, SplideSlide } from '@splidejs/svelte-splide';
  import CrouselCard from './CrouselCard.svelte';
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
];
</script>

<div class="text-center my-16   " style="color: #0464C4;">
  <h1 class="text-xl font-bold">Voices Of Hope</h1>
  <p class="text-lg font-normal">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
</div>
<Splide options={{rewind: true}} aria-label="My Favorite Images">
    {#each cancerPatients as patient }
      <SplideSlide>
          <CrouselCard data={patient} />
      </SplideSlide>
    {/each}
  
</Splide>
 <div class="line mt-16 mx-32 border-t-2 border-darkblue py-24 relative ">
    <button id="left" class="left absolute top-1 right-16 border-none h-12 w-12 text-lg cursor-pointer bg-white rounded-full shadow-md">
      <span class="text-darkblue">◀︎</span>
    </button>


    <button id="right" class="right absolute top-1 right-0 border-none h-12 w-12 text-lg cursor-pointer bg-white rounded-full shadow-md">
      <span class="text-darkblue">▶︎</span>
    </button>
  <div class="grid gap-4 sm:grid-cols-1 md:grid-cols-3 px-24 ">
  <iframe class="w-full" src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
  <iframe class="w-full" src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
  <iframe class="w-full" src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
</div>
 </div>
  
  