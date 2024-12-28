 <script>
   import Nav from "$lib/components/nav.svelte";
	import NewsFooter from "$lib/components/news-footer.svelte";
	import Search from "$lib/svg/search.svelte";
	import Research from "$lib/components/cancer-research/research.svelte";
	import Abstract from "$lib/components/cancer-research/abstract.svelte";

   let advice ="Advicory Board"
  let support="Seek Support"
  let help ="Help"


  




    let title="Discover The latest In Cancer Science"
    $: searchInput = "";
   

 </script>
 <style>
   .bg-grad{
     background: linear-gradient(180deg, #ECF1F8 0%, #D1F1FB 100%);
  }

.text-gradient {
    background: linear-gradient(179.1deg, #132442 0.77%, #0155BD 125%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    color: transparent;
  }
 </style>

 <Nav />
  <div class="relative bg-grad hidden md:flex flex-col items-center gap-[2.5rem] md:pb-12">
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
    <p class="text-[12px] font-[700] text-[#24272A]">{support}</p>
    <p class="text-[12px] font-[700] text-[#24272A]">{advice}</p>
    <p class="text-[12px] font-[700] text-[#24272A]">{help}</p>

  </div>
  </div>
  </div>
<div class="relative bg-grad  md:hidden flex flex-col items-center gap-[2.5rem] md:pb-12">
    <div class="md:mt-[12rem] w-[50vw] md:w-full mt-[8rem] max-w-[65rem] flex flex-col gap-[10px] text-center items-center">
    <h1 class="md:text-[3.5rem] sm:text-[2.25rem] text-[1.5rem]  md:w-2/3 mx-auto sm:mx-0 sm:w-full font-bold text-gradient">
       {title}
    </h1>
  </div>

  <div class="  border-white relative max-w-[35rem] w-full  bg-[rgb(222,242,272)] p-8 rounded-[0.6rem] flex items-center justify-center gap-[1.25rem] ">
    <input 
      type="text"
      placeholder="Search Your Query"
      class=" w-11/12 p-4 rounded-tl-[0.5rem] rounded-bl-[0.5rem] rounded-br-[0rem] rounded-tr-[0rem] border border-gray-300"
    >
    <p  class="absolute right-[15%] top-1/2 transform -translate-y-1/2">

    </p>
  </div> 
</div>
 <div class="bg-[#F8FCFF] ">
  <Abstract />
  <Research />
  <NewsFooter />
    </div>
