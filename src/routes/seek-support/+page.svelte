<script>
import Nav from "$lib/components/nav.svelte";
	import CaregivingSeeksupport from "$lib/components/seek-support/caregiving-seeksupport.svelte";
	import HeroSection from "$lib/components/seek-support/heroSection.svelte";
	import HowWeSupport from "$lib/components/seek-support/how-we-support.svelte";
	import OurServices from "$lib/components/seek-support/our-services.svelte";
	import OurVisionSeeksupport from "$lib/components/seek-support/our-vision-seeksupport.svelte";
	import SharedJourney from "$lib/components/seek-support/shared-journey.svelte";
    import ApplyForSupport from "$lib/components/seek-support/apply-for-support.svelte";
	import Newsletter from "$lib/components/newsletter.svelte";
    import CaregivingSolution from "$lib/components/caregiving-solution.svelte"
	import MindBodyWellness from "$lib/components/seek-support/mind-body-wellness.svelte";
	import SupportUs from "$lib/components/seek-support/support-us.svelte";

	
</script>

<div class="bg-[#F8FCFF]">
    <Nav/>
    <HeroSection/>
    <OurServices/>
    <ApplyForSupport/>
    <HowWeSupport/>
<MindBodyWellness/>
    <OurVisionSeeksupport/>
    <CaregivingSeeksupport/>
    <SharedJourney/>
    <SupportUs/>
    <Newsletter/>
</div>