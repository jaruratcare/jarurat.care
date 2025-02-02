 <script>
   import Nav from "$lib/components/nav.svelte";
	import NewsFooter from "$lib/components/news-footer.svelte";
	import Search from "$lib/svg/search.svelte";
	import Research from "$lib/components/cancer-research/research.svelte";
	import Abstract from "$lib/components/cancer-research/abstract.svelte";
	import BlogFullView from "$lib/components/blogs/blog-full-view.svelte";
	import RightArrow from "$lib/svg/right-arrow.svelte";
import { page } from '$app/stores';
   let advice ="Advicory Board"
  let support="Seek Support"
  let help ="Help"
  const pageName=$page.params.id
  export let data
   let Blogdata=data.otherData.blog




    let title="Discover The latest In Cancer Science"
    $: searchInput = "";
   

 </script>
 <style>
   .bg-grad{
     background-color: #0D2460;
  }
   .border-grad {
  border: 1px solid; /* Set a solid border to apply the gradient on */
  border-image-source: linear-gradient(2.73deg, #00C8F4 -55.63%, #24D0F5 20.89%, #D6B7FF 104.25%, #FFFFFF 161.36%);
  border-image-slice: 1; /* Ensures the gradient covers the entire border */
  border-radius: 1rem; /* Apply rounded corners */
}
.text-gradient {
   
    color: white;
  }
 </style>

 <Nav />
  <div class="relative bg-grad hidden md:flex flex-col items-center gap-[2.5rem] md:pb-12">
     <div class="absolute text-white top-20 left-20 md:flex hidden gap-4">
    <h1 class="text-[1rem] font-[400] z-30">Home</h1>
    <p class="my-auto items-center text-white "><RightArrow /></p>
    <h1 class="text-[1rem] font-[400] z-30 text-white">Blogs</h1>
      <p class="my-auto items-center text-white "><RightArrow /></p>
    <h1 class="text-[1rem] font-[400] z-30 text-[#FFBA41]">{searchInput}</h1>
  </div>
 <div class=" grid grid-cols-4 pl-20 pr-12 gap-8">
   <div class="md:mt-[12rem] col-span-1  md:w-full mt-[8rem] max-w-[65rem]  ">
    <h1 class="md:text-[2rem]  sm:text-[1.5rem]  sm:mx-0 sm:w-full font-bold text-gradient">
       {title}
    </h1>
  </div>

  <div class=" md:mt-[12rem] col-span-2 relative max-w-[35rem] w-full  bg-opacity-[35%] bg-transparent  rounded-[0.6rem] flex items-center justify-center gap-[0.5rem] ">
    <input bind:value={searchInput}
      type="text"
      placeholder="Search Your Query"
      class=" w-11/12 p-4 rounded-tl-[0.5rem] rounded-bl-[0.5rem] rounded-br-[0rem] rounded-tr-[0rem] border border-gray-300"
    >
    <p class="absolute right-[15%] top-1/2 transform -translate-y-1/2">
      <Search />
    </p>
  </div>
  <div  class="col-span-1 mt-[12rem] flex justify-center items-center gap-4">
    <p class="text-[12px] font-[700] text-white">{support}</p>
    <p class="text-[12px] font-[700] text-white">{advice}</p>
    <p class="text-[12px] font-[700] text-white">{help}</p>

  </div>
  </div>
  </div>
<div class="relative bg-grad  md:hidden flex flex-col items-center gap-[2.5rem] md:pb-12">
    <div class="md:mt-[12rem] w-[50vw] md:w-full mt-[8rem] max-w-[65rem] flex flex-col gap-[10px] text-center items-center">
    <h1 class="md:text-[3.5rem] sm:text-[2.25rem] text-[1.5rem]  md:w-2/3 mx-auto sm:mx-0 sm:w-full font-bold text-gradient">
       {title}
    </h1>
  </div>

  <div class="  border-white relative max-w-[35rem] w-full  bg-[#0155BD] p-8 rounded-[0.6rem] flex items-center justify-center gap-[1.25rem] ">
    <input 
      type="text"
      placeholder="Search Your Query"
      class=" w-11/12 p-4 rounded-tl-[0.5rem] rounded-bl-[0.5rem] rounded-br-[0rem] rounded-tr-[0rem] border border-gray-300"
    >
    <p  class="absolute right-[15%] top-1/2 transform -translate-y-1/2">
 <p class="absolute right-[15%] top-1/2 transform -translate-y-1/2">
      <Search />
    </p>
  </div> 
</div>
 <div class="bg-[#F8FCFF] ">
<BlogFullView Blogdata={Blogdata}  currentBlogId={pageName}/>
  <NewsFooter />
    </div>
