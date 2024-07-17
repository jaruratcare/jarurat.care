<script>
	import Donate from '../../assets/svg/donate.svelte';
	import Member from '../../assets/svg/member.svelte';
	import SeekSupport from '../../assets/svg/seekSupport.svelte';
	import bgImage from '../../assets/images/Mask group.png';
</script>

<div
	class="w-full flex justify-between border border-blue-700 bg-no-repeat bg-[length:100%_100%]"
	style="background-image: url('{bgImage}'); "
>
	<div class="w-1/3 py-11 px-32 flex flex-col justify-center items-center border border-[#0072C4]">
		<span class="bg-white p-3 rounded-full"><SeekSupport /></span>
		<h1
			class="mt-6 mb-12 text-4xl font-bold font-serif text-center"
			style="font-family: 'Playfair Display', sans-serif;"
		>
			Seek Support
		</h1>
		<p class="mt-2 text-center">
			Our NGO is here to help you through your cancer journey. <span class="font-bold"
				>Reach out to us today.</span
			>
		</p>
		<button
			class="relative z-1 mt-8 px-9 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease"
			style="font-family: 'Roboto', sans-serif;"
		>
			<span
				class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
				style="transform: translateX(-50%), translateX(50%);"
			></span>
			<span class="relative">Seek Support</span>
		</button>
	</div>

	<div class="w-1/3 py-11 px-32 flex flex-col justify-center items-center border border-[#0072C4]">
		<span class="bg-white p-3 rounded-full"><Donate /></span>
		<h1
			class="mt-6 mb-12 text-4xl font-bold font-serif text-center"
			style="font-family: 'Playfair Display', sans-serif;"
		>
			Donate
		</h1>
		<p class="mt-2 text-center">
			One Life at a Time "<span class="font-bold">Donate</span> today and be the light that brings hope
			and healing to cancer patients."
		</p>
		<button
			class="relative z-1 mt-8 px-9 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease"
			style="font-family: 'Roboto', sans-serif;"
		>
			<span
				class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
				style="transform: translateX(-50%), translateX(50%);"
			></span>
			<span class="relative">Donate Now</span>
		</button>
	</div>

	<div class="w-1/3 py-11 px-32 flex flex-col justify-center items-center border border-[#0072C4]">
		<span class="bg-white p-3 rounded-full"><Member /></span>
		<h1
			class="mt-6 mb-12 text-4xl font-bold font-serif text-center"
			style="font-family: 'Playfair Display', sans-serif;"
		>
			Become a Member
		</h1>
		<p class="mt-2 text-center">
			Be a hero. <span class="font-bold">Volunteer</span> with us and provide support and hope to those
			affected by cancer.
		</p>
		<button
			class="relative z-1 mt-8 px-9 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease"
			style="font-family: 'Roboto', sans-serif;"
		>
			<span
				class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
				style="transform: translateX(-50%), translateX(50%);"
			></span>
			<span class="relative">Get Started</span>
		</button>
	</div>
</div>
