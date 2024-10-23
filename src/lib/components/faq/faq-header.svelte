<script>
  import { goto } from "$app/navigation";
  import RightArrow from "$lib/svg/right-arrow.svelte";
  import Search from "$lib/svg/search.svelte";
  import TiltedArrow from "$lib/svg/tilted-arrow.svelte";
	import Loader from "../ui/loader.svelte";
  let homeBreadCrum="Home"
  let faqBreadCrum="Faqs"
  let questions="How Can We Help You?"
  let questionDes="Find All You Need to Know About Cancer, Jarurat Care, and How We Can Support You."
  let dontKnow="Don’t Know"
  let whatToAsk="What To Ask?"
  let secondaryDes ="Comprehensive care and support throughout your cancer journey"
  let searchResult ="Search Results for "
  // Array of items
  const arr = [
    "Initial Diagnosis and Questions to Ask",
    "Discussing with Healthcare Providers",
    "Post-Surgery and Recovery",
    "Surgical Questions",
    "Prognosis and Survival",
    "Treatment Options",
    "General Gallbladder Health",
    "Additional Considerations",
    "Glossary of Cancer Terms"
  ];

  const answers = [
    "What are the early signs and symptoms of breast cancer?",
    "How often should I get a mammogram for breast cancer screening?",
    "What are the risk factors for developing breast cancer?",
    "Can men get breast cancer, and what are the symptoms?",
    "How can I reduce my risk of breast cancer?",
    "How does family history affect my risk of breast cancer?",
    "How does breast cancer affect fertility and pregnancy?",
    "What should I expect during a breast cancer diagnosis?",
    "What treatment options are available for breast cancer?",
    "What is the survival rate for different stages of breast cancer?"
  ];

  $: searchInput = "";
  let isSearchReady=false
  let loading = false;
  let hasError = false;
  $: searchResults = [];
  // Pagination variables
  let currentPage = 1;
  const itemsPerPage = 6;

  // Function to get the items for the current page
  function paginatedItems(page, array) {
    const startIndex = (page - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    return array.slice(startIndex, endIndex);
  }

  // Reactive statement to update data when currentPage or arr changes
  $: data = paginatedItems(currentPage, arr);
  $: totalPages = Math.ceil(arr.length / itemsPerPage);
  $: answerArr = paginatedItems(currentPage, answers);


  // Functions to handle pagination
  function goToPreviousPage() {
    if (currentPage > 1) {
      currentPage -= 1;
    }
  }

  function goToNextPage() {
    if (currentPage < totalPages) {
      currentPage += 1;
    }
  }

  function handleClick(val) {
    goto(`/faq/${val}`);
  }
   $: if (searchInput === "") {
    isSearchReady = false;
  }
  
   async function searchBlogs() {

      if (searchInput.trim() !== "") {
         loading = true;
         hasError = false; // Reset error state before starting the search
         try {
            const response = await fetch(`http://localhost:5000/api/faqs/search?query=${searchInput}`);
            if (!response.ok) {
               throw new Error('Search failed');
            }
            const result = await response.json();
            console.log(result);
               searchResults = result;
               isSearchReady = true;
         } catch (error) {
            console.error("Error searching blogs:", error);
            hasError = true; // Set error state if an error occurs
         } finally {
            loading = false;
         }
      }
   }
</script>

<style>
  .bg-gradient-background {
    background: linear-gradient(2.73deg, #00C8F4 -55.63%, #24D0F5 20.89%, #D6B7FF 104.25%, #FFFFFF 161.36%);
  }
  .text-gradient {
    background: linear-gradient(179.1deg, #132442 0.77%, #0155BD 125%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    color: transparent;
  }
</style>

<div class="relative bg-gradient-to-b from-[#ECF1F8] to-[#D1F1FB] flex flex-col items-center gap-[2.5rem] md:pb-12">
  <!-- Gradient background using pseudo-element -->
  <div class="absolute inset-0 z-[-1] bg-gradient-background opacity-30"></div>

  <div class="absolute top-20 left-20 md:flex hidden gap-4">
    <h1 class="text-[1.25rem] font-[400] z-30">{homeBreadCrum}</h1>
    <p class="my-auto"><RightArrow /></p>
    <h1 class="text-[1.25rem] font-[400] z-30 text-[#0155BD]">{faqBreadCrum}</h1>
  </div>

  {#if searchInput !=='' && isSearchReady===true}
   

   <div class=" grid grid-cols-4 pl-20 pr-12 gap-8">
   <div class="md:mt-[12rem] col-span-1  md:w-full mt-[8rem] max-w-[65rem]  ">
    <h1 class="md:text-[2rem]  sm:text-[1.5rem]  sm:mx-0 sm:w-full font-bold text-gradient">
       {questions}
    </h1>
  </div>

  <div class=" md:mt-[12rem] col-span-2 relative max-w-[35rem] w-full  bg-opacity-[35%] bg-transparent  rounded-[0.6rem] flex items-center justify-center gap-[0.5rem] ">
    <input  bind:value={searchInput}
      type="text"
      placeholder="Search Your Query"
      class=" w-11/12 p-4 rounded-tl-[0.5rem] rounded-bl-[0.5rem] rounded-br-[0rem] rounded-tr-[0rem] border border-gray-300"
      on:keydown={(e) => e.key === 'Enter' && searchBlogs()}
    >
    <!-- <p class="absolute right-[15%] top-1/2 transform -translate-y-1/2">
      <Search />
    </p> -->
  </div>
  <div  class="col-span-1 mt-[12rem] flex justify-center items-center gap-4">
    <p class="text-[12px] font-[400]">Seek Support</p>
    <p class="text-[12px] font-[400]">Seek Support</p>
    <p class="text-[12px] font-[400]">Seek Support</p>

  </div>
  </div>
  {:else}
   <div class="md:mt-[12rem] w-[50vw] md:w-full mt-[8rem] max-w-[65rem] flex flex-col gap-[10px] text-center">
    <h1 class="md:text-[3.5rem] sm:text-[2.25rem] w-2/3 mx-auto sm:mx-0 sm:w-full font-bold text-gradient">
       {questions}
    </h1>
    <p class="text-[1.25rem] text-[#24272A] font-medium hidden md:block">
      {questionDes}
    </p>
  </div>

  <div class="border-2 relative max-w-[35rem] w-full  bg-opacity-[35%] bg-[#0155BD] p-8 rounded-[0.6rem] flex items-center justify-center gap-[1.25rem] ">
    <input bind:value={searchInput}
      type="text"
      placeholder="Search Your Query"
      class=" w-11/12 p-4 rounded-tl-[0.5rem] rounded-bl-[0.5rem] rounded-br-[0rem] rounded-tr-[0rem] border border-gray-300"
      on:keydown={(e) => e.key === 'Enter' && searchBlogs()}
    >
    <p on:click={searchBlogs} class="absolute right-[15%] top-1/2 transform -translate-y-1/2">
      <Search />
    </p>
  </div> 
  {/if}
   

</div>

<div class="w-[80%] mx-auto py-16">
 {#if loading}
  <div class="loader-container">
    <Loader />
  </div>
{:else if hasError || (searchResults.length === 0 && isSearchReady)}
  <p class="text-center mb-16 text-[1.25rem] text-[#24272A]">
    No results found for "{searchInput}"
  </p>
{:else if searchInput !== '' && isSearchReady === true}
  <p class="text-center mb-16 text-[1.25rem] text-[#24272A]">
    {searchResult}“ {searchInput} ”
  </p>
  <!-- Display search results here -->
  {#each searchResults as item}
    <div on:click={() => handleClick(item)} class="min-h-[5.5rem] rounded-4 text-[1.25rem] border-2 border-[#FFBA41] md:border-spacing-0 md:bg-[white] flex flex-col justify-center px-4 md:px-0 cursor-pointer">
      <p class="text-[#24272A] font-[1.25rem] text-center">{item}</p>
    </div>
  {/each}
{:else}
  <div class="flex flex-col gap-4 mb-16 text-center">
    <h1 class="text-[#0D2561] font-[600] text-[2.5rem]">
      {dontKnow} <span class="text-[#0155BD]">{whatToAsk}</span>
    </h1>
    <p>{secondaryDes}</p>
  </div>
{/if}

  <!-- Mobile grid (md:hidden) -->
  <div class="grid md:hidden grid-cols-1 gap-[1.25rem]">
    {#if searchInput === ''}
      {#each data as item}
        <div on:click={() => handleClick(item)} class="min-h-[5.5rem] rounded-4 text-[1.25rem] border-2 border-[#D3F2FC] md:border-spacing-0 md:bg-[#D3F2FC] flex flex-col justify-center px-4 md:px-0 cursor-pointer">
          <p class="text-[#24272A] font-[1.25rem] text-center">{item}</p>
        </div>
      {/each}
    {:else}
      {#each answerArr as item}
        <div on:click={() => handleClick(item)} class="min-h-[5.5rem] rounded-4 text-[1.25rem] border-2 border-[#FFBA41] md:border-spacing-0 md:bg-[#D3F2FC] flex flex-col justify-center px-4 md:px-0 cursor-pointer">
          <p class="text-[#24272A] font-[1.25rem] text-center">{item}</p>
        </div>
      {/each}
    {/if}
  </div>

  <!-- Desktop grid (md:grid) -->
  <div class="md:grid hidden gap-[1.25rem]"
     style="grid-template-columns: repeat({searchInput === ''|| isSearchReady===false ? 3 : 2}, minmax(0, 1fr));">
    {#if searchInput ==='' || isSearchReady===false}
      {#each arr as item}
        <div on:click={() => handleClick(item)} class="min-h-[5.5rem] rounded-4 text-[1.25rem] border-2 border-[#D3F2FC] md:border-spacing-0 md:bg-[#D3F2FC] flex flex-col justify-center px-4 md:px-0 cursor-pointer">
          <p class="text-[#24272A] font-[1.25rem] text-center">{item}</p>
        </div>
      {/each}
    {:else}
      {#each searchResults as item}
        <div on:click={() => handleClick(item)} class="min-h-[5.5rem] rounded-4 text-[1.25rem] border-2 border-[#FFBA41] md:border-spacing-0 md:bg-[white] flex flex-col justify-center px-4 md:px-0 cursor-pointer">
          <p class="text-[#24272A] font-[1.25rem] text-center">{item}</p>
        </div>
      {/each}
    {/if}
  </div>

  <!-- Pagination controls visible only below md -->
  <div class="flex justify-center gap-4 mt-8 md:hidden">
    <button 
      on:click={goToPreviousPage}
      class="px-4 py-2 border bg-[#CFD6DF] rounded h-8 w-8 flex justify-center items-center"
      disabled={currentPage === 1}
    >
      &lt;
    </button>
    
    <button 
      on:click={goToNextPage}
      class="px-4 py-2 border bg-[#CFD6DF] rounded h-8 w-8 flex justify-center items-center"
      disabled={currentPage === totalPages}
    >
      &gt;
    </button>
  </div>
</div>

    