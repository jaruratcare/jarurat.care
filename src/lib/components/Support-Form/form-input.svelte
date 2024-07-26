<script>
  import LocateIcon from "$lib/svg/locate-icon.svelte";
  import LocateInsideInput from "$lib/svg/locate-inside-input.svelte";

  export let data;

  // Determine if we should show the SVG icon
  let showIcon = data.name === "Address line 2";
</script>

<style>
  .input-group:hover .label,
  .input:focus {
    @apply text-gray-700;
  }
</style>

<div class="relative">
  <label for="name" class="block mb-1 text-sm font-bold text-[#0D2561] transition-colors duration-300 ease-in-out">
    {data.name}
    {#if data.required}
      <span class="text-red-500 ml-1">*</span>
    {:else}
      <span class="ml-1">(optional)</span>
    {/if}
  </label>
  <input
    id="name"
    value={data.value ? data.value : ''}
    required={data.required}
    placeholder={data.placeholder}
    type={data.type}
    class={`mb-4 w-full h-12 bg-gray-100 rounded-lg px-4 ${showIcon ? 'pr-12' : ''} border-2 border-transparent text-base transition-all duration-300 ease-in-out hover:border-gray-700 focus:outline-none focus:border-gray-700`}
  />
  {#if showIcon}
   <LocateInsideInput />
  {/if}
</div>