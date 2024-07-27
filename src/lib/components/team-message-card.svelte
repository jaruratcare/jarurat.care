<script>
  import Quote from "$lib/svg/quote.svelte";

  export let data;
</script>

<div class="bg-neutral-200 h-[30rem] w-[50rem]  flex items-center justify-center">
  <div class="w-1/2 h-full bg-white relative">
    <div
      style="transform: translateX(-50%);"
      class="h-28 bg-white w-28 absolute top-12 rounded-full flex items-center justify-center"
    >
      <div class="bg-[#FFBA41] p-4 rounded-full ">
        <Quote />
      </div>
    </div>
    <p class="absolute top-24 w-2/3 left-12 mx-4">{data.Message}</p>
    <p class="absolute bottom-16 left-12 mx-4">{data.Name}</p>
    <p class="absolute bottom-10 left-12 mx-4">{data.Designation}</p>
  </div>
</div>
