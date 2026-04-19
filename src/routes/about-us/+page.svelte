<script>
    import AboutHeroSection from "$lib/components/about-hero-section.svelte";
    import AboutUsSection from "$lib/components/about-us-section.svelte";
    import CaregivingSolution from "$lib/components/caregiving-solution.svelte";
    import Nav from '$lib/components/nav.svelte';
    import NewletterCollabrate from "$lib/components/newletter-collabrate.svelte";
    import OurJounery from "$lib/components/our-jounery.svelte";
    import OurValue from "$lib/components/our-value.svelte";
    import OurVision from "$lib/components/our-vision.svelte";

    let isMenuOpen = false;

    const toggleMenu = () => {
        isMenuOpen = !isMenuOpen;
    };
</script>

<Nav {isMenuOpen} on:toggleMenu={toggleMenu} />

<main class="w-full overflow-x-hidden">

    <AboutHeroSection />

    <section class="px-4 md:px-8 lg:px-16">
        <AboutUsSection />
    </section>

    <section class="px-4 md:px-8 lg:px-16">
        <OurVision />
    </section>

    <section class="px-4 md:px-8 lg:px-16">
        <OurValue />
    </section>

    <section class="px-4 md:px-8 lg:px-16">
        <OurJounery />
    </section>

    <section class="px-4 md:px-8 lg:px-16">
        <CaregivingSolution />
    </section>

    <section class="px-4 md:px-8 lg:px-16">
        <NewletterCollabrate />
    </section>

</main>