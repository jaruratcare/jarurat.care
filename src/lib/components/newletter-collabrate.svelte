<script>
	import NextIcon from '$lib/svg/next-icon.svelte';

	let email = '';
	let message = '';
	let timeout;

	async function handleSubmit() {
		if (!email || !email.includes('@')) {
			message = 'Please enter a valid email.';
			clearTimeout(timeout);
			timeout = setTimeout(() => message = '', 3000);
			return;
		}

		
		//  Temporary feedback logic
		message = 'Thanks for subscribing!';
		email = '';

		clearTimeout(timeout);
		timeout = setTimeout(() => message = '', 3000); // Hide after 3 seconds
	}
</script>


<div class="flex flex-col-reverse lg:flex-row">
	<div
		class="bg-[#D3F2FC] w-[22rem] md:w-full mx-auto rounded-2xl md:rounded-none drop-shadow-xl mb-10 md:mb-0 lg:w-1/2 md:px-24 px-2 py-10"
	>
		<div class=" md:px-6 md:border-l-4 border-[#0155BD]">
			<div class="mb-10">
				<div class="text-[#0D2460] font-bold text-2xl mb-3">Subscribe to our Newsletter</div>
				<div class="text-sm">
					Get the latest updates, inspiring stories, and important news delivered right to your
					inbox.
				</div>
			</div>
			<div class="gap-10 flex flex-col md:flex-row">
				<input
	type="text"
	bind:value={email}
	class="rounded-lg md:w-96 px-3 py-4"
	placeholder="Enter your email address"
/>
<button
	on:click={handleSubmit}
	class="text-[#0155BD] border border-[#0155BD] font-bold px-4 py-2 rounded-3xl"
>
	Submit
</button>

{#if message}
	<p class="text-sm mt-2 text-[#0155BD]">{message}</p>
{/if}


			</div>
		</div>
	</div>
	<div class=" lg:w-1/2 md:px-24 px-3 py-10">
		<div class=" px-6 border-l-4 border-[#FFBA41]">
			<div class="mb-10">
				<div class="text-[#0D2460] font-bold text-2xl mb-3">
					<span class="text-[#FFBA41]">Collaborate </span>
					<span class="text-[#78C520]"> With Us</span>
				</div>
				<div class="text-sm">
					Our mission thrives through the support of compassionate corporates, individuals, and
					organizations. You can be a part of this journey too—join us in making a difference.
				</div>
			</div>

			<div class="grid grid-cols-2 font-semibold">
				<div class="flex gap-5 items-center">
					<div class="w-4 h-4 flex gap-[1px]"><NextIcon /><NextIcon /></div>
					<div class="text-sm">Join jarurat care team</div>
				</div>
				<div class="flex gap-5 items-center">
					<div class="w-4 h-4 flex gap-[1px]"><NextIcon /><NextIcon /></div>
					<div class="text-sm">Become a caregiver</div>
				</div>
				<div class="flex gap-5 items-center">
					<div class="w-4 h-4 flex gap-[1px]"><NextIcon /><NextIcon /></div>
					<div class="text-sm">Become a mentor</div>
				</div>
				<div class="flex gap-5 items-center">
					<div class="w-4 h-4 flex gap-[1px]"><NextIcon /><NextIcon /></div>
					<div class="text-sm">Donate money</div>
				</div>
			</div>
		</div>
	</div>
</div>
