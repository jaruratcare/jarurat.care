<script>
	import { onDestroy, onMount, tick } from 'svelte';
	import { writable } from 'svelte/store';
	import Button from '../ui/button.svelte';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
	import ThanksBgImg from '$lib/assets/donate/thank-you-bg-image.webp';
	import JSON_ThanksLetters from '$lib/data/donate/thank-you-letters.json';
	import Quotes from '$lib/svg/quotes.svelte';
	import { ChevronLeft, ChevronRight } from 'lucide-svelte';

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

		onDestroy(() => {
			activeDotObserver.disconnect();
		});
	});
</script>

<div
	class="sm:min-h-screen sm:max-w-screen min-w-screen sm:bg-cover bg-contain bg-no-repeat bg-right-bottom font-rubik bg-[#92C0DE]"
	style="background-image: url('{ThanksBgImg}')"
>
	<div class="sm:py-16 py-4">
		<div class="flex flex-col items-start justify-center gap-2 sm:px-40 px-4 py-4">
			<h2 class="text-[1.5em] sm:text-[2em] text-[#0D2561] leading-tight">
				Thank You <span class="text-[#0155BD]">Letters</span>
			</h2>
			<p class="text-gray-700 leading-snug text-[0.7em] sm:text-[0.9em] md:w-[48%] w-[100%]">
				The Impact of Your Kindness: Letters from Beneficiaries
			</p>
		</div>

		<Splide
			hasTrack={false}
			class="sm:py-12 pl-4 sm:px-40 z-10 relative"
			options={{
				start: 1,
				perPage: 3,
				perMove: 1,
				gap: '5rem',
				type: 'loop',
				drag: 'free',
				snap: false,
				interval: 3000,
				arrows: true,
				pagination: false,
				rewind: false,
				rewindByDrag: true,
				lazyLoad: true,
				breakpoints: {
					600: {
						start: 1,
						perPage: 1.2,
						perMove: 1,
						snap: true,
						gap:'1rem'
					}
				}
			}}
		>
			<div class="custom-wrapper relative">
				<SplideTrack>
					{#each JSON_ThanksLetters as item}
						<SplideSlide>
							<div
								class="relative mx-auto sm:aspect-square aspect-auto h-full sm:p-8 p-4 py-8 bg-white rounded-lg shadow-lg overflow-hidden transition-shadow hover:shadow-xl group"
							>
								<div class="text-[#1E88E5] group-hover:text-[#FDE3A7] transition-colors">
									<Quotes class="group-hover:fill-[#ffd06a]"/>
								</div>

								<div class="mt-4">
									<h3 class="text-base text-gray-400 transition-transform group-hover:scale-y-110">
										{item.title}
									</h3>
									<p
										class="mt-2 text-gray-900 text-xs transition-transform group-hover:scale-y-105"
									>
										{item.content}
									</p>
								</div>
								<p class="mt-2 text-gray-500 group-hover:text-gray-500 transition-all text-xs">
									{item.name} <br />
									{item.description}
								</p>

								<div
									class="absolute inset-0 z-[-1] bg-white group-hover:bg-gradient-to-br group-hover:from-[#FDE3A7] group-hover:via-white group-hover:to-[#FDE3A7] transition-all duration-300"
								></div>
							</div>
						</SplideSlide>
					{/each}
				</SplideTrack>
			</div>
			<div class="splide__arrows flex items-start justify-start gap-4 sm:mt-4 mt-2">
				<Button class="splide__arrow splide__arrow--prev size-10 p-2 items-center">
					<ChevronLeft class="w-full" />
				</Button>
				<Button class="splide__arrow splide__arrow--next size-10 p-2 flex items-center">
					<ChevronRight class="w-full" />
				</Button>
			</div>
		</Splide>
	</div>
</div>
