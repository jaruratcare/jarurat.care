<script>

	import DoubleArrow from "$lib/svg/double-arrow.svelte";

</script>
<div class=" hidden md:grid grid-cols-2 h-full">
	<div class=" bg-[#D3F2FC] py-20 pr-4 pl-12 h-full  flex flex-col gap-[4rem]">
		<div class=" relative border-[#0155BD] border-l-4 h-full px-4">
			<h1 class="text-[#0D2460] font-[500] text-[1.8rem]">Subscribe to our Newsletter</h1>
			<p class="text-[#3B3E43] ">Get the latest updates, inspiring stories, and important news delivered right to your inbox.</p>
			<div class=" absolute bottom-0 grid pr-4  grid-cols-5 w-full gap-[1rem] mt-16">
				 <input class=" col-span-3  h-12 px-4 " placeholder="Enter your email address" type="email">
			 <button class="h-[3rem] col-span-2  md:h-[3.5rem] bg-transparent mx-auto  w-full border-2 border-[#0155BD] text-[#0155BD] bg-[white] rounded-3xl flex items-center justify-center">
          Submit
        </button>
			</div>
		</div>



	</div>
	<div class="  py-20 pr-4 pl-12 h-full  flex flex-col gap-[4rem]">
		<div class=" relative border-[#FFBA41] border-l-4 h-full px-4">
			<h1 class="text-[#FFBA41] font-[500] text-[1.8rem]">Collaborate <span class="text-[#78C520]">With Us</span></h1>
			<p class="text-[#3B3E43] ">Our mission thrives through the support of compassionate corporates, individuals, and organizations. You can be a part of this journey too—join us in making a difference.</p>
			<div class=" grid grid-cols-2 w-full gap-[1rem] mt-16">
				 <div class="flex my-auto"> <DoubleArrow /><a href="/" class="ml-2 font-semibold my-auto text-[#24272A]  text-[1rem]">Join jarurat care team</a></div>
				 <div class="flex my-auto"> <DoubleArrow /><a href="/" class="ml-2 font-semibold my-auto text-[#24272A]  text-[1rem]">Join jarurat care team</a></div>
				 <div class="flex my-auto"> <DoubleArrow /><a href="/" class="ml-2 font-semibold my-auto text-[#24272A]  text-[1rem]">Join jarurat care team</a></div>
				 <div class="flex my-auto"> <DoubleArrow /><a href="/" class="ml-2 font-semibold my-auto text-[#24272A]  text-[1rem]">Join jarurat care team</a></div>
			</div>
		</div>



	</div>

</div>

 <div class="w-full    relative pb-16 md:hidden flex flex-col items-center bg-[#D3F2FC]">
    <!-- First Card -->
    <div class="  max-w-[65rem]  flex flex-col  h-full p-[1.25rem] bg-white rounded-[0.5rem] shadow-[0px_0px_10px_2px_rgba(1,85,189,0.12)] mt-[6rem]">
        <div class="max-w-[16rem] flex flex-col gap-4 items-center">
            <h1 class="w-auto h-[2rem] text-center font-rubik text-[1.75rem] text-[#0155BD] font-medium leading-[2rem]">
                Support Us
            </h1>
            <p class="w-2/3 text-[0.75rem] font-[500] text-center text-[#0D2561]">All donations to JaruratCare Foundation are eligible for 50% tax exemption under section 80G of the Income Tax Act.</p>
        </div>
        <button class="mx-auto mt-12 h-[3rem] w-2/3 text-white py-[1rem] bg-[#FFBA41] rounded-3xl flex items-center justify-center">
            Seek Support
        </button>
    </div>
	<div class="  max-w-[65rem]  flex flex-col  h-full p-[1.25rem] bg-white rounded-[0.5rem] shadow-[0px_0px_10px_2px_rgba(1,85,189,0.12)] mt-[6rem]">
        <div class="max-w-[16rem] flex flex-col gap-12 items-center">
            <h1 class="w-auto  text-center font-rubik text-[1.75rem] text-[#0155BD] font-medium leading-[2rem]">
                Subscribe to our Newsletter px
            </h1>
            <p class="w-full text-[0.75rem] font-[500] text-center text-[#0D2561]">Get the latest updates, inspiring stories, and important news delivered right to your inbox.</p>
        </div>
       <div class="mt-12 flex flex-col gap-4">
			<input class="w-full p-4 h-12 bg-[#FFF1D2]" placeholder="Enter your email address" type="email">
			 <button class="h-12 p-6 mx-auto  text-white  bg-[#0155BD] rounded-3xl flex items-center justify-center">
                Submit
            </button>
		 </div>
    </div>
</div>