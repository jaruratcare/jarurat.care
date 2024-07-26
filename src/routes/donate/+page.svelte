<script>
  import BillingOption from '$lib/components/donation-page/billing-option.svelte';
  import Content from '$lib/components/donation-page/content.svelte';
  import DisplayImage from '$lib/components/donation-page/display-image.svelte';
  import MoneyTracker from '$lib/components/donation-page/money-tracker.svelte';
</script>

<main class="z-10">
  <DisplayImage />
  <BillingOption />
  <Content />
  <MoneyTracker />
</main>
