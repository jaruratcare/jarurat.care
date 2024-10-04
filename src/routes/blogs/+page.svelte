<script>
   import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
   import '@splidejs/svelte-splide/css';
	import Nav from "$lib/components/nav.svelte";
	import RightArrow from "$lib/svg/right-arrow.svelte";
	import Search from "$lib/svg/search.svelte";
	import { ChevronLeft, ChevronRight, QuoteIcon } from 'lucide-svelte';
	import Button from '$lib/components/ui/button.svelte';
  import image1 from "./image.png"
  import image2 from "./image2.png"
  import image3 from "./image3.png"
	import FeaturesBlog from '$lib/components/blogs/features-blog.svelte';
	import { goto } from '$app/navigation';
	import NewNewsLetter from '$lib/components/ui/new-news-letter.svelte';
  function handleNavigate(id){
    goto(`/blogs/${id}`)
  }
  function goToPage(page) {
    currentPage = page;
    // Add your page navigation logic here
  }
     let searchResult="Search Results for "
    let homeBreadCrum="Home"
    let cancerBreadCrum="Blogs"
    let title1="Jarurat care "
    let title2="Blogs"
    let title3="What would you like to explore today?"
    let subDes="All of the information has been summarized by Jururat Care from PubMed so that it is easy to use. Further links to the full article are provided."
    let advice ="Advicory Board"
    let support="Seek Support"
    let help ="Help"
   


  $: searchInput = "";
  $: isSearchReady=false

  const newsArticles = [
  {
    date: "July 12, 2024",
    title: "Jarurat Care Launches New Support Program for Cancer Patients",
    linkText: "Read More"
  },
  {
    date: "July 12, 2024",
    title: "Jarurat Care Launches New Support Program for Cancer Patients",
    linkText: "Read More"
  },{
    date: "July 12, 2024",
    title: "Jarurat Care Launches New Support Program for Cancer Patients",
    linkText: "Read More"
  },{
    date: "July 12, 2024",
    title: "Jarurat Care Launches New Support Program for Cancer Patients",
    linkText: "Read More"
  }
];
  let currentPage = 1;
  const itemsPerPage = 3;
function paginatedItems(page, array) {

    const startIndex = (page - 1) * itemsPerPage;

    const endIndex = startIndex + itemsPerPage;

    
    return array.slice(startIndex, endIndex);
  }
 $: answerArr = paginatedItems(currentPage, newsArticles);

const buttonArray = Array.from({length:Math.ceil(newsArticles.length/itemsPerPage)})
function handleSearch(){
    isSearchReady=true
  }
    function handleClick(val) {
    goto(`/faq/${val}`);
  }
   $: if (searchInput === "") {
    isSearchReady = false;
  }
</script>
<style>
  .shadow-grad{
    box-shadow: 0px 0px 10px 2px #1324421F;
    
  }
</style>

