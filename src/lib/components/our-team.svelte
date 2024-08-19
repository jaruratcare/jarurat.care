<script>
	import Header from './ui/header.svelte';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
   import {t}from "$lib/translations/translations.js"
	import ImgPriyanka from '$lib/assets/team-members/priyanka.webp';

	const teamMembers = [
		{
			name: $t("home.Team.Name"),
			designation: $t("home.Team.Designation"),
			text: $t("home.Team.Message"),
			imageSrc: ImgPriyanka
		}
	];
</script>

<div class="max-w-[70rem] mx-auto py-16 sm:py-20 px-4 overflow-hidden">
	<Header title={$t("home.Team.Heading")} class="text-left font-rubik" />

	<Splide
		hasTrack={false}
		aria-label="..."
		class="max-w-[70rem] mx-auto mt-8 z-10"
		options={{ type: 'loop', arrows: false }}
	>
		<div class="custom-wrapper">
			<SplideTrack>
				{#each teamMembers as member}
					<SplideSlide class="p-2 pl-2 md:max-w-[80%] md:aspect-video">
						<div
							class="h-full rounded-lg flex overflow-hidden text-[#0D2561] shadow-md md:shadow-none border md:border-0"
						>
							<div class="grow max-w-0 md:max-w-[33%] h-full bg-[#D9D9D9]"></div>

							<div class="grow flex flex-col max-w-[100%] md:max-w-[40%] px-8 pt-6 pb-4 gap-4">
								<p>{member.text}</p>
								<div class="grow"></div>

								<div class="flex gap-2">
									<div
										class="aspect-square min-w-[3rem] rounded-full bg-cover"
										style="background-image: url('{member.imageSrc}')"
									></div>
									<div class="flex flex-col font-semibold">
										<h4>{member.name}</h4>
										<small>{member.designation}</small>
									</div>
								</div>
							</div>

							<div class="grow max-w-0 md:max-w-[33%] h-full bg-[#D9D9D9]"></div>
						</div>
					</SplideSlide>
				{/each}
			</SplideTrack>
		</div>
	</Splide>
</div>
