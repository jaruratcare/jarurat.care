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
	// import { Carousel, Slide } from 'svelte-carousel';
	import { onMount } from 'svelte';
	import { Splide, SplideSlide } from '@splidejs/svelte-splide';

	
	let splideInstance;



  // Go to next slide
  const handleNextClick = () => {
    
      splideInstance.go('>');
    
  };

   // Go to previous slide
   const handlePrevClick = () => {
    
      splideInstance.go('<');
    
  };

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

	const comunityMember = [
		{
			image: member,
			name: 'Neha M.',
			position: 'Member',
			about: 'Being a member of Jarurat Care feels like being part of a caring family.'
		},
		{
			image: member,
			name: 'Neha M.',
			position: 'Member',
			about: 'Being a member of Jarurat Care feels like being part of a caring family.'
		},
		{
			image: member,
			name: 'Neha M.',
			position: 'Member',
			about: 'Being a member of Jarurat Care feels like being part of a caring family.'
		},
		{
			image: member,
			name: 'Neha M.',
			position: 'Member',
			about: 'Being a member of Jarurat Care feels like being part of a caring family.'
		},
		{
			image: member,
			name: 'Neha M.',
			position: 'Member',
			about: 'Being a member of Jarurat Care feels like being part of a caring family.'
		},
		{
			image: member,
			name: 'Neha M.',
			position: 'Member',
			about: 'Being a member of Jarurat Care feels like being part of a caring family.'
		},
		{
			image: member,
			name: 'Neha M.',
			position: 'Member',
			about: 'Being a member of Jarurat Care feels like being part of a caring family.'
		}
	];
</script>

<div class="bg-[#D3F2FC] mt-64 relative">
	<div class="h-[411px]">
		<div class="lg:px-16 px-8 py-16 bg-[#0D2561] absolute -top-44 md:left-[5%] left-0 rounded-3xl">
			<div class="text-xl text-start md:text-center font-extrabold mb-8">
				<span class="text-white"> CAREGIVING </span> <span class="text-[#FFBA41]">Solution</span>
			</div>
			<div class="flex lg:gap-10 gap-6">
				{#each items as item}
					<CaregivingCard icon={item.icon} about={item.about} title={item.title} />
				{/each}
			</div>
		</div>
	</div>
	<div
		class="advisory board flex md:flex-row flex-col px-8 md:px-0 text-center md:text-start justify-center gap-6"
	>
		<div class="md:w-[332px]">
			<div class="font-extrabold text-xl">
				<span class="text-primaryBlue">Advisory </span> <span class="text-black">Board</span>
			</div>
			<div class="text-[#00408A] text-sm mt-2">
				Our Advisory Board features top oncologists and cancer experts. They guide us in providing
				the best care and staying updated on treatment advances, helping us make a real difference.
			</div>
		</div>
		<div class="cards px-4 md:px-0  gap-5 md:w-1/2 relative z-30">
			<button class="back-arrow absolute w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] md:-left-3 left-4 z-10 top-[45%]"
			on:click={handlePrevClick}
			>
				<PreviousIcon />
				
			</button>

			<button class="front-arrow absolute w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] md:-right-3 right-4 z-10 top-[45%]" 
			
			on:click={handleNextClick}
			>
				<NextIcon />
			</button>
			<Splide
			options={{
			  type: 'loop',
			  perPage: 3,
			  gap: '16rem',
			  autoplay: false,
			  speed: 800,
			  arrows: false,
			  pagination: false,
			}}
			bind:this={splideInstance}
		  >
				{#each cards as card}
				<SplideSlide>
					<Cards image={card.image} name={card.name} about={card.about} />
				</SplideSlide>
				{/each}
			</Splide>
		</div>
	</div>

	<!-- background wave -->
	<div class="absolute bottom-[28rem] hidden lg:block left-0 w-[99.5%] rotate-6 z-10">
		<WaveUnion />
	</div>

	<!-- pioneers of our foundation -->
	<div class="bg-[#FFFFFF] w-2/3 mx-auto pt-10 mt-20 rounded-3xl z-10 relative">
		<div class="md:text-center text-start px-16">
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
			class="cards flex justify-center gap-10 pt-9 pb-16 rounded-b-3xl lg:px-16 bg-gradient-to-b from-white to-[#D3F2FC]"
		>
			{#each founders as item}
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
			{/each}
		</div>
	</div>

	<!-- our team -->
	<div
		class=" bg-primaryBlue flex md:flex-row flex-col justify-center py-24 gap-6 relative bottom-11"
	>
		<div class="md:w-[332px] px-8 md:px-0 lg:mr-12">
			<div class="font-extrabold text-xl relative z-30">
				<span class="text-white">Our </span> <span class="text-[#FFBA41]">Team</span>
			</div>
			<div class="text-white text-sm mt-2">
				Our Advisory Board features top oncologists and cancer experts. They guide us in providing
				the best care and staying updated on treatment advances, helping us make a real difference.
			</div>
		</div>
		<div class="cards px-4 md:px-0  gap-5 md:w-1/2 relative z-30">
			<button class="back-arrow absolute w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] md:-left-3 left-4 z-10 top-[45%]"
			on:click={handlePrevClick}
			>
				<PreviousIcon />
				
			</button>

			<button class="front-arrow absolute w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] md:-right-3 right-4 z-10 top-[45%]" 
			
			on:click={handleNextClick}
			>
				<NextIcon />
			</button>
			<Splide
			options={{
			  type: 'loop',
			  perPage: 2,
			  gap: '-3rem',
			  autoplay: false,
			  speed: 800,
			  arrows: false,
			  pagination: false,
			}}
			bind:this={splideInstance}
		  >
				{#each cards as card}
				<SplideSlide>
					<Cards image={card.image} name={card.name} about={card.about} />
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
			<button class="bakward w-8 h-8 hidden md:block cursor-pointer rounded-full p-2 bg-[#CFD6DF]"
			on:click={handlePrevClick}
			>
				<PreviousIcon />
			</button>
			<button class="upward w-8 h-8 cursor-pointer hidden md:block rounded-full p-2 bg-[#CFD6DF]"
			on:click={handleNextClick}
			>
				<NextIcon />
			</button>
		</div>
	</div>
	<div class="cards overflow-x-hidden">
		<div class="card  px-8 md:px-0">
			<Splide
			options={{
			  type: 'loop',
			  perPage: 3,
			  gap: '2rem',
			  autoplay: false,
			  speed: 800,
			  arrows: false,
			  pagination: false,
			}}
			bind:this={splideInstance}
		  >
			{#each comunityMember as member}
			<SplideSlide>
				<ComunityCard
					name={member.name}
					image={member.image}
					about={member.about}
					position={member.position}
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
