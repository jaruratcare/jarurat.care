<script>
	import Connect from "$lib/svg/connect.svelte";
	import Resources from "$lib/svg/resources.svelte";
import OurCommunityItem from "../our-community-item.svelte";
	import Button from "../ui/button.svelte";

const items = [
		{
			icon: Connect,
			title: 'The Cancer Project',
            about:"India ’ s first ever cancer podcast and webinar series for discussions on treatments, stories, and expert insights."
		},
		{
			icon: Connect,
			title: 'Resource Guidance',
            about:"Building India’s largest Edtech hub for any cancer queries in 7+ regional languages."
		},
        {
            icon: Resources,
            title: 'Social Community',
            about:"Building social media hub to discuss early detection & prevention of terminal cancers."
        },
        {
			icon: Resources,
			title: 'Patient Advocacy',
            about:"Advocating patients’ rights & educating people about what’ s rightfully correct in healthcare space."
		},
    ]
</script>

<div class="mx-auto mt-16 lg:w-[20rem]">
    <div class="flex gap-3 justify-center ">
        <div class="text-primaryBlue text-center  font-extrabold text-4xl ">OUR </div>
        <div class="mb-24 text-center font-extrabold text-4xl ">SERVICES</div>
    </div>
    
    <div class="flex lg:gap-3 gap-2 justify-center">
        {#each items as item}
        <OurCommunityItem icon={item.icon} about={item.about} title={item.title}/>
        {/each}
    </div>

    <div class="btns flex gap-5 justify-center py-5">
        <button class="rounded-3xl border-2 py-3 px-7">Know More</button>
        <Button class="relative z-10">Get Support</Button>
    </div>
</div>