<script>
	import Button from './ui/button.svelte';
	import Header from './ui/header.svelte';
</script>

<div class="py-10 sm:py-32 px-2 bg-[#0D2460] h-[400px]">
	<div class="max-w-[45rem] w-full h-[250px] mx-auto px-4 py-8 sm:p-8 rounded-xl sm:rounded-3xl bg-[#0155BD] relative z-10">
		<h1 class="text-center text-white text-4xl font-bold pb-2">Subscribe to our <span class="text-[#FFBA41]"> Newsletter</span></h1>

		
		<p class=" w-full mx-auto leading-[1.2] text-center text-[0.9em] text-white mt-2">
			Get the latest updates, inspiring stories, and important news delivered right to your inbox.
		</p>

		<form class="max-w-[30rem] mx-auto mt-5 flex items-stretch gap-2">
			<input
				type="email"
				placeholder="Enter your email address"
				class="px-4 py-2 bg-[#EBF0F4] w-full grow text-manrope rounded-lg placeholder:text-[#9196A3] placeholder:text-manrope"
			/>

			<Button class="text-[0.8em] rounded-lg px-4 py-2 min-w-[5rem] text-center">Submit</Button>
		</form>
	</div>
</div>
