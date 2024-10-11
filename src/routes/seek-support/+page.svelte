<script>
import Nav from "$lib/components/nav.svelte";
	import CaregivingSeeksupport from "$lib/components/seek-support/caregiving-seeksupport.svelte";
	import HeroSection from "$lib/components/seek-support/heroSection.svelte";
	import HowWeSupport from "$lib/components/seek-support/how-we-support.svelte";
	import OurServices from "$lib/components/seek-support/our-services.svelte";
	import OurVisionSeeksupport from "$lib/components/seek-support/our-vision-seeksupport.svelte";
	import SharedJourney from "$lib/components/seek-support/shared-journey.svelte";
</script>

<div class="bg-[#F8FCFF]">
    <Nav/>
    <HeroSection/>
    <OurServices/>
    <HowWeSupport/>
    <OurVisionSeeksupport/>
    <CaregivingSeeksupport/>
    <SharedJourney/>
</div>