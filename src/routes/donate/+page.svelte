<script>
  import BillingOption from '$lib/components/donation-page/billing-option.svelte';
  import Content from '$lib/components/donation-page/content.svelte';
  import DisplayImage from '$lib/components/donation-page/display-image.svelte';
  import GetInvolved from '$lib/components/donation-page/get-involved.svelte';
  import MoneyTracker from '$lib/components/donation-page/money-tracker.svelte';
  import SupportUs from '$lib/components/donation-page/support-us.svelte';
  import ThanksLetter from '$lib/components/donation-page/thanks-letter.svelte';
</script>

<main class="z-10">
  <DisplayImage />
  <BillingOption />
  <Content />
  <MoneyTracker />
  <GetInvolved />
  <ThanksLetter />
  <SupportUs />
</main>
