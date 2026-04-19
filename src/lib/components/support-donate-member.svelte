<script lang="ts">
	import HandPlatterHeart from '$lib/svg/hand-platter-heart.svelte';
	import HandPlatterPlus from '$lib/svg/hand-platter-plus.svelte';
	import Hand from '$lib/svg/hand.svelte';
	import Button from './ui/button.svelte';
	import ImgMom from '$lib/assets/mom.webp';

	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
	import { ChevronLeft, ChevronRight } from 'lucide-svelte';

	const cards = [
		{
			icon: HandPlatterPlus,
			title: 'Seek Support',
			description:
				'Our NGO is here to help you through your cancer journey. Reach out to us today for support and care.',
			ctaText: 'Seek Support',
			href: 'mailto:Priyanka.joshi@jarurat.care?subject=Seek%20Support%20For%20Cancer'
		},
		{
			icon: HandPlatterHeart,
			title: 'Cancer Care Community',
			description:
				'Healing Hearts – Our community is here to guide, comfort, and stand beside those facing cancer.',
			ctaText: 'Join Here',
			href: 'https://chat.whatsapp.com/I95f9ycybYSDQIbOW4e0bY'
		},
		{
			icon: Hand,
			title: 'HOPE AI CHATBOT',
			description:
				'Our AI assistant is here to provide clarity and support during your cancer journey. Try it today.',
			ctaText: 'Visit Here',
			href: 'https://wa.me/919389529263?text=Hi'
		}
	];

	const sliderTop = [
		{
			title: 'Why Cancer',
			content:
				'Cancer remains one of the leading causes of death worldwide, affecting millions of lives each year. Despite advancements in research and treatment, many people still lack access to the care and support they need. By focusing on cancer, we aim to raise awareness, provide vital resources, and drive efforts toward early detection, effective treatment.'
		},
		{
			title: 'Why Donate',
			content:
				'Donations are the lifeblood of Jarurat Care, empowering us to provide critical support to cancer patients who need it most. Your contributions directly fund life-saving treatments, emotional care, and essential services that make a real difference in peoples lives. Every donation brings us one step closer to a world where no one has to face cancer alone. Your support is not just important—its transformative.'
		},
		{
			title: 'Why Collaborate',
			content:
				'Join us because together, we can do so much more. By collaborating, we pool our strengths and resources to bring greater support to those fighting cancer. Your partnership helps us reach more people and make a bigger impact. Let’s work hand in hand to change lives.'
		}
	];

	const sliderBottom = [
		{
			type: 'image',
			title: 'Where it all Started',
			img: ImgMom,
			caption:
				`Rekha's last trip before she started her battle with Cholangiocarcinoma. She succumbed on 25th December, 2023.`
		},
		{
			type: 'text',
			title: 'Our Beginning',
			content:
				'After losing our beloved mother in December 2023, we founded Jarurat Care to support families navigating similar battles.'
		}
	];
</script>

<!-- ================= TOP SLIDER ================= -->

