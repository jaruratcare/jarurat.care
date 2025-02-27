<script>

	import Star from "$lib/svg/star.svelte";
	import { Phone } from "lucide-svelte";
	import Review from "./review.svelte";
   import img1 from "./image.png"
   import Specs from "./specs.svelte";
	import Map from "./Map.svelte";



</script>
<div class="col-span-4 border-2 rounded-lg overflow-hidden">
        <div class="grid grid-cols-2  gap-2 h-full">
          <div class="grid grid-rows-2 gap-2 p-2 h-full ">
            <div class="flex justify-center relative items-center h-full ">
              <img src={img1} alt="Image" class="w-full h-auto object-cover" />
              <div class="bg-[#FFFFFF] w-[95%] flex justify-between items-center rounded-3xl px-4 py-1 bottom-6    absolute bg-opacity-25">
                  <div>
                    <span class=" text-[0.75rem] font-bold text-white">Suraksha Cancer Institute</span>
                  <p class="text-white text-[0.75rem] font-bold">Kalyani Nagar, Kolkata</p>
                  </div>
                  <span class=" h-8 w-8 rounded-full bg-white flex justify-center items-center"><Phone /></span>
              </div>
            </div>
  
            <div class="  p-1  flex flex-col gap-6">
               <div class="border-2 rounded-2xl p-2 flex justify-center">
                  <span class="">Services Offered</span>
               </div>
               <div class=" border-2 p-4 rounded-2xl">
                  <span>Specialties</span>
                  <ul class="grid mt-2  h-[5rem] overflow-auto gap-2 list-disc ">
                     <Specs />
                     <Specs />
                     <Specs />
                     <Specs />

                 </ul>
               </div>
                 

                
            </div>
          </div>
          
          <div class="h-full grid gap-4 grid-rows-2 p-2">
            <div class="p-2 border-2 overflow-auto rounded-2xl">
               <div class="flex   items-center justify-around">
                 <h1> Rating:</h1>
                 <div class=" flex gap-1"> 
                     <Star />
                     <Star />
                     <Star />
                     <Star />
                     <Star />
                 </div>
                 <span class="text-[0.65rem]">(843 reviews)</span>
               </div>
                 <div class="p-1 my-2 max-h-[10rem]  flex flex-col gap-2 ">
                  <h1 class="mb-3"> Reviews:</h1>
                  <Review />
                  <Review />
                  <Review />
                  <Review />
                  <Review />
                  <Review />
                  <Review />
                  <Review />
                  <Review />
                  
                 </div>
            </div>
            <div class="p-4 border-2 rounded-xl">

              <Map />
            </div>
          </div>
        </div>
      </div>