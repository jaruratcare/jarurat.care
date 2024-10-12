 <script>
   export let handleNavigate
   export  let item
   export let convertDate

 </script>
 <style>
  .shadow-grad{
    box-shadow: 0px 0px 10px 2px #1324421F;
    
  }
</style>
 <div class="shadow-grad rounded-xl grid grid-rows-auto">
    <img class="rounded-tl-xl rounded-tr-xl h-48 object-cover" src={item.images[0]} alt="">
    <div class="px-4 py-2">
      <h1 class="text-[#0155BD]">News {convertDate(item.date)}</h1>
      <p class="text-[#0D2561] text-[1rem]">{item.title}Jarurat Care Launches New Support Program for Cancer Patients</p>
      <p class="text-[0.75rem] text-[#576171]">
        {item.description}Jarurat Care is excited to announce the launch of its new comprehensive support program for cancer patients
      </p>
      <button  on:click={()=>handleNavigate(item._id)} class="w-32 mt-8 h-10 rounded-[1.5rem] text-[#0155BD] border-[#0155BD] bg-[#D2F2FC]">Read More</button>
    </div>
  </div>
    
   