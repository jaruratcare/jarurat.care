<script>
	const rows = [
		{ title: 'Help a Caregiver', amount: 30 },
		{ title: 'Buy a patient medicine', amount: 1000 },
		{ title: 'Cancer Awareness Campaign', amount: 300 }
	];
</script>

<main class="max-w-2xl mx-auto min-h-svh p-4 space-y-8">
	<header>
		<h1 class="text-xl">Jarurat Care!</h1>
		<h2 class="">jaisi Jarurat, waisi care</h2>
	</header>

	<div class="space-y-2">
		{#each rows as row}
			<a
				href={`/quick-donate/amount/${row.amount}`}
				class="flex items-center justify-between w-full border py-2 px-4 rounded-md hover:bg-gray-100 transition-colors"
			>
				<small>{row.title}</small>
				<span class="font-bold">₹{row.amount}/-</span>
			</a>
		{/each}
	</div>
</main>
