<script>
	import Header from "../ui/header.svelte";
   import image1 from "./image.png"
   import banner from "./banner-bg.png"
	import Heart from "$lib/svg/heart.svelte";
	import Share from "$lib/svg/share.svelte";
   let heading="Empowering Cancer Patients with Nutritional Support: A Key to Better Recovery"
   let authorName="Anna Farris"
   let designation="Nutritionist"
   let para1="Cancer treatment is a challenging journey, not just physically but emotionally and mentally as well. At Jarurat Care, we understand the multifaceted needs of our patients, and one crucial aspect that often gets overlooked is nutrition. Proper nutrition plays a vital role in helping patients cope with treatment side effects, boosting their strength, and improving their overall quality of life. In this blog, we’ll explore how nutritional support can empower cancer patients during their treatment journey."
   let para2="Cancer and its treatment can lead to various nutritional challenges, including loss of appetite, weight loss, and changes in taste. These issues can make it difficult for patients to get the necessary nutrients, which are vital for maintaining energy levels, supporting the immune system, and aiding in recovery."
   let para3="At Jarurat Care, we advocate for personalized nutrition plans tailored to each patient’s specific needs. Here are some general strategies that can help:"
   let point="Small, Freque nt Meals: Eating smaller, more frequent meals can help manage appetite changes and make it easier to consume enough calories."
   let Like="Like"
   let share="Share"
   let query="Raise A Query"
</script>
<style>
  .shadow-grad{
    box-shadow: 0px 0px 10px 2px #1324421F;
     
  }
</style>
<div class="sm:w-[80%] w-full  mx-auto">
    <p class=" text-[#0155BD] text-center my-8">{heading}</p>
    <div class="my-12 lg:p-16 p-8 w-full rounded-lg shadow-grad ">
         <p class=" text-[#0155BD]">26/06/2003 <span class="ml-6">Blogs</span></p>
          <h1 class=" lg:w-[80%] md:w-[90%] w-full font-[600] text-[#14171C] md:text-[2rem] text-[1.25rem]" >{heading}</h1>
          <div class="  my-6 flex gap-[1rem]">
                  <img class=" h-[3rem] w-[3rem] rounded-full" src={image1} alt="">
                  <div>
                     <p class="text-[#464646]">{authorName}</p>
                     <p class="text-[0.7rem] text-[#464646]">{designation}</p>
                  </div>
                  
          </div>
          <img class="my-6" src={banner} alt="">
          <div class="flex flex-col gap-8">
             <p class="text-[#24272A]">{para1}</p>
             <p class="text-[#24272A]">{para2}</p>
             <p class="text-[#24272A]">{para3}</p>
             
          </div>

               <div class="mt-4 px-4">
                  <li>{point}</li>
               <li>{point}</li>
               <li>{point}</li>
               <li>{point}</li>
               <li>{point}</li>
               </div>
    <div class="flex items-center flex-col gap-4 sm:flex-row justify-center mt-8 sm:gap-6">
         <button   class="w-32 sm:mt-8 h-10 rounded-[1.5rem] text-[#0D2460] border-[#0D2460] border-2 flex justify-between items-center px-6">{Like} <Heart /></button>
         <button   class="w-32 sm:mt-8 h-10 rounded-[1.5rem] text-[#0D2460] border-[#0D2460] border-2 flex justify-between items-center px-6">{share} <Share /></button>
         <button   class="w-32 sm:mt-8 h-10 rounded-[1.5rem] text-[#0D2460] border-[#0D2460] border-2 ">{query} </button>

    </div>
    </div>
</div>