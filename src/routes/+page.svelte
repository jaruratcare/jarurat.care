<script context="module">
	export async function load({ page }) {
		// Perform redirection logic
		if (page.url.pathname !== '/en') {
			return {
				redirect: '/en'
			};
		}
	}
</script>

<script>
	import { goto } from '$app/navigation';
	import { onMount } from 'svelte';

	// Perform redirection when the component mounts
	onMount(() => {
		goto('/en');
	});
</script>
