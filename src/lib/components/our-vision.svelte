<script>
	import background from '$lib/assets/our-vison-image.png';
	import OurVisionCard from './our-vision-card.svelte';
	import { Splide, SplideSlide } from '@splidejs/svelte-splide';
	import { onMount } from 'svelte';

	let particlesToShow = 3;

	const updateParticlesToShow = () => {
		if (window.innerWidth < 768) {
			particlesToShow = 1;
		} else if (window.innerWidth < 1318) {
			particlesToShow = 2;
		} else {
			particlesToShow = 3;
		}
	};

	onMount(() => {
		updateParticlesToShow(); // Set initial value based on window size
		window.addEventListener('resize', updateParticlesToShow);

		return () => {
			window.removeEventListener('resize', updateParticlesToShow); // Cleanup on component destruction
		};
	});

	let cards = [
		'We support cancer patients through education, emotional well-being, research, and time management with community backing.',
		'Our vision is to create an all-inclusive community of cancer warriors, caregivers, and doctors in India, fostering support, knowledge sharing, and solidarity in the fight against cancer.',
		'We support cancer patients through education, emotional well-being, research, and time management with community backing.'
	];
</script>

<div class="pb-10 w-[85%] mx-auto my-24">
	<div
		class="w-full h-full bg-cover bg-center rounded-3xl pb-11"
		style={`background-image: url(${background})`}
	>
		<div class="text-white text-center flex justify-center items-center flex-col w-full">
			<div class="py-7 font-extrabold text-xl">Our Vision</div>
			<div class=" md:w-[580px]">
				Our vision is to create an all-inclusive community of cancer warriors, caregivers, and
				doctors in India, fostering support, knowledge sharing, and solidarity in the fight against
				cancer.
			</div>
		</div>

		<div class="cards w-[90%] md:pl-5 mt-8">
			<Splide
				options={{
					type: 'loop',
					perPage: 3,
					gap: '2rem',
					autoplay: false,
					speed: 800,
					arrows: false,
					pagination: true,
					breakpoints: {
            768: {
              perPage: 1, // Ensure 1 card is shown on phones
              gap: '1rem', // Smaller gap for mobile
            },
            1212: {
              perPage: 2, // 2 cards between 768px and 1212px
            },
          },
				}}
				class=""
			>
				{#each cards as item}
					<SplideSlide>
						<OurVisionCard content={item} />
					</SplideSlide>
				{/each}
			</Splide>
		</div>
	</div>
</div>
