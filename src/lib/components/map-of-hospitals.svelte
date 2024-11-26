<script lang="ts">
	import MapOfIndia from '$lib/svg/map-of-india.svelte';
	import { writable } from 'svelte/store';
	import Header from './ui/header.svelte';
	import Direction from '$lib/svg/direction.svelte';
	import { onMount } from 'svelte';
	import { Locate, Search } from 'lucide-svelte';
	import Button from './ui/tbutton.svelte';

	const hospitals = writable<any[]>([]);
	const filteredHospitals = writable<any[]>([]);
	const stateCode = writable('DL');
	const states: Record<string, string> = {
		AN: 'Andaman and Nicobar Islands',
		AP: 'Andhra Pradesh',
		AR: 'Arunachal Pradesh',
		AS: 'Assam',
		BR: 'Bihar',
		CH: 'Chandigarh',
		CG: 'Chhattisgarh',
		DD: 'Daman and Diu',
		DL: 'Delhi',
		DN: 'Dadra and Nagar Haveli',
		GA: 'Goa',
		GJ: 'Gujarat',
		HP: 'Himachal Pradesh',
		HR: 'Haryana',
		JH: 'Jharkhand',
		JK: 'Jammu and Kashmir',
		KA: 'Karnataka',
		KL: 'Kerala',
		LA: 'Ladakh',
		LD: 'Lakshadweep',
		MH: 'Maharashtra',
		ML: 'Meghalaya',
		MN: 'Manipur',
		MP: 'Madhya Pradesh',
		MZ: 'Mizoram',
		NL: 'Nagaland',
		OD: 'Odisha',
		PB: 'Punjab',
		PY: 'Puducherry',
		RJ: 'Rajasthan',
		SK: 'Sikkim',
		TN: 'Tamil Nadu',
		TS: 'Telangana',
		TR: 'Tripura',
		UP: 'Uttar Pradesh',
		UK: 'Uttarakhand',
		WB: 'West Bengal'
	};

	onMount(() => {
		const hospitalsJSON = import('$lib/data/hospitals.json');
		hospitalsJSON.then((data) => {
			hospitals.set(data.default);

			filteredHospitals.set(
				data.default.filter((hospital) => {
					return hospital.address.state_code === $stateCode;
				})
			);
		});
	});

	stateCode.subscribe((value) => {
		filteredHospitals.set(
			$hospitals.filter((hospital) => {
				console.log(value);
				return hospital.address.state_code === value;
			})
		);
	});
</script>

<div class="py-16 sm:py-32 px-4 flex flex-col bg-[#D3F2FC]">
	<Header
		class="p-4"
		title="Locate Cancer Hospitals Near You"
		subtitle="Finding Hope, One Location at a Time"
	/>
	<div class="search flex flex-col sm:flex-row justify-center items-center gap-2">
		<form class="border rounded-full max-w-[28rem] w-full bg-[white] overflow-hidden flex items-center mt-8">
			<div class="py-2 px-3 text-[#576171]">
				<Search />
			</div>

			<input
				class="py-2 border-0 bg-transparent placeholder:text-[#576171] grow outline-none"
				placeholder="Enter your location"
			/>

			<div class="py-2 px-3 text-[#576171]">
				<Locate />
			</div>
		</form>

		<!-- Adjust button layout -->
		<Button color="#0054bd" class="mt-7 max-w-[22rem] w-full text-left py-2 px-3">Select your state</Button> 
	</div>

	<div class="flex flex-col md:flex-row justify-center gap-10 sm:gap-28 max-w-[60rem] mx-auto py-4 sm:my-20">
		<div class="md:max-w-[40%] grow flex flex-col gap-6 bg-[white]">
			<p class="px-4 py-2 text-[#0155BD] bg-[#85B6FF]/[0.15] border-l-2 border-[#0155BD] leading-[1.3] text-sm">
				Note: we might be missing some hospitals or maybe they are unregistered
			</p>

			<div class="flex flex-col h-full">
				<h2 class="bg-[#0D2561] text-white px-4 py-2 font-bold text-[1.2em] rounded-md">
					{states[$stateCode]}
				</h2>

				<div class="p-4 gap-6 flex flex-col py-4 h-full max-h-[30rem] overflow-auto max-w-[90vw]">
					{#each $filteredHospitals as hospital}
						<div class="flex items-center justify-between gap-4 w-full mx-auto">
							<article class="max-w-[80%] w-full">
								<h4 class="leading-[1] font-semibold truncate">{hospital.name}</h4>
								<small class="text-[#868B93] truncate block">
									{hospital.address.state_district || hospital.city}, {hospital.address.state} - {hospital
										.address.postcode}
								</small>
							</article>

							<a
								class="block w-[10%] text-right"
								target="_blank"
								href={`https://www.google.com/maps/search/${hospital.name?.replaceAll(' ', '+')}/@${hospital.location.coordinates[1]},${hospital.location.coordinates[0]},25z`}
							>
								<Direction />
							</a>
						</div>
					{/each}
				</div>
			</div>
		</div>

		<div class="relative flex items-center justify-center w-full h-full">
			<!-- Ellipse -->
			<div class="absolute w-[350px] sm:w-[525px] h-[350px] sm:h-[525px] opacity-20 bg-[#0071c3] rounded-full border border-[#85daff] blur-[200px]"></div>

			<!-- Map of India -->
			<div class="relative w-[350px] sm:w-[600px] h-[350px] sm:h-[600px] flex-shrink-0 flex items-start justify-end p-4 md:p-0 md:bg-transparent rounded-lg">
				<MapOfIndia class="w-full h-full" onStateChange={stateCode.set} />
			</div>
		</div>
	</div>
</div>
