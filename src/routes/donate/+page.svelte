<script>
	import DonationImpact from '$lib/components/donate/donation-impact.svelte';
	import DonationThankyouLetters from '$lib/components/donate/donation-thankyou-letters.svelte';
	import DonationWork from '$lib/components/donate/donation-work.svelte';
	import PayComponent from '$lib/components/donate/pay/pay-component.svelte';
	import BecomePartOfTeam from '$lib/components/donate/become-part-team.svelte';
	import Nav from '$lib/components/nav.svelte';
	import Faq from '$lib/components/donate/faq.svelte';
	import SupportUs from '$lib/components/donate/support-us.svelte';
</script>

<Nav />
<PayComponent />
<DonationWork/>
<DonationThankyouLetters/>
<DonationImpact />
<BecomePartOfTeam />
<Faq />
<SupportUs/>