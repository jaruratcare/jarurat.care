<script>
	import CaregivingCard from './caregiving-card.svelte';
	import WhiteLogo from '$lib/svg/white-logo.svelte';
	import PeoplePlus from '$lib/svg/people-plus.svelte';
	import ChatbotIcon from '$lib/svg/chatbot-icon.svelte';
	import Cards from './cards.svelte';
	import priyanka from '$lib/assets/team-members/priyanka.webp';
	import priyankaWhiteBackgound from '$lib/assets/team-members/priyanka-whiteBackgound.png';
	import ayush from '$lib/assets/team-members/ayush.png';
	import member from '$lib/assets/community-member/member-example.png';
	import ComunityCard from './comunity-card.svelte';
	import PreviousIcon from '$lib/svg/previous-icon.svelte';
	import NextIcon from '$lib/svg/next-icon.svelte';
	import WaveUnion from '$lib/svg/about/wave-union.svelte';
	import { onMount } from 'svelte';
	import { Splide, SplideSlide } from '@splidejs/svelte-splide';

	let advisoryBoard = [];
	let teamMeamber = [];
	let communityMember = [];

	let splideInstance1;
	let splideInstance2;
	let splideInstance;

	let particlesToShow;

	const updateParticlesToShow = () => {
		if (window.innerWidth < 768) {
			particlesToShow = 1;
		} else if (window.innerWidth < 1318) {
			particlesToShow = 2;
		} else {
			particlesToShow = 3;
		}
	};

	onMount(() => {
		updateParticlesToShow();
		window.addEventListener('resize', updateParticlesToShow);

		return () => window.removeEventListener('resize', updateParticlesToShow);
	});

	// Go to next slide
	const handleNextClick = () => {
		splideInstance.go('>');
	};
	const handleNextClick1 = () => {
		splideInstance1.go('>');
	};
	const handleNextClick2 = () => {
		splideInstance2.go('>');
	};

	// Go to previous slide
	const handlePrevClick = () => {
		splideInstance.go('<');
	};
	const handlePrevClick1 = () => {
		splideInstance1.go('<');
	};
	const handlePrevClick2 = () => {
		splideInstance2.go('<');
	};

	onMount(async () => {
		//advisory board
		try {
			const response = await fetch(`http://localhost:5001/jc/advisoryBoard/profiles/getAll`);
			if (!response.ok) {
				throw new Error('Failed to fetch data');
			}
			let advisory = await response.json();
			advisoryBoard = advisory?.profiles || [];
			console.log('advisory board ', advisory);
			console.log('profiles', advisoryBoard);
			console.log('images', advisoryBoard?.images);
		} catch (error) {
			console.log(`network error:- ${error}`);
		}

		//team Member

		try {
			const response = await fetch('http://localhost:5001/jc/team/getAll/');
			if (!response.ok) {
				throw new Error('Failed to fetch data');
			}
			let memberarray = await response.json();
			teamMeamber = memberarray?.members;
			console.log('communityMember', teamMeamber);
		} catch (error) {
			console.log('network error ', error);
		}

		//community
		try {
			const response = await fetch(`http://localhost:5001/jc/community/getAll/`);
			if (!response.ok) {
				throw new Error('Failed to fetch data');
			}
			let memberArray = await response.json();
			communityMember = memberArray?.members || [];
			console.log('community member images ', communityMember[0]?.images?.[0]);
		} catch (error) {
			console.log('Network error:- ', error);
		}
	});

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
		{
			image: priyanka,
			name: 'Priyanka',
			about: 'Medical Oncologist'
		},
		{
			image: priyanka,
			name: 'priyam',
			about: 'Medical Oncologist'
		},
		{
			image: priyanka,
			name: 'abdus',
			about: 'Medical Oncologist'
		},
		{
			image: priyanka,
			name: 'sourav',
			about: 'Medical Oncologist'
		}
	];

	const founders = [
		{ image: priyankaWhiteBackgound, name: 'Priyanka Joshi' },
		{ image: ayush, name: 'Ayush Anand' }
	];
</script>

