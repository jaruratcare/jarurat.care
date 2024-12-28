<script>
    import AboutHeroSection from "$lib/components/about-hero-section.svelte";
    import AboutUsSection from "$lib/components/about-us-section.svelte";
    import CaregivingSolution from "$lib/components/caregiving-solution.svelte";
    import Nav from '$lib/components/nav.svelte';
    import NewletterCollabrate from "$lib/components/newletter-collabrate.svelte";
    import OurJounery from "$lib/components/our-jounery.svelte";
    import OurValue from "$lib/components/our-value.svelte";
    import OurVision from "$lib/components/our-vision.svelte";
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";
    import Hero from '$lib/components/get-involved/hero.svelte';

    let isMenuOpen = false;

    onMount(() => {
        goto('/about-us');
    });

    const toggleMenu = () => {
        isMenuOpen = !isMenuOpen;  // Function to toggle the menu state
    };
</script>

<Nav {isMenuOpen} on:toggleMenu={toggleMenu} />
<Hero {isMenuOpen} />
<AboutUsSection />
<OurVision />
<OurValue />
<OurJounery />
<CaregivingSolution />
<NewletterCollabrate />


