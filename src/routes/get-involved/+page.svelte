<script>
    import { onMount } from 'svelte';
    import { goto } from '$app/navigation';
    import Nav from '$lib/components/nav.svelte';
    import Hero from '$lib/components/get-involved/hero.svelte';
    import Help from '$lib/components/get-involved/help.svelte';
    import MoreInfo from '$lib/components/get-involved/moreInfo.svelte';
    import Community from '$lib/components/get-involved/community.svelte';
    import ContentSeperator from '$lib/components/get-involved/contentSeperator.svelte';

    let isMenuOpen = false;

    onMount(() => {
        goto('/get-involved');
    });

    const toggleMenu = () => {
        isMenuOpen = !isMenuOpen;  // Function to toggle the menu state
    };
</script>

<!-- Pass isMenuOpen and toggleMenu to the Nav and Hero components -->
<Nav {isMenuOpen} on:toggleMenu={toggleMenu} />
<Hero {isMenuOpen} />
<Help />
<MoreInfo />
<Community />
<ContentSeperator />
