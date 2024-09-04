<script>
	import Button from './ui/button.svelte';
	import ImpactGradient from '$lib/svg/impact-gradient.svelte';

	const cards = [
		{ title: 'Mentor', count: 54, suffix: '' },
		{ title: 'Doctor', count: 28, suffix: '' },
		{ title: 'People', count: 1245, suffix: '' },
		{ title: 'Early Treatments Initiated', count: 95, suffix: '%' },
		{ title: 'Patients Assisted', count: 1000, suffix: '+' },
		{ title: 'People Reached', count: 5, suffix: 'M' },
		{ title: 'Emotional Support Services', count: 50, suffix: '+' },
		{ title: 'Early Treatments Initiated', count: 95, suffix: '+' }
	];
</script>

<div class="py-8 sm:py-16 pb-16 sm:pb-36 flex flex-col bg-[#D3F2FC]">
	<div
		class="bg-[#f2fafe] max-w-[80rem] w-[95%] mx-auto flex flex-col md:py-10 sm:py-16 px-4 sm:px-8 rounded-2xl relative shadow-lg shadow-gray-300"
	>
		<div class="absolute inset-0 overflow-hidden opacity-80">
			<ImpactGradient />
		</div>
		<div class="max-w-[60rem] mx-auto flex flex-col md:gap-12 sm:gap-16 z-10">
			<div class="text-center">
				<h2 class="text-[30px] font-semibold mb-2" style="font-family: Rubik;">
					Our <span class="text-[#0155BD]">Impact</span>
				</h2>
				<p class="text-[0.9rem] font-medium">
					Explore the real-world difference we are making in Cancer Care
				</p>
			</div>

			<div class="grid grid-cols-2 sm:grid-cols-4 gap-4 sm:gap-8 p-1 sm:p-4 z-10">
				{#each cards as card}
					<div
						class=" border border-blue-200 flex flex-col py-3 px-6 rounded-lg shadow-md bg-white z-10"
					>
						<span class="md:text-[2.6em] sm:text-[3em] text-[#0D2561] font-semibold"
							>{card.count}{card.suffix}</span
						>
						<div class="grow"></div>
						<small class="leading-[1.3] text-[#0155BD] font-medium text-[0.7em]">{card.title}</small
						>
					</div>
				{/each}
			</div>
		</div>

		<a href="/" class="rounded-full bg-[#0155BD] mx-auto mt-14 z-10"
			><Button>Seek Support</Button></a
		>
	</div>
</div>
