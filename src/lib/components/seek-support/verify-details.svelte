<script>
	import EditPencilIcon from '$lib/svg/seek-support/edit-pencil-icon.svelte';
	import { onMount } from 'svelte';

	// Mock user details - Replace this with actual Redux or store data as needed
	let userDetails = {
		fullName: 'John Doe',
		briefDescription: 'Looking for patient advocacy support.'
	};

	let isEditing = false;

	// Function to toggle editing mode
	const handleEditClick = () => {
		isEditing = !isEditing;
	};

	// Function to handle form submission
	const handleSubmit = () => {
		// This is where you'd dispatch the action to update the Redux store or Svelte store
		console.log('Updated Details:', userDetails);

		// Redirect or perform other actions on submit (if needed)
		window.location.href = '/edit-profile';
	};
</script>

<!-- UI Structure -->
<div class="container mx-auto p-4">
	<div class="mt-10 mb-20">
		<h1 class="text-3xl font-bold text-[#0D2561] text-center">Verify Details</h1>
		<div class="text-xs text-[#576171] text-center">
			Please review all the information below carefully to ensure it is accurate and complete.
			Correct any errors and make sure all fields are filled in properly.
		</div>
	</div>

	<div class="flex justify-center mx-auto gap-10 ">
		<!-- Personal Details Section -->
		<div class="w-[40rem] ">
			<div class="flex justify-between items-center bg-[#DBEDFD] rounded-lg py-2 mb-4">
				<h2 class="text-xl text-[#0D2561] font-semibold pl-8">Personal Details</h2>
				<div class="flex gap-3 justify-center items-center">
					<span class="text-[#0155BD]">Edit</span> <span> <EditPencilIcon /></span>
				</div>
			</div>
			<div class="grid grid-cols-2 gap-3 items-center justify-between mb-4">
                
                    <!-- name -->
                    <div class="flex gap-1 flex-col">
                        <span class=" text-[#576171] text-sm">Full Name</span>
                        <span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.fullName}</span>
                    </div>

                    <!-- gender &dob -->
                    <div class="flex gap-4">
                        <div class="flex gap-1 flex-col">
                            <span class=" text-[#576171] text-sm">Gender</span>
                            <span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.fullName}</span>
                        </div>
                        <div class="flex gap-1 flex-col">
                            <span class=" text-[#576171] text-sm">Date of Birth</span>
                            <span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.fullName}</span>
                        </div>
                    </div>

                    <!-- Email -->
                    <div class="flex gap-1 flex-col">
                        <span class=" text-[#576171] text-sm">Email</span>
                        <span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.fullName}</span>
                    </div>
                    <!-- phoneNumber -->
                    <div class="flex gap-1 flex-col">
                        <span class=" text-[#576171] text-sm">Phone no.</span>
                        <span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.fullName}</span>
                    </div>
                    <!-- Address line 1 -->
                    <div class="flex gap-1 flex-col">
                        <span class=" text-[#576171] text-sm">Address line 1</span>
                        <span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.fullName}</span>
                    </div>
                    <!-- Address line 2 -->
                    <div class="flex gap-1 flex-col">
                        <span class=" text-[#576171] text-sm">Address line 1</span>
                        <span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.fullName}</span>
                    </div>

                    <!-- Country -->
                    <div class="flex gap-1 flex-col">
                        <span class=" text-[#576171] text-sm">Country</span>
                        <span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.fullName}</span>
                    </div>

                    <!-- city/pincode -->
                    <div class="flex gap-4">
                        <div class="flex gap-1 flex-col">
                            <span class=" text-[#576171] text-sm">City/ Town</span>
                            <span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.fullName}</span>
                        </div>
                        <div class="flex gap-1 flex-col">
                            <span class=" text-[#576171] text-sm">Pincode</span>
                            <span class="text-[#0D2561] bg-[#EBF0F4] py-2 px-4 rounded-lg">{userDetails.fullName}</span>
                        </div>
                    </div>
                
                
				<!-- <button class="text-blue-500 hover:underline ml-4" on:click={handleEditClick}>
					{isEditing ? 'Save' : 'Edit'}
				</button> -->
			</div>
		</div>

		<!-- Support Needed Section -->
		<div>
			<div class="flex justify-between items-center bg-[#DBEDFD] rounded-lg py-2">
				<h2 class="text-xl font-semibold text-[#0D2561] pl-8">Support Needed</h2>
				<div class="flex gap-3 justify-center items-center">
					<span class="text-[#0155BD]">Edit</span> <span> <EditPencilIcon /></span>
				</div>
			</div>
			<div class="flex flex-col items-center justify-between mb-4">
				<div class="my-10 w-full">
					<div
						class="bg-[#EBF0F4] text-[#0D2561] py-2 px-7 border font-bold border-[#0D2561] rounded-lg w-full"
					>
						priyan
					</div>
				</div>

				<div class="">
					<div class="flex gap-1 flex-col">
						<span class="block text-[#576171] text-sm">Brief Description</span>
						<span class="text-[#0D2561] p-4 rounded-lg bg-[#EBF0F4]"
							>{userDetails.briefDescription}</span
						>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Submit Button -->
     <div class="flex justify-center">

         <button
         type="submit"
         class="px-6 py-3 bg-[#0155BD] text-white rounded-full hover:bg-blue-600"
         on:click={handleSubmit}
         >
         Submit
        </button>
    </div>
</div>
