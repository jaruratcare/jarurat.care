<script>
	import { onMount } from 'svelte';
	import { writable } from 'svelte/store';
	import Button from '../ui/button.svelte';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
	import { ArrowRight, ChevronLeft, ChevronRight } from 'lucide-svelte';
	import JSON_ImpactOfDonation from '$lib/data/donate/impact-of-donation.json';

	let innerWidth = writable(0);

	onMount(() => {
		innerWidth.set(window.innerWidth);
		window.addEventListener('resize', () => {
			innerWidth.set(window.innerWidth);
		});
	});

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
	});
</script>

<div class="py-3 sm:py-1 md:px-14 px-4 overflow-hidden">
	<div class=" mx-auto px-2 sm:px-10">
		<h2 class="text-[1.5em] sm:text-[2em] font-bold text-[#0155BD] leading-tight max-w-[7em] md:max-w-[16em]">
			<span class="text-[#0D2561]">Impact Of </span>
			<span class="text-[#0155BD]">Your Donation</span>
		</h2>
		<p class="text-[#000000] leading-snug text-[0.8em] sm:text-[0.9em] md:w-[48%] w-[100%]">
			Supporting the physical and emotional needs of a cancer patient empowers them to contribute
			positively to society, turning them into a source of strength rather than burden.
		</p>
	</div>

	<Splide 
	hasTrack={false}
	aria-label="Impact of Donation"
	class="mx-auto mt-8 z-10 relative"
	options={{
		start: 1,
		perPage: 3,
		perMove: 3,
		gap: '1rem',
		type: "loop",
		drag: "free",
		snap: false,
		interval: 3000,
		arrows: true,
		pagination: true,
		rewind: true,
		rewindByDrag: true,
		lazyLoad: true,

		breakpoints: {
			600: {
				start:1,
				perPage: 1,
				perMove: 1,
				snap: true,
			}
		}
		}}
		>
		<div class="custom-wrapper relative">
			<SplideTrack>
				{#each JSON_ImpactOfDonation as item}
				<SplideSlide class="p-1 px-5 sm:px-10 w-full">
					<div class="h-full flex flex-col justify-start pb-4">
						<div class="w-full aspect-[9/11] rounded-2xl bg-cover bg-center" style="background-image: url('{item.imgSrc}');"></div>

						<div class="p-3 bg-white max-w-[80%] mx-auto rounded-xl -mt-20 border">
							<small class="block uppercase text-[#37393D] font-bold leading-snug">{item.title}</small>
							<p class="text-[0.7em] text-[#5D5F64] leading-snug">{item.content}</p>
						</div>
					</div>
				</SplideSlide>
				{/each}
			</SplideTrack>
			<div class="splide__pagination flex justify-center"></div>
		</div>

		<div class="splide__arrows hidden md:flex items-center justify-between absolute top-1/2 -left-3 -right-3 sm:-left-8 sm:-right-8 mx-auto transform -translate-y-1/2 px-4">
			<Button class="splide__arrow splide__arrow--prev size-8 p-0 items-center rounded-full">
				<ChevronLeft class="w-full" />
			</Button>
			<Button class="splide__arrow splide__arrow--next size-8 p-0 flex items-center rounded-full">
				<ChevronRight class="w-full" />
			</Button>
		</div>
	</Splide>

	<a href="#donate">
		<Button class="hidden md:flex gap-2 mx-auto mt-8 sm:mt-16 px-8">Make a Donation <ArrowRight /></Button>
		<Button class="flex md:hidden gap-2 mx-auto mt-8 sm:mt-16 px-7 py-2 text-[0.8em]">Donate Now</Button>
	</a>
</div>