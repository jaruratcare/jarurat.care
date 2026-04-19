<script>
	import OurValueItem from "./our-value-item.svelte";
	import ReachIcon from "$lib/svg/reach-icon.svelte";
	import Envolve from "$lib/svg/about/envolve.svelte";
	import Sustain from "$lib/svg/about/sustain.svelte";
	import Impact from "$lib/svg/about/impact.svelte";

	import { Splide, SplideSlide } from "@splidejs/svelte-splide";

	const items = [
		{
			icon: ReachIcon,
			title: 'Reach',
			about:"We actively connect with cancer patients, caregivers, and communities across India."
		},
		{
			icon: Impact,
			title: 'Impact',
			about:"We focus on delivering meaningful interventions that make a real difference."
		},
		{
			icon: Sustain,
			title: 'Sustain',
			about:"We provide ongoing assistance and stability throughout the cancer journey."
		},
		{
			icon: Envolve,
			title: 'Evolve',
			about:"We adapt and grow to meet the changing needs of the cancer community."
		}
	];
</script>

<section class="max-w-7xl mx-auto px-4 md:px-12 my-20">

	<h2 class="font-extrabold text-xl mb-10">
		Our Values
	</h2>

	<Splide
		options={{
			type: 'loop',
			perPage: 3,
			gap: '1.5rem',
			autoplay: true,
			interval: 2500,
			pauseOnHover: true,
			arrows: false,
			pagination: true,
			breakpoints: {
				1024: { perPage: 2 },
				768: { perPage: 1 }
			}
		}}
	>

		{#each items as item}

			<SplideSlide>
				<OurValueItem
					icon={item.icon}
					title={item.title}
					about={item.about}
				/>
			</SplideSlide>

		{/each}

	</Splide>

</section>
