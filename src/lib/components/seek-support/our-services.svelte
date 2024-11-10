<script>
	import CancerProjectTvIcon from '$lib/svg/about/cancer-project-tv-icon.svelte';
	import PatientAdviosry from '$lib/svg/about/patient-adviosry.svelte';
	import ResourceGuidenceIcon from '$lib/svg/about/resource-guidence-icon.svelte';
	import SocialCommunitySpeakerIon from '$lib/svg/about/social-community-speaker-ion.svelte';
    import OurCommunityItem from "../our-community-item.svelte";
	import Button from "../ui/button.svelte";
	import { Splide, SplideSlide } from '@splidejs/svelte-splide';

	import { onMount } from 'svelte';
	let particlesToShow ;

	const updateParticlesToShow = () => {
		if (window.innerWidth < 768) {
			particlesToShow = 1;
		} else if (window.innerWidth < 1318) {
			particlesToShow = 3;
		} else if (window.innerWidth < 1040) {
			particlesToShow = 3;
		} else {
			particlesToShow = 4;
		}
	};

	onMount(() => {
		updateParticlesToShow(); // Set initial value based on window size
		window.addEventListener('resize', updateParticlesToShow);

		return () => {
			window.removeEventListener('resize', updateParticlesToShow); // Cleanup on component destruction
		};
	});


    const items = [
		{
			icon: CancerProjectTvIcon,
			title: 'The Cancer Project',
			about:
				'India ’ s first ever cancer podcast and webinar series for discussions on treatments, stories, and expert insights.'
		},
		{
			icon: ResourceGuidenceIcon,
			title: 'Resource Guidance',
			about: 'Building India’s largest Edtech hub for any cancer queries in 7+ regional languages.'
		},
		{
			icon: SocialCommunitySpeakerIon,
			title: 'Social Community',
			about:
				'Building social media hub to discuss early detection & prevention of terminal cancers.'
		},
		{
			icon: PatientAdviosry,
			title: 'Patient Advocacy',
			about:
				'Advocating patients’ rights & educating people about what’ s rightfully correct in healthcare space.'
		}
	];
</script>

<div class="mx-auto mt-16 w-[90%]">
    <div class="flex gap-3 justify-center ">
        <div class="text-primaryBlue text-center  font-extrabold text-4xl ">OUR </div>
        <div class="md:mb-24 mb-5  text-center font-extrabold text-4xl ">SERVICES</div>
    </div>
    
    <div class="lg:gap-3 w-[90%] md:w-full gap-2  ">
        <Splide	options={{
            type: 'loop',
            perPage: particlesToShow,
            gap: '2rem',
            autoplay: false,
            speed: 800,
            arrows: false,
            pagination: true
        }}
  class=' '
    >
    
        {#each items as item}
            <SplideSlide>
                <OurCommunityItem icon={item.icon} about={item.about} title={item.title} />
            </SplideSlide>
        {/each}
    </Splide>
    </div>

    <div class="btns flex gap-5 justify-center py-5">
        <button class="rounded-3xl border-2 py-3 px-7">Know More</button>
        <Button class="relative z-10">Get Support</Button>
    </div>
</div>