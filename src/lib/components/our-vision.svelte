<script>
	import background from '$lib/assets/our-vison-image.png';
	import OurVisionCard from './our-vision-card.svelte';
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
		updateParticlesToShow();
		window.addEventListener('resize', updateParticlesToShow);

		return () => {
			window.removeEventListener('resize', updateParticlesToShow);
		};
	});

	let cards = [
		'We support cancer patients through education, emotional well-being, research, and time management with community backing.',
		'Our vision is to create an all-inclusive community of cancer warriors, caregivers, and doctors in India, fostering support, knowledge sharing, and solidarity in the fight against cancer.',
		'We support cancer patients through education, emotional well-being, research, and time management with community backing.'
	];
</script>

<div class="pb-10 max-w-7xl mx-auto px-4 my-12 md:my-20">

	<div
		class="w-full bg-cover bg-center rounded-3xl p-6 md:p-10"
		style={`background-image: url(${background})`}
	>

		<!-- Vision Section -->
		<div class="text-white text-center flex flex-col items-center">

			<h2 class="py-4 font-extrabold text-2xl md:text-3xl">
				Our Vision
			</h2>

			<p class="mt-2 text-sm md:text-base max-w-2xl">
				Our vision is to create an all-inclusive community of cancer warriors, caregivers, and doctors in India, fostering support, knowledge sharing, and solidarity in the fight against cancer.
			</p>

		</div>

		<!-- Cards -->
		<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 md:gap-6 mt-8">

			{#each cards as item}
				<OurVisionCard content={item} />
			{/each}

		</div>

	</div>

</div>