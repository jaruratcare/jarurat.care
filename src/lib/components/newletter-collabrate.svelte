<script>
	import NextIcon from '$lib/svg/next-icon.svelte';
</script>

<div class="flex flex-col lg:flex-row mt-16 md:mt-24">

	<!-- Newsletter -->
	<div class="bg-[#D3F2FC] w-full max-w-xl mx-auto lg:mx-0 lg:w-1/2 rounded-2xl lg:rounded-none shadow-xl px-6 md:px-16 py-10">

		<div class="md:border-l-4 border-[#0155BD] md:pl-6">

			<div class="mb-8">
				<h2 class="text-[#0D2460] font-bold text-xl md:text-2xl mb-3">
					Subscribe to our Nesletter
				</h2>

				<p class="text-sm text-[#0D2460]">
					Get the latest updates, inspiring stories, and important news delivered right to your inbox.
				</p>
			</div>

			<div class="flex flex-col sm:flex-row gap-4">

				<input
					type="email"
					class="rounded-lg w-full sm:flex-1 px-4 py-3 border border-gray-200 focus:outline-none"
					placeholder="Enter your email address"
				/>

				<button
					class="text-[#0155BD] border border-[#0155BD] font-bold px-6 py-3 rounded-3xl whitespace-nowrap"
				>
					Submit
				</button>

			</div>

		</div>

	</div>


	<!-- Collaborate -->
	<div class="lg:w-1/2 px-6 md:px-16 py-10">

		<div class="border-l-4 border-[#FFBA41] pl-6">

			<div class="mb-8">

				<h2 class="font-bold text-xl md:text-2xl mb-3">
					<span class="text-[#FFBA41]">Collaborate</span>
					<span class="text-[#78C520]"> With Us</span>
				</h2>

				<p class="text-sm text-[#0D2460]">
					Our mission thrives through the support of compassionate corporates, individuals, and organizations.
					You can be a part of this journey too—join us in making a difference.
				</p>

			</div>


			<div class="grid grid-cols-1 sm:grid-cols-2 gap-4 font-semibold">

				<div class="flex gap-3 items-center">
					<div class="w-4 h-4 flex gap-[1px]"><NextIcon /><NextIcon /></div>
					<div class="text-sm">
						<a href="/join-us">Join Jarurat Care Team</a>
					</div>
				</div>

				<div class="flex gap-3 items-center">
					<div class="w-4 h-4 flex gap-[1px]"><NextIcon /><NextIcon /></div>
					<div class="text-sm">
						<a href="/contact-us">Become a Caregiver</a>
					</div>
				</div>

				<div class="flex gap-3 items-center">
					<div class="w-4 h-4 flex gap-[1px]"><NextIcon /><NextIcon /></div>
					<div class="text-sm">
						<a href="/contact-us">Become a Mentor</a>
					</div>
				</div>

				<div class="flex gap-3 items-center">
					<div class="w-4 h-4 flex gap-[1px]"><NextIcon /><NextIcon /></div>
					<div class="text-sm">
						<a href="/donate">Donate Money
					</div>
				</div>

			</div>

		</div>

	</div>

</div>	