<script>
	import Button from './ui/button.svelte';
	import Header from './ui/header.svelte';
	import Wave from '$lib/svg/newletterwave.svelte';
</script>
<div class="newsletter bg-[#D0F0FB] overflow-hidden">

<div class="py-10 sm:py-32 px-2 ">
	<div class="max-w-[45rem] w-full mx-auto px-4 py-8 sm:p-8 rounded-xl sm:rounded-3xl  relative z-10">
		<Header title="Subscribe to our Newsletter" class="text-[0.8em] text-[#0D2561]" />

		<p class="max-w-[25rem] w-full mx-auto leading-[1.2] text-center text-[0.9em] text-[#6F737E]">
			Get the latest updates, inspiring stories, and important news delivered right to your inbox.
		</p>

		<form class="max-w-[30rem] mx-auto mt-12 flex items-stretch gap-2">
			<input
				type="email"
				placeholder="Enter your email address"
				class="px-4 py-2 bg-[white] w-full grow text-manrope rounded-lg placeholder:text-[#9196A3] placeholder:text-manrope"
			/>

			<Button class="text-[0.8em] rounded-[50px] px-4 py-2 min-w-[5rem] text-center ">Submit</Button>

		</form>
	</div>
	
</div>
<div class="wave mt-4">
	<Wave></Wave>
</div>

</div>