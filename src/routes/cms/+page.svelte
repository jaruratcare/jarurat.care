<script>
import { goto } from '$app/navigation';
import { onMount } from 'svelte';

onMount(() => {
  goto('/cms/login');
});
</script>

<p>Redirecting...</p>