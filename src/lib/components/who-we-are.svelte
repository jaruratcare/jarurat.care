<script>
	import CancerRibbon from '$lib/svg/cancer-ribbon.svelte';
	import SmallCircle from '$lib/svg/small-circle.svelte';
</script>

<div class="bg-[#0D2561] flex justify-between md:py-0  sm:py-28 relative overflow-hidden sm:rounded-b-3xl z-10 ">

	<div class="absolute top-0">
		<SmallCircle/>
	</div>

	<section class="w-[39rem] flex sm:flex-row flex-col my-28 ml-44 sm:gap-4 max-w-[57rem] relative">
		<div class=" flex flex-col gap-4 sm:gap-8 sm:max-w-[80%] grow text-[#F9FFF2]">
			<h2 class="font-manrope text-[1.5rem] font-bold">Who we are</h2>

			<p class="font-manrope text-[#F9FFF2] text-[1.7em] font-semibold leading-[1.1]">
				Our mission is to support and empower cancer patients and their families.
			</p>

			<p class="font-manrope font-thin text-[#E5FAFF] text-[0.9rem] leading-[1.6]">
				Through comprehensive programs, Jarurat Care provides vital support to those affected by
				cancer. We offer emotional, financial, and practical assistance to help patients and their
				families navigate their cancer journey.
			</p>
		</div>
	</section>

</div>
