<script>
	import HospitalSearchResults from "$lib/components/dashboard/main-dashboard/hospital-search-results.svelte";
	import Review from "$lib/components/dashboard/main-dashboard/review.svelte";
	import SearchComponent from "$lib/components/dashboard/main-dashboard/search-component.svelte";
	import SideDashboard from "$lib/components/dashboard/main-dashboard/side-dashboard.svelte";
	import Specs from "$lib/components/dashboard/main-dashboard/specs.svelte";
	import Nav from "$lib/components/nav.svelte";
   let data = [
    {
      name: "Hospital 1",
      distance: "2.5 km",
    },
    {
      name: "Hospital 2",
      distance: "3.5 km",
    },
    {
      name: "Hospital 3",
      distance: "4.5 km",
    },
    {
      name: "Hospital 4",
      distance: "5.5 km",
    },
    {
      name: "Hospital 5",
      distance: "6.5 km",
    },
    {
      name: "Hospital 6",
      distance: "7.5 km",
    },
    {
      name: "Hospital 7",
      distance: "8.5 km",
    },
    {
      name: "Hospital 8",
      distance: "9.5 km",
    },
    {
      name: "Hospital 9",
      distance: "10.5 km",
    },
    {
      name: "Hospital 10",
      distance: "11.5 km",
    },
    {
      name: "Hospital 11",
      distance: "12.5 km",
    },
    {
      name: "Hospital 12",
      distance: "13.5 km",
    },
    {
      name: "Hospital 13",
      distance: "14.5 km",
    },
    {
      name: "Hospital 14",
      distance: "15.5 km",
    },
    {
      name: "Hospital 15",
      distance: "16.5 km",
    },
    {
      name: "Hospital 16",
      distance: "17.5 km",
    },
    {
      name: "Hospital 17",
      distance: "18.5 km",
    },
    {
      name: "Hospital 18",
      distance: "19.5 km",
    },
    {
      name: "Hospital 19",
      distance: "20.5 km",
    },
    {
      name: "Hospital 20",
      distance: "21.5 km",
    },];
</script>

<Nav />
<div class="grid grid-rows-auto gap-8 w-[80%] mx-auto">
  <div class="flex flex-col items-center justify-center relative bg-white pt-28 gap-2">
    <h1 class="text-[2rem] text-[#0D2460] font-bold tracking-wide">
      Locate Cancer <span class="text-[#0155BD]">Hospitals Near You</span>
    </h1>
    <h2 class="text-[1.25rem] text-[#0D2460]">Finding Hope, One Location at a Time</h2>
  </div>

  <div class="border-[#5D5F6426] border-2 p-4 rounded-lg">
    <SearchComponent />

    <div class="grid grid-cols-7 gap-4 h-[30rem] my-8">
      <!-- Left Column -->
      <div class="col-span-3 border-2 flex flex-col gap-2  rounded-lg overflow-auto p-4">
        {#each data as h }
          <HospitalSearchResults data={h} />
        {/each}
      </div>

    {#if 2 < 3}
  <SideDashboard />
{:else}
<div class="col-span-4 flex justify-center items-center border-2 rounded-lg overflow-hidden">
  <p>Selct Hospital to get Relavent Result</p>
</div>
{/if}


    </div>
  </div>
</div>

