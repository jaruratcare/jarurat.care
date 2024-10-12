 <script>
    export let articleData
    export let convertDate
    export let handleNavigate
 </script>
 {#each articleData as item}
      <div class="mt-12 shadow-grad rounded-xl md:hidden sm:w-2/3 w-[90%] mx-auto grid grid-rows-[auto,1fr]">
         <img class="rounded-tl-xl h-full w-full rounded-tr-xl" src={item.images[0]} alt="">
         <div class="px-4 py-2">
            <h1 class="text-[#0155BD]">News {convertDate(item.date)}</h1>
            <p class="text-[#0D2561]">{item.title}</p>
            <button on:click={() => handleNavigate(item._id)} class="w-32 mt-8 h-10 rounded-[1.5rem] text-[#0155BD] border-[#0155BD] bg-[#D2F2FC]">
               Read More
            </button>
         </div>
      </div>
   {/each}