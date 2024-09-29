<script>
	import RightArrow from '$lib/svg/seek-support/right-arrow.svelte';
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';

	let selectedSupport = '';
	let description = '';

	onMount(() => {
		// Initialize any necessary state or logic here
	});

	const handleSubmit = () => {
		// e.preventDefault(); // Prevent the default form submission behavior
		console.log('Selected support:', selectedSupport);
		console.log('Description:', description);
	};
</script>

<div class="container flex justify-center mx-auto p-4">
	<!-- <h1 class="text-3xl font-bold text-center">Support Form</h1> -->

	<!-- Form with on:submit event listener -->
	<form on:submit={handleSubmit} class="mx-auto w-[58rem] gap-4 mt-4">
		<!-- Type of Support Needed Section -->
		<div>
			<div class="mb-12">
				<label class="block text-[#0D2561] font-extrabold text-xl">Type of Support Needed</label>
				<div class="text-xs font-[#576171]">Select the type of support you need.</div>
			</div>

			<div class="space-y-2 gap-y-6 grid grid-cols-2">
				<!-- Patient Advocacy -->
				<label
					class={`flex justify-between w-2/3 py-4 rounded-xl px-5 border cursor-pointer
                    ${selectedSupport === 'Patient Advocacy' ? 'bg-blue-100 border-blue-200' : 'hover:bg-[#DBEDFD] hover:border-[#DBEDFD] focus:bg-[#DBEDFD]'}`}
					for="patient-advocacy"
				>
					<span class="text-gray-700">Patient Advocacy</span>
					<input
						type="radio"
						id="patient-advocacy"
						name="support"
						value="Patient Advocacy"
						bind:group={selectedSupport}
						class="mr-2"
						required
					/>
				</label>

				<!-- Emotional Support -->
				<label
					class={`flex justify-between w-2/3 py-4 rounded-xl px-5 border cursor-pointer
                    ${selectedSupport === 'Emotional Support' ? 'bg-blue-100 border-blue-200' : 'hover:bg-[#DBEDFD] hover:border-[#DBEDFD] focus:bg-[#DBEDFD]'}`}
					for="emotional-support"
				>
					<span class="text-gray-700">Emotional Support</span>
					<input
						type="radio"
						id="emotional-support"
						name="support"
						value="Emotional Support"
						bind:group={selectedSupport}
						class="mr-2"
						required
					/>
				</label>

				<!-- Education Resources -->
				<label
					class={`flex justify-between w-2/3 py-4 rounded-xl px-5 border cursor-pointer
                    ${selectedSupport === 'Education Resources' ? 'bg-blue-100 border-blue-200' : 'hover:bg-[#DBEDFD] hover:border-[#DBEDFD] focus:bg-[#DBEDFD]'}`}
					for="education-resources"
				>
					<span class="text-gray-700">Education Resources</span>
					<input
						type="radio"
						id="education-resources"
						name="support"
						value="Education Resources"
						bind:group={selectedSupport}
						class="mr-2"
						required
					/>
				</label>

				<!-- Cancer Connect -->
				<label
					class={`flex justify-between w-2/3 py-4 rounded-xl px-5 border cursor-pointer
                    ${selectedSupport === 'Cancer Connect' ? 'bg-blue-100 border-blue-200' : 'hover:bg-[#DBEDFD] hover:border-[#DBEDFD] focus:bg-[#DBEDFD]'}`}
					for="cancer-connect"
				>
					<span class="text-gray-700">Cancer Connect</span>
					<input
						type="radio"
						id="cancer-connect"
						name="support"
						value="Cancer Connect"
						bind:group={selectedSupport}
						class="mr-2"
						required
					/>
				</label>

				<!-- Other -->
				<label
					class={`flex justify-between w-2/3 py-4 rounded-xl px-5 border cursor-pointer
                    ${selectedSupport === 'Other' ? 'bg-blue-100 border-blue-200' : 'hover:bg-[#DBEDFD] hover:border-[#DBEDFD] focus:bg-[#DBEDFD]'}`}
					for="other"
				>
					<span class="text-gray-700">Other</span>
					<input
						type="radio"
						id="other"
						name="support"
						value="Other"
						bind:group={selectedSupport}
						class="mr-2"
						required
					/>
				</label>
			</div>
		</div>

		<!-- Brief Description Section -->
		<div class="mt-16">
			<label for="description" class="block text-[#0D2561] font-semibold mb-2"
				>Brief Description <span class="text-[#868B93] text-sm"> (Optional) </span>
			</label>
			<textarea
				id="description"
				class="border w-full max-h-72 rounded-md p-2"
				placeholder="Describe your situation"
				rows="5"
				bind:value={description}
			></textarea>
		</div>

		<!-- Submit Button -->
		<div class="flex justify-center mt-9">
			<button
				type="button"
				class="px-6 py-5 bg-[#0155BD] text-white rounded-full flex gap-3 hover:bg-blue-600"
				on:click={() => goto('/verify')}
			>
				Save and Continue
				<span><RightArrow /></span>
			</button>
		</div>
	</form>
</div>
