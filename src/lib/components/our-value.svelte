<script>
	import OurValueItem from "./our-value-item.svelte";
	import ReachIcon from "$lib/svg/reach-icon.svelte";
	import Envolve from "$lib/svg/about/envolve.svelte";
	import Sustain from "$lib/svg/about/sustain.svelte";
	import Impact from "$lib/svg/about/impact.svelte";
	import { Splide, SplideSlide, SplideTrack } from "@splidejs/svelte-splide";
    
    const items = [
		{
			icon: ReachIcon,
			title: 'Reach',
            about:"We actively connect with cancer patients, caregivers, and communities across India, ensuring that our support is accessible to those who need it most."
		},
		{
			icon: Impact,
			title: 'Impact',
            about:"We focus on delivering meaningful interventions that make a real difference in the lives of those affected by cancer, from emotional support to practical care."
		},
		{
			icon: Sustain,
			title: 'Sustain',
            about:"We are committed to providing ongoing assistance, ensuring that our programs and services offer continuous care and stability throughout the cancer journey."
		},
		{
			icon: Envolve,
			title: 'Evolve',
            about:"We adapt and grow to meet the changing needs of the cancer community, always striving to improve our services and expand our reach."
		},
		
	];
</script>

<div class=" md:ml-28 ml-4">
	<div class=" font-extrabold mb-11 text-xl">Our values</div>
		<Splide
		hasTrack={false}
		aria-label="..."
		class="max-w-[70rem] mx-auto mt-8 z-10"
		options={{ type: 'loop',padding:10, arrows: false,autoplay:true,interval:2000,perMove:1,mediaQuery:"min", breakpoints: {
            640: {
                perPage: 2,
                media: '(min-width: 640px)'
            },
            1024: {
                perPage: 3,
                media: '(min-width: 1024px)'
            }
        }}}
	>

			<SplideTrack>
				{#each items as item}
					<SplideSlide class="p-2 pl-2 md:max-w-[80%]  md:aspect-video">
						<OurValueItem title={item.title} icon={item.icon} about={item.about}  />
					</SplideSlide>
				{/each}
			</SplideTrack>

	</Splide>
</div>
