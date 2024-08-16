<script>
  import Footer from '$lib/components/footer.svelte';
  import '../app.css';
  import '@fontsource/manrope'; // weight 400
  import '@fontsource/manrope/500.css';
  import '@fontsource/manrope/600.css';
  import '@fontsource/manrope/700.css';
  import '@fontsource/manrope/800.css';
  import '@fontsource/playfair-display/600.css';
  import '@fontsource/rubik/500.css';

  import '@splidejs/svelte-splide/css/core';
</script>

<main class="font-manrope">
  <slot />
  <Footer />
</main>
