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
			class="relative z-1 mt-8 px-9 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full before:rounded-full before:text-white before:colors before:absolute before:h-full before:w-full before:origin-bottom before:scale-y-0 before:left-0 before:bottom-0 before:bg-[#DBEAFE] before:transition-transform before:duration-800 before:content-[''] before:hover:scale-y-100 before:hover:text-white"
			style="font-family: 'Roboto', sans-serif;">Seek Support</button
		>
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
			class="relative z-10 mt-8 px-9 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full before:rounded-full before:text-white before:colors before:absolute before:h-full before:w-full before:origin-bottom before:scale-y-0 before:z-9 before:left-0 before:bottom-0 before:bg-[#DBEAFE] before:transition-transform before:duration-800 before:content-[''] before:hover:scale-y-100 before:hover:text-white"
			style="font-family: 'Roboto', sans-serif;">Donate Now</button
		>
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
			class="relative z-10 mt-8 px-9 py-3 bg-[#0155BD] text-xl text-white v rounded-full before:rounded-full before:text-white before:colors before:absolute before:h-full before:w-full before:origin-bottom before:scale-y-0 before:z-9 before:left-0 before:bottom-0 before:bg-[#DBEAFE] before:transition-transform before:duration-800 before:content-[''] before:hover:scale-y-100 before:hover:text-white"
			style="font-family: 'Roboto', sans-serif;">Get Started</button
		>
	</div>
</div>
