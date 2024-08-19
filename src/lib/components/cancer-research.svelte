<script>
	import { onMount } from 'svelte';
	import Header from './ui/header.svelte';
	import Button from './ui/button.svelte';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
	import { ChevronLeft, ChevronRight, Search } from 'lucide-svelte';
	import AnimatedGradientMesh from '$lib/svg/animated-gradient-mesh.svelte';
	import { t } from '$lib/translations/translations.js';

	let innerWidth = 0;
	const results = [
		{
			title: 'Global Cancer Incidence and Mortality Rates and Trends',
			excerpt: 'There are limited published data on recent cancer incidence and mortality trends worldwide. We used the International Agency for Research on Cancer\'s CANCERMondial clearinghouse to present age-'
		},
		{
			title: 'Measuring cancer evolution from the genome.',
			excerpt: 'The temporal dynamics of cancer evolution remain elusive, because it is impractical to longitudinally observe cancers unperturbed by treatment. Co'
		},
		{
			title: 'The Role of Telomerase in Breast Cancer\'s Response to Therapy.',
			excerpt: 'Currently, breast cancer appears to be the most widespread cancer in the world and the most common cause of cancer deaths.'
		}
	];

	onMount(() => {
		innerWidth = window.innerWidth;
		window.addEventListener('resize', () => {
			innerWidth = window.innerWidth;
		});
	});
</script>

<div class="py-10 sm:py-20 relative px-4">
	<div class="absolute inset-0 overflow-hidden -z-10">
		<!-- can't use. performance issue -->
		<!-- <AnimatedWave class="" /> -->

		<AnimatedGradientMesh />
	</div>

	<Header
		title={$t('home.cancer-research.heading')}
		subtitle={$t('home.cancer-research.subheading')}
	/>

	<form
		class="border rounded-full max-w-[35rem] w-full mx-auto bg-[#DBE1E6] overflow-hidden flex items-center my-14 relative z-10"
	>
		<input
			class="py-2 px-4 border-0 bg-transparent placeholder:text-[#576171] grow outline-none"
			placeholder={$t('home.cancer-research.search_placeholder')}
		/>

		<div class="py-2 px-3 text-[#576171]">
			<Search />
		</div>
	</form>

	<p class="font-manrope text-[#04509C] font-semibold text-center flex items-center justify-center">
		<span class="px-3 py-1 bg-[#FDDCA1] rounded-full text-sm">{$t('home.cancer-research.results_for')} “Breast Cancer”</span>
	</p>

	<Splide
		hasTrack={false}
		aria-label="..."
		class="max-w-[80rem] mx-auto mt-4"
		options={{ perPage: innerWidth < 600 ? 1 : 3, type: 'loop', gap: '2rem' }}
	>
		<div class="splide__arrows flex items-center justify-end gap-2 mb-2">
			<Button class="splide__arrow splide__arrow--prev size-8 p-0 items-center">
				<ChevronLeft class="w-full" />
			</Button>
			<Button class="splide__arrow splide__arrow--next size-8 p-0 flex items-center">
				<ChevronRight class="w-full" />
			</Button>
		</div>

		<div class="custom-wrapper">
			<SplideTrack>
				{#each results as result}
					<SplideSlide class="p-1">
						<div class="h-full flex flex-col gap-1 border rounded-lg p-4 sm:p-8 bg-white">
							<h2 class="text-[#04509C] text-[1.2em] leading-[1.3] font-semibold">
								{result.title}
							</h2>
							<div class="grow"></div>
							<p class="text-[#576171]">{result.excerpt.trim().substring(0, 100) + '...'}</p>

							<Button class="mt-4 px-4 py-1 self-start text-sm">{$t('home.cancer-research.read_now')}</Button>
						</div>
					</SplideSlide>
				{/each}
			</SplideTrack>
		</div>
	</Splide>
</div>
