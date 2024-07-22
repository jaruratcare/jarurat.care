<script>
  import Footer from '$lib/components/Footer/footer.svelte';
  import '../app.css';
  import '@fontsource/roboto/500.css';
  import '@fontsource/roboto/100.css';
  import '@fontsource/public-sans/500.css';
</script>

<main>
  <slot />
  <Footer/>
</main>

