<script>

    import SpokeDiagram from "$lib/components/join-us/SpokeDiagram.svelte";

    let spokes = [
        "UI Design",
        "Frontend",
        "Backend",
        "APIs",
        "Database",
        "SEO",
        "Performance",
        "Accessibility",
        "Testing",
        "Security",
        "DevOps",
        "Analytics",
        "Content",
        "Research"
    ];

    import Nav from "$lib/components/nav.svelte";
    import JoinUsHero from "$lib/components/join-us/join-us-hero.svelte";
    import InternshipsSection from "$lib/components/join-us/internships-section.svelte";
    import VolunteerSection from "$lib/components/join-us/volunteer-section.svelte";

</script>

<Nav />

<JoinUsHero />

<SpokeDiagram center="Jarurat Care" items={spokes} />
<InternshipsSection />

<VolunteerSection />

<svelte:head>
<title>Join Us | Jarurat Care</title>

<meta
name="description"
content="Join Jarurat Care through internships or volunteer opportunities. Contribute to cancer awareness, research support, and patient assistance programs."
/>

</svelte:head>