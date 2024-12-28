<script>
	import background from '$lib/assets/our-vison-image.png';
	import OurVisionCard from './our-vision-card.svelte';
	// import Carousel from 'svelte-carousel';
	import { onMount } from "svelte";

	let particlesToShow = 3;

const updateParticlesToShow = () => {
  if (window.innerWidth < 768) {
	particlesToShow = 1;
  } else {
	particlesToShow = 3;
  }
};

onMount(() => {
  updateParticlesToShow();  // Set initial value based on window size
  window.addEventListener('resize', updateParticlesToShow);

  return () => {
	window.removeEventListener('resize', updateParticlesToShow);  // Cleanup on component destruction
  };
});


	let cards = [
		'We support cancer patients through education, emotional well-being, research, and time management with community backing.',
		'Our vision is to create an all-inclusive community of cancer warriors, caregivers, and doctors in India, fostering support, knowledge sharing, and solidarity in the fight against cancer.',
		'We support cancer patients through education, emotional well-being, research, and time management with community backing.'
	];
</script>

<div class="pb-10 w-[90%] lg:w-[85%] mx-auto my-24">
	<div
			class="w-full h-full bg-cover bg-center rounded-3xl p-6 lg:p-10"
			style={`background-image: url(${background})`}
	>
		<!-- Vision Section -->
		<div class="text-white text-center flex flex-col justify-center items-center">
			<div class="py-4 font-extrabold text-2xl lg:text-3xl">Our Vision</div>
			<div class="mt-4 text-sm lg:text-base max-w-3xl">
				Our vision is to create an all-inclusive community of cancer warriors, caregivers, and doctors in India, fostering support, knowledge sharing, and solidarity in the fight against cancer.
			</div>
		</div>

		<!-- Cards Section -->
		<div class="cards grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mt-8 justify-center items-center">
			{#each cards as item}
				<OurVisionCard content={item} />
			{/each}
		</div>
	</div>
</div>

