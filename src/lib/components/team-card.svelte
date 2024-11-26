<script lang="ts">
  import img from "$lib/assets/landing-page/ourTeam/CEO.png";
  import dq from "$lib/assets/landing-page/ourTeam/“.png";
  export let isSlidIn = false;
  export let name = '';
  export let designation = '';
  export let text = '';
  export let imageSrc = '';

  function toggleSlide() {
    isSlidIn = !isSlidIn;
  }

  function handleKeydown(event: KeyboardEvent) {
    if (event.key === 'Enter' || event.key === ' ') {
      toggleSlide();
    }
  }
</script>

<div
  id="slidingCard"
  role="button"
  aria-label="Slide Card"
  tabindex="0"
  class="card h-[80vh] max-h-[600px] shadow justify-start items-start inline-flex cursor-pointer"
  style="width: {!isSlidIn ? 'fit-content' : '820px'};"
  on:click={toggleSlide}
  on:keydown={handleKeydown}
>
  <div class="w-full h-full px-[2vw] py-[1vh] justify-end items-center gap-2.5 flex">
    <div class="w-full h-full relative flex">
      <!-- Image container with background gradient before the click -->
      <div 
        class="image-container w-[40vw] sm:w-[70vw] h-full max-w-[335px] max-h-[600px] absolute rounded-tl-[15px] rounded-bl-[15px] transition-all duration-500" 
        style={`background-image: ${isSlidIn ? 'none' : 'linear-gradient(to bottom, rgba(169, 169, 169, 0.1), rgba(128, 128, 128, 0.4))'}, url(${imageSrc}); 
                filter: ${isSlidIn ? 'none' : 'grayscale(100%)'}; 
                opacity: ${isSlidIn ? '1' : '1'};` }
      >
        {#if isSlidIn}
          <div 
            class="w-full h-full rounded-tl-[15px] rounded-bl-[15px] transition-all duration-500 bg-cover bg-center"
            style="background-image: url({imageSrc}); filter: none; opacity: 1;"
            aria-label="CEO of Jarurat Care"
          ></div>
        {/if}
        {#if !isSlidIn}
          <div class="flex flex-col absolute bottom-0 left-0 right-0 items-center justify-end text-white pb-4">
            <h2 class="text-2xl font-semibold">wanna meet</h2>
            <p class="text-lg">{designation}?</p>
          </div>
        {/if}
      </div>

      <!-- This element will only be visible after the click -->
      {#if isSlidIn}
        <div class="absolute left-[84%] top-[12.5vh] w-[12vw] h-[12vw] max-w-[100px] max-h-[100px] bg-white rounded-full z-0"></div>

        <div class="slide-in w-[10vw] h-[10vw] max-w-[92px] max-h-[92px] absolute left-[85%] top-[13vh] bg-[#2cbfe2] rounded-full flex justify-center items-center">
          <img src={dq} alt=""/>
        </div>
      {/if}
    </div>

    <div class="flex-grow p-2.5"></div>

    <!-- Sliding description container with class binding for sliding animation -->
    <div class={`w-full flex-col justify-start items-start gap-[196px] inline-flex ${isSlidIn ? 'slide-in' : 'slide-out'}`}>
      {#if isSlidIn}
        <div class="text-[#0d2460] text-xl font-normal leading-7 tracking-tight font-['Manrope']">
          “{text}”
        </div>
        <div class="h-[10vh] flex flex-col justify-between items-start">
          <div class="flex-col justify-start items-start gap-1">
            <div class="text-[#0d2460] text-[28px] font-semibold tracking-wide font-['Manrope']">{name}</div>
            <div class="text-[#0d2460] text-xl font-semibold tracking-tight font-['Manrope']">{designation}</div>
          </div>
        </div>
      {/if}
    </div>
  </div>
</div>

<style>
  /* Custom CSS for image container */
.image-container {
  /* Default width for the container */
  width: 40vw;
}

@media (min-width: 640px) {
  /* Apply the custom width on larger screens */
  .image-container {
    width: 70vw; /* Increase image size for mobile */
  }
}

/* Optional custom styles for the sliding animation */
@keyframes slideIn {
  from {
    transform: translateX(-100%);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

@keyframes slideOut {
  from {
    transform: translateX(0);
    opacity: 1;
  }
  to {
    transform: translateX(-100%);
    opacity: 0;
  }
}

.slide-out {
  animation: slideOut 1.5s ease-in-out forwards;
}

.slide-in {
  animation: slideIn 1.5s ease-in-out forwards;
}

</style>
