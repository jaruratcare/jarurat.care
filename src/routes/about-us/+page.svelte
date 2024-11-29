<script>
    export let data;
    export let name = "About Us";
	import AboutHeroSection from "$lib/components/about-hero-section.svelte";
	import AboutUsSection from "$lib/components/about-us-section.svelte";
	import CaregivingSolution from "$lib/components/caregiving-solution.svelte";
    import Nav from "$lib/components/nav.svelte";
	import NewletterCollabrate from "$lib/components/newletter-collabrate.svelte";
	import OurCommunity from "$lib/components/our-community.svelte";
	import OurJounery from "$lib/components/our-jounery.svelte";
	import OurValue from "$lib/components/our-value.svelte";
	import OurVision from "$lib/components/our-vision.svelte";
    </script>
    
    
    <div class="h-[80px]"><Nav/></div>
    <div class="bg-[#F8FCFF]">
        <div class="md:mb-36"><AboutHeroSection/></div>
        <div class=""><AboutUsSection/></div>
        <OurVision/>
        <OurValue/>
    </div> 
    <OurJounery/>
    <div class="bg-[#F8FCFF] pt-12"><OurCommunity /></div>
    <CaregivingSolution  data={data} />
    <NewletterCollabrate/>
