<script lang="ts">
	import HandPlatterHeart from '$lib/svg/hand-platter-heart.svelte';
	import HandPlatterPlus from '$lib/svg/hand-platter-plus.svelte';
	import Hand from '$lib/svg/hand.svelte';
	import Wave from '$lib/svg/wave.svelte';
	import Button from './ui/button.svelte';
	import ImgBgChildren from '$lib/assets/bg-children.webp';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
	import { ChevronLeft, ChevronRight, QuoteIcon } from 'lucide-svelte';
	import ImgMom from '$lib/assets/mom.webp';
	import SingleWave from '$lib/svg/single-wave.svelte';
   import {t}from "$lib/translations/translations.js"

	$:cards = [
		{
			icon: HandPlatterPlus,
			title: $t("home.cards.seekSupport.title"),
			description: $t("home.cards.seekSupport.description"),
			ctaText: $t("home.cards.seekSupport.ctaText")
		},
		{
			icon: HandPlatterHeart,
			title: $t("home.cards.donate.title"),
			description:$t("home.cards.donate.description"),
			ctaText: $t("home.cards.donate.ctaText")
		},
		{
			icon: Hand,
			title: $t("home.cards.becomeMember.title"),
			description:$t("home.cards.becomeMember.description"),
			ctaText: $t("home.cards.becomeMember.ctaText")
		}
	];

	const slider = [
		{
			title: $t("home.slider.whyCancer.title"),
			content: $t("home.slider.whyCancer.content")
		},
		{
			title: $t("home.slider.whyDonate.title"),
			content: $t("home.slider.whyDonate.content")
		},
		{
			title: $t("home.slider.whyCollaborate.title"),
			content: $t("home.slider.whyCollaborate.content")
		}
	];
</script>

<div
	class="sm:-my-32 relative overflow-hidden bg-cover z-0"
	style="background-image: url('{ImgBgChildren}');"
