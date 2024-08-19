<script lang="ts">
	import { page } from '$app/stores';
	import Footer from '$lib/components/footer.svelte';
	import { loadTranslations, locale } from '$lib/translations/translations';
	import '@fontsource/manrope'; // weight 400
	import '@fontsource/manrope/500.css';
	import '@fontsource/manrope/600.css';
	import '@fontsource/manrope/700.css';
	import '@fontsource/manrope/800.css';
	import '@fontsource/playfair-display/600.css';
	import '@fontsource/rubik/500.css';

	import '@splidejs/svelte-splide/css/core';
	import { onMount } from 'svelte';
	 onMount(async () => {
  const { pathname } = $page.url;
  console.log("Pathname:", pathname);
  const pathSegments = pathname.split('/').filter(Boolean);
  const initLocale = pathSegments[0] === 'en' || pathSegments[0] === 'hi' ? pathSegments[0] : 'en';

  console.log("Initial Locale:", initLocale);

  await loadTranslations(initLocale, pathname);
  locale.set(initLocale); // Update the locale based on the path
  console.log("Locale set to:", $locale);
});

</script>

<main class="font-manrope">
	<slot />
	<Footer />
</main>
