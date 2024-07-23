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
        class="bg-blue-700 text-white px-6 py-3 rounded-full mt-4 flex items-center relative overflow-hidden transition-all duration-300 ease-in-out hover:bg-blue-300 hover:border-b-4 border-blue-800"
      >
        <span class="text-2xl transition-all duration-300 ease-in-out hover:text-blue-900"
          >Seek Cancer Support</span
        >
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
