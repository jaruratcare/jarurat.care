<script>
	import NextIcon from "$lib/svg/next-icon.svelte";


	import PreviousIcon from "$lib/svg/previous-icon.svelte";
	import { Splide, SplideSlide } from "@splidejs/svelte-splide";
</script>
<div class="advisory mt-20 md:mt-1 board flex md:flex-row flex-col justify-center md:px-0 text-center md:text-start gap-9">
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
				<PreviousIcon/>
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
						<!-- <Cards image={profile.images[0]} name={profile.name} about={profile.designation} /> -->
					</SplideSlide>
				{/each}
			</Splide>
		</div>
	</div>