<section class="py-12 md:py-16 bg-[#EFFAFD]">
	<div class="max-w-6xl mx-auto px-4 md:px-6 relative">
		<Splide
			hasTrack={false}
			options={{
				type: 'loop',
				perPage: 1,
				autoplay: true,
				interval: 3000,
				pauseOnHover: true,
				speed: 800,
				gap: '1.5rem'
			}}
		>
			<SplideTrack>
				{#each sliderTop as slide}
					<SplideSlide>
						<div class="bg-white p-6 md:p-10 rounded-3xl md:rounded-4xl shadow-lg text-center min-h-[240px] md:min-h-[280px] flex flex-col justify-center">
							<h3 class="text-xl md:text-3xl font-bold text-[#0D2561] mb-3 md:mb-4">
								{slide.title}
							</h3>
							<p class="text-[#475569] leading-relaxed text-sm md:text-base">
								{slide.content}
							</p>
						</div>
					</SplideSlide>
				{/each}
			</SplideTrack>

			<div class="splide__arrows absolute inset-x-0 flex justify-between top-1/2 -translate-y-1/2 px-2 md:px-4">
				<Button class="splide__arrow splide__arrow--prev bg-white rounded-full shadow-md p-2 md:-ml-4">
					<ChevronLeft class="w-5 h-5 md:w-6 md:h-6 text-[#1E40AF]" />
				</Button>
				<Button class="splide__arrow splide__arrow--next bg-white rounded-full shadow-md p-2 md:-mr-4">
					<ChevronRight class="w-5 h-5 md:w-6 md:h-6 text-[#1E40AF]" />
				</Button>
			</div>
		</Splide>
	</div>
</section>

<!-- ================= CARDS ================= -->

<section class="relative py-10 md:py-12 px-4 bg-[#F4F8FF]">
	<div class="max-w-7xl mx-auto">
		<h2 class="text-2xl md:text-4xl font-bold text-[#0D2561] text-center mb-8 md:mb-10">
			Our Support Services
		</h2>

		<div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6 md:gap-8">
			{#each cards as card}
				<div class="bg-white rounded-3xl shadow-lg p-6 md:p-8 text-center hover:scale-105 transition duration-300">
					<div class="w-14 h-14 mx-auto flex items-center justify-center bg-[#E5ECFF] rounded-full mb-4">
						<svelte:component this={card.icon} class="w-7 h-7 text-[#1E40AF]" />
					</div>
					<h3 class="text-lg md:text-xl font-semibold text-[#0D2561] mb-2">
						{card.title}
					</h3>
					<p class="text-[#475569] text-sm mb-4">
						{card.description}
					</p>
					<a href={card.href}>
						<Button class="bg-[#1E40AF] text-white hover:bg-[#0D2561]">
							{card.ctaText}
						</Button>
					</a>
				</div>
			{/each}
		</div>
	</div>
</section>
<!-- ================= BOTTOM STORY SLIDER ================= -->
<section class="py-12 md:py-16 bg-white">
	<div class="max-w-5xl mx-auto px-4 md:px-6 relative">
		<Splide
			hasTrack={false}
			options={{
				type: 'loop',
				perPage: 1,
				autoplay: true,
				interval: 5000,
				speed: 900,
				pauseOnHover: true
			}}
		>
			<!-- added leading-none here -->
			<SplideTrack class="leading-none">
				{#each sliderBottom as slide}
					<SplideSlide>
						{#if slide.type === 'image'}
							<div class="relative rounded-3xl overflow-hidden shadow-2xl">
								<img
									src={slide.img}
									alt={slide.title}
									class="w-full h-[250px] md:h-[360px] object-cover block"
								/>

								<div class="absolute inset-0 bg-[#002944]/60 flex items-end">
									<div class="p-5 md:p-8 text-white">
										<h3 class="text-xl md:text-3xl font-bold mb-2">
											{slide.title}
										</h3>
										<p class="opacity-90 max-w-xl text-sm md:text-base">
											{slide.caption}
										</p>
									</div>
								</div>
							</div>

						{:else}

							<div class="bg-[#F8FAFC] p-6 md:p-12 rounded-3xl shadow-xl text-center min-h-[240px] md:min-h-[300px] flex flex-col justify-center">
								<h3 class="text-xl md:text-3xl font-bold text-[#0D2561] mb-4">
									{slide.title}
								</h3>

								<p class="text-[#475569] leading-relaxed max-w-2xl mx-auto text-sm md:text-base">
									{slide.content}
								</p>

								<p class="text-sm text-gray-400 mt-4">
									Empowering communities through care, compassion, and support.
								</p>
							</div>

						{/if}
					</SplideSlide>
				{/each}
			</SplideTrack>

			<div class="splide__arrows absolute inset-x-0 flex justify-between top-1/2 -translate-y-1/2 px-2 md:px-4">
				<button class="splide__arrow splide__arrow--prev bg-white rounded-full shadow-md p-2">
					<ChevronLeft class="w-5 h-5 md:w-6 md:h-6 text-[#1E40AF]" />
				</button>

				<button class="splide__arrow splide__arrow--next bg-white rounded-full shadow-md p-2">
					<ChevronRight class="w-5 h-5 md:w-6 md:h-6 text-[#1E40AF]" />
				</button>
			</div>

		</Splide>
	</div>
</section>