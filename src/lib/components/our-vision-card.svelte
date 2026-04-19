<script>
	export let content;
</script>

<div class="relative w-full max-w-sm mx-auto flex justify-center">

	<!-- Background Card -->
	<div class="bg-[#0D2561] -rotate-1 rounded-2xl w-full h-[17rem] md:h-[18rem]"></div>

	<!-- Foreground Card -->
	<div class="absolute bottom-1 left-1/2 -translate-x-1/2 bg-white rounded-2xl w-[92%] md:w-[85%] h-[17rem] md:h-[18rem] flex items-center justify-center p-5 text-center">

		<div class="text-sm md:text-base leading-relaxed break-words">
			{content}
		</div>

	</div>

</div>