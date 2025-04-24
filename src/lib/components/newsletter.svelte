<script>
	import Button from './ui/button.svelte';
	import Header from './ui/header.svelte';
	import { onMount } from 'svelte';

	let email = '';
	let success = false;
	let error = '';
	let timeout;

	const handleSubmit = async (e) => {
		e.preventDefault();
		clearTimeout(timeout); // clear any existing timers
		error = '';
		success = false;

		// Basic validation
		if (!email.includes('@')) {
			error = 'Please enter a valid email.';
			timeout = setTimeout(() => error = '', 3000); // Hide error after 3s
			return;
		}

		// Simulate storing email (replace with real logic)
		try {
			// Simulate success
			success = true;
			email = '';
			timeout = setTimeout(() => success = false, 3000); // Hide success after 3s
		} catch (err) {
			error = 'Something went wrong. Try again later.';
			timeout = setTimeout(() => error = '', 3000); // Hide error after 3s
		}
	};
</script>



<div class="py-10 sm:py-32 px-2 bg-[#535963]">
	<div class="max-w-[45rem] w-full mx-auto px-4 py-8 sm:p-8 rounded-xl sm:rounded-3xl bg-white relative z-10">
		<Header title="Subscribe to our Newsletter" class="text-[0.8em] text-[#0D2561]" />

		<p class="max-w-[25rem] w-full mx-auto leading-[1.2] text-center text-[0.9em] text-[#6F737E]">
			Get the latest updates, inspiring stories, and important news delivered right to your inbox.
		</p>

		<form class="max-w-[30rem] mx-auto mt-12 flex items-stretch gap-2" on:submit|preventDefault={handleSubmit}>
	<input
		type="email"
		bind:value={email}
		placeholder="Enter your email address"
		class="px-4 py-2 bg-[#EBF0F4] w-full grow text-manrope rounded-lg placeholder:text-[#9196A3] placeholder:text-manrope"
	/>

	<Button class="text-[0.8em] rounded-lg px-4 py-2 min-w-[5rem] text-center">Submit</Button>
</form>

{#if success}
	<p class="text-green-600 text-center mt-4">Thank you for subscribing!</p>
{:else if error}
	<p class="text-red-600 text-center mt-4">{error}</p>
{/if}

	</div>
</div>

