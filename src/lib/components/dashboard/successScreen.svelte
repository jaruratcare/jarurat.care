<script>
  import CheckIn from "$lib/svg/check-in.svelte";
	import RightArrow from "$lib/svg/right-arrow.svelte";
import Success from "$lib/svg/success.svelte";
</script>

<div class=" flex flex-col  justify-center h-[30rem]">
 <div class="flex flex-col items-center gap-4">
     <div class="w-16 h-16 flex justify-center items-center border-4 rounded-full">
         <CheckIn />
     </div>
     <h1 class="text-xl">Registration completed successfully</h1>
      <div class="flex text-center items-center">
      <a href="#" class="  text-lg underline my-12  mr-2">Proceed to dashboard  </a>
     <RightArrow />
     </div>
 </div>
    
</div>


