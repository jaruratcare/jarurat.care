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
	import ayush from '$lib/assets/team-members/ayush.png';


	// Constants for display
	const items = [
		{
			icon: PeoplePlus,
			title: 'Cancer Connect',
			about:
					'A group of committed caregivers ready to assist with connecting patients, caregivers, and healthcare professionals for sharing updates, promoting awareness, and accessing educational resources.'
		},
		{
			icon: WhiteLogo,
			title: 'Treatment Care Think Tank',
			about:
					'An expert team of oncologists, officials, dieticians, and spiritual advisors to analyze individual cases for tailored, comprehensive patient care.'
		},
		{
			icon: ChatbotIcon,
			title: 'Hope - AI Chatbot',
			about:
					'Available on our website jarurat.care, Hope provides immediate support and information, designed to answer questions and provide guidance at any time.'
		}
	];

	const cards = [
		{ image: '$lib/assets/team-members/priyanka.webp', name: 'Priyanka', about: 'Medical Oncologist' }
	];

	const founders = [
		{ image: priyankaWhiteBackgound, name: 'Priyanka Joshi' },
		{ image: ayush, name: 'Ayush Anand' }
	];

	const communityMembers = [
		{
			image: '$lib/assets/community-member/member-example.png',
			name: 'Neha M.',
			position: 'Member',
			about: 'Being a member of Jarurat Care feels like being part of a caring family.'
		}
	];

	// State variables
	let particlesToShow;
	let advisoryBoard = [];
	let splideInstance;
	let loadingAdvisoryBoard = true;

	// Helper Functions
	async function fetchData(url, callback) {
		try {
			const response = await fetch(url);
			if (!response.ok) throw new Error(`Failed to fetch ${url}`);
			const data = await response.json();
			callback(data);
		} catch (error) {
			console.error(`Error fetching data from ${url}:`, error);
		}
	}

	function updateParticlesToShow() {
		particlesToShow = window.innerWidth < 768 ? 1 : window.innerWidth < 1318 ? 2 : 3;
	}

	// Lifecycle
	onMount(() => {
		updateParticlesToShow();
		window.addEventListener('resize', updateParticlesToShow);

		// Fetch advisory board data
		fetchData('https://jarurat-care-backend.onrender.com/jc/advisoryBoard/profiles/getAll', (data) => {
			advisoryBoard = data?.profiles || [];
			loadingAdvisoryBoard = false;

			if (splideInstance) {
				splideInstance.refresh();
			}
		});

		return () => {
			window.removeEventListener('resize', updateParticlesToShow);
		};
	});
</script>

<!-- Caregiving Section -->
<div class="bg-[#D3F2FC] mt-64 relative">
	<div class="h-[411px]">
		<div class="lg:px-16 px-8 py-16 bg-[#0D2561] absolute -top-44 md:left-[5%] left-0 rounded-3xl">
			<div class="text-xl text-start md:text-center font-extrabold mb-8">
				<span class="text-white">CAREGIVING</span> <span class="text-[#FFBA41]">Solution</span>
			</div>
			<div class="flex lg:gap-10 gap-6">
				{#each items as item}
					<CaregivingCard icon={item.icon} about={item.about} title={item.title} />
				{/each}
			</div>
		</div>
	</div>

	<!-- Advisory Board Section -->
	<div class="advisory mt-20 md:mt-1 flex md:flex-row flex-col justify-center md:px-0 text-center md:text-start gap-8">
		<!-- Left Section -->
		<div class="md:w-[332px] md:ml-20">
			<div class="font-extrabold text-2xl">
				<span class="text-primaryBlue">Advisory</span> <span class="text-black">Board</span>
			</div>
			<div class="text-[#00408A] text-sm mt-2">
				Our Advisory Board features top oncologists and cancer experts. They guide us in providing the best care and staying updated on treatment advances, helping us make a real difference.
			</div>
		</div>

		<!-- Right Section with Slider -->
		<div class="relative md:w-[63%] z-30">
			<!-- Splide Slider -->
			{#if !loadingAdvisoryBoard}
			<Splide
					options={{
          type: 'loop', // Enables looping
          perPage: 3, // Number of slides visible at once
          gap: '1rem', // Spacing between slides
          autoplay: true, // Auto-scrolls the carousel
          interval: 2000, // Time in ms for each slide (if autoplay is enabled)
          pauseOnHover: true, // Pause autoplay on hover
          speed: 1000, // Transition speed
          arrows: false, // Hide default arrows
          pagination: true, // Enable pagination dots
          breakpoints: {
            768: {
              perPage: 1, // Smaller viewports show 1 card
              gap: '0.5rem'
            },
            1212: {
              perPage: 2 // Medium viewports show 2 cards
            }
          }
        }}
					bind:this={splideInstance}
					class="relative"
			>
				{#each advisoryBoard as profile}
					<SplideSlide>
						<Cards image={profile.images[0]} name={profile.name} about={profile.designation} />
					</SplideSlide>
				{/each}
			</Splide>
			{:else}
				<div>Loading...</div> <!-- Loading state while fetching advisory board data -->
			{/if}
		</div>
	</div>

	<!-- Background Wave -->
	<div class="absolute bottom-[28rem] hidden lg:block left-0 w-[99.5%] rotate-6 z-10">
		<WaveUnion />
	</div>

	<!-- Pioneers Section -->
	<div class="bg-[#FFFFFF] w-11/12 sm:w-2/3 mx-auto pt-10 mt-20 rounded-3xl z-10 relative">
		<div class="md:text-center text-start px-6 sm:px-16">
			<div class="mb-5">
				<div class="text-primaryBlue font-extrabold text-2xl">Pioneers Of</div>
				<div class="font-extrabold text-2xl">Our Foundation</div>
			</div>
			<div class="text-[#00408A] text-sm font-semibold">
				The heart behind our foundation is someone who personally knows the challenges of battling cancer. Driven by a deep desire to help others, they started Jarurat Care to offer support, hope, and a community to those facing this difficult journey.
			</div>
		</div>

		<div class="cards flex flex-wrap justify-center gap-6 pt-9 pb-16 rounded-b-3xl lg:px-16 bg-gradient-to-b from-white to-[#D3F2FC]">
			{#each founders as founder}
				<div class="card relative z-30">
					<div class="back bg-primaryBlue w-60 h-[20rem] -rotate-2"></div>
					<div class="front w-60 h-[20rem] absolute top-0">
						<img class="h-[20rem] relative z-30" src={founder.image} alt="" />
						<div class="about bg-white flex flex-col justify-center items-center py-4 absolute w-full bottom-[1px]">
							<div class="text-[#0D2460]">{founder.name}</div>
							<div class="text-primaryBlue">Co-Founder</div>
						</div>
					</div>
				</div>
			{/each}
		</div>
	</div>

</div>


