<script lang="ts">
	import Button from '../ui/button.svelte';
	import Heart from '$lib/svg/donate/Heart.svelte';
	import CaregiverSupport from '$lib/svg/donate/caregiver-support.svelte';
	import Outreachedu from '$lib/svg/donate/outreachedu.svelte';
	import Apple from '$lib/svg/donate/apple.svelte';
</script>

<div class="bg-[#D3F2FC] py-12 sm:py-32 sm:px-10 px-2 font-rubik">
	<div class="block md:flex md:justify-between sm:px-32 relative overflow-hidden">
		<div class="flex flex-col items-center justify-center px-4 md:items-start z-20">
			<div>
				<h2 class="text-3xl sm:text-start text-center sm:max-w-[50em] max-w-[12em] text-[#0D2561]">
					Your <span class="text-[#0155BD]">Donation</span>
				</h2>
				<h2 class="text-3xl sm:text-start text-center md:max-w-[50em] max-w-[12em] text-[#0D2561]">
					Can Pave The Way
				</h2>
				<p
					class="mt-2 text-sm max-w-[25em] text-center md:text-start text-gray-600"
				>
					Help us support cancer patient's get the treatment, support and a chance at life.
				</p>
			</div>
			<div class="md:mt-8 hidden md:flex">
				<Button class="custom-button z-20 font-sans">Donate Now</Button>
			</div>
		</div>

		<div class="flex sm:w-[59%] w-full items-center justify-center mt-6 md:mt-0">
			<div class="grid grid-cols-2 grid-rows-2 md:grid-cols-2 md:grid-rows-2 gap-2 sm:gap-[1.2rem]">
				<div class="flex items-start justify-between rounded-xl gap-8 shadow-md p-5 bg-white border border-gray-200 max-w-sm relative">
						<div class="flex flex-col items-start justify-center gap-3 mr-6">
							<p class="md:text-[2vw] md:text-[#0D2561] text-start">₹5,000 +</p>
							<p class="font-rubik text-gray-600 text-[0.5em] md:text-[0.7em] ml-4 leading-[1.2]">Patient Care</p>
						</div>
						<div class="flex items-center justify-center w-10 h-10 rounded-full bg-[#D3F2FC] absolute right-4 top-4">
							<Heart class="p-2"/>
						</div>
					</div>
					<div class="flex items-start justify-between rounded-xl gap-4 shadow-md p-5 bg-white border border-gray-200 max-w-sm relative">
						<div class="flex flex-col items-start justify-center gap-3 mr-6">
							<p class="md:text-[2vw] md:text-[#0D2561] text-start">₹5,000 +</p>
							<p class="font-rubik text-gray-600 text-[0.5em] md:text-[0.7em] ml-4 leading-[1.2]">Caregiver Support</p>
						</div>
						<div class="flex items-center justify-center w-10 h-10 rounded-full bg-[#D3F2FC] absolute right-4 top-4">
							<CaregiverSupport class="p-2"/>
						</div>
					</div>
					<div class="flex items-start justify-between rounded-xl gap-4 shadow-md p-5 bg-white border border-gray-200 max-w-sm relative">
						<div class="flex flex-col items-start justify-center gap-3 mr-6">
							<p class="md:text-[2vw] md:text-[#0D2561] text-start">₹5,000 +</p>
							<p class="font-rubik text-gray-600 text-[0.5em] md:text-[0.7em] ml-4 leading-[1.2]">Outreach & Education</p>
						</div>
						<div class="flex items-center justify-center w-10 h-10 rounded-full bg-[#D3F2FC] absolute right-4 top-4">
							<Outreachedu class="p-2"/>
						</div>
					</div>
					<div class="flex items-start justify-between rounded-xl gap-4 shadow-md p-5 bg-white border border-gray-200 max-w-sm relative">
						<div class="flex flex-col items-start justify-center gap-3 mr-6">
							<p class="md:text-[2vw] md:text-[#0D2561] text-start">₹20,000 +</p>
							<p class="font-rubik text-gray-600 text-[0.5em] md:text-[0.7em] ml-4 leading-[1.2]">Holistic Wellness Programs</p>
						</div>
						<div class="flex items-center justify-center w-10 h-10 rounded-full bg-[#D3F2FC] absolute right-4 top-4">
							<Apple class="p-2"/>
						</div>
					</div>
			</div>
		</div>

		<div class="mt-8 md:hidden flex items-center justify-center">
			<Button class="custom-button z-20 px-6 py-2">Donate Now</Button>
		</div>
	</div>
</div>