<div class="bg-[hsl(195,87%,91%)] mt-64 relative">
	<div class="h-[411px]">
		<div
			class="lg:px-12 px-8 py-16 bg-[#0D2561] w-[90%] absolute -top-44 md:left-[4%] left-0 rounded-3xl"
		>
			<div class="text-2xl text-start md:text-center font-extrabold mb-8">
				<span class="text-white"> Caregiving </span> <span class="text-[#FFBA41]">Solutions</span>
			</div>
			<div class="">
				<Splide
					options={{
						type: '',
						perPage: particlesToShow,
						gap: '8rem',
						autoplay: false,
						speed: 800,
						arrows: false,
						pagination: true
					}}
					class="w-[98%]  .splide__pagination__page"
				>
					{#each items as item}
						<SplideSlide>
							<CaregivingCard icon={item.icon} about={item.about} title={item.title} />
						</SplideSlide>
					{/each}
				</Splide>
			</div>
		</div>
	</div>
	<div
		class="advisory mt-20 md:mt-1 board flex md:flex-row flex-col justify-center md:px-0 text-center md:text-start gap-9"
	>
		<div class="md:w-[332px] md:ml-20">
			<div class="font-extrabold text-2xl">
				<span class="text-primaryBlue">Advisory </span> <span class="text-black">Board</span>
			</div>
			<div class="text-[#00408A] text-sm mt-2">
				Our Advisory Board features top oncologists and cancer experts. They guide us in providing
				the best care and staying updated on treatment advances, helping us make a real difference.
			</div>
		</div>
		<div class="cards px-4 md:px-0 gap-5 md:w-[63%] relative z-30">
			<button
				class="back-arrow absolute w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] md:-left-3 left-4 z-10 top-[45%]"
				on:click={handlePrevClick1}
			>
				<PreviousIcon />
			</button>

			<button
				class="front-arrow absolute w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] md:-right-3 right-4 z-10 top-[45%]"
				on:click={handleNextClick1}
			>
				<NextIcon />
			</button>

			<Splide
				options={{
					type: 'loop',
					perPage: 3,
					gap: '5rem',
					autoplay: false,
					speed: 800,
					arrows: false,
					pagination: false,
					breakpoints: {
						768: {
							perPage: 1, // Ensure 1 card is shown on phones
							gap: '1rem' // Smaller gap for mobile
						},
						1212: {
							perPage: 2 // 2 cards between 768px and 1212px
						}
					}
				}}
				bind:this={splideInstance1}
				class=""
			>
				{#each advisoryBoard as profile}
					<SplideSlide>
						<Cards image={profile.images[0]} name={profile.name} about={profile.designation} />
					</SplideSlide>
				{/each}
			</Splide>
		</div>
	</div>

	<!-- background wave -->
	<div class="absolute bottom-[28rem] hidden lg:block left-0 w-[99.5%] rotate-6 z-10">
		<!-- <WaveUnion /> -->
	</div>

	<!-- pioneers of our foundation -->
	<div class="bg-[#FFFFFF] w-2/3 mx-auto pt-10 mt-20 rounded-3xl z-10 relative">
		<div class="md:text-center text-start md:px-16 px-4">
			<div class="mb-5">
				<div class="text-primaryBlue font-extrabold text-2xl">Pioneers Of</div>
				<div class="font-extrabold text-2xl">Our Foundation</div>
			</div>
			<div class="text-[#00408A] text-sm font-semibold">
				The heart behind our foundation is someone who personally knows the challenges of battling
				cancer. Driven by a deep desire to help others, they started Jarurat Care to offer support,
				hope, and a community to those facing this difficult journey.
			</div>
		</div>

		<div
			class="cards gap-10 pt-9 pb-16 rounded-b-3xl lg:px-16 bg-gradient-to-b from-white to-[#D3F2FC]"
		>
			<Splide
				options={{
					type: 'loop',
					perPage: 2,
					gap: '2rem',
					autoplay: true,
					speed: 800,
					arrows: false,
					pagination: true,
					breakpoints: {
						768: {
							perPage: 1, // Ensure 1 card is shown on phones
							gap: '1rem' // Smaller gap for mobile
						}
					}
				}}
			>
				{#each founders as item}
					<SplideSlide>
						<div class="card relative z-30">
							<div class="back bg-primaryBlue w-60 h-[20rem] -rotate-2"></div>
							<div class="front w-60 h-[20rem] absolute top-0">
								<div class="image h-[20rem]">
									<img class="h-[20rem] relative z-30" src={item.image} alt="" />
								</div>
								<div
									class="about bg-white flex flex-col justify-center items-center py-4 absolute w-full bottom-[1px]"
								>
									<div class="text-[#0D2460]">{item.name}</div>
									<div class="text-primaryBlue">Co- Founder</div>
								</div>
							</div>
						</div>
					</SplideSlide>
				{/each}
			</Splide>
		</div>
	</div>

	<!-- our team -->
	<div
		class=" bg-primaryBlue flex md:flex-row flex-col justify-center py-24 gap-6 relative bottom-11"
	>
		<div class="md:w-[332px] px-8 md:px-0 lg:mr-12">
			<div class="font-extrabold text-2xl relative z-30">
				<span class="text-white">Our </span> <span class="text-[#FFBA41]">Team</span>
			</div>
			<div class="text-white text-sm mt-2">
				Our Advisory Board features top oncologists and cancer experts. They guide us in providing
				the best care and staying updated on treatment advances, helping us make a real difference.
			</div>
		</div>
		<div class="cards px-4 md:px-0 gap-5 md:w-[60%] relative z-30">
			<button
				class="back-arrow absolute w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] md:-left-3 left-4 z-10 top-[45%]"
				on:click={handlePrevClick2}
			>
				<PreviousIcon />
			</button>

			<button
				class="front-arrow absolute w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] md:-right-3 right-4 z-10 top-[45%]"
				on:click={handleNextClick2}
			>
				<NextIcon />
			</button>
			<Splide
				options={{
					type: 'loop',
					perPage: 3,
					gap: '8rem',
					autoplay: false,
					speed: 800,
					arrows: false,
					pagination: false,
					breakpoints: {
						768: {
							perPage: 1, // Ensure 1 card is shown on phones
							gap: '1rem' // Smaller gap for mobile
						},
						1212: {
							perPage: 2 // 2 cards between 768px and 1212px
						}
					}
				}}
				bind:this={splideInstance2}
			>
				{#each teamMeamber as member}
					<SplideSlide>
						<Cards image={member.images[0]} name={member.name} about={member.designation} />
					</SplideSlide>
				{/each}
			</Splide>
		</div>
	</div>
</div>

<!-- //hear for community -->
<div class="bg-[#F8FCFF] pt-5 md:pl-10">
	<div class="flex justify-between mb-9">
		<div class="text-[#0D2460] pl-8 md:pl-0 font-extrabold">Hear from our community</div>
		<div class="flex gap-6 pr-7">
			<button
				class="bakward w-8 h-8 hidden md:block cursor-pointer rounded-full p-2 bg-[#CFD6DF]"
				on:click={handlePrevClick}
			>
				<PreviousIcon />
			</button>
			<button
				class="upward w-8 h-8 cursor-pointer hidden md:block rounded-full p-2 bg-[#CFD6DF]"
				on:click={handleNextClick}
			>
				<NextIcon />
			</button>
		</div>
	</div>
	<div class="cards overflow-x-hidden">
		<div class="card px-8 md:px-0 relative">
			
				<button
					class="bakward absolute left-3 top-[50%] z-10 w-8 h-8 md:hidden cursor-pointer rounded-full p-2 bg-[#CFD6DF]"
					on:click={handlePrevClick}
				>
					<PreviousIcon />
				</button>
				<button
					class="upward absolute right-4 top-[50%] z-10  w-8 h-8 cursor-pointer md:hidden  rounded-full p-2 bg-[#CFD6DF]"
					on:click={handleNextClick}
				>
					<NextIcon />
				</button>
			
			<Splide
				options={{
					type: 'loop',
					perPage: 3,
					gap: '2rem',
					autoplay: false,
					speed: 800,
					arrows: false,
					pagination: false,
					breakpoints: {
            768: {
              perPage: 1, // Ensure 1 card is shown on phones
              gap: '1rem', // Smaller gap for mobile
            },
            1212: {
              perPage: 2, // 2 cards between 768px and 1212px
			  gap: '4rem'
            },
          },
				}}
				bind:this={splideInstance}
			>
				<!-- {#each communityMember as member} -->
				{#each cards as member}
					<SplideSlide>
						<ComunityCard
							name={member.name}
							image={member.image}
							about={member.about}
							position={member.about}
						/>
					</SplideSlide>
				{/each}
			</Splide>
		</div>
	</div>

	<div class="showcase justify-center py-20 hidden md:flex">
		<div class="bg-[#D9D9D9] lg:h-[20rem] lg:w-[38rem] h-[18rem] w-[28rem] rounded-xl relative">
			<div
				class="w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] absolute lg:-left-28 -left-16 top-[40%]"
			>
				<PreviousIcon />
			</div>
			<div
				class="w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] absolute lg:-right-28 -right-16 top-[40%]"
			>
				<NextIcon />
			</div>
		</div>
	</div>
</div>

<style>
	/* Optional: Adjust the pagination dot styles */
	.splide__pagination__page {
		background-color: white;
		border-radius: 50%;
		width: 10px;
		height: 10px;
	}
</style>
