<script>
    import { onDestroy, onMount } from 'svelte';
	import { writable } from 'svelte/store';
	import Button from '../ui/button.svelte';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
	import SingleWavedown from '$lib/svg/single-wavedown.svelte';
    import FindMentorImg from '$lib/assets/donate/find-a-mentor.webp'
	import JSON_ThanksLetters from '$lib/data/donate/thank-you-letters.json';

    let innerWidth = writable(0);

	onMount(() => {
		innerWidth.set(window.innerWidth);
		window.addEventListener('resize', () => {
			innerWidth.set(window.innerWidth);
		});
	});

	onMount(() => {
		const activeDotObserver = new MutationObserver((mutations) => {
			mutations.forEach((mutation) => {
				// @ts-ignore
				if (mutation.target.classList.contains('is-active')) {
					// @ts-ignore
					mutation.target.style.backgroundColor = '#ffba41';
				} else {
					// @ts-ignore
					mutation.target.style.backgroundColor = '#B4BCC8';
				}
			});
		});

		const paginationDots = document.querySelectorAll('.splide__pagination__page');
		paginationDots.forEach((dot) => {
			activeDotObserver.observe(dot, { attributes: true });
		});

        onDestroy(() => {
            activeDotObserver.disconnect();
        });
	});
</script>

<div>
    <div class="flex md:hidden w-full mt-20">
        <div class="w-full bg-[#D3F2FC] rounded-t-3xl relative">
            <img src="{FindMentorImg}" alt="" class="w-full object-cover object-center">
            <h1 class="absolute text-2xl text-white max-w-48 font-semibold font-rubik top-8 left-4">Find a Mentor for Your <span class="text-[#ffba41]">Cancer</span> Journey</h1>
            <Button class="absolute bottom-10 z-10 custom-button left-4 px-7 py-2">Seek Support</Button>
        </div>
    </div>

    <div class="py-8 sm:py-24 bg-[#D3F2FC] md:bg-white">
        <div class="flex flex-col items-center justify-center mx-auto px-5 sm:px-10">
            <h2 class="text-[1.5em] sm:text-[2em] font-bold text-[#0D2561] leading-tight">
                Thank you <span class="text-[#0155BD]">letters</span>
            </h2>
            <p class="text-[#0D2561] text-center font-medium leading-snug text-[0.7em] sm:text-[0.9em] md:w-[48%] w-[100%]">
                The Impact of Your Kindness: Letters from Beneficiaries
            </p>
        </div>

        <Splide
        hasTrack={false}
        class="md:px-16 px-4 mt-8 z-10 relative"
        options={{
            start: 1,
            perPage: 4,
            perMove: 4,
            gap: '1rem',
            type: "loop",
            drag: "free",
            snap: false,
            interval: 3000,
            arrows: false,
            pagination: true,
            rewind: true,
            rewindByDrag: true,
            lazyLoad: true,
            breakpoints: {
                600: {
                    start:1,
                    perPage: 1,
                    perMove: 1,
                    snap: true
                }
            }
            }}
            >

            <div class="custom-wrapper relative">
                <SplideTrack>
                    {#each JSON_ThanksLetters as item, index}
                    <SplideSlide>
                        <div class="h-full flex flex-col justify-start aspect-[6/6] rounded-xl bg-white border-blue-500/50 overflow-hidden relative group">
                            <div class="w-full h-full py-8 px-4 md:py-16 md:px-12 mx-auto rounded-xl border transition-all duration-300 ease-in-out {index % 2 != 0 ? 'group-hover:bg-white' : 'group-hover:bg-transparent'}">
                                <small class="block {index % 2 != 0 ? 'text-white group-hover:text-[#0D2561]' : 'text-[#0D2561] group-hover:text-white'} font-bold leading-snug transition-all duration-300 ease-in-out">"{item.title}"</small>
                                <p class="text-[0.7em] {index % 2 != 0 ? 'text-white group-hover:text-[#0D2561]' : 'text-[#0D2561] group-hover:text-white'} leading-snug min-h-24 py-4 transition-all duration-300 ease-in-out">"{item.content}"</p>
                                <p class="text-[0.7em] {index % 2 != 0 ? 'text-white group-hover:text-gray-600' : 'text-gray-600 group-hover:text-white'} font-semibold leading-snug transition-all duration-300 ease-in-out">{item.name}</p>
                                <p class="text-[0.7em] {index % 2 != 0 ? 'text-white group-hover:text-gray-600' : 'text-gray-600 group-hover:text-white'} font-semibold leading-snug transition-all duration-300 ease-in-out">{item.description}</p>
                            </div>
                            <div class="absolute inset-0 bg-cover bg-center transition-all duration-300 ease-in-out {index % 2 != 0 ? 'opacity-100 group-hover:opacity-0' : 'opacity-0 group-hover:opacity-100'}" style="background-image: url('{item.imgSrc}');">
                                <div class="w-full h-full py-8 px-4 md:py-16 md:px-12 mx-auto rounded-xl border transition-all duration-300 ease-in-out {index % 2 != 0 ? 'group-hover:bg-white' : 'group-hover:bg-transparent'}">
                                    <small class="block {index % 2 != 0 ? 'text-white group-hover:text-[#0D2561]' : 'text-[#0D2561] group-hover:text-white'} font-bold leading-snug transition-all duration-300 ease-in-out">"{item.title}"</small>
                                    <p class="text-[0.7em] {index % 2 != 0 ? 'text-white group-hover:text-[#0D2561]' : 'text-[#0D2561] group-hover:text-white'} leading-snug min-h-24 py-4 transition-all duration-300 ease-in-out">"{item.content}"</p>
                                    <p class="text-[0.7em] {index % 2 != 0 ? 'text-white group-hover:text-gray-600' : 'text-gray-600 group-hover:text-white'} font-semibold leading-snug transition-all duration-300 ease-in-out">{item.name}</p>
                                    <p class="text-[0.7em] {index % 2 != 0 ? 'text-white group-hover:text-gray-600' : 'text-gray-600 group-hover:text-white'} font-semibold leading-snug transition-all duration-300 ease-in-out">{item.description}</p>
                                </div>
                            </div>
                        </div>
                    </SplideSlide>
                    {/each}
                </SplideTrack>

                <div class="splide__pagination flex justify-center py-6"></div>
            </div>
        </Splide>

    </div>

    <SingleWavedown fill="#D3F2FC" class="z-10 flex md:hidden"/>
</div>