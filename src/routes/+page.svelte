<script>
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

    // Google Analytics initialization
    if (typeof window !== 'undefined') {
        window.dataLayer = window.dataLayer || [];
        function gtag() {
            dataLayer.push(arguments);
        }
        gtag('js', new Date());
        gtag('config', 'G-SR1C89YNC3');
    }
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

<!-- Add the script asynchronously in the document -->
<svelte:head>
    <title>Gallbladder Cancer & Biliary Tract Support | Jarurat Care</title>
    <meta name="description" content="Jarurat Care provides export support for gallbladder cancer, biliary tract cancer, and cholangiocarcinoma. Get medical assistance, awareness, and caregiving support today." />
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-SR1C89YNC3"></script>
</svelte:head>
