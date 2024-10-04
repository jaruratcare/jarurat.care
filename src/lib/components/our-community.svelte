<script>
	import Connect from "$lib/svg/connect.svelte";
	import Resources from "$lib/svg/resources.svelte";
	import SingleWave from "$lib/svg/single-wavedown.svelte";
import OurCommunityItem from "./our-community-item.svelte";
// import Carousel from 'svelte-carousel'
	import { onMount } from "svelte";
    let particlesToShow = 4;

const updateParticlesToShow = () => {
  if (window.innerWidth < 768) {
	particlesToShow = 1;
  } else {
	particlesToShow = 4;
  }
};

onMount(() => {
  updateParticlesToShow();  // Set initial value based on window size
  window.addEventListener('resize', updateParticlesToShow);

  return () => {
	window.removeEventListener('resize', updateParticlesToShow);  // Cleanup on component destruction
  };
});


    const items = [
		{
			icon: Connect,
			title: 'The Cancer Project',
            about:"India ’ s first ever cancer podcast and webinar series for discussions on treatments, stories, and expert insights."
		},
		{
			icon: Resources,
			title: 'Resource Guidance',
            about:"Building India’s largest Edtech hub for any cancer queries in 7+ regional languages."
		},
        {
            icon: Resources,
            title: 'Social Community',
            about:"Building social media hub to discuss early detection & prevention of terminal cancers."
        },
		{
			icon: Resources,
			title: 'Patient Advocacy',
            about:"Advocating patients’ rights & educating people about what’ s rightfully correct in healthcare space."
		},
	];
</script>

<div class="mx-auto mt-16 lg:w-[28rem]">
    <div class="text-primaryBlue text-center  font-extrabold text-xl">OUR COMMUNITY </div>
    <div class="mb-24 text-center font-extrabold text-xl">BACKED SERVICES</div>
    
    <div class="flex lg:gap-3 justify-center">
        <!-- <Carousel  particlesToShow={particlesToShow} particlesToScroll={1}> -->
        {#each items as item}
        <OurCommunityItem icon={item.icon} about={item.about} title={item.title}/>
        {/each}
        <!-- </Carousel> -->
    </div>
</div>

