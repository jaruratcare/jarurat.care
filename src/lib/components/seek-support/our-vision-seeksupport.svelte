<script>
	import background from '$lib/assets/our-vison-image.png';
	import VisionCardsSeekSupport from './vision-cards-seek-support.svelte';
	import { Splide, SplideSlide } from '@splidejs/svelte-splide';

	let cards = [
		'We support cancer patients through education, emotional well-being, research, and time management with community backing.',
		'Our vision is to create an all-inclusive community of cancer warriors, caregivers, and doctors in India, fostering support, knowledge sharing, and solidarity in the fight against cancer.',
		'We support cancer patients through education, emotional well-being, research, and time management with community backing.'
	];
</script>

<div>
	<div class=" w-full mx-auto my-24">
		<div class="w-full h-full bg-cover bg-center" style={`background-image: url(${background})`}>
			<div class="text-white flex justify-start items-start flex-col px-9 w-full">
				<div class="py-5 font-extrabold text-xl">Our Vision</div>
				<div class=" md:w-[580px] text-sm">
					Our vision is to create an all-inclusive community of cancer warriors, caregivers, and
					doctors in India, fostering support, knowledge sharing, and solidarity in the fight
					against cancer.
				</div>
			</div>

			<div class="cards  pl-5 gap-4 mt-8  pb-6">
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
								gap: '1rem' // Smaller gap for mobile
							},
							1212: {
								perPage: 2 // 2 cards between 768px and 1212px
							}
						}
					}}>
					{#each cards as item}
						<SplideSlide>
							<VisionCardsSeekSupport content={item} />
						</SplideSlide>
					{/each}
				</Splide>
			</div>
		</div>
	</div>
</div>
