<script>
  import Footer from '$lib/components/footer.svelte';
  import '../app.css';
  import '@fontsource/roboto/500.css';
  import '@fontsource/roboto/100.css';
  import '@fontsource/public-sans/500.css';
  import '@fontsource/manrope/300.css';
  import '@fontsource-variable/rubik';
</script>

<main>
  <slot />
  <Footer />
</main>
