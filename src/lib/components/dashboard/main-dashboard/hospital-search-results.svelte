<script>
	import FilterArrow from "$lib/svg/filter-arrow.svelte";
  export let data;
  import Sort from "$lib/svg/sort.svelte";

</script>

<div class="w-full border-2 grid grid-cols-4 items-center px-4 border-[#5D5F6426] min-h-12 max-h-16 rounded-full">
  <!-- Left Section -->
  <div class="flex items-center justify-between col-span-3 gap-2">
    <span class="truncate w-[70%] text-ellipsis">{data.name}</span>
    <span class={`w-[30%]  text-center rounded-full ${data.distance.split("k")[0]<10?"bg-[#50FF5BCC]":data.distance.split("k")[0]>10 && data.distance.split("k")[0]<20?"bg-[#FFE250CC]":"bg-[#FF5050CC]"} `}>{data.distance}</span>
  </div>
  <!-- Right Section -->
  <div class="flex justify-end">
    <button class="bg-[#5D5F6433] w-8 h-8 rounded-full flex items-center justify-center">
      <span>
        <FilterArrow />
      </span>
    </button>
  </div>
</div>
