<script>
	import Button from '../ui/button.svelte';
	import SingleWavedown from '$lib/svg/single-wavelightblue.svelte';
	import BecomeMentor from '$lib/svg/donate/become-mentor.svelte';
	import PartTeam from '$lib/svg/donate/part-team.svelte';
	import BecomeCaregiver from '$lib/svg/donate/become-caregiver.svelte';
</script>

<SingleWavedown fill="#D3F2FC" class="w-full sm:-mb-8 -mb-14" />
<div class="relative bg-[#D3F2FC] grid sm:grid-cols-3 grid-cols-1 sm:px-44 px-12 sm:py-24 py-2 font-rubik">
	<PartTeam class="absolute sm:flex hidden inset-0 h-dvh w-full object-cover -left-[35%] -top-20 z-10" />
	<div>
		<h1 class="text-3xl sm:text-3xl text-center sm:max-w-[60%] sm:text-start text-[#0D2561]">
			Become a part of our <br /><span class="text-[#0155BD]">TEAM TODAY</span>
		</h1>
	</div>

	<div class="sm:flex sm:flex-row flex-col sm:col-span-2 col-span-1 gap-16 z-20 mt-20 sm:mt-0 pb-4">
		<div
			class="relative aspect-square bg-[#2CBFE2] rounded-lg shadow-lg transform transition-transform group"
		>
			<div
				class="absolute aspect-square h-full w-full -z-30 bg-white rounded-lg transition-transform hover:shadow-lg overflow-hidden group-hover:rotate-0 -rotate-1"
			></div>
			<div
				class="absolute flex items-center justify-center h-32 w-32 bg-[#D3F2FC] rounded-full mx-auto -top-16 transition-transform shadow-lg group-hover:shadow-none"
			>
				<BecomeCaregiver class="h-24 w-24 p-4 bg-white rounded-full" />
			</div>
			<div class="pt-20 px-10 text-start">
				<h3 class="text-[#0D2561] text-2xl">Become a Caregiver</h3>
				<p class="mt-2 text-gray-600 text-sm font-sans font-normal">
					Build your skills and provide your assistance to help cancer patients. Join the mission
					today.
				</p>
				<Button class="my-5 z-20 font-sans">Apply Now</Button>
			</div>
		</div>

		<div
			class="relative aspect-square bg-[#2CBFE2] rounded-lg shadow-lg transform transition-transform group mt-20 sm:mt-0"
		>
			<div
				class="absolute aspect-square h-full w-full -z-30 bg-white rounded-lg transition-transform hover:shadow-lg overflow-hidden group-hover:rotate-0 -rotate-1"
			></div>
			<div
				class="absolute flex items-center justify-center h-32 w-32 bg-[#D3F2FC] rounded-full mx-auto -top-16 transition-transform shadow-lg group-hover:shadow-none"
			>
				<BecomeMentor class="h-24 w-24 p-4 bg-white rounded-full" />
			</div>
			<div class="pt-20 px-10 text-start">
				<h3 class="text-[#0D2561] text-2xl">Become a Mentor</h3>
				<p class="mt-2 text-gray-600 text-sm font-sans font-normal">
					Build your skills and provide your assistance to help cancer patients. Join the mission
					today.
				</p>
				<Button class="my-5 z-20 font-sans">Apply Now</Button>
			</div>
		</div>
	</div>
</div>
<SingleWavedown fill="#D3F2FC" class="w-full rotate-180 -mt-14" />
