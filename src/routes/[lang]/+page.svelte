<script>
	import { locale, translations, } from '$lib/translations/translations';
	import { onMount } from 'svelte';
	import { page } from '$app/stores';

	// Import all components
	import Blogs from '$lib/components/blogs.svelte';
	import CancerResearch from '$lib/components/cancer-research.svelte';
	import Faq from '$lib/components/faqs/faq.svelte';
	import Hero from '$lib/components/hero.svelte';
	import HowWeSupportYou from '$lib/components/how-we-support-you.svelte';
	import Impact from '$lib/components/impact.svelte';
	import MapOfHospitals from '$lib/components/map-of-hospitals.svelte';
	import MonthlyUpdates from '$lib/components/monthly-updates.svelte';
	import Nav from '$lib/components/nav.svelte';
	import Newsletter from '$lib/components/newsletter.svelte';
	import OurTeam from '$lib/components/our-team.svelte';
	import SupportDonateMember from '$lib/components/support-donate-member.svelte';
	import VoiceOfHope from '$lib/components/voice-of-hope.svelte';
	import WhoWeAre from '$lib/components/who-we-are.svelte';

	// Get the current language from the URL or fallback to a default

</script>

<Nav />
<Hero />
<WhoWeAre />
<SupportDonateMember />
<Impact />
<MonthlyUpdates />
<HowWeSupportYou />
<MapOfHospitals />
<CancerResearch />
<VoiceOfHope />
<OurTeam />
<Blogs />
<Faq />
<Newsletter />
