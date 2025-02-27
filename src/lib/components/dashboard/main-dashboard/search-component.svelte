<script>
	import DropDown from "$lib/svg/drop-down.svelte";
	import FilterArrow from "$lib/svg/filter-arrow.svelte";
	import Filter from "$lib/svg/filter.svelte";
	import Search from "$lib/svg/search.svelte";
	import Sort from "$lib/svg/sort.svelte";
	import RangeInput from "./range-input.svelte";
	import { slide } from "svelte/transition";
	
	$: isFilterOpen = false;
	$: isSortOpen = false;
</script>

<div class="flex w-full gap-4 h-10">
	<div class="relative w-[60%] h-full">
		<input
			class="bg-[#5D5F6433] w-full h-full pl-10 pr-16 rounded-full"
			placeholder="Search"
			type="text"
		/>

		<span class="absolute left-3 top-1/2 transform -translate-y-1/2 z-20 text-xl">
			<Search />
		</span>
	</div>

	<div class="relative w-[25%]">
		<button
			on:click={() => {
				isFilterOpen = !isFilterOpen;
			}}
			class="w-full bg-[#5D5F6433] h-full px-4 rounded-full flex items-center justify-between space-x-2"
		>
			<div class="flex justify-between items-center space-x-2">
				<span><Filter /></span>
				<span>Filter</span>
			</div>
			<span class="text-[#5D5F64]">
				{#if !isFilterOpen}
					<FilterArrow />
				{:else}
					<DropDown />
				{/if}
			</span>
		</button>

		{#if isFilterOpen}
			<div
				class="absolute w-full bg-[#D9D9D9] rounded-2xl p-2 top-12 z-20 space-y-4"
				transition:slide={{ duration: 200 }}
			>
				<div class="mb-8">
					<span class="text-[1rem]">Ayushman Bharat Accepted</span>
					<div class="my-4">
						<span class="text-[1rem]">Average Cost Range</span>
						<RangeInput max={50000} min={1000} value={28000} step={100} />
					</div>
				</div>
				<div>
					<span class="text-[1rem]">Outpatient division</span>
					<div class="my-4">
						<span class="text-[1rem]">Minimum Rating</span>
						<RangeInput max={3} min={1} value={2} step={1} />
					</div>
				</div>
			</div>
		{/if}
	</div>
<div class="relative w-[15%]">
	<button on:click={()=>{
    isSortOpen = !isSortOpen;
  }} class="w-full bg-[#5D5F6433] h-full px-4 rounded-full flex items-center space-x-2">
		<span><Sort /></span>
		<span>Sort</span>
	</button>
  {#if isSortOpen}
			<div
				class="absolute w-full bg-[#D9D9D9] rounded-2xl p-2 top-12 z-20 space-y-4"
				transition:slide={{ duration: 200 }}
			>
				<ul>
          <li class="p-2 text-left "><button>By Rating</button></li>
          <li class="p-2 text-left "><button>By Distance</button></li>
          <li class="p-2 text-left "><button>By Name </button></li>
        
        </ul>
			</div>
		{/if}
  </div>
</div>