<Nav />
<div class="relative bg-[#0D2561] flex flex-col items-center gap-[2.5rem] md:pb-12">
  <!-- Gradient background using pseudo-element -->


  <div class="absolute text-white top-20 left-20 md:flex hidden gap-4">
    <h1 class="text-[1rem] font-[400] z-30">{homeBreadCrum}</h1>
    <p class="my-auto items-center text-white "><RightArrow /></p>
    <h1 class="text-[1rem] font-[400] z-30 text-white">{cancerBreadCrum}</h1>
     {#if searchInput !== "" && isSearchReady}
  <div class="flex items-center">
    <p class="my-auto items-center text-white">
      <RightArrow />
    </p>
    <h1 class="text-[1rem] font-[400] z-30 text-[#FFBA41]">{searchInput}</h1>
  </div>
{/if}
  </div>

  {#if searchInput !=='' && isSearchReady}
   

   <div class=" hidden lg:grid grid-cols-4 pl-20 pr-12 gap-8">
   <div class="md:mt-[12rem] col-span-1  md:w-full mt-[8rem] max-w-[65rem]  ">
    <h1 class="md:text-[2rem]  sm:text-[1.5rem]  sm:mx-0 sm:w-full font-bold text-[white]">
      {title3}
    </h1>
    
  </div>

  <div class=" md:mt-[12rem] col-span-2 relative max-w-[35rem] w-full  bg-opacity-[35%] bg-transparent  rounded-[0.6rem] flex items-center justify-center gap-[0.5rem] ">
    <input 
    bind:value={searchInput}
     on:keydown={(e) => e.key === 'Enter' && handleSearch()}
      type="text"
      placeholder="Search Your Query"
      class=" w-11/12 p-4 rounded-tl-[0.5rem] rounded-bl-[0.5rem] rounded-br-[0rem] rounded-tr-[0rem] border border-gray-300"
    >
    <p on:click={handleSearch} class="absolute right-[15%] top-1/2 transform -translate-y-1/2">
      <Search />
    </p>
  </div>
  <div  class="col-span-1 mt-[12rem] flex justify-center items-center gap-4">
    <p class="text-[12px] font-[700] text-white">{support}</p>
    <p class="text-[12px] font-[700] text-white">{advice}</p>
    <p class="text-[12px] font-[700] text-white">{help}</p>

  </div>
  </div>
  <div class="md:mt-[12rem] w-[50vw] md:w-full mt-[8rem] max-w-[65rem] flex lg:hidden flex-col gap-[10px] text-center items-center">
    <h1 class="md:text-[3.5rem] sm:text-[2.25rem] text-[1.5rem]  md:w-2/3 mx-auto sm:mx-0 sm:w-full font-bold text-[#FFBA41]">
       {title1} <span class="text-white">{title2}</span>
    </h1>
    <p class="text-white" >Stay updated with the latest news, stories, and insights from Jarurat Care.</p>
  </div>

  <div class=" lg:hidden border-white relative max-w-[35rem] w-full  bg-[#0155BD] bg-opacity-[35%] p-8 rounded-[0.6rem] flex items-center justify-center gap-[1.25rem] ">
    <input bind:value={searchInput}
     on:keydown={(e) => e.key === 'Enter' && handleSearch()}
      type="text"
      placeholder="Search Your Query"
      class=" w-11/12 p-4 rounded-tl-[0.5rem] rounded-bl-[0.5rem] rounded-br-[0rem] rounded-tr-[0rem] border border-gray-300"
    >
    <p on:click={handleSearch} class="absolute right-[15%] top-1/2 transform -translate-y-1/2">
      <Search />
    </p>
  </div> 
  {:else}
   <div class="md:mt-[12rem] w-[70vw] md:w-full mt-[8rem] max-w-[65rem] flex flex-col gap-[10px] text-center items-center">
    <h1 class="md:text-[3.5rem] sm:text-[2.25rem] text-[1.5rem]  md:w-2/3 mx-auto sm:mx-0 sm:w-full font-bold text-[#FFBA41]">
       {title1}<span class="text-white">{title2}</span>
    </h1>
    <p class="text-white" >Stay updated with the latest news, stories, and insights from Jarurat Care.</p>
  </div>

  <div class="  border-white relative max-w-[35rem] w-full  bg-[#0155BD] bg-opacity-[35%] p-8 rounded-[0.6rem] flex items-center justify-center gap-[1.25rem] ">
    <input 
        bind:value={searchInput}
         on:keydown={(e) => e.key === 'Enter' && handleSearch()}
      type="text"
      placeholder="Search Your Query"
      class=" w-11/12 p-4 rounded-tl-[0.5rem] rounded-bl-[0.5rem] rounded-br-[0rem] rounded-tr-[0rem] border border-gray-300"
    >
    <p on:click={handleSearch} class="absolute right-[15%] top-1/2 transform -translate-y-1/2">
      <Search />
    </p>
  </div> 

  {/if}
</div>

 {#if searchInput !=='' && isSearchReady }
   <p class=" my-8 text-center mb-16 text-[1.25rem] text-[#24272A]">{searchResult}“ {searchInput} ”</p>
{/if}
{#if searchInput==="" || !isSearchReady }
  <div class="py-8 w-[80vw] hidden md:block  my-12 mx-auto bg-white">
  <Splide
		hasTrack={false}
		aria-label="..."
		class="max-w-[70rem] mx-auto mt-8 z-10"
		options={{ perPage: 1 , type: 'loop' }}
	>
		<div class="custom-wrapper">
			<SplideTrack>
			
					<SplideSlide class="p-1 pl-2">
						<div class="  rounded-2xl grid grid-cols-2 w-[80%] mx-auto gap-4">
                    <div class=" shadow-grad rounded-xl  grid grid-rows-2">
                      <img class=" rounded-tl-xl h-full  rounded-tr-xl " src={image1} alt="">
                      <div class="px-4 py-2">
                        <h1 class="text-[#0155BD]">News July 12, 2024</h1>
                        <p class="text-[#0D2561]">Jarurat Care Launches New Support Program for Cancer Patients</p>
                        <p class="text-[0.75rem] text-[#576171]">Jarurat Care is excited to announce the launch of its new comprehensive support program for cancer patients. This initiative aims to provide enhanced financial aid, counseling services, and community support for patients</p>
                        <button on:click={()=>handleNavigate(1)}  class="w-32 mt-8 h-10  rounded-[1.5rem] text-[#0155BD]  border-[#0155BD] bg-[#D2F2FC]">Read More</button>
                      </div>
                    </div>
                     <div class=" grid grid-rows-2 gap-4 " >
                        <div class=" shadow-grad rounded-xl   grid grid-cols-5">
                            <img class="  h-full rounded-tl-xl col-span-2 rounded-bl-xl " src={image2} alt="">
                            <div class="col-span-3 px-4 py-2">
                                <h1 class="text-[#0155BD]">News July 12, 2024</h1>
                                 <p class="mt-2">How Jarurat Care Helped Ravi Overcome His Cancer Battle</p>
                                 <button  on:click={()=>handleNavigate(1)}  class="w-32 mt-12 h-10   rounded-[1.5rem] text-[#0155BD]  border-[#0155BD] bg-[#D2F2FC]">Read More</button>
                            </div>
                        </div>
                        <div class=" shadow-grad rounded-xl   grid grid-cols-5">
                            <img class=" h-full rounded-tl-xl col-span-2 rounded-bl-xl " src={image3} alt="">
                              <div class="col-span-3 px-4 py-2">
                                <h1 class="text-[#0155BD]">News July 12, 2024</h1>
                                 <p class="mt-2">How Jarurat Care Helped Ravi Overcome His Cancer Battle</p>
                                 <button  on:click={()=>handleNavigate(1)}  class="w-32 mt-12 h-10   rounded-[1.5rem] text-[#0155BD]  border-[#0155BD] bg-[#D2F2FC]">Read More</button>
                            </div>
                        </div>
                     </div>
              </div>
					</SplideSlide>

			</SplideTrack>
		</div>

		<div class="splide__arrows flex items-center justify-center gap-2 sm:mt-4">
			<Button class="splide__arrow splide__arrow--prev size-8 p-0 items-center">
				<ChevronLeft class="w-full" />
			</Button>
			<Button class="splide__arrow splide__arrow--next size-8 p-0 flex items-center">
				<ChevronRight class="w-full" />
			</Button>
		</div>
	</Splide>
</div>
{:else}
<div class="mx-auto gap-4 grid grid-cols-3 my-4 w-[80%]">
  <div class="shadow-grad rounded-xl grid grid-rows-auto">
    <img class="rounded-tl-xl rounded-tr-xl h-48 object-cover" src={image1} alt="">
    <div class="px-4 py-2">
      <h1 class="text-[#0155BD]">News July 12, 2024</h1>
      <p class="text-[#0D2561]">Jarurat Care Launches New Support Program for Cancer Patients</p>
      <p class="text-[0.75rem] text-[#576171]">
        Jarurat Care is excited to announce the launch of its new comprehensive support program for cancer patients. This initiative aims to provide enhanced financial aid, counseling services, and community support for patients.
      </p>
      <button  on:click={()=>handleNavigate(1)} class="w-32 mt-8 h-10 rounded-[1.5rem] text-[#0155BD] border-[#0155BD] bg-[#D2F2FC]">Read More</button>
    </div>
  </div>
    <div class="shadow-grad rounded-xl grid grid-rows-auto">
    <img class="rounded-tl-xl w-full rounded-tr-xl h-48 object-cover" src={image2} alt="">
    <div class="px-4 py-2">
      <h1 class="text-[#0155BD]">News July 12, 2024</h1>
      <p class="text-[#0D2561]">Jarurat Care Launches New Support Program for Cancer Patients</p>
      <p class="text-[0.75rem] text-[#576171]">
        Jarurat Care is excited to announce the launch of its new comprehensive support program for cancer patients. This initiative aims to provide enhanced financial aid, counseling services, and community support for patients.
      </p>
      <button  on:click={()=>handleNavigate(1)} class="w-32 mt-8 h-10 rounded-[1.5rem] text-[#0155BD] border-[#0155BD] bg-[#D2F2FC]">Read More</button>
    </div>
  </div>
    <div class="shadow-grad rounded-xl grid grid-rows-auto">
    <img class="rounded-tl-xl w-full rounded-tr-xl h-48 object-cover" src={image3} alt="">
    <div class="px-4 py-2">
      <h1 class="text-[#0155BD]">News July 12, 2024</h1>
      <p class="text-[#0D2561]">Jarurat Care Launches New Support Program for Cancer Patients</p>
      <p class="text-[0.75rem] text-[#576171]">
        Jarurat Care is excited to announce the launch of its new comprehensive support program for cancer patients. This initiative aims to provide enhanced financial aid, counseling services, and community support for patients.
      </p>
      <button  on:click={()=>handleNavigate(1)} class="w-32 mt-8 h-10 rounded-[1.5rem] text-[#0155BD] border-[#0155BD] bg-[#D2F2FC]">Read More</button>
    </div>
  </div>
</div>

{/if}
{#if  searchInput==="" || !isSearchReady }
   {#each newsArticles as item ,i }
  <div class=" mt-12 shadow-grad rounded-xl md:hidden sm:w-2/3 w-[80%] mx-auto  grid grid-rows-2">
                      <img class=" rounded-tl-xl h-full w-full  rounded-tr-xl " src={image1} alt="">
                      <div class="px-4 py-2">
                        <h1 class="text-[#0155BD]">{item.date}</h1>
                        <p class="text-[#0D2561]">{item.title}</p>
                        <p class="text-[0.75rem] text-[#576171]">Jarurat Care is excited to announce the launch of its new comprehensive support program for cancer patients. This initiative aims to provide enhanced financial aid, counseling services, and community support for patients</p>
                        <button on:click={()=>handleNavigate(i)}  class="w-32 mt-8 h-10  rounded-[1.5rem] text-[#0155BD]  border-[#0155BD] bg-[#D2F2FC]">Read More</button>
                      </div>
 </div>
 {/each}
 {:else}
 {#each answerArr as item ,i }
  <div class=" mt-12 shadow-grad rounded-xl md:hidden sm:w-2/3 w-[80%] mx-auto  grid grid-rows-2">
                      <img class=" rounded-tl-xl h-full w-full  rounded-tr-xl " src={image1} alt="">
                      <div class="px-4 py-2">
                        <h1 class="text-[#0155BD]">{item.date}</h1>
                        <p class="text-[#0D2561]">{item.title}</p>
                        <button on:click={()=>handleNavigate(i)}  class="w-32 mt-8 h-10  rounded-[1.5rem] text-[#0155BD]  border-[#0155BD] bg-[#D2F2FC]">Read More</button>
                      </div>
 </div>
 
 {/each}
  <div class="flex md:hidden justify-center  gap-4 py-8">
 {#each buttonArray as item, i}
  <button 

    on:click={() => goToPage(i + 1)} 
    disabled={currentPage === (i + 1)} 
    class="px-4 py-2 border bg-[#CFD6DF] disabled:bg-black disabled:text-white rounded h-8 w-8 flex justify-center items-center">
    {i + 1}
  </button>
{/each}
  </div>
{/if}
<FeaturesBlog searchInput={searchInput} isSearchReady={isSearchReady} />
<NewNewsLetter />