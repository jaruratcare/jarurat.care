<script lang="ts">
	import { cn } from '$lib/utils';
	import Header from './ui/header.svelte';
	import OurTeamwave from '$lib/svg/our-teamwave.svelte';
	import TeamCard from '$lib/components/team-card.svelte';
	import ImgPriyanka from '$lib/assets/landing-page/ourTeam/CoFounder.png';
	import ImgVishnu from '$lib/assets/landing-page/ourTeam/CEO.png';
	import ImgDeepak from '$lib/assets/landing-page/ourTeam/DesignIntern.png';
	import Button from './ui/button.svelte';
	import Button2 from './ui/tbutton.svelte';

	let activeIndex: number | null = null;
	const keyTeamMembers = [
		{
			name: 'Priyanka Joshi',
			designation: 'Co founder',
			text: `At Jarurat Care, our mission is to bring hope and healing to every person battling cancer. We believe in the power of community support, compassion, and innovation to make a real difference in the lives of those we serve.`,
			imageSrc: ImgPriyanka
		},
		{
			name: 'Vishnu Vijaykumar',
			designation: 'CEO',
			text: `At Jarurat Care, our mission is to bring hope and healing to every person battling cancer. We believe in the power of community support, compassion, and innovation to make a real difference in the lives of those we serve.`,
			imageSrc: ImgVishnu
		},
		{
			name: 'Deepak V Nair',
			designation: 'Design Intern',
			text: `At Jarurat Care, our mission is to bring hope and healing to every person battling cancer. We believe in the power of community support, compassion, and innovation to make a real difference in the lives of those we serve.`,
			imageSrc: ImgDeepak
		},
	];

	function toggleActiveCard(index: number) {
		activeIndex = activeIndex === index ? null : index;
		console.log(activeIndex);
	}
</script>

<!-- Team section -->
<div class="wave overflow-hidden">
	<OurTeamwave/>
</div>

<div class="max-w-[100%] mx-auto pb-16 sm:pb-20 px-2 overflow-hidden flex-col justify-center items-center relative">
	<Header title="Hear from " spantitle="our team" class="text-center font-rubik" />

	<div class="cards w-[80%] flex justify-between mt-10">
		{#each keyTeamMembers as member, index}
		   <button 
		   on:click={() => toggleActiveCard(index)}>
			<TeamCard
				name={member.name} 
				designation={member.designation} 
				imageSrc={member.imageSrc} 
				text={member.text}
				isSlidIn={activeIndex === index}
			/></button>
		{/each}
	</div>

	<div class="btn relative flex items-center justify-center gap-4">
		<a class="mt-8 flex items-center justify-center z-10 relative" href="/" target="_blank">
			<Button2 color="#0D2561">About Us</Button2>
		</a>
		<a class="mt-8 flex items-center justify-center z-10 relative" href="/" target="_blank">
			<Button>Contact Us</Button>
		</a>
	</div>
</div>
