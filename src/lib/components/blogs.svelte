<script>
	import Header from './ui/header.svelte';
	import Button from './ui/button.svelte';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
	import { ChevronLeft, ChevronRight, Search } from 'lucide-svelte';
	import AnimatedWave from '$lib/svg/animated-wave.svelte';
	import { onMount } from 'svelte';
	import AnimatedGradientMesh from '$lib/svg/animated-gradient-mesh.svelte';
	import SingleWave from '$lib/svg/single-wave.svelte';
	import { t } from '$lib/translations/translations';

	let innerWidth = 0;
  $: blogs = [
		{
			title: $t("home.newsAndBlogs.blog1.title"),
			excerpt: $t("home.newsAndBlogs.blog1.excerpt"),
			tag: $t("home.newsAndBlogs.blog1.tag"),
			date: new Date()
		},
		{
			title: $t("home.newsAndBlogs.blog2.title"),
			excerpt: $t("home.newsAndBlogs.blog2.excerpt"),
			tag: $t("home.newsAndBlogs.blog2.tag"),
			date: new Date()
		},
		{
			title: $t("home.newsAndBlogs.blog3.title"),
			excerpt: $t("home.newsAndBlogs.blog3.excerpt"),
			tag: $t("home.newsAndBlogs.blog3.tag"),
			date: new Date()
		}
	];

	onMount(() => {
		innerWidth = window.innerWidth;
		window.addEventListener('resize', () => {
			innerWidth = window.innerWidth;
		});
	});
</script>

<SingleWave fill="#d2f1fc" />
<div class="py-10 sm:py-20 relative px-4 bg-[#dbeffe]">
	<Header title={$t("home.newsAndBlogs.title")} />

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
				{#each blogs as blog}
					<SplideSlide class="p-1">
						<div class="h-full flex flex-col gap-1 border rounded-xl bg-white overflow-hidden">
							<div class="bg-gray-200 aspect-video"></div>

							<h2
								class="text-[#04509C] text-[1.2em] leading-[1.3] font-semibold px-4 sm:px-8 pt-4 sm:pt-8"
							>
								{blog.title}
							</h2>

							<div class="grow"></div>

							<p class="text-[#576171] px-4 sm:px-8">
								{blog.excerpt.trim().substring(0, 100) + '...'}
							</p>

							<div class="px-4 sm:px-8 pb-4 sm:pb-8">
								<Button class="mt-4 px-4 py-1 self-start text-sm">Know More</Button>
							</div>
						</div>
					</SplideSlide>
				{/each}
			</SplideTrack>
		</div>
	</Splide>
</div>
<SingleWave fill="#d2f1fc" class="rotate-180" />