<script>
  export let value
  export let min 
  export let max 
  export let step
  // Calculate the position of the bubble
  $: total = max - min;
  $: percentage = (value - min) / total * 100;
</script>

<div class="relative flex justify-between items-center w-full">
   <span class="mr-2">{min}</span>
  <div class="relative w-full">
      
    <input
      type="range"
      step={step}
      min={min}
      max={max}
      bind:value
      class="w-full appearance-none h-1 bg-gray-700 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
    />
    <div
      class="absolute top-6 transform -translate-x-1/2  text-xs font-semibold py-1 px-2 rounded "
      style="left: {percentage}%;"
    >
      {value}
    </div>
  </div>
   <span class="ml-2">{max}</span>
 
</div>
