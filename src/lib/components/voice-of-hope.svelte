<script>
	import { onMount } from 'svelte';
	import { writable } from 'svelte/store';
	import Header from './ui/header.svelte';
	import Button from './ui/button.svelte';
	import testimonials from '$lib/data/testimonials.json';
	import InstagramEmbed from './ui/instagram-embed.svelte';
	import instaReels from '$lib/data/insta-reels-for-testimonials.json';
	import { ChevronLeft, ChevronRight, QuoteIcon } from 'lucide-svelte';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
   import {t} from "$lib/translations/translations"
	let innerWidth = writable(0);
	let isInstaEmbedOpen = false;
	let selectedInstaReelUrl = '';

	onMount(() => {
		innerWidth.set(window.innerWidth);
		window.addEventListener('resize', () => {
			innerWidth.set(window.innerWidth);
		});
	});
</script>

<div
	class="max-w-[95%] sm:max-w-[90%] mx-auto py-10 sm:py-28 rounded-2xl relative overflow-hidden bg-[#DCF2FF] sm:bg-[#F4F4F4] sm:bg-transparent shadow-lg"
>
	<div class="absolute inset-x-0 top-0 z-0">
		<div
			class="w-full aspect-square -translate-y-[83%] scale-[4] sm:scale-[1.4] rounded-full bg-[#DCF2FF]"
			style="opacity: 0.75;"
		></div>
	</div>

	<Header
		title={$t("home.voicesOfHope.title")}
		class="relative z-10"
		subtitle={$t("home.voicesOfHope.description")}
	/>

	<Splide
		hasTrack={false}
		aria-label="..."
		class="max-w-[70rem] mx-auto mt-8 z-10"
		options={{ perPage: $innerWidth < 600 ? 1 : 3, type: 'loop' }}
	>
		<div class="custom-wrapper">
			<SplideTrack>
				{#each testimonials as testimonial}
					<SplideSlide class="p-1 pl-2 max-w-[90%]">
						<div
							class="h-full flex sm:flex-col items-center sm:items-start flex-wrap gap-2 sm:gap-4 rounded-2xl p-4 md:p-8 bg-white text-[#0D2561] shadow border border-gray-100"
						>
							<div class="w-12 sm:w-20 aspect-square border rounded-full"></div>

							<div class="leading-[1.2]">
								<h3 class="text-[#0D2561] font-medium font-rubik text-[1.1em]">
									{testimonial.name}
								</h3>
								<h4 class="text-[0.8em] text-[#576171]">{testimonial.description}</h4>
							</div>

							<div class="flex gap-2 w-full">
								<div><QuoteIcon class="size-10 rotate-180 text-[#FFBA41]" /></div>
								<p class="text-[0.9em]">{testimonial.content}</p>
							</div>
						</div>
					</SplideSlide>
				{/each}
			</SplideTrack>
		</div>

		<div class="splide__arrows flex items-center justify-center gap-2 sm:mt-4">
			<Button class="splide__arrow splide__arrow--prev size-8 p-0 items-center">
				<ChevronLeft class="w-full" />
			</Button>
			<Button class="splide__arrow splide__arrow--next size-8 p-0 flex items-center">
				<ChevronRight class="w-full" />
			</Button>
		</div>
	</Splide>

	<Splide
		hasTrack={false}
		aria-label="..."
		class="max-w-[70rem] mx-auto mt-14 z-10"
		options={{ perPage: $innerWidth < 600 ? 1 : 3, type: 'loop' }}
	>
		<div class="custom-wrapper">
			<SplideTrack>
				{#each instaReels as reel}
					<SplideSlide class="p-1 pl-2 max-w-[90%]">
						<button
							class="bg-white text-[#0D2561] w-full aspect-video rounded-2xl shadow object-cover bg-center overflow-hidden"
							style="background-image: url('{reel.thumbnailSrc ||
								'https://placehold.jp/30/dd6699/ffffff/700x400.png?text=placeholder+image'}');"
							on:click={() => {
								isInstaEmbedOpen = false; // reset
								isInstaEmbedOpen = true;
								selectedInstaReelUrl = reel.reelUrl;
							}}
						>
							<div class="p-4 bg-black/20 text-white size-full">{reel.title}</div>
						</button>
					</SplideSlide>
				{/each}
			</SplideTrack>
		</div>

		<div class="splide__arrows flex items-center justify-center gap-2 sm:mt-4">
			<Button class="splide__arrow splide__arrow--prev size-8 p-0 items-center">
				<ChevronLeft class="w-full" />
			</Button>
			<Button class="splide__arrow splide__arrow--next size-8 p-0 flex items-center">
				<ChevronRight class="w-full" />
			</Button>
		</div>
	</Splide>

	<div class="mt-8 flex items-center justify-center z-10 relative">
		<Button>{$t("home.voicesOfHope.button")}</Button>
	</div>

	<div class="absolute inset-x-0 top-0 z-0">
		<div
			class="w-full aspect-square translate-y-[75%] sm:scale-[1.6] rounded-full bg-[#DCF2FF]"
			style="opacity: 0.75;"
		></div>
	</div>
</div>

{#if selectedInstaReelUrl}
	<InstagramEmbed reelUrl={selectedInstaReelUrl} isOpen={isInstaEmbedOpen} />
{/if}