>
	<div class="bg-black/20 px-2 sm:px-4 py-8 sm:py-32 backdrop-blur-lg">
		<section class="py-8 sm:py-16 px-2 sm:px-4 max-w-[60rem] mx-auto">
			<div class="py-4 sm:py-8 sm:px-32 bg-[#fefefe] rounded-3xl">
				<Splide
					hasTrack={false}
					options={{ perPage: 1, type: 'loop', gap: '2rem', pagination: true }}
					on:paginationUpdated={(ev) => {
						const curr = ev?.detail.curr;
						const list = ev?.detail.data.list;

						// set styles to pagination icons
						list?.querySelectorAll('li > button').forEach((button) => {
							if (!button) return;

							button.style.width = '1rem';
							button.style.aspectRatio = '1';
							button.style.backgroundColor = '#b4bcc8';
							button.style.borderRadius = '100%';
						});

						if (curr?.button) {
							curr.button.style.backgroundColor = '#ffba41';
						}
					}}
				>
					<div
						class="splide__arrows items-center justify-between gap-2 absolute -inset-x-12 translate-y-28 hidden sm:flex"
					>
						<Button class="splide__arrow splide__arrow--prev size-8 p-0 items-center">
							<ChevronLeft class="w-full" />
						</Button>

						<Button class="splide__arrow splide__arrow--next size-8 p-0 flex items-center">
							<ChevronRight class="w-full" />
						</Button>
					</div>

					<SplideTrack>
						{#each slider as slide}
							<SplideSlide class="p-1">
								<div class="h-full flex flex-col gap-1 rounded-lg p-8 text-center">
									<h2 class="font-extrabold uppercase text-[1.3em]">{slide.title}</h2>
									<p class="text-[#656b75]">{slide.content}</p>
								</div>
							</SplideSlide>
						{/each}
					</SplideTrack>

					<ul class="splide__pagination flex gap-2"></ul>
				</Splide>
			</div>
		</section>
	</div>
</div>

<div
	class="relative flex flex-col sm:flex-row bg-[#effafd] sm:bg-white overflow-hidden rounded-t-3xl"
>
	<div class="absolute inset-x-0 top-0 z-0 hidden sm:block">
		<Wave class="w-full " />
	</div>

	{#each cards as card}
		<div
			class="flex flex-col items-center border-l-0 sm:border-l border-b border-[#0072C4] px-4 sm:px-8 py-8 sm:py-16 grow z-10"
		>
			<div class="w-14 p-2 bg-white rounded-full mb-4">
				<svelte:component this={card.icon}></svelte:component>
			</div>

			<h2 class="max-w-[14rem] mx-auto text-center font-playfair-display text-3xl">{card.title}</h2>
			<div class="grow"></div>
			<p class="max-w-[15rem] w-full text-center sm:text-left sm:text-sm mt-4">
				{card.description}
			</p>
			<div class="mt-12">
				<Button>{card.ctaText}</Button>
			</div>
		</div>
	{/each}
</div>

<!-- Where it all Started -->
<div class="py-8 sm:py-32 bg-[#fefefe] max-w-[60rem] mx-auto">
	<Splide hasTrack={false} options={{ perPage: 1, type: 'loop', gap: '2rem', pagination: true }}>
		<div
			class="splide__arrows items-center justify-between gap-2 absolute -inset-x-12 h-full hidden xl:flex"
		>
			<Button class="splide__arrow splide__arrow--prev size-8 p-0 items-center">
				<ChevronLeft class="w-full" />
			</Button>

			<Button class="splide__arrow splide__arrow--next size-8 p-0 flex items-center">
				<ChevronRight class="w-full" />
			</Button>
		</div>

		<SplideTrack>
			{#each slider as slide}
				<SplideSlide class="p-1 aspect-[16/9] ">
					<div class="size-full relative rounded-xl sm:rounded-3xl overflow-hidden">
						<img src={ImgMom} alt="Rekha Joshi - Jarurat Care" class="size-full object-cover" />
						<div class="absolute inset-0 bg-[#002944]/65 flex items-center justify-center">
							<h3 class="hidden sm:block sm:text-[2em] font-black text-[#F9FDFE]">
								{$t("home.story.title")}<span class="text-[#FFBA41]">{$t("home.story.started")}</span>
							</h3>

							<div
								class="absolute bottom-0 left-0 max-w-[28rem] p-4 sm:p-8 text-[#DBE1E6] text-[0.8em] sm:text-[1.2em] leading-[1.3]"
							>
								<h3 class="block sm:hidden text-[1.5em] font-black text-[#F9FDFE] mb-2">
									{$t("home.story.title")} <span class="text-[#FFBA41]">{$t("home.story.started")}</span>
								</h3>
{$t("home.story.description")}
								
							</div>
						</div>
					</div>
				</SplideSlide>

				<SplideSlide class="p-1">
					<div class="size-full flex items-center justify-center p-4 smp-8">
						{$t("home.story.content")}
					</div>
				</SplideSlide>
			{/each}
		</SplideTrack>
	</Splide>
</div>

<SingleWave fill="#d3f2fc" />
<div class="py-28 px-8 bg-[#D3F2FC]">
	<div class="max-w-[30rem] mx-auto">
		<div class="flex gap-2">
			<QuoteIcon class="rotate-180 text-[#0155BD] size-[7em] -mt-[3em]" />
			<h3 class="text-[1.5em] sm:text-[2em] font-bold text-[#0155BD] leading-[1.1]">
				{$t("home.quote.text")}
			</h3>
		</div>

		<div class="text-center mt-8 text-[#0D2561] text-[1.1em] sm:text-[1.5em]">
			<h3 class="font-bold">{$t("home.quote.author")}</h3>
			<h4 class="font-semibold text-[0.8em]">{$t("home.quote.position")}</h4>
		</div>
	</div>
</div>
