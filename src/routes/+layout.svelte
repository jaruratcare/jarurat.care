<script>
  import Footer from '$lib/components/footer.svelte';
  import Nav from '$lib/components/nav.svelte';
  import '../app.css';
  import '@fontsource/roboto/500.css';
  import '@fontsource/roboto/100.css';
  import '@fontsource/public-sans/500.css';
  import '@fontsource-variable/manrope';
  import '@fontsource-variable/rubik';
  import '@fontsource-variable/playfair-display';
</script>

<main>
  <Nav />
  <div class="fixed bottom-0 right-0 p-4 z-50">
    <img
      src="../../src/assets/images/talk-to-hope.png"
      alt="Talk to Hope"
      class="cursor-pointer w-40 h-auto shadow-sm"
    />
  </div>
  <slot />
  <Footer />
</main>
