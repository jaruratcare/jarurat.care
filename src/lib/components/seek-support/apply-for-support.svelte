<script>
  import Button from '../ui/button.svelte';
  import ButtonYellow from '../ui/button-yellow.svelte';
  
</script>


<div class=" flex flex-row w-[100%] h-[500px]">
    <div class=" flex flex-row justify-center items-center w-[57%] bg-[#0D2561]">
        <div class="h-[83%] w-[80%] rounded-[30px] bg-white">
            <div class="h-[25%] bg-[#0155BD] rounded-t-[30px] flex justify-between">
                <p  class="text-white sm:xl md:text-3xl font-bold mt-2 pb-2 pl-10" >Apply For<br>Support</p>
                <div class="flex items-center">
                    <ButtonYellow class="relative z-10 mr-10">Apply for Support</ButtonYellow>
                </div>
        
            </div>

           <div class=" flex flex-col items-center  h-[75%] rounded-b-[30px] bg-[#D3F2FC] overflow-auto">

            <h2 class="text-[#0D2460] text-center font-bold text-xl   pb-5 pt-5 ">How to Apply</h2>
            <div class="flex flex-row   md:w-[100%] pl-[70px] pr-7 ">

                <div class="flex flex-col">
                    <div class="flex flex-row">
                        <div class=" flex h-[80px] w-[80px] rounded-full bg-white items-center justify-center  text-4xl text-[#FFBA41] font-bold">1</div>
                        <div class="h-[40px] w-[150px] border-b-4 border-dashed border-[#FFBA41]"></div>
                    </div>
                    <p class="mt-3 mr-4 font-bold text-[#24272A]">Fill in Your Details</p>
                </div>
                
                <div class="flex flex-col">
                    <div class="flex flex-row">
                        <div class=" flex h-[80px] w-[80px] rounded-full bg-white items-center justify-center  text-4xl text-[#FFBA41] font-bold">2</div>
                        <div class="h-[40px] w-[150px] border-b-4 border-dashed border-[#FFBA41]"></div>
                    </div>
                    <p class="mt-3 mr-4 font-bold text-[#24272A]">Select Your <br>Support Needs</p>
                </div>

                <div class="flex flex-col">
                    
                        <div class=" flex h-[80px] w-[80px] rounded-full bg-white items-center justify-center  text-4xl text-[#FFBA41] font-bold">3</div>
                        <p class="mt-3 mr-4 font-bold text-[#24272A] ">Submit the Form</p>
                </div>
             
            </div>

            <div class="mt-7 bg-[#FDE3A7] p-1 pb-2 border-l-2 border-black">
                <p class="text-xs">Once the form is submitted, we’ll get back to you in 24 hrs of submission</p>
            </div>

           </div>
        </div>
    </div>

    <!--image-->
    <div class="w-[43%] h-[100%]">
        <img  class="h-[100%]" src="src/lib/assets/seek-support/apply-img.jpeg">
    </div>
    
</div>