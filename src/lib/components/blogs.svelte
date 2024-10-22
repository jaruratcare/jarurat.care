<script lang="ts">
    import Header from './ui/header.svelte';
    import Button from './ui/button.svelte';
    import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
    import { ChevronLeft, ChevronRight } from 'lucide-svelte';
    import SingleWave from '$lib/svg/single-wave.svelte';
    import Button2 from './ui/tbutton.svelte';

    const blogs = [
        {
            title: 'Jarurat Care Launches New Support Program for Cancer Patients',
            excerpt: `Jarurat Care is excited to announce the launch of its new comprehensive support program for cancer patients. This initiative aims to provide enhanced financial aid, counseling services, and community support for patients.`,
            tag: 'News',
            date: new Date()
        },
        {
            title: 'How Jarurat Care Helped Ravi Overcome His Cancer Battle',
            excerpt: `In this heartfelt blog post, we share the inspiring journey of Ravi Kumar, one of the many patients who have benefitted from our services.`,
            tag: 'Blog',
            date: new Date()
        },
        {
            title: 'Jarurat Care Partners with Leading Research Institutions for New Cancer Research',
            excerpt: `Jarurat Care is proud to announce a new partnership with top cancer research institutions to advance the search for effective treatments and cures. This collaboration aims to fund innovative research projects and share knowledge.`,
            tag: 'News',
            date: new Date()
        }
    ];



</script>



<SingleWave fill="#d2f1fc" />


<div class="py-10 sm:py-20 relative px-4 bg-[#D3F2FC] items-center justify-center" id="news">
    <Header title="Jarurat Care" spantitle="Blogs" spantitleColor="#0155BD" />

    <div class="relative flex justify-center p-5">
        <Splide
    hasTrack={false}
    options={{
        perPage: 3,     // Set perPage to your desired value
        type: 'loop',   // Loop the slides
        gap: '2rem',    // Set gap between slides
        pagination: true  // Ensure pagination is active
    }}
    on:paginationUpdated={(ev) => {
        const curr = ev?.detail.curr;
        const list = ev?.detail.data.list;

        if (list) {
            // Show pagination even if the number of slides <= perPage
            list.style.display = 'flex';  // Ensure pagination is visible

            // Apply styles to pagination buttons
            list.querySelectorAll('li > button').forEach((button) => {
                if (!button) return;

                button.style.width = '1rem';
                button.style.aspectRatio = '1';
                button.style.backgroundColor = '#b4bcc8';
                button.style.borderRadius = '100%';
            });

            if (curr?.button) {
                curr.button.style.backgroundColor = '#ffba41';  // Highlight current button
            }
        }
    }}
>
            <SplideTrack class="w-full max-w-[60rem] mx-auto">
                {#each blogs as blog}
                    <SplideSlide class="p-1">
                        <div class="cards h-full flex flex-col gap-1 border-2 rounded-xl bg-white overflow-hidden hover:shadow-lg hover:bg-[#FFD791] hover:border-white transition-all duration-300 ease-in-out">
                            <div class="bg-gray-200 aspect-video"></div>

                            <h2 class="text-[#04509C] text-[1.2em] leading-[1.3] font-semibold px-4 sm:px-8 pt-4 sm:pt-8">
                                {blog.title}
                            </h2>

                            <div class="grow"></div>

                            <p class="text-[#576171] px-4 sm:px-8">
                                {blog.excerpt.trim().substring(0, 100) + '...'}
                            </p>

                            <div class="sm:px-8 pb-4 sm:pb-8 relative z-10">
                                <Button class="rounded-[50px] mt-4">Know More</Button>
                            </div>
                        </div>
                    </SplideSlide>
                {/each}
            </SplideTrack>

            <!-- Arrows -->
            <div class="splide__arrows flex items-center justify-between gap-2 absolute -inset-x-16 top-1/2 transform -translate-y-1/2">
                <Button class="splide__arrow splide__arrow--prev size-8 p-0 items-center bg-[#0155BD] transition transform duration-300 ease-in-out">
                    <ChevronLeft class="w-full" />
                </Button>
                <Button class="splide__arrow splide__arrow--next size-8 p-0 flex items-center bg-[#0155BD] transition transform duration-300 ease-in-out">
                    <ChevronRight class="w-full" />
                </Button>
            </div>

            <ul class="splide__pagination flex justify-center gap-2 p-5"></ul>

        </Splide>
    </div>

    <a href="/" class="flex justify-center mt-5">
        <Button class="mx-auto bg-[#0155BD] transition transform duration-300 ease-in-out">Read More</Button>
    </a>
</div>

<SingleWave fill="#d2f1fc" class="rotate-180" />
