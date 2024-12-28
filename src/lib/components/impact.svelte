<script>
	import Button from './ui/button.svelte';
	import Header from './ui/header.svelte';
	import AnimatedGradientMesh from '$lib/svg/animated-gradient-mesh.svelte';

	const cards = [
		{ title: 'Mentor', count: 54, suffix: '' },
		{ title: 'Doctor', count: 28, suffix: '' },
		{ title: 'People', count: 1245, suffix: '' },
		{ title: 'Early Treatments Initiated', count: 95, suffix: '%' },
		{ title: 'Patients Assisted', count: 150, suffix: '+' },
		{ title: 'People Reached', count: 2000, suffix: '' },
		{ title: 'Emotional Support Services', count: 50, suffix: '+' },
		{ title: 'Early Treatments Initiated', count: 95, suffix: '+' }
	];
</script>

<div class="py-8 sm:py-16 pb-16 sm:pb-36 flex flex-col bg-[#D3F2FC]">
	<div
		class="bg-[#f2fafe] max-w-[80rem] w-[90%] mx-auto flex flex-col py-8 sm:py-16 px-4 sm:px-8 rounded-2xl relative"
	>
		<div class="absolute inset-0 overflow-hidden opacity-80">
			<AnimatedGradientMesh />
		</div>
		<div class="max-w-[60rem] mx-auto flex flex-col gap-4 sm:gap-16">
			<Header
				title="Our Impact"
				subtitle="Explore the real-world difference we are making in Cancer Care"
			/>

			<div class="grid grid-cols-2 sm:grid-cols-4 gap-4 sm:gap-10 p-1 sm:p-4">
				{#each cards as card}
					<div class="border flex flex-col p-4 rounded-md shadow-md bg-white z-10">
						<span class="text-[2em] sm:text-[3em] font-semibold">{card.count}{card.suffix}</span>
						<div class="grow"></div>
						<small class="leading-[1.3]">{card.title}</small>
					</div>
				{/each}
			</div>
		</div>

		<a href="/" class="inline-block mx-auto mt-16"><Button>Seek Support</Button></a>
	</div>
</div>
