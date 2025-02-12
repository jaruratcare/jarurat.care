<script>
	import Navbar from '$lib/components/+navbar.svelte';
	let cancers = [
		'Adrenal Cancer',
		'Anal Cancer',
		'Bile Duct Cancer',
		'Bladder Cancer',
		'Bone Cancer',
		'Lungs Cancer',
		'Thyroid Cancer',
		'Mouth Cancer',
		'Liver Cancer',
		'Breast Cancer'
	];

	function toggleDropDown2() {
		document.getElementById('dropdown2')?.classList.toggle('hidden');
	}
</script>

<div class="min-h-screen bg-white">
	<!-- Navbar -->
	<Navbar />

	<!-- Hero Section with Background Image -->
	<div class="container mx-auto flex flex-col items-center text-center mt-20">
		<div
			class="w-[85%] min-h-[400px] bg-cover bg-center bg-no-repeat rounded-lg shadow-lg relative flex flex-col justify-center items-center p-10"
			style="background-image: url('/bg_image.png');"
		>
			<!-- Overlay for better readability -->
			<div class="absolute inset-0 bg-white bg-opacity-5 rounded-lg"></div>

			<div class="relative z-10">
				<!-- Title -->
				<h1 class="text-4xl font-bold text-[#0D2460]">Cancer Research Hub</h1>
				<p class="text-2xl font-bold text-[#1055BD] mt-2">Bringing Cancer Research Within Reach.</p>
				<div class="flex justify-center">
					<div class="search-bar relative w-[45%] ml-[29%] mt-8">
						<!-- Search Bar -->
						<div class="mt-[5%] flex justify-center relative">
							<input
								type="text"
								placeholder="Search Cancer Type"
								class="w-[100%] p-3 rounded-3xl bg-blue-700 text-white text-center placeholder-white border border-gray-300 focus:outline-none mr-[70px]"
								on:click={toggleDropDown2}
							/>
							<div
								class="absolute left-0 top-full w-[80%] bg-white shadow-lg rounded-lg z-50 max-h-60 overflow-y-auto custom-scrollbar-second hidden dropdown ml-4"
								
							>
								{#each cancers as cancer}
									<div id="dropdown2">
										<div class="p-3 hover:bg-gray-100 cursor-pointer border-b">{cancer}</div>
									</div>
								{/each}
							</div>
						</div>

						<svg
							width="10"
							height="9"
							viewBox="0 0 10 9"
							fill="none"
							xmlns="http://www.w3.org/2000/svg"
							class="absolute ml-[430px] top-[48px] size-3"
						>
							<path d="M5 9L0.669872 0H9.33013L5 9Z" fill="white" />
						</svg>

						<svg
							width="19"
							height="18"
							viewBox="0 0 19 18"
							fill="none"
							xmlns="http://www.w3.org/2000/svg"
							class="absolute size-6 ml-[20px] top-0 mt-9"
						>
							<path
								d="M13.9321 11.6083H13.0517L12.7396 11.3234C13.4361 10.5574 13.9451 9.65499 14.2303 8.6809C14.5154 7.70682 14.5696 6.6851 14.389 5.68886C13.8652 2.7555 11.2797 0.413034 8.1593 0.0542773C7.06226 -0.0771269 5.94801 0.0308227 4.90182 0.369866C3.85563 0.708909 2.90521 1.27006 2.12331 2.01038C1.3414 2.75069 0.748731 3.65056 0.390643 4.64111C0.0325542 5.63166 -0.0814594 6.68664 0.0573264 7.72533C0.436236 10.6798 2.91029 13.1278 6.00843 13.6237C7.06063 13.7947 8.13974 13.7434 9.16855 13.4734C10.1974 13.2034 11.1504 12.7215 11.9595 12.0621L12.2604 12.3575V13.1911L16.9968 17.6755C17.4537 18.1082 18.2004 18.1082 18.6573 17.6755C19.1142 17.2429 19.1142 16.536 18.6573 16.1033L13.9321 11.6083ZM7.24546 11.6083C4.4705 11.6083 2.23048 9.48746 2.23048 6.86009C2.23048 4.23273 4.4705 2.11185 7.24546 2.11185C10.0204 2.11185 12.2604 4.23273 12.2604 6.86009C12.2604 9.48746 10.0204 11.6083 7.24546 11.6083Z"
								fill="white"
							/>
						</svg>
					</div>
					<!-- Common Cancer Types -->
					<div class="mt-6 text-left ml-15">
						<h2 class="text-xl font-bold text-gray-800">Common Cancer Types</h2>
						<ul class="mt-2 space-y-2 text-blue-600 font-medium">
							<li><a href="#">Breast Cancer</a></li>
							<li><a href="#">Lung Cancer</a></li>
							<li><a href="#">Skin Cancer</a></li>
							<li><a href="#">Prostate Cancer</a></li>
							<li><a href="#">Pancreatic Cancer</a></li>
						</ul>
					</div>
				</div>

				<!-- Learn More Section -->
				<h2 class="text-2xl font-extrabold text-[#1055BD] flex mt-10">Learn more about Cancer</h2>
				<div class="grid grid-cols-3 gap-4 mt-4">
					<div class="bg-white p-2 rounded-md shadow-md">
						<h3 class="font-bold text-gray-900">What is Cancer?</h3>
						<p class="text-[#37393D] text-sm">
							Cancer is a disease in which abnormal cells grow uncontrollably and invade surrounding
							tissues. It can develop in almost any part of the body and is caused by genetic
							mutations, environmental factors, or lifestyle choices.
						</p>
					</div>
					<div class="bg-white p-2 rounded-md shadow-md">
						<h3 class="font-bold text-gray-900">Genetics and Cancer Risk</h3>
						<p class="text-[#37393D] text-sm">
							Cancer is primarily caused by genetic mutations, which can be inherited (hereditary)
							or acquired (sporadic) over time.While genetic predisposition cannot be changed,
							lifestyle modifications like a healthy diet, regular exercise .....
						</p>
					</div>
					<div class="bg-white p-2 rounded-md shadow-md">
						<h3 class="font-bold text-gray-900">Cancer Staging</h3>
						<p class="text-[#37393D] text-sm">
							Cancer staging describes how much cancer is present and how far it has spread. It
							helps doctors decide treatment plans and predict outcomes.Each cancer type may have
							its own specific staging criteria, and early detection .....
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
