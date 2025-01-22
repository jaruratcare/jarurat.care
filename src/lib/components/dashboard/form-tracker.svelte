<script>
  export let activeStep;
</script>

<style>
  .check::after {
    content: "\2714"; /* Check mark character */
    opacity: 0; /* Initially hidden */
    transition: opacity 150ms ease-in-out;
    position: absolute;
    left: 25%;

   

  }

  .check::after {
    opacity: 1; /* Show check mark when checked */
  }
</style>

<div class={`${activeStep===5?"hidden":"block"} flex justify-between items-center mt-8`}>
  {#each [1, 2, 3, 4] as step}
    <div class="flex items-center">
      <input
        type="radio"
        bind:group={activeStep}
        value={step}
        name="step"
        class={`${activeStep>step?"check":""} relative w-5 h-5 border-gray-300 rounded-full text-blue-500 focus:ring-2 focus:ring-blue-500`}
        aria-label={`Step ${step}`}
      />
      <span class="ml-2 hidden lg:block text-sm font-light peer-checked:text-yellow-400">
        Step {step}
      </span>
       <span class="ml-2 lg:hidden block text-sm font-light peer-checked:text-yellow-400">
       {step}
      </span>
    </div>
    {#if step !== 4}
      <div class="w-6 border-t-2 border-dashed border-gray-300"></div>
    {/if}
  {/each}
</div>
