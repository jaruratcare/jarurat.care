<script lang="ts">
	import SupportUsBg from '$lib/assets/donate/support-us-bg.webp';
	import SingleWavedarkblue from '$lib/svg/single-wavedarkblue.svelte';
	import Button from '../ui/button.svelte';
</script>

<div id="donate" class="font-rubik">
	<div
		class="sm:py-16 py-4 px-1 sm:px-48 max-w-full mx-auto bg-cover bg-right-bottom relative overflow-hidden sm:bg-none"
		style="background-image: url('{SupportUsBg}')"
	>
		<div class="sm:py-28 py-16 flex flex-col items-center justify-center md:items-start z-20 px-4">
			<div>
				<h2 class="text-3xl text-start sm:max-w-[50em] max-w-[7em] text-[#0155BD]">
					Support Us <span class="lg:text-[#0D2561]">On</span> <span class="text-[#0D2561]"> Our Mission</span>
				</h2>
				<p
					class="mt-6 text-sm sm:max-w-[30em] max-w-[85%] text-start text-[#0D2561]"
				>
					All donations to JaruratCare Foundation are eligible for 50%
					tax exemption under section 80G of the Income Tax Act.
				</p>
				<Button class="mt-4 z-20 font-sans">Donate Now</Button>
			</div>
		</div>
	</div>
</div>
<SingleWavedarkblue fill="#0D2561" class="absolute w-full sm:-mt-32 -mt-24"/>
