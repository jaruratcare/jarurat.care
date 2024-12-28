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
	import SingleWave from '$lib/svg/single-wavelightblue.svelte';

	const cards = [
		{
			icon: HandPlatterPlus,
			title: 'Seek Support',
			description:
				'Our NGO is here to help you through your cancer journey. Reach out to us today.',
			ctaText: 'Seek Support',
			href: 'mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer'
		},
		{
			icon: HandPlatterHeart,
			title: 'Donate',
			description:
				'One Life at a Time "Donate today and be the light that brings hope and healing to cancer patients.',
			ctaText: 'Donate Now',
			href: '/donate'
		},
		{
			icon: Hand,
			title: 'Become a Member',
			description:
				'Be a hero . Volunteer with us and provide support and hope to those affected by cancer.',
			ctaText: 'Get Started',
			href: 'mailto:jaruratcare@gmail.com?subject=Become%20A%20Member%20at%20JaruratCare'
		}
	];

	const slider = [
		{
			title: 'Why Cancer',
			content: `Cancer remains one of the leading causes of death worldwide, affecting millions of lives each year. Despite advancements in research and treatment, many people still lack access to the care and support they need. By focusing on cancer, we aim to raise awareness, provide vital resources, and drive efforts toward early detection, effective treatment.`
		},
		{
			title: 'Why Donate',
			content: `Donations are the lifeblood of Jarurat Care, empowering us to provide critical support to cancer patients who need it most. Your contributions directly fund life-saving treatments, emotional care, and essential services that make a real difference in people's lives. Every donation brings us one step closer to a world where no one has to face cancer alone. Your support is not just important—it's transformative.`
		},
		{
			title: 'Why Collaborate',
			content: `Join us because together, we can do so much more. By collaborating, we pool our strengths and resources to bring greater support to those fighting cancer. Your partnership helps us reach more people and make a bigger impact. Let’s work hand in hand to change lives.`
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
	id="get-involved"
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
			<a class="mt-12 block" href={card.href}>
				<Button>{card.ctaText}</Button>
			</a>
		</div>
	{/each}
</div>

<!-- Where it all Started -->
<div id="about" class="py-8 sm:py-32 bg-[#fefefe] max-w-[60rem] mx-auto">
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
								WHERE IT ALL <span class="text-[#FFBA41]">STARTED</span>
							</h3>

							<div
								class="absolute bottom-0 left-0 max-w-[28rem] p-4 sm:p-8 text-[#DBE1E6] text-[0.8em] sm:text-[1.2em] leading-[1.3]"
							>
								<h3 class="block sm:hidden text-[1.5em] font-black text-[#F9FDFE] mb-2">
									WHERE IT ALL <span class="text-[#FFBA41]">STARTED</span>
								</h3>

								Rekha's last trip before she started her battle with Cholangiocarcinoma and
								succumbed on 25th December, 2023
							</div>
						</div>
					</div>
				</SplideSlide>

				<SplideSlide class="p-1">
					<div class="size-full flex items-center justify-center p-4 smp-8">
						Our story commenced in December 2023, marking a pivotal moment in our lives. It was then
						that we faced the profound loss of our cherished mother, who bravely battled
						Cholangiocarcinoma, a form of bile duct cancer, for seven months. Motivated by our
						experiences and driven by the same determination any devoted son or daughter would
						possess, we established the Jarurat Care Foundation with the intent to rectify these
						systemic shortcomings and improve the healthcare landscape for others..
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
				Alone we can do so little; together we can do so much.
			</h3>
		</div>

		<div class="text-center mt-8 text-[#0D2561] text-[1.1em] sm:text-[1.5em]">
			<h3 class="font-bold">Priyanka Joshi</h3>
			<h4 class="font-semibold text-[0.8em]">Founder, Jarurat Care Foundation</h4>
		</div>
	</div>
</div>
