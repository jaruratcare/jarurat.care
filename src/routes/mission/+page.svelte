<script>
	import Nav from '$lib/components/nav.svelte';
	import { goto } from '$app/navigation';

	let isMenuOpen = false;

	const toggleMenu = () => {
		isMenuOpen = !isMenuOpen;
	};

	function goHome() {
		goto('/');
	}
</script>

<Nav {isMenuOpen} on:toggleMenu={toggleMenu} />

<main class="w-full overflow-x-hidden bg-[#F8FCFF] min-h-screen flex items-center justify-center px-4 md:px-8 lg:px-16 py-20">

	<div class="max-w-3xl w-full bg-white rounded-3xl shadow-xl p-8 md:p-12 text-center">

		<h1 class="text-3xl md:text-4xl font-extrabold text-primaryBlue mb-6">
			Our Mission
		</h1>

		<p class="text-[#0D2460] text-sm md:text-base leading-relaxed mb-10">
			Our mission is to support cancer patients and their caregivers by providing
			accessible resources, emotional guidance, and a compassionate community.
			Through collaboration, education, and innovative solutions, we aim to make
			the cancer journey more manageable and less isolating for individuals and
			families across India.
		</p>

		<h2 class="text-2xl md:text-3xl font-extrabold text-primaryBlue mb-4">
			Our Vision
		</h2>

		<p class="text-[#0D2460] text-sm md:text-base leading-relaxed mb-10">
			Our vision is to create an inclusive community of cancer warriors,
			caregivers, and healthcare professionals who support one another,
			share knowledge, and work together to improve the quality of life
			for those affected by cancer.
		</p>

		<button
			on:click={goHome}
			class="bg-primaryBlue text-white px-6 py-3 rounded-full font-semibold hover:opacity-90 transition"
		>
			Return to Homepage
		</button>

	</div>

</main>