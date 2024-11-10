<script>
	import EditPencilIcon from '$lib/svg/seek-support/edit-pencil-icon.svelte';
	import { onMount, onDestroy } from 'svelte';
	import userData from '../../../store/userStore.js';
	import { goto } from '$app/navigation';

	let userDetails = {};

	const unsubscribe = userData.subscribe((value) => {
		userDetails = { ...value };
		console.log(userDetails);
	});

	// Function to handle form submission
	const handleSubmit = () => {
		console.log('Updated Details:', $userData, userDetails);

		// Redirect or perform other actions on submit (if needed)
		// window.location.href = '/edit-profile';
	};

	onDestroy(() => {
		unsubscribe();
	});
</script>

<!-- UI Structure -->
<div class="container mx-auto p-4">
	<div class="mt-10 mb-20">
		<h1 class="text-3xl mb-5 font-bold text-[#0D2561] text-center">Verify Details</h1>
		<div class="text-xs text-[#576171] text-center">
			<p>Please review all the information below carefully to ensure it is accurate and complete.</p>
			<p>Correct any errors and make sure all fields are filled in properly.</p>
		</div>
	</div>

	<div class="flex justify-center mx-auto gap-10">
		<!-- Personal Details Section -->
		<div class="w-[40rem]">
			<div class="flex justify-between items-center bg-[#DBEDFD] rounded-lg py-2 mb-4">
				<h2 class="text-xl text-[#0D2561] font-semibold pl-8">Personal Details</h2>
				<button
					class="flex gap-2 justify-center items-center"
					on:click={() => goto('/personal-details')}
				>
					<span class="text-[#0155BD]">Edit</span> <span> <EditPencilIcon /></span>
				</button>
			</div>
			<div class="grid grid-cols-2 gap-3 items-center justify-between mb-4">
				<!-- name -->
				<div class="flex gap-1 flex-col">
					<span class=" text-[#576171] text-sm">Full Name</span>
					<span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg"
						>{userDetails.fullName}</span
					>
				</div>

				<!-- gender &dob -->
				<div class="flex gap-4">
					<div class="flex gap-1 flex-col">
						<span class=" text-[#576171] text-sm">Gender</span>
						<span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg"
							>{userDetails.gender}</span
						>
					</div>
					<div class="flex gap-1 flex-col">
						<span class=" text-[#576171] text-sm">Date of Birth</span>
						<span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.dob}</span>
					</div>
				</div>

				<!-- Email -->
				<div class="flex gap-1 flex-col">
					<span class=" text-[#576171] text-sm">Email</span>
					<span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.email}</span>
				</div>
				<!-- phoneNumber -->
				<div class="flex gap-1 flex-col">
					<span class=" text-[#576171] text-sm">Phone no.</span>
					<span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg"
						>{userDetails.countryCode}{userDetails.phoneNumber}</span
					>
				</div>
				<!-- Address line 1 -->
				<div class="flex gap-1 flex-col">
					<span class=" text-[#576171] text-sm">Address line 1</span>
					<span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg"
						>{userDetails.addressLine1}</span
					>
				</div>
				<!-- Address line 2 -->
				<div class="flex gap-1 flex-col">
					<span class=" text-[#576171] text-sm">Address line 1</span>
					<span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg"
						>{userDetails.addressLine2}</span
					>
				</div>

				<!-- Country -->
				<div class="flex gap-1 flex-col">
					<span class=" text-[#576171] text-sm">Country</span>
					<span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.country}</span
					>
				</div>

				<!-- city/pincode -->
				<div class="flex gap-4">
					<div class="flex gap-1 flex-col">
						<span class=" text-[#576171] text-sm">City/ Town</span>
						<span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.city}</span>
					</div>
					<div class="flex gap-1 flex-col">
						<span class=" text-[#576171] text-sm">Pincode</span>
						<span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg"
							>{userDetails.pincode}</span
						>
					</div>
				</div>
			</div>
		</div>

		<!-- Support Needed Section -->
		<div>
			<div class="flex justify-between items-center bg-[#DBEDFD] rounded-lg py-2">
				<h2 class="text-xl font-semibold text-[#0D2561] pl-8">Support Needed</h2>
				<button
					class="flex gap-2 justify-center items-center"
					on:click={() => goto('/personal-detail2')}
				>
					<span class="text-[#0155BD]">Edit</span> <span> <EditPencilIcon /></span>
				</button>
			</div>
			<div class="flex flex-col items-center justify-between mb-4 w-[20rem]">
				<div class="my-10 w-full">
					<div
						class="bg-[#EBF0F4] text-[#0D2561] py-2 px-7 border font-bold border-[#0D2561] rounded-lg w-full"
					>
						{userDetails.selectedSupport}
					</div>
				</div>

				<div class="">
					<div class="flex gap-1 flex-col">
						<span class="block text-[#576171] text-sm">Brief Description</span>
						<span class="text-[#0D2561] p-4 rounded-lg min-h-[10rem] w-[15rem] bg-[#EBF0F4]"
							>{userDetails.description}</span
						>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Submit Button -->
	<div class="flex justify-center pt-8">
		<button
			type="submit"
			class="px-8 py-3 bg-[#0155BD] text-white rounded-full hover:bg-blue-600"
			on:click={handleSubmit}
		>
			Submit
		</button>
	</div>
</div>
