<script>

	import { Splide, SplideSlide, SplideTrack } from "@splidejs/svelte-splide";
	import SliderSignUp from "./slider-sign-up.svelte";
   const slides = [
    "I love how this platform supports caregivers as much as it supports families. It’s a win-win!",
    "Joining this platform has been the best decision for my career. I’ve built meaningful relationships and a steady income.",
    "I’ve never felt more appreciated in my work. This platform connects me with people who value my skills and compassion."
  ];
</script>
<Splide
        hasTrack={false}
        options={{ perPage: 1, type: 'loop', gap: '1rem', pagination: true, arrows: false, autoplay: true, interval: 3000 }}
        on:paginationUpdated={(event) => {
          const curr = event?.detail.curr;
          const list = event?.detail.data.list;

          list?.querySelectorAll('li > button').forEach((button) => {
            if (!button) return;
            button.style.cssText = `
              width: 0.75rem;
              height: 0.75rem;
              aspect-ratio: 1;
              background-color: #b4bcc8;
              border-radius: 50%;
            `;
          });

          if (curr?.button) {
            curr.button.style.backgroundColor = '#ffba41';
          }
        }}
      >
        <SplideTrack>
          {#each slides as slide}
            <SplideSlide class="">
              <SliderSignUp data={slide} />
            </SplideSlide>
          {/each}
        </SplideTrack>
        <ul class="splide__pagination flex gap-2 mt-4"></ul>
      </Splide>