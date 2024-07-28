<script>
  import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
  import '@splidejs/svelte-splide/css';

  import ThansksLetterGr from '$lib/svg/donate-page-svgs/thanks-letter-gradient.svelte';
  import ThansksLetterGr2 from '$lib/svg/donate-page-svgs/thanks-letter-gr2.svelte';

  const stories = [
    {
      id: 1,
      title: 'A Lifeline in Times of Need',
      description:
        'Thanks to your generous donations, we received the support we needed when my husband lost his job. We are forever grateful.',
      writerName: 'Asha',
      disease: 'Breast cancer fighter'
    },
    {
      id: 2,
      title: 'Nourishment and Hope',
      description:
        'During my chemotherapy, the nutritional support and care packages were a blessing. Your donations ensured I had the strength to fight cancer.',
      writerName: 'Priya',
      disease: 'Breast cancer fighter'
    },
    {
      id: 3,
      title: 'Restoring Health and Dignity',
      description:
        "After my cancer diagnosis, the medical supplies and emotional support from the NGO were lifesaving. Your contributions helped me regain my health and dignity. I'm forever grateful.",
      writerName: 'Ramesh',
      disease: 'Colon cancer survivor'
    },
    {
      id: 4,
      title: 'A Beacon of Hope',
      description:
        'The nutritional support and care packages from the NGO were a blessing for my sister and me during her chemo. Your kindness ensured she had the strength to fight.',
      writerName: 'Vikram',
      disease: ''
    },
    {
      id: 5,
      title: 'A Beacon of Hope',
      description:
        'The nutritional support and care packages from the NGO were a blessing for my sister and me during her chemo. Your kindness ensured she had the strength to fight.',
      writerName: 'Vikram',
      disease: ''
    }
  ];
</script>

<div class="relative w-full overflow-hidden">
  <div class="absolute w-full h-full -z-10">
    <ThansksLetterGr />
  </div>
  <div class="absolute w-full h-full -z-10">
    <ThansksLetterGr2 />
  </div>

  <div class="w-full text-center my-24 z-10">
    <h1 class="text-[2.1rem] text-[#0D2561] font-bold z-10">
      Thank you <span class="text-[#0155BD]">letters</span>
    </h1>
    <p class="text-[1.2rem] text-[#0D2561]">
      The Impact of Your Kindness: Letters from Beneficiaries
    </p>
  </div>

  <div class="w-full mb-32 z-20">
    <Splide
      class="flex items-center"
      hasTrack={false}
      options={{
        perPage: 4.5,
        perMove: 1,
        focus: 'center',
        pagination: false,
        trimSpace: false,
        arrows: true,
        breakpoints: {
          600: {
            perPage: 2
          }
        }
      }}
      aria-label="slider"
    >
      <SplideTrack>
        {#each stories as story}
          <SplideSlide>
            <div
              class="py-16 px-12 border-4 border-blue-200 bg-white shadow-md mx-3 rounded-xl"
              style="font-family: 'Manrope', sans-serif;"
            >
              <h3 class="text-[#0D2561] text-[1.1rem] font-semibold">
                "{story?.title}"
              </h3>
              <p class="my-3 text-[#0D2561] text-[1rem] font-medium">
                "{story?.description}"
              </p>
              <p class="text-[#576171] text-[.8rem] font-semibold">
                {story?.writerName}
              </p>
              <p class="text-[#576171] text-[.8rem] font-semibold">{story?.disease}</p>
            </div>
          </SplideSlide>
        {/each}
      </SplideTrack>
    </Splide>
  </div>
</div>
