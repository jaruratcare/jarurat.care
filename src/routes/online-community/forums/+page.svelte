<script>
	import Navbar from '$lib/components/online-community/navbar.svelte';
	import { goto } from "$app/navigation";
	function navigateTo(page) {
		if (page) goto(page);
	}
</script>

<main class="w-full min-h-screen">
	<Navbar forumsPage={true} />
	<div class="absolute z-0 top-0">
		<svg
			width="266"
			height="183"
			viewBox="0 0 266 183"
			fill="none"
			xmlns="http://www.w3.org/2000/svg"
		>
			<ellipse cx="80" cy="1.5" rx="186" ry="181.5" fill="#A4D9FB" fill-opacity="0.52" />
		</svg>
	</div>

	<div class="grid grid-cols-1 md:grid-cols-2 ml-5 md:ml-auto md:mr-10 md:gap-x-20  md:mt-10 lg:mt-auto lg:ml-40 xl:ml-64 gap-y-5 md:p-10">
		{#each [{ title: 'Support and discussion',link:'/online-community/forums/support-and-discussion/'}, { title: 'Announcement',link: null}, { title: 'q and a',link:null }, { title: 'inspiration corner',link:null}] as forum}
			<div class="w-[350px] h-[350px] bg-blue-100 flex flex-col justify-center items-center rounded-[3.5rem] text-center shadow-md p-10">
				<p class="text-lg font-semibold italic flex-grow flex items-center mb-4">{forum.title}</p>
				<button class=" bg-[#065AC0] text-white px-6 py-2 rounded-md" on:click={()=>navigateTo(forum.link)}
				disabled={!forum.link}> Click here </button>
			</div>
		{/each}
	</div>
</main>
