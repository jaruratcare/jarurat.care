<script>
	import Button from '../ui/button.svelte';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
	import { ChevronLeft, ChevronRight } from 'lucide-svelte';
	import JSON_ImpactOfDonation from '$lib/data/donate/impact-of-donation.json';
	import { onDestroy, onMount } from 'svelte';

	onMount(() => {
		const activeDotObserver = new MutationObserver((mutations) => {
			mutations.forEach((mutation) => {
				// @ts-ignore
				if (mutation.target.classList.contains('is-active')) {
					// @ts-ignore
					mutation.target.style.backgroundColor = '#ffba41';
				} else {
					// @ts-ignore
					mutation.target.style.backgroundColor = '#B4BCC8';
				}
			});
		});

		const paginationDots = document.querySelectorAll('.splide__pagination__page');
		paginationDots.forEach((dot) => {
			activeDotObserver.observe(dot, { attributes: true });
		});

        onDestroy(() => {
            activeDotObserver.disconnect();
        });
	});

</script>

<div class="sm:py-14 py-8 overflow-hidden font-rubik">
	<div class="sm:px-40 px-4 flex flex-col gap-2 sm:items-start items-center">
		<h2 class="text-[1.5em] sm:text-[2em] text-[#0155BD] leading-tight">
			<span class="text-[#0D2561]">Impact Of Your</span> Donation
		</h2>
		<p class="text-gray-700 leading-snug text-[0.8em] sm:text-[0.8em] sm:w-[46%] w-[80%] text-center sm:text-start">
			We empower cancer patients to thrive and positively impact society. Together, we can make a difference in their lives.
		</p>
	</div>

	<Splide 
	hasTrack={false}
	aria-label="Impact of Donation"
	class="mx-auto mt-8 z-10 relative sm:px-40 px-4"
	options={{
		start: 1,
		perPage: 3,
		perMove: 3,
		gap: '2rem',
		type: "loop",
		drag: "free",
		snap: false,
		interval: 3000,
		arrows: true,
		pagination: false,
		rewind: true,
		rewindByDrag: true,
		lazyLoad: true,

		breakpoints: {
			600: {
				start:1,
				perPage: 1.3,
				perMove: 1,
				snap: true,
				pagination:true,
				gap:'1rem'
			}
		}
		}}
		>
		<div class="custom-wrapper relative">
			<SplideTrack>
				{#each JSON_ImpactOfDonation as item}
				<SplideSlide class="p-1 w-full">
					<div class="h-full flex flex-col justify-start pb-4">
						<div class="w-full aspect-[8/11] rounded-2xl bg-cover bg-center" style="background-image: url('{item.imgSrc}');"></div>

						<div class="p-4 bg-white max-w-[90%] mx-auto rounded-xl -mt-20 border border-blue-700">
							<small class="block uppercase text-[#37393D] text-center leading-snug">{item.title}</small>
							<p class="text-[0.7em] text-[#5D5F64] leading-snug text-center">{item.content}</p>
						</div>
					</div>
				</SplideSlide>
				{/each}
			</SplideTrack>
		</div>
		 <div class="splide__pagination flex justify-center py-6"></div>

		<div class="splide__arrows hidden md:flex items-center justify-between absolute top-1/2 -left-3 -right-3 sm:left-10 sm:right-10 mx-auto transform -translate-y-1/2 px-4">
			<Button class="splide__arrow splide__arrow--prev size-12 p-2 items-center rounded-full">
				<ChevronLeft class="w-full" />
			</Button>
			<Button class="splide__arrow splide__arrow--next size-12 p-2 flex items-center rounded-full">
				<ChevronRight class="w-full" />
			</Button>
		</div>
	</Splide>

	<a href="#donate">
		<Button class="flex gap-2 mx-auto mt-8 sm:mt-10 sm:px-8 sm:py-4 px-6 py-3 sm:text-[1em] text-[0.8em] font-sans">Make a Donation</Button>
	</a>
</div>