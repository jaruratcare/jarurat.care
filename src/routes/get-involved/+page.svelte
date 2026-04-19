<script>
  import Nav from '$lib/components/nav.svelte';
  import Hero from '$lib/components/get-involved/hero.svelte';
  import Help from '$lib/components/get-involved/help.svelte';
  import Community from '$lib/components/get-involved/community.svelte';
  import MoreInfo from '$lib/components/get-involved/moreInfo.svelte';
</script>

<Nav />

<Hero />

<section class="w-full">
  <Help />
</section>

<section id="community" class="w-full">
  <Community />
</section>

<section class="w-full">
  <MoreInfo />
</section>