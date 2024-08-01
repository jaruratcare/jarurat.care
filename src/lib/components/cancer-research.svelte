<script>
  import PrevButtonIcon from '$lib/svg/prev.svelte';
  import NextButtonIcon from '$lib/svg/next.svelte';
  import SearchIcon from '$lib/svg/search-icon.svelte';
  import WaveGradient from '../../assets/images/Wave gradient.png';
  let searchQuery = 'Breast Cancer';
  let currentIndex = 0;
  const articles = [
    {
      title: 'Global Cancer Incidence and Mortality Rates and Trends',
      description:
        'There are limited published data on recent cancer incidence and mortality trends worldwide...',
      link: '#'
    },
    {
      title: 'Measuring cancer evolution from the genome.',
      description:
        'The temporal dynamics of cancer evolution remain elusive, because it is impractical to lon...',
      link: '#'
    },
    {
      title: 'The Role of Telomerase in Breast Cancer’s Response to Therapy.',
      description:
        'Currently, breast cancer appears to be the most widespread cancer in the world and the most c...',
      link: '#'
    }
  ];

  const nextSlide = () => {
    currentIndex = (currentIndex + 1) % articles.length;
  };

  const prevSlide = () => {
    currentIndex = (currentIndex - 1 + articles.length) % articles.length;
  };
</script>

<div class="relative">
  <img src={WaveGradient} alt="" class="absolute top-0 left-0 w-full h-full object-cover z-[-1]" />
  <div class="flex flex-col items-center py-10">
    <h1 class="text-4xl font-semibold mb-4" style="color: #0D2561;">
      Read the Latest in Cancer Research
    </h1>
    <p class="mb-6" style="color: #0D2561;">
      Explore the latest studies and breakthroughs in cancer research.
    </p>

    <div class="relative w-2/3 max-w-xl mb-6">
      <input
        type="text"
        placeholder="Search topic"
        class="w-full pl-12 py-2 border rounded-full bg-[#F4F4F4] text-gray-700 placeholder-[#6A5A58] focus:outline-none"
      />
      <div class="absolute inset-y-0 right-0 flex items-center pr-3">
        <SearchIcon />
      </div>
    </div>
    <div class="text-lg mb-20" style="color: #04509C; font-size: 1rem;">
      Results for <strong>"{searchQuery}"</strong>
    </div>

    <div class="relative w-full flex flex-col items-center">
      <div class="flex space-x-2 mb-6 ml-auto mr-12 -mt-6">
        <button
          class="w-[40px] h-[40px] p-0 border rounded-full flex items-center justify-center bg-white"
          on:click={prevSlide}><PrevButtonIcon /></button
        >
        <button
          class="w-[40px] h-[40px] p-0 border-2 rounded-full flex items-center justify-center"
          on:click={nextSlide}><NextButtonIcon /></button
        >
      </div>

      <div class="w-[80%] overflow-hidden">
        <div
          class="w-full flex transition-transform duration-500"
          style="transform: translateX(-{currentIndex * 33.333}%);"
        >
          {#each articles as article}
            <div
              class="min-w-[33.333%] bg-white p-4 shadow-lg rounded-md mx-2 flex flex-col"
              style="flex: 1 0 0;"
            >
              <div class="flex flex-col flex-grow">
                <h2
                  class="font-semibold mb-2 text-[#04509C]"
                  style="text-align: left; word-wrap: break-word;"
                >
                  {#if article.title === 'Measuring cancer evolution from the genome.'}
                    Measuring cancer evolution from the&nbsp;<br />genome.
                  {:else}
                    {article.title}
                  {/if}
                </h2>
                <p
                  class="text-sm mb-4"
                  style="color: #576171; text-align: left; word-wrap: break-word;"
                >
                  {#if article.description === 'The temporal dynamics of cancer evolution remain elusive, because it is impractical to lon...'}
                    The temporal dynamics of cancer evolution remain elusive,<br />
                    because it is impractical to lon...
                  {:else if article.description === 'Currently, breast cancer appears to be the most widespread cancer in the world and the most c...'}
                    Currently, breast cancer appears to be the most <br />
                    widespread cancer in the world and the most c...
                  {:else}
                    {article.description}
                  {/if}
                </p>
              </div>
              <button
                class="relative px-4 py-1 bg-[#0155BD] text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center mt-auto"
                style="align-self: flex-start;"
              >
                <span
                  class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
                  style="transform: translateX(-50%), translateX(50%);"
                ></span>
                <span class="relative">Read Now</span>
              </button>
            </div>
          {/each}
        </div>
      </div>
    </div>
  </div>
</div>
