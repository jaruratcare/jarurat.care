<script>
	import CaregivingCard from './caregiving-card.svelte';
	import WhiteLogo from '$lib/svg/white-logo.svelte';
	import PeoplePlus from '$lib/svg/people-plus.svelte';
	import ChatbotIcon from '$lib/svg/chatbot-icon.svelte';
	import Cards from './cards.svelte';
	import WaveUnion from '$lib/svg/about/wave-union.svelte';
	import { onMount } from "svelte";
	import { Splide, SplideSlide } from "@splidejs/svelte-splide";
	import priyankaWhiteBackgound from '$lib/assets/team-members/priyanka-whiteBackgound.png';

	const items = [
		{
			icon: PeoplePlus,
			title: 'Cancer Connect',
			about:
				'A group of committed caregivers ready to assist with connecting patients, caregivers, and healthcare professionals.'
		},
		{
			icon: WhiteLogo,
			title: 'Treatment Care Think Tank',
			about:
				'An expert team of oncologists, officials, dieticians, and spiritual advisors for tailored patient care.'
		},
		{
			icon: ChatbotIcon,
			title: 'Hope - AI Chatbot',
			about:
				'Available on our website jarurat.care, Hope provides immediate support and guidance.'
		}
	];

	const founders = [
		{ image: priyankaWhiteBackgound, name: 'Priyanka Joshi' }
	];

	let advisoryBoard = [];
	let splideInstance;
	let loadingAdvisoryBoard = true;

	async function fetchData(url, callback) {
		try {
			const response = await fetch(url);
			const data = await response.json();
			callback(data);
		} catch (error) {
			console.error(error);
		}
	}

	onMount(() => {
		fetchData(
			'https://jarurat-care-backend.onrender.com/jc/advisoryBoard/profiles/getAll',
			(data) => {
				advisoryBoard = data?.profiles || [];
				loadingAdvisoryBoard = false;
			}
		);
	});
</script>

<div class="bg-[#D3F2FC] pt-32 pb-20 relative overflow-x-hidden">

	<!-- CAREGIVING -->
	<section class="max-w-6xl mx-auto px-4 mt-20 md:mt-32">

		<div class="bg-[#0D2561] rounded-3xl px-6 md:px-16 py-10 md:py-16 shadow-xl">

			<h2 class="text-center text-xl font-extrabold mb-10">
				<span class="text-white">CAREGIVING</span>
				<span class="text-[#FFBA41]"> Solution</span>
			</h2>

			<div class="flex flex-col md:flex-row gap-8 md:gap-10">

				{#each items as item}
					<CaregivingCard
						icon={item.icon}
						about={item.about}
						title={item.title}
					/>
				{/each}

			</div>

		</div>

	</section>


	<!-- ADVISORY BOARD -->
<section class="max-w-6xl mx-auto mt-20 px-6 md:px-0 flex flex-col md:flex-row gap-10 items-start">

	<!-- Left text -->
	<div class="md:w-[320px]">

		<h2 class="text-2xl font-extrabold">
			<span class="text-primaryBlue">Advisory</span>
			<span class="text-black"> Board</span>
		</h2>

		<p class="text-[#00408A] text-sm mt-3">
			Our Advisory Board features top oncologists and cancer experts guiding us in providing the best care.
		</p>

	</div>

	<!-- Slider -->
	<div class="w-full md:w-[65%]">

		{#if !loadingAdvisoryBoard}

			<Splide
				options={{
					type: 'loop',
					perPage: 3,
					gap: '2rem',
					autoplay: true,
					interval: 3000,
					pauseOnHover: true,
					speed: 800,
					arrows: false,
					pagination: true,
					padding: '1rem',
					breakpoints: {
						1024: {
							perPage: 2,
							gap: '1.5rem',
							padding: '0.5rem'
						},
						768: {
							perPage: 1,
							gap: '1rem',
							padding: '0'
						}
					}
				}}
				bind:this={splideInstance}
				class="w-full"
			>

				{#each advisoryBoard as profile}

					<SplideSlide>

						<Cards
							image={profile.images[0]}
							name={profile.name}
							about={profile.designation}
						/>

					</SplideSlide>

				{/each}

			</Splide>

		{:else}

			<div class="text-center">Loading...</div>

		{/if}

	</div>

</section>


	<!-- WAVE -->
	<div class="absolute bottom-[28rem] hidden lg:block left-0 w-[99.5%] rotate-6 z-10">
		<WaveUnion />
	</div>


	<section class="bg-white max-w-4xl mx-auto mt-24 rounded-3xl pt-10 pb-16 px-6 relative z-10">

	<div class="text-center mb-8">

		<h2 class="font-extrabold text-2xl">
			<span class="text-primaryBlue">Pioneers Of</span>
			<br />
			Our Foundation
		</h2>

		<p class="text-[#00408A] text-sm font-semibold mt-3">
			The heart behind our foundation comes from someone who personally experienced the challenges of cancer.
		</p>

	</div>

	<div class="flex flex-col items-center justify-center gap-10">

		{#each founders as founder}

			<div class="relative mx-auto">

				<div class="bg-primaryBlue w-56 h-[20rem] -rotate-2 rounded-xl"></div>

				<div class="absolute top-0 w-56 h-[20rem]">

					<img
						class="h-full w-full object-cover rounded-xl"
						src={founder.image}
						alt=""
					/>

					<div class="bg-white text-center py-3 absolute bottom-0 w-full">

						<div class="text-[#0D2460]">
							{founder.name}
						</div>

						<div class="text-primaryBlue">
							Co-Founder
						</div>

					</div>

				</div>

			</div>

		{/each}

	</div>

</section>

</div>