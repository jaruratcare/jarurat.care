<script>
  import { fade, fly } from 'svelte/transition';
  import { onMount } from 'svelte';
  import gradient from '../../assets/gradient.png';

  let showFinalScreen = false;

  onMount(() => {
    setTimeout(() => {
      showFinalScreen = true;
    }, 1500);
  });
</script>

<!-- svelte-ignore a11y-no-noninteractive-tabindex -->
<div
  id="home"
  class="relative h-screen w-screen overflow-hidden flex items-center justify-center"
  tabindex="0"
  style="background: url({gradient}) no-repeat center center; background-size: cover;"
>
  {#if !showFinalScreen}
    <div
      class="flex flex-col items-center gap-4 text-white"
      in:fade={{ duration: 500 }}
      out:fly={{ x: -1000, duration: 1000 }}
    ></div>
  {/if}

  {#if showFinalScreen}
    <div class="flex flex-col items-center text-center gap-4" in:fly={{ y: 1000, duration: 1000 }}>
      <h1 class="text-8xl font-extrabold" style="color: #0464C4;">JARURAT CARE</h1>
      <h2 class="text-4xl font-semibold" style="color:#132F78">Jaisi Jarurat Vaisi Care</h2>
      <p class="text-blue-900 text-xl max-w-md">
        Providing support, guidance, hope and personalized care for <span
          class="underline font-bold">cancer patients</span
        > and their families. Here to ensure you never face your journey alone.
      </p>
      <button
        class="relative z-1 mt-8 px-6 py-3 bg-blue-700 text-2xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease"
      >
        <span
          class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
          style="transform: translateX(-50%), translateX(50%);"
        ></span>
        <span class="relative">Seek Cancer Support</span>
      </button>
    </div>
  {/if}
</div>

<style>
  div[tabindex='0'] {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 4rem;
    padding: 0;
    margin: 0;
    cursor: pointer;
    outline: none;
  }
</style>
