<script>
	import Check from '$lib/svg/seek-support/check.svelte';
	import RightArrow from '$lib/svg/seek-support/right-arrow.svelte';
    import { goto } from '$app/navigation';
	import userData from '../../../store/userStore.js'


let data;
  userData.subscribe(value => (data = value));

  const handleSubmit = () => {
    // Log form data on submission
    console.log(data);
    userData.set(data);
	userData.update(current => ({
        ...current,
        ...data 
    }));
	
    goto('/personal-detail2');
  };

	const countryCodes = [
		{ code: '+1', country: 'USA' },
		{ code: '+44', country: 'UK' },
		{ code: '+91', country: 'India' },
		{ code: '+61', country: 'Australia' },
		{ code: '+81', country: 'Japan' }
		// Add more country codes as needed
	];

</script>

<div class="flex justify-center gap-20 mt-16">
	<!-- form -->

	<form class="  p-6 w-1/2 text-[#0D2561] font-semibold" on:submit|preventDefault={handleSubmit}>
		<div class=" mb-12">
			<div class="font-bold text-[#0D2561] text-xl">Personal Details</div>
			<div class="text-[#576171] text-sm">Please fill in your details correctly</div>
		</div>
		<!-- Full Name -->
		<div class="mb-4">
			<label for="fullName" class="block text-gray-700"
				>Full Name <span class="text-red-500">*</span></label
			>
			<input
				type="text"
				id="fullName"
				bind:value={data.fullName}
				class="w-full px-4 py-2 mt-1 border rounded-lg focus:outline-none focus:ring focus:border-blue-500"
				required
			/>
		</div>

		<!-- Gender -->
		<div class="mb-4">
			<label class="block text-gray-700" for="gender"
				>Gender <span class="text-red-500">*</span></label
			>
			<div class="flex items-center mt-1">
				<input
					type="radio"
					id="male"
					name="gender"
					value="male"
					bind:group={data.gender}
					class="mr-2"
					required
				/>
				<label for="male" class="mr-4 text-gray-700">Male</label>
				<!-- Associate with input -->

				<input
					type="radio"
					id="female"
					name="gender"
					value="female"
					bind:group={data.gender}
					class="mr-2"
					required
				/>
				<label for="female" class="mr-4 text-gray-700">Female</label>
				<!-- Associate with input -->

				<input
					type="radio"
					id="other"
					name="gender"
					value="other"
					bind:group={data.gender}
					class="mr-2"
					required
				/>
				<label for="other" class="text-gray-700">Other</label>
				<!-- Associate with input -->
			</div>
		</div>

		<!-- Email -->
		<div class="mb-4">
			<label for="email" class="block text-gray-700"
				>Email <span class="text-red-500">*</span></label
			>
			<input
				type="email"
				id="email"
				bind:value={data.email}
				class="w-full px-4 py-2 mt-1 border rounded-lg focus:outline-none focus:ring focus:border-blue-500"
				required
			/>
		</div>

		<!-- Date of Birth -->
		<div class="mb-4">
			<label for="dob" class="block text-gray-700"
				>Date of Birth <span class="text-red-500">*</span></label
			>
			<input
				type="date"
				id="dob"
				bind:value={data.dob}
				class="w-full px-4 py-2 mt-1 border rounded-lg focus:outline-none focus:ring focus:border-blue-500"
				required
			/>
		</div>

		<!-- Phone Number with Country Code -->
		<div class="mb-4">
			<label for="phoneNumber" class="block text-gray-700"
				>Phone Number <span class="text-red-500">*</span></label
			>
			<div class="flex">
				<select
					id="countryCode"
					bind:value={data.countryCode}
					class="w-20 px-4 py-2 mr-2 border rounded-lg focus:outline-none focus:ring focus:border-blue-500"
					required
				>
					{#each countryCodes as country}
						<option value={country.code}>{country.code} ({country.country})</option>
					{/each}
				</select>
				<input
					type="tel"
					id="phoneNumber"
					bind:value={data.phoneNumber}
					class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring focus:border-blue-500"
					placeholder="Phone Number"
					required
				/>
			</div>
		</div>

		<!-- Address Line 1 -->
		<div class="mb-4">
			<label for="addressLine1" class="block text-gray-700"
				>Address Line 1 <span class="text-red-500">*</span></label
			>
			<input
				type="text"
				id="addressLine1"
				placeholder="Street address"
				bind:value={data.addressLine1}
				class="w-full px-4 py-2 mt-1 border rounded-lg focus:outline-none focus:ring focus:border-blue-500"
				required
			/>
		</div>

		<!-- Address Line 2 (Optional) -->
		<div class="mb-4">
			<label for="addressLine2" class="block text-gray-700">Address Line 2 (Optional) </label>
			<input
				type="text"
				id="addressLine2"
				placeholder="Street address"
				bind:value={data.addressLine2}
				class="w-full px-4 py-2 mt-1 border rounded-lg focus:outline-none focus:ring focus:border-blue-500"
			/>
		</div>

		<!-- Country -->
		<div class="mb-4">
			<label for="country" class="block text-gray-700">Country </label>
			<input
				type="text"
				id="country"
				bind:value={data.country}
				class="w-full px-4 py-2 mt-1 border rounded-lg focus:outline-none focus:ring focus:border-blue-500"
				required
			/>
		</div>

		<div class="flex gap-3 justify-between">
			<!-- City -->
			<div class="mb-4">
				<label for="city" class="block text-gray-700">City </label>
				<input
					type="text"
					id="city"
					placeholder="Enter city/town"
					bind:value={data.city}
					class="w-full px-4 py-2 mt-1 border rounded-lg focus:outline-none focus:ring focus:border-blue-500"
					required
				/>
			</div>

			<!-- Pincode -->
			<div class="mb-4">
				<label for="pincode" class="block text-gray-700">Pincode </label>
				<input
					type="text"
					id="pincode"
					bind:value={data.pincode}
					class="w-full px-4 py-2 mt-1 border rounded-lg focus:outline-none focus:ring focus:border-blue-500"
					required
				/>
			</div>
		</div>

		<div class="mb-4">
			<input type="checkbox" id="agreeToTerms" bind:checked={data.agreeToTerms} class="mr-2" required />
			<label for="agreeToTerms" class="text-gray-700">
				By submitting this form, I agree to Zarurat Care using and verifying my information for
				support. I understand my information will be kept confidential.
			</label>
		</div>

		<!-- Newsletter Subscription Checkbox -->
		<div class="mb-6">
			<input
				type="checkbox"
				id="subscribeToNewsletter"
				bind:checked={data.subscribeToNewsletter}
				class="mr-2"
			/>
			<label for="subscribeToNewsletter" class="text-gray-700">
				Subscribe to Zaruratcare foundation newsletter
			</label>
		</div>

		<!-- Submit Button -->
		<div class="flex ">
			<button type="submit" class="px-6 py-5 bg-[#0155BD] text-white rounded-full flex gap-3 hover:bg-blue-600" >
				Save and Continue
                <span><RightArrow/></span>
			</button>
		</div>
	</form>

	<!-- side info -->
	<div class="w-[22rem] h-fit rounded-xl px-7 border-4 border-[#DBEDFD]">
		<div class="border-b-2 border-[#DBEDFD] p-8">
			<div class="check"><Check /></div>
			<div class="title text-[#26272C] mt-3 mb-4 font-semibold">How is my privacy protected?</div>
			<div class="text-[#576171]">
				Your personal information is securely stored and only accessed by authorized support staff.
			</div>
		</div>
		<div class="border-b-2 border-[#DBEDFD] p-8">
			<div class="check"><Check /></div>
			<div class="title text-[#26272C] mt-3 mb-4 font-semibold">
				When can I expect updates on my support request?
			</div>
			<div class="text-[#576171]">
				Our team reviews requests promptly. You'll be notified within 48 hours about next steps or
				any additional information needed.
			</div>
		</div>
		<div class="  p-8">
			<div class="check"><Check /></div>
			<div class="title text-[#26272C] mt-3 mb-4 font-semibold">
				Can I connect with other cancer fighters or caregivers?
			</div>
			<div class="text-[#576171]">
				Yes, we offer peer support groups and mentorship programs. Indicate your interest in your
				profile, and we'll provide options while respecting everyone's privacy.
			</div>
		</div>
	</div>
</div>
