<script lang="ts">
	import MapOfIndia from '$lib/svg/map-of-india.svelte';
	import { writable } from 'svelte/store';
	import Header from './ui/header.svelte';
	import Direction from '$lib/svg/direction.svelte';
	import { onMount } from 'svelte';
	import { Locate, Search } from 'lucide-svelte';
	import { t } from "$lib/translations/translations.js";

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
					return hospital.components.state_code === $stateCode;
				})
			);
		});
	});

	stateCode.subscribe((value) => {
		filteredHospitals.set(
			$hospitals.filter((hospital) => {
				return hospital.components.state_code === value;
			})
		);
	});
</script>

<div class="py-16 sm:py-32 px-4 flex flex-col">
	<Header
		class="p-4"
		title={$t("home.hospital-locator.heading")}
		subtitle={$t("home.hospital-locator.subheading")}
	/>

	<form
		class="border rounded-full max-w-[28rem] w-full mx-auto bg-[#DBE1E6] overflow-hidden flex items-center mt-8"
	>
		<div class="py-2 px-3 text-[#576171]">
			<Search />
		</div>

		<input
			class="py-2 border-0 bg-transparent placeholder:text-[#576171] grow outline-none"
			placeholder={$t("home.hospital-locator.placeholder")}
		/>

		<div class="py-2 px-3 text-[#576171]">
			<Locate />
		</div>
	</form>

	<div class="flex flex-col md:flex-row justify-center gap-8 max-w-[60rem] mx-auto py-4 sm:my-20">
		<div class="md:max-w-[40%] grow flex flex-col gap-6">
			<p
				class="px-4 py-2 text-[#0155BD] bg-[#85B6FF]/[0.15] border-l-2 border-[#0155BD] leading-[1.3] text-sm"
			>
				{$t("home.hospital-locator.note")}
			</p>

			<div class="flex flex-col h-full">
				<h2 class="bg-[#0D2561] text-white px-4 py-2 font-bold text-[1.2em] rounded-md">
					{states[$stateCode]}
				</h2>

				<div class="gap-6 flex flex-col py-4 h-full max-h-[30rem] overflow-auto">
					{#each $filteredHospitals as hospital}
						<div class="flex items-center justify-between gap-4 w-full mx-auto">
							<article class="max-w-[80%] w-full">
								<h4 class="leading-[1] font-semibold truncate">
									{hospital.components.hospital ||
										hospital.components.neighbourhood ||
										hospital.components.city}
								</h4>
								<small class="text-[#868B93] truncate block">
									{hospital.components.city}, {hospital.components.postcode}
								</small>
							</article>

							<div class="w-[10%] text-right">
								<Direction />
							</div>
						</div>
					{/each}
				</div>
			</div>
		</div>

		<div
			class="md:max-w-[60%] grow flex items-start justify-end p-4 md:p-0 bg-[#FAF5F3] md:bg-transparent rounded-lg"
		>
			<MapOfIndia class="w-full" onStateChange={stateCode.set} />
		</div>
	</div>
</div>
