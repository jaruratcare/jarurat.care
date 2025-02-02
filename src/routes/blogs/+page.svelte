<script>
   import { goto } from '$app/navigation';
   import Crousel from '$lib/components/blogs/crousel.svelte';
   import FeaturesBlog from '$lib/components/blogs/features-blog.svelte';
   import MobileSearchGrid from '$lib/components/blogs/mobile-search-grid.svelte';
   import NormalMobileGrid from '$lib/components/blogs/normal-mobile-grid.svelte';
   import SearchCard from '$lib/components/blogs/search-card.svelte';
   import Loader from '$lib/components/loader.svelte';
   import Nav from "$lib/components/nav.svelte";
   import NewNewsLetter from '$lib/components/ui/new-news-letter.svelte';
   import RightArrow from "$lib/svg/right-arrow.svelte";
   import Search from "$lib/svg/search.svelte";
   import '@splidejs/svelte-splide/css';
	import { onMount } from 'svelte';
   export let data;

   let articleData = data?.item?.blogs || [];
   let searchInput = "";
   let searchResults = [];
   let isSearchReady = false;
   let loading = false;
   let currentPage = 1;
   const itemsPerPage = 3;
   
   let hasError = false; // Track if an error has occurred
  $: loadingData = data ? data.loading : true;


  

   $: if (searchInput === "") {
      searchResults = [];
      isSearchReady = false;
      hasError = false; // Reset error state when search input is cleared
   }

   function handleNavigate(id) {
      goto(`/blogs/${id}`);
   }

   async function searchBlogs() {
      if (searchInput.trim() !== "") {
         loading = true;
         hasError = false; // Reset error state before starting the search
         try {
            const response = await fetch(`http://localhost:5000/jc/blogs/searchBlogs/?query=${searchInput}`);
            if (!response.ok) {
               throw new Error('Search failed');
            }
            const result = await response.json();
            if (result.success) {
               searchResults = result.blogs;
               isSearchReady = true;
            } else {
               searchResults = [];
               isSearchReady = true;
            }
         } catch (error) {
            console.error("Error searching blogs:", error);
            hasError = true; // Set error state if an error occurs
         } finally {
            loading = false;
         }
      }
   }

   function convertDate(isoDate) {
      const date = new Date(isoDate);
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Intl.DateTimeFormat('en-US', options).format(date);
   }

   function paginatedItems(page, array) {
      const startIndex = (page - 1) * itemsPerPage;
      const endIndex = startIndex + itemsPerPage;
      return array.slice(startIndex, endIndex);
   }

   function groupInThrees(arr) {
      const grouped = [];
      for (let i = 0; i < arr.length; i += 3) {
         grouped.push(arr.slice(i, i + 3));
      }
      return grouped;
   }

   $: groupedItems = groupInThrees(articleData);
   $: paginatedData = paginatedItems(currentPage, searchResults.length > 0 ? searchResults : articleData);
</script>

<style>
   .shadow-grad {
      box-shadow: 0px 0px 10px 2px #1324421F;
   }
   .no-results {
      text-align: center;
      font-size: 1.5rem;
      margin-top: 2rem;
      color: #FF0000;
   }
   .loader-container {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 70vh;
   }
</style>

<Nav />

<!-- Initial Loader while data is being fetched -->


   <div class="relative bg-[#0D2561] flex flex-col items-center gap-[2.5rem] md:pb-12">
      <div class="absolute text-white top-20 left-20 md:flex hidden gap-4">
         <h1 class="text-[1rem] font-[400] z-30">Home</h1>
         <p class="my-auto items-center text-white"><RightArrow /></p>
         <h1 class="text-[1rem] font-[400] z-30 text-white">Blogs</h1>
         {#if searchInput && isSearchReady}
            <div class="flex items-center">
               <p class="my-auto items-center text-white"><RightArrow /></p>
               <h1 class="text-[1rem] font-[400] z-30 text-[#FFBA41]">{searchInput}</h1>
            </div>
         {/if}
      </div>

      <div class="md:mt-[12rem] w-[70vw] md:w-full mt-[8rem] max-w-[65rem] flex flex-col gap-[10px] text-center items-center">
         <h1 class="md:text-[3.5rem] sm:text-[2.25rem] text-[1.5rem] md:w-2/3 mx-auto sm:mx-0 sm:w-full font-bold text-[#FFBA41]">
            Jarurat Care <span class="text-white">Blogs</span>
         </h1>
         <p class="text-white">Stay updated with the latest news, stories, and insights from Jarurat Care.</p>
      </div>

      <div class="border-white relative max-w-[35rem] w-full bg-[#0155BD] bg-opacity-[35%] p-8 rounded-[0.6rem] flex items-center justify-center gap-[1.25rem]">
         <input 
            bind:value={searchInput}
            on:keydown={(e) => e.key === 'Enter' && searchBlogs()}
            type="text" 
            placeholder="Search Your Query"
            class="w-11/12 p-4 rounded-tl-[0.5rem] rounded-bl-[0.5rem] border border-gray-300"
         />
         <p class="absolute right-[15%] top-1/2 transform -translate-y-1/2 cursor-pointer" on:click={searchBlogs}>
            <Search />
         </p>
      </div>
   </div>

   <!-- If there's an error, display the error message and stop further rendering -->
   {#if hasError}
      <p class="no-results">No results found for "{searchInput}"</p>
   {:else if isSearchReady && searchInput !== ""}
      {#if loading}
         <div class="loader-container">
            <Loader />
         </div>
      {:else if searchResults.length === 0}
         <p class="no-results">No results found for "{searchInput}"</p>
      {:else}
         <p class="my-8 text-center mb-16 text-[1.25rem] text-[#24272A]">Search Results for “{searchInput}”</p>
         <div class="mx-auto gap-4 md:grid hidden lg:grid-cols-3 grid-cols-2 my-4 w-[80%]">
            {#each searchResults as article}
               <SearchCard convertDate={convertDate} handleNavigate={handleNavigate} item={article} />
            {/each}
         </div>
      {/if}
   {:else}
      {#if loading || loadingData}
         <div class="loader-container">
            <Loader />
         </div>
      {:else}
         {#each groupedItems as group}
            <Crousel item1={group[0]} item2={group[1]} item3={group[2]} />
         {/each}
      {/if}
   {/if}

   {#if isSearchReady && searchInput !== ""}
      <MobileSearchGrid paginatedData={paginatedData} convertDate={convertDate} handleNavigate={handleNavigate} />
   {:else}
      <NormalMobileGrid articleData={articleData} convertDate={convertDate} handleNavigate={handleNavigate} />
   {/if}

   <FeaturesBlog searchInput={searchInput} isSearchReady={isSearchReady} />
   <NewNewsLetter />

