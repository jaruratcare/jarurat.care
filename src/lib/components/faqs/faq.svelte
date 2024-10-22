<script>
	import Header from '../ui/header.svelte';
	import FaqCard from './faq-card.svelte';
	import Button from '../ui/orangebtn.svelte';
	import faqs from '$lib/data/faq.json';
	import AnimatedGradientMesh from '$lib/svg/animated-gradient-mesh.svelte';

	const columnOne = faqs.slice(0, faqs.length / 2);
	const columnTwo = faqs.slice(faqs.length / 2);
</script>

<div class="py-16 sm:py-32 px-4 relative">
	<div class="absolute inset-0 overflow-hidden -z-10">
		<!-- can't use. performance issue -->
		<!-- <AnimatedWave class="" /> -->

		<AnimatedGradientMesh />
	</div>

	<Header
		title="Frequently"
		spantitle="asked question"
		subtitle="Look through some of oue frequently answered questions"
	/>
	

	<div class="max-w-[50rem] mx-auto flex flex-col sm:flex-row sm:gap-4 my-8">
		<div class="grow flex flex-col sm:gap-4">
			{#each columnOne as faq}
				<FaqCard question={faq.question} answer={faq.answer} />
			{/each}
		</div>
		<div class="grow flex flex-col sm:gap-4">
			{#each columnOne as faq}
				<FaqCard question={faq.question} answer={faq.answer} />
			{/each}
		</div>
	</div>

	<div class="text-center">
		<div class="text-[#3b3d43] text-[1.75rem] sm:text-2xl md:text-3xl font-semibold font-['Rubik'] capitalize">
			Still have Questions?
		</div>
		<div class="text-[#676e73] text-lg sm:text-xl font-normal font-['Manrope']">
			Feel free to contact us
		</div>
	</div>
	<a href="mailto:jaruratcare@gmail.com?subject=">
		<Button class="mt-8 block mx-auto">Contact Us</Button>
	</a>
</div>
