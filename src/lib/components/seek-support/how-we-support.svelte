<script>
	import Button from '../ui/button.svelte';
	import CardsOfSeeksupport from './cards-of-seeksupport.svelte';
	import { Splide, SplideSlide } from '@splidejs/svelte-splide';
	import { onMount } from 'svelte';

	let particlesToShow;

	const updateParticlesToShow = () => {
		if (window.innerWidth < 768) {
			particlesToShow = 1;
		} else if (window.innerWidth < 1318) {
			particlesToShow = 2;
		} else if (window.innerWidth < 1040) {
			particlesToShow = 3;
		} else {
			particlesToShow = 4;
		}
	};

	onMount(() => {
		updateParticlesToShow(); // Set initial value based on window size
		window.addEventListener('resize', updateParticlesToShow);

		return () => {
			window.removeEventListener('resize', updateParticlesToShow); // Cleanup on component destruction
		};
	});

	let items = [
		{
			title: 'Patient Advocacy',
			about: 'We stand by your side, helping navigate treatment options and healthcare systems',
			border: '#B0DFF9',
			list1: 'Insurance Assistance',
			list2: 'Legal Support',
			list3: 'Healthcare Navigation'
		},
		{
			title: 'Access to Treatment',
			about: 'We stand by your side, helping navigate treatment options and healthcare systems',
			border: '#B0DFF9',
			list1: 'Individual Counseling',
			list2: 'Support Groups',
			list3: '24/7 Helpline'
		},
		{
			title: 'Holistic Care Options',
			about: 'We stand by your side, helping navigate treatment options and healthcare systems',
			border: '#B0DFF9',
			list1: 'Nutrition Counseling',
			list2: 'Legal Support',
			list3: 'Healthcare Navigation'
		},
		{
			title: 'Patient Advocacy',
			about: 'We stand by your side, helping navigate treatment options and healthcare systems',
			border: '#B0DFF9',
			list1: 'Insurance Assistance',
			list2: 'Legal Support',
			list3: 'Healthcare Navigation'
		}
	];
</script>

<div class="py-24 ">
	<div class="flex flex-col justify-center items-center gap-3 ">
		<div class="text-3xl font-extrabold">
			<span>How We </span> <span class="text-[#0D2460]">Support You</span>
		</div>
		<div class="text-sm text-center">
			Comprehensive care and support throughout your cancer journey
		</div>
	</div>
	<div class=" mx-auto ">
		<Splide
			options={{
				type: 'loop',
				perPage: particlesToShow,
				gap: '2rem',
				autoplay: false,
				speed: 800,
				arrows: false,
				pagination: true
			}}
            class="mb-14 mt-20  mx-auto "
            >
			{#each items as item}
				<SplideSlide >
					<CardsOfSeeksupport
						title={item.title}
						about={item.about}
						border={item.border}
						list1={item.list1}
						list2={item.list2}
						list3={item.list3}
					/>
				</SplideSlide>
			{/each}
		</Splide>
	</div>

	<div class="flex justify-center">
		<Button class="relative z-10">Seek Support</Button>
	</div>
</div>
