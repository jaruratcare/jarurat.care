<script>
	import background from '$lib/assets/our-vison-image.png';
	import VisionCardsSeekSupport from './vision-cards-seek-support.svelte';

	let cards = [
		'We support cancer patients through education, emotional well-being, research, and time management with community backing.',
		// 'Our vision is to create an all-inclusive community of cancer warriors, caregivers, and doctors in India, fostering support, knowledge sharing, and solidarity in the fight against cancer.',
		// 'We support cancer patients through education, emotional well-being, research, and time management with community backing.'
	];
</script>

<div>
	<div class=" w-full mx-auto my-24">
		<div
			class="w-full h-full bg-cover bg-center "
			style={`background-image: url(${background})`}
		>
			<div class="text-white  flex justify-start items-start flex-col px-9  w-full">
				<div class="py-5 font-extrabold text-xl">Our Vision</div>
				<div class=" md:w-[580px] text-sm">
					Our vision is to create an all-inclusive community of cancer warriors, caregivers, and
					doctors in India, fostering support, knowledge sharing, and solidarity in the fight
					against cancer.
				</div>
			</div>

			<div class="cards flex pl-5 gap-4 mt-8 justify-center items-center pb-9">
				{#each cards as item}
					<VisionCardsSeekSupport content={item} />
				{/each}
			</div>
		</div>
	</div>
</div>
