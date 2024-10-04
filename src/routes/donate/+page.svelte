<script>
	import DonateHeader from '$lib/components/donate/donate-header.svelte';
	import DonationImpact from '$lib/components/donate/donation-impact.svelte';
	import DonationThankyouLetters from '$lib/components/donate/donation-thankyou-letters.svelte';
	import DonationWork from '$lib/components/donate/donation-work.svelte';
	import PayComponent from '$lib/components/donate/pay/pay-component.svelte';
	import MonthlyUpdates from '$lib/components/donate/monthly-updates.svelte';
	import Nav from '$lib/components/nav.svelte';
</script>

<Nav />
<DonateHeader />
<PayComponent />
<DonationImpact />
<DonationWork/>
<DonationThankyouLetters/>
<MonthlyUpdates/>
<!-- <MonthlyUpdates /> -->

