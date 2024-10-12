<script>
	import { goto } from '$app/navigation';
	import Button from '$lib/components/ui/button.svelte';
	import { Splide, SplideSlide, SplideTrack } from "@splidejs/svelte-splide";
	import { ChevronLeft, ChevronRight } from 'lucide-svelte';
    function handleNavigate(id){
    goto(`/blogs/${id}`)
  }
  export let item1
  export let item2
  export let item3
</script>
<style>
  .shadow-grad{
    box-shadow: 0px 0px 10px 2px #1324421F;
    
  }
</style>
<div class="py-8 lg:w-[80vw] w-[95vw] hidden md:block  my-12 mx-auto bg-white">
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
                      <img class=" rounded-tl-xl h-full  rounded-tr-xl " src={item1.images[0]} alt="">
                      <div class="px-4 py-2">
                        <h1 class="text-[#0155BD]">News July 12, 2024</h1>
                        <p class="text-[#0D2561]">Jarurat Care Launches New Support Program for Cancer Patients</p>
                        <p class="text-[0.75rem] text-[#576171]">Jarurat Care is excited to announce the launch of its new comprehensive support program for cancer patients. This initiative aims to provide enhanced financial aid, counseling services, and community support for patients</p>
                        <button on:click={()=>handleNavigate(item1._id)}  class="w-32 mt-8 h-10  rounded-[1.5rem] text-[#0155BD]  border-[#0155BD] bg-[#D2F2FC]">Read More</button>
                      </div>
                    </div>
                     <div class=" grid grid-rows-2 gap-4 " >
                        <div class=" shadow-grad rounded-xl   grid grid-cols-5">
                            <img class="  h-full rounded-tl-xl col-span-2 rounded-bl-xl " src={item2.images[0]} alt="">
                            <div class="col-span-3 px-4 py-2">
                                <h1 class="text-[#0155BD]">News July 12, 2024</h1>
                                 <p class="mt-2">How Jarurat Care Helped Ravi Overcome His Cancer Battle</p>
                                 <button  on:click={()=>handleNavigate(item2._id)}  class="w-32 mt-12 h-10   rounded-[1.5rem] text-[#0155BD]  border-[#0155BD] bg-[#D2F2FC]">Read More</button>
                            </div>
                        </div>
                        <div class=" shadow-grad rounded-xl   grid grid-cols-5">
                            <img class=" h-full rounded-tl-xl col-span-2 rounded-bl-xl " src={item3.images[0]} alt="">
                              <div class="col-span-3 px-4 py-2">
                                <h1 class="text-[#0155BD]">News July 12, 2024</h1>
                                 <p class="mt-2">How Jarurat Care Helped Ravi Overcome His Cancer Battle</p>
                                 <button  on:click={()=>handleNavigate(item3._id)}  class="w-32 mt-12 h-10   rounded-[1.5rem] text-[#0155BD]  border-[#0155BD] bg-[#D2F2FC]">Read More</button>
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