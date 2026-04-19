<script lang="ts">
	import Button from './ui/button.svelte';
	import Header from './ui/header.svelte';
	import AnimatedGradientMesh from '$lib/svg/animated-gradient-mesh.svelte';
	import { onMount } from 'svelte';
	import {
		Users,
		User,
		Stethoscope,
		Activity,
		Heart,
		Globe,
		Smile,
		PlusCircle
	} from 'lucide-svelte';

	const cards = [
		{ title: 'Mentor', count: 54, metricIcon: Users },
		{ title: 'Doctor', count: 28, metricIcon: Stethoscope },
		{ title: 'People', count: 1245, metricIcon: User },
		{ title: 'Early Treatments Initiated', count: 95, suffix: '%', metricIcon: Activity },
		{ title: 'Patients Assisted', count: 150, suffix: '+', metricIcon: Heart },
		{ title: 'People Reached', count: 2000, metricIcon: Globe },
		{ title: 'Emotional Support Services', count: 50, suffix: '+', metricIcon: Smile },
		{ title: 'Treatments Initiated', count: 95, suffix: '+', metricIcon: PlusCircle }
	];

	let animatedCounts = cards.map(() => 0);
	let hasAnimated = false;
	let sectionElement: HTMLElement;

	function startAnimation() {
		if (hasAnimated) return;
		hasAnimated = true;

		cards.forEach((card, index) => {
			let current = 0;
			const increment = Math.max(1, card.count / 80);

			const interval = setInterval(() => {
				current += increment;

				if (current >= card.count) {
					current = card.count;
					clearInterval(interval);
				}

				animatedCounts[index] = Math.floor(current);
			}, 20);
		});
	}

	onMount(() => {
		const observer = new IntersectionObserver(
			(entries) => {
				if (entries[0].isIntersecting) startAnimation();
			},
			{ threshold: 0.4 }
		);

		if (sectionElement) observer.observe(sectionElement);
	});
</script>

<section
	bind:this={sectionElement}
	class="relative py-12 sm:py-20 bg-[#EFFAFD] overflow-hidden"
>
	<!-- background mesh -->
	<div class="absolute inset-0 opacity-30 pointer-events-none">
		<AnimatedGradientMesh />
	</div>

	<div class="relative max-w-7xl mx-auto px-4 sm:px-6">

		<Header
			title="Our Impact"
			subtitle="Explore the real-world difference we are making in Cancer Care"
			class="mb-8 sm:mb-12"
		/>

		<!-- cards -->
		<div class="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 gap-4 sm:gap-6">

			{#each cards as card, i}
				<div
					class="flex flex-col items-center bg-white rounded-3xl shadow-md p-4 sm:p-5 text-center relative z-10 hover:scale-105 transition-transform duration-200"
				>

					<div class="flex items-center justify-center gap-2">

						<span class="text-2xl sm:text-3xl font-extrabold leading-none whitespace-nowrap">
							{animatedCounts[i]}{card.suffix || ''}
						</span>

						<svelte:component
							this={card.metricIcon}
							class="w-5 h-5 text-[#1E40AF] shrink-0"
						/>

					</div>

					<div class="mt-1 text-[#475569] text-xs sm:text-sm leading-tight max-w-[120px]">
						{card.title}
					</div>

				</div>
			{/each}

		</div>

		<!-- button -->
		<div class="mt-8 sm:mt-10 text-center">
			<Button>Seek Support</Button>
		</div>

	</div>
</section>