<script>
	import TiltedArrow from "$lib/svg/tilted-arrow.svelte";

   export let searchInput ;
    export let isSearchReady;
    let text1="Explore Our"
  let text2="Insights"
  let secondaryDes ="Dive into a wealth of knowledge and stories curated to inform, inspire, and support you on your journey."
  let searchResult ="Search Results for "
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

  
const buttonArray = Array.from({length:Math.round(arr.length/itemsPerPage)})
  
  function handleSearch(){
    isSearchReady=true
  }
  $: data = paginatedItems(currentPage, arr);
  $: totalPages = Math.ceil(arr.length / itemsPerPage);
  $: answerArr = paginatedItems(currentPage, answers);

  // Pagination variables
  let currentPage = 1;
  const itemsPerPage = 6;

  // Function to get the items for the current page
  function paginatedItems(page, array) {
    const startIndex = (page - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    return array.slice(startIndex, endIndex);
  }
  function goToPage(page) {
    currentPage = page;
    // Add your page navigation logic here
  }
</script>
<style>
  .shadow-grad{
    box-shadow: 0px 0px 10px 2px #1324421F;
    
  }
</style>
<!-- new comp -->
<!-- Mobile grid (md:hidden) -->
  
  <!-- {#if searchInput !=='' && isSearchReady===true}
   <p class=" text-center mb-16 text-[1.25rem] text-[#24272A]">{searchResult}“ {searchInput} ”</p>
{:else} -->
  
   <!-- <div class="flex flex-col gap-4 mb-16 text-center">
    <h1 class="text-[#0D2561] font-[600] text-[2.5rem]">{text1} <span class="text-[#0155BD]">{text2}</span></h1>
    <p>{secondaryDes}</p>
  </div> -->
<!-- {/if} -->
   <div class=" mx-auto py-16">
 <div class="flex flex-col gap-4  text-center">
    <h1 class="text-[#0D2561] font-[600] text-[1.5rem]">{text1} <span class="text-[#0155BD]">{text2}</span></h1>
    <p class="text-[1rem] mb-4 sm:w-full w-[60%] mx-auto">{secondaryDes}</p>
  </div>
  <div class="grid md:hidden grid-cols-1 gap-[1.25rem] px-4">

      {#each data as item}
        <div  class=" p-4 shadow-grad rounded-4 text-[1rem] border-2 border-[#D3F2FC] md:border-spacing-0 md:bg-[#D3F2FC] flex items-center justify-between md:px-0 cursor-pointer">
          <p class="text-[#24272A] font-[1.25rem] ">{item}</p>
          <p class="text-[#0155BD] font-[800]"><TiltedArrow /></p>
        </div>
      {/each}
    

    <div class="flex justify-center  gap-4 py-8">
 {#each buttonArray as item, i}
  <button 

    on:click={() => goToPage(i + 1)} 
    disabled={currentPage === (i + 1)} 
    class="px-4 py-2 border bg-[#CFD6DF] disabled:bg-black disabled:text-white rounded h-8 w-8 flex justify-center items-center">
    {i + 1}
  </button>
{/each}
  </div>
  </div>
  <!-- Desktop grid (md:grid) -->
   <div class="w-[80%] mx-auto py-16">
  <div class="md:grid hidden gap-[1.25rem]  lg:grid-cols-3 md:grid-cols-2">

      {#each arr as item}
        <div  class="min-h-[5.5rem] rounded-4 text-[1rem] border-2 border-[#D3F2FC] md:border-spacing-0 md:bg-[#D3F2FC] flex flex-col justify-center px-4 md:px-0 cursor-pointer">
          <p class="text-[#24272A] font-[1.25rem] text-center">{item}</p>
        </div>
      {/each}
    
  
  </div>
   
</div>
</div>
