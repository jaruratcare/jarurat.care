<script lang="ts">
	import HandPlatterHeart from '$lib/svg/hand-platter-heart.svelte';
	import HandPlatterPlus from '$lib/svg/hand-platter-plus.svelte';
	import Hand from '$lib/svg/hand.svelte';
	import Wave from '$lib/svg/wave.svelte';
	import Button from './ui/button.svelte';

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

</script>



<div
	id="get-involved"
	class="relative flex flex-col sm:flex-row bg-[#effafd] sm:bg-white overflow-hidden rounded-t-3xl"
>
	<div class="absolute inset-x-0 top-0 z-0 hidden sm:block">
		<Wave class="w-full " />
	</div>

	{#each cards as card}
		<div
			class="flex flex-col items-center  px-4 sm:px-8 py-8 sm:py-16 grow z-10"
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

