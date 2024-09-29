<script>
	import ChatbotIcon from '$lib/svg/chatbot-icon.svelte';
	import NextIcon from '$lib/svg/next-icon.svelte';
	import PeoplePlus from '$lib/svg/people-plus.svelte';
	import PreviousIcon from '$lib/svg/previous-icon.svelte';
	import WhiteLogo from '$lib/svg/white-logo.svelte';
	import Cards from '../cards.svelte';
	import CaregivingCard from '../caregiving-card.svelte';
	import priyanka from '$lib/assets/team-members/priyanka.webp';
	import priyankaWhiteBackgound from '$lib/assets/team-members/priyanka-whiteBackgound.png';
	import ayush from '$lib/assets/team-members/ayush.png';
	import Button from '../ui/button.svelte';

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
		// {
		// 	image: priyanka,
		// 	name: 'Priyanka',
		// 	about: 'Medical Oncologist'
		// },
		// {
		// 	image: priyanka,
		// 	name: 'Priyanka',
		// 	about: 'Medical Oncologist'
		// }
	];

	const founders = [
		{ image: priyankaWhiteBackgound, name: 'Priyanka Joshi' },
		{ image: ayush, name: 'Ayush Anand' }
	];
</script>

<div class="bg-[#D3F2FC] h-[35rem] mb-5">
	<!-- <div class="h-[18rem] w-[90%] mx-auto">
		<div class="lg:px-16 px-8 py-16 bg-[#0D2561] rounded-3xl">
			<div class="text-xl text-start md:text-center font-extrabold mb-8">
				<span class="text-white"> CAREGIVING </span> <span class="text-[#FFBA41]">Solution</span>
			</div>
			<div class="flex justify-center lg:gap-10 gap-6">
				{#each items as item}
					<CaregivingCard icon={item.icon} about={item.about} title={item.title} />
				{/each}
			</div>
		</div>
	</div> -->
</div>

<div
	class="advisory board pt-64 mt-80 pb-20 bg-[#D3F2FC] flex md:flex-row flex-col px-8 md:px-0 text-center md:text-start justify-center gap-6 relative"
>
	<!-- pioneers of our foundation -->

	 <div class="bg-[#FFFFFF] flex flex-col lg:flex-row bg-gradient-to-b from-white to-[#abd7e793] md:w-2/3 mx-auto pt-10 mt-20 rounded-3xl z-10 absolute -top-80 shadow-lg"> 
		<div class="md:text-center text-start lg:pl-16 ">
			<div class="mb-5">
				<div class="text-primaryBlue font-extrabold text-2xl">Pioneers Of</div>
				<div class="font-extrabold text-2xl">Our Foundation</div>
			</div>
			<div class="text-[#00408A] text-sm font-semibold">
				The heart behind our foundation is someone who personally knows the challenges of battling
				cancer. Driven by a deep desire to help others, they started Jarurat Care to offer support,
				hope, and a community to those facing this difficult journey.
			</div>
			<a class="hidden md:block" href="/about-us"><Button class="mt-5">About us</Button> </a>
			
		</div>

		<div class="cards flex flex-col md:flex-row  justify-center items-center gap-10 pt-9 pb-16 rounded-b-3xl lg:px-16 ">
			{#each founders as item}
				<div class="card relative z-30">
					<div class="back bg-primaryBlue w-56 md:w-52 h-[18rem] -rotate-2"></div>
					<div class="front w-56 md:w-52  absolute top-0">
						<div class="image h-[18rem]">
							<img class="h-[18rem] relative z-30" src={item.image} alt="" />
						</div>
						<div
							class="about bg-white z-30 flex flex-col justify-center items-center py-4 absolute w-full bottom-[1px] "
						>
							<div class="text-[#0D2460]">{item.name}</div>
							<div class="text-primaryBlue">Co- Founder</div>
						</div>
					</div>
				</div>
			{/each}
		</div>
	</div> 


	<!-- adviousry Board -->
	 <div class="md:mt-56 mt-[33rem] lg:mt-20 w-full  flex md:flex-row flex-col bg-red-700 text-center md:text-start justify-center gap-6">
	<div class="md:w-[332px]">
		<div class="font-extrabold text-xl">
			<span class="text-primaryBlue">Advisory </span> <span class="text-black">Board</span>
		</div>
		<div class="text-[#00408A] text-sm mt-2">
			Our Advisory Board features top oncologists and cancer experts. They guide us in providing the
			best care and staying updated on treatment advances, helping us make a real difference.
		</div>
	</div>
	<div class="cards flex justify-center gap-5 md:w-1/2 relative z-30">
		<div
			class="back-arrow absolute w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] md:-left-3 left-4 top-[45%]"
		>
			<PreviousIcon />
		</div>
		<div
			class="front-arrow absolute w-8 h-8 cursor-pointer rounded-full p-2 bg-[#CFD6DF] md:-right-3 right-4 top-[45%]"
		>
			<NextIcon />
		</div>
		{#each cards as card}
			<Cards image={card.image} name={card.name} about={card.about} />
		{/each}
	</div>
</div>
</div>
