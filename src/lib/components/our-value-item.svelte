<script>
	export let icon;
	export let title;
	export let about;
</script>

<div class="bg-[#F8FCFF] rounded-xl p-6 shadow-sm hover:shadow-md transition-shadow h-full flex flex-col gap-4">

	<!-- Icon -->
	<div class="flex items-center shrink-0">
		<svelte:component this={icon} />
	</div>

	<!-- Title -->
	<h3 class="text-primaryBlue font-extrabold text-lg md:text-xl leading-tight">
		{title}
	</h3>

	<!-- Description -->
	<p class="text-sm text-gray-700 leading-relaxed">
		{about}
	</p>

</div>
