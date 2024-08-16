<script>
  import AnimatedGradientMesh from '$lib/svg/animated-gradient-mesh.svelte';
  import Button from './ui/button.svelte';
</script>

<div class="relative h-svh w-full overflow-hidden flex items-center justify-center p-4">
  <!-- <AnimatedGradientMesh class="z-0 absolute inset-0" /> -->

  <div class="absolute inset-0 z-0 flex items-end translate-y-1/2">
    <div
      class="ribbon aspect-square rounded-full border-[1rem] border-[#48B5E4]/12 z-10 w-[80%] mx-auto mt-[4rem]"
    ></div>
  </div>

  <div class="container flex flex-col items-center text-center gap-2 md:gap-4 relative z-10">
    <h1 class="text-4xl md:text-8xl font-extrabold text-[#0464C4]">JARURAT CARE</h1>
    <h2 class="text-2xl md:text-4xl font-semibold" style="color:#132F78">
      Jaisi Jarurat Vaisi Care
    </h2>
    <p class="text-blue-900 text-md md:text-xl max-w-md m-3">
      Providing support, guidance, hope and personalized care for
      <span class="underline font-bold"> cancer patients </span>
      and their families. Here to ensure you never face your journey alone.
    </p>

    <div class="my-4"></div>

    <Button>Seek Cancer Support</Button>
  </div>
</div>

<style>
  @keyframes flyIn {
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }

  .container {
    opacity: 0;
    transform: translateY(150%);
    animation: flyIn 1s cubic-bezier(0.5, 0.36, 0.22, 1) forwards;
  }

  @keyframes shrink {
    to {
      width: 40%;
      top: auto;
      bottom: 0;
    }
  }

  .ribbon {
    animation: shrink 1s cubic-bezier(0.5, 0.36, 0.22, 1) forwards;
  }
</style>
