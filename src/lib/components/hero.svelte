<script lang="ts">
	import Button from './ui/button.svelte';
	import heroImage from '../assets/hero.png';
	import hero2Image from '../assets/hero2.png';
	import hero3Image from '../assets/hero3.png';

	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
	import { ChevronLeft, ChevronRight } from 'lucide-svelte';
	import QuadCircle from '$lib/svg/quad-circle.svelte';
	import HalfCircle from '$lib/svg/half-circle.svelte';
	import QuadCircle2 from '$lib/svg/quad-circle2.svelte';

	const heroSlider = [];
</script>

<Splide
	hasTrack={false}
	options={{ perPage: 1, type: 'loop', gap: '3rem', pagination: true }}
	class="bg-black h-[91vh] "
>
	<div
		class="splide__arrows items-center justify-between absolute inset-x-10 z-10 translate-y-[20rem] hidden sm:flex"
	>
		<Button class="splide__arrow splide__arrow--prev size-10 p-0 flex items-center">
			<ChevronLeft class="w-full" />
		</Button>

		<Button class="splide__arrow splide__arrow--next size-10 p-0 flex items-center">
			<ChevronRight class="w-full" />
		</Button>
	</div>

	<SplideTrack>
		<SplideSlide class="m-0">
			<div
				class="relative h-full w-full bg-cover bg-center bg-no-repeat z-10"
				style="background-image: url({heroImage});"
			>
				<div class="w-full flex justify-end">
					<QuadCircle2 />
				</div>

				<div class="absolute inset-0 flex justify-center items-center">
					<div class="absolute left-24 bottom-24 flex flex-col gap-4">
						<h1 class="text-xl md:text-6xl text-white" style="font-family: 'Bayon', sans-serif;">
							JARURAT <span class="text-yellow-500">CARE</span>
						</h1>
						<p class="text-white text-md md:text-[0.85rem] max-w-[22rem] leading-5">
							Providing support, guidance, hope and personalized care for
							<span class="underline text-yellow-500">cancer patients</span>
							and their families. Here to ensure you never face your journey alone.
						</p>
						<a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
							<Button class="bg-yellow-500 border border-white">Seek Cancer Support</Button>
						</a>
					</div>
				</div>
			</div>
		</SplideSlide>

		<SplideSlide class="p-0">
			<div
				class="relative h-svh w-full bg-contain bg-center bg-no-repeat z-10"
				style="background-image: url({hero2Image});"
			>
				<div class="z-0">
					<QuadCircle />
				</div>

				<div class="absolute inset-0 flex justify-center items-center z-10">
					<div
						class=" flex flex-col justify-center items-center text-center absolute left-16 top-32 gap-4"
					>
						<h1
							class="flex flex-col text-xl md:text-6xl text-white"
							style="font-family: 'Bayon', sans-serif;"
						>
							<span>MAKE A </span> <span class="text-red-500">DIFFERENCE</span> <span>TODAY</span>
						</h1>
						<p class="text-white text-md md:text-[0.85rem] max-w-[22rem] leading-5">
							Every donation brings us one step closer to a cure ans a brighter future for those in
							need
						</p>

						<a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
							<Button class="bg-red-500 border border-white px-5 ">Donate Today</Button>
						</a>
					</div>
				</div>
			</div>
		</SplideSlide>

		<SplideSlide class="p-0">
			<div
				class="relative h-svh w-full bg-cover bg-center bg-no-repeat z-10"
				style="background-image: url({hero3Image});"
			>
				<div class=" w-full flex justify-center items-end boder border-red-600 z-0">
					<HalfCircle />
				</div>

				<div class="absolute inset-0 flex justify-center items-center z-10">
					<div class=" flex flex-col justify-center items-center text-center top-32 gap-4">
						<h1
							class="flex flex-col text-xl md:text-6xl text-white"
							style="font-family: 'Bayon', sans-serif;"
						>
							<span>GET <span class="text-yellow-500">INVOLVED</span> </span>
							TODAY
						</h1>
						<p class="text-white text-md md:text-[0.85rem] max-w-[22rem] leading-5">
							Join our community and make a meaningful impact with your time and skills.
						</p>

						<a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
							<Button class="bg-yellow-500 border border-white px-5 ">Volunteer</Button>
						</a>
					</div>
				</div>
			</div>
		</SplideSlide>
	</SplideTrack>
</Splide>

<style>
</style>
