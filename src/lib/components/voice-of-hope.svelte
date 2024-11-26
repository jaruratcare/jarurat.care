<script>
	import { onMount } from 'svelte';
	import { writable } from 'svelte/store';
	import Header from './ui/header.svelte';
	import Button from './ui/button.svelte';
	import Button2 from './ui/tbutton.svelte';
	import testimonials from '$lib/data/testimonials.json';
	import { ChevronLeft, ChevronRight, QuoteIcon } from 'lucide-svelte';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';

	let innerWidth = writable(0);

	onMount(() => {
		innerWidth.set(window.innerWidth);
		window.addEventListener('resize', () => {
			innerWidth.set(window.innerWidth);
		});
	});
</script>

<div class="max-w-full h-fit mx-auto py-10 sm:pt-28 relative overflow-hidden bg-[#B1E7F5]">
  
	<!-- Header Section -->
	<Header
	  title="Voice of Hope"
	  class="relative z-10"
	  subtitle="Inspiring Journeys from the People We've Supported"
	/>
  
	<!-- Splide Carousel -->
	<Splide
  hasTrack={false}
  aria-label="..."
  class="max-w-full sm:max-w-[70rem] mx-auto mt-8 z-10"
  options={{
    perPage: $innerWidth < 600 ? 1 : ($innerWidth < 1024 ? 2 : 3), 
    type: 'loop'
  }}
>
  <div class="custom-wrapper">
    <SplideTrack>
      {#each testimonials as testimonial}
        <SplideSlide class="p-1 pl-2 max-w-[90%] group">
          <div class="h-full flex sm:flex-col items-center sm:items-start flex-wrap gap-2 sm:gap-4 rounded-2xl p-4 md:p-8 bg-white text-[#0D2561] drop-shadow-sm border transition-all duration-500 ease-in-out group-hover:bg-black group-hover:text-white group-hover:border-4 group-hover:border-[#FFBA41]">
            
            <!-- Avatar and Content -->
            <div class="w-12 sm:w-20 aspect-square border rounded-full transition-all duration-500 ease-in-out group-hover:opacity-0"></div>
  
            <div class="leading-[1.2] transition-all duration-500 ease-in-out group-hover:opacity-0">
              <h3 class="text-[#0D2561] font-medium font-rubik text-[1.1em]">
                {testimonial.name}
              </h3>
              <h4 class="text-[0.8em] text-[#576171]">
                {testimonial.description}, {testimonial.location}
              </h4>
            </div>
  
            <!-- Quote and Content -->
            <div class="flex gap-2 w-full">
              <div><QuoteIcon class="size-10 rotate-180 text-[#FFBA41]" /></div>
              <p class="text-[0.9em]">{testimonial.content}</p>
            </div>
          </div>
        </SplideSlide>
      {/each}
    </SplideTrack>
    
    <!-- Arrows for Splide Carousel -->
    <div class="splide__arrows flex items-center justify-between gap-2 absolute -inset-x-12 top-1/2 transform -translate-y-1/2">
      <Button class="splide__arrow splide__arrow--prev size-12 p-0 items-center bg-[#0155BD] transition transform duration-300 ease-in-out">
        <ChevronLeft class="w-full" />
      </Button>
      <Button class="splide__arrow splide__arrow--next size-12 p-0 flex items-center bg-[#0155BD] transition transform duration-300 ease-in-out">
        <ChevronRight class="w-full" />
      </Button>
    </div>
  </div>
</Splide>

  
	<!-- Action Buttons -->
	<div class="btn relative flex items-center justify-center gap-4 mt-8">
	  <a class="flex items-center justify-center z-10 relative" href="/" target="_blank">
		<Button2 color="#0D2561">Submit a Story</Button2>
	  </a>
	  <a class="flex items-center justify-center z-10 relative" href="/" target="_blank">
		<Button>Get Support</Button>
	  </a>
	</div>
  </div>
  