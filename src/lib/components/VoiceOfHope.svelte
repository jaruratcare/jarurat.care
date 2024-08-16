<script>
  import {t} from "$lib/translations/translations.js"
  import '@splidejs/splide/dist/css/themes/splide-default.min.css';
  import { Splide, SplideSlide } from '@splidejs/svelte-splide';
  import CrouselCard from './CrouselCard.svelte';
  $: cancerPatients = [
    {
      img: 'https://www.india.com/wp-content/uploads/2017/11/Cancer-patient.jpg',
      name: $t('home.voicesOfHope.patients.name1'),
      cancerType: $t('home.voicesOfHope.patients.cancerType1'),
      location: $t('home.voicesOfHope.patients.location1'),
      description:$t('home.voicesOfHope.patients.description1')
    },
    {
      img: 'https://www.shutterstock.com/image-photo/happy-smiling-indian-recovered-breast-260nw-2346325923.jpg',
      name: $t('home.voicesOfHope.patients.name2'),
      cancerType: $t('home.voicesOfHope.patients.cancerType2'),
      location: $t('home.voicesOfHope.patients.location2'),
      description:$t('home.voicesOfHope.patients.description2')
    },
    {
      img: 'https://www.shutterstock.com/image-photo/indian-portrait-happy-cancer-patient-260nw-2341118991.jpg',
      name: $t('home.voicesOfHope.patients.name3'),
      cancerType: $t('home.voicesOfHope.patients.cancerType3'),
      location: $t('home.voicesOfHope.patients.location3'),
      description:$t('home.voicesOfHope.patients.description3'),
    }
  ];
</script>

<div class="text-center my-16" style="color: #0464C4;">
  <h1 class="text-xl font-bold">{$t('home.voicesOfHope.title')}</h1>
  <p class="text-lg font-normal">{$t('home.voicesOfHope.description')}</p>
</div>
<Splide options={{ rewind: true }} aria-label="My Favorite Images">
  {#each cancerPatients as patient}
    <SplideSlide>
      <CrouselCard data={patient} />
    </SplideSlide>
  {/each}
</Splide>
<div class="line mt-16 mx-32 border-t-2 border-darkblue py-24 relative">
  <button
    id="left"
    class="left absolute top-1 right-16 border-none h-12 w-12 text-lg cursor-pointer bg-white rounded-full shadow-md"
  >
    <span class="text-darkblue">◀︎</span>
  </button>

  <button
    id="right"
    class="right absolute top-1 right-0 border-none h-12 w-12 text-lg cursor-pointer bg-white rounded-full shadow-md"
  >
    <span class="text-darkblue">▶︎</span>
  </button>
  <div class="grid gap-4 sm:grid-cols-1 md:grid-cols-3 px-24">
    <iframe
      class="w-full"
      src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      referrerpolicy="strict-origin-when-cross-origin"
      allowfullscreen
    ></iframe>
    <iframe
      class="w-full"
      src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      referrerpolicy="strict-origin-when-cross-origin"
      allowfullscreen
    ></iframe>
    <iframe
      class="w-full"
      src="https://www.youtube.com/embed/QPjCHJE3_U4?si=f1raf0RZwoAG_uFn"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      referrerpolicy="strict-origin-when-cross-origin"
      allowfullscreen
    ></iframe>
  </div>
</div>
