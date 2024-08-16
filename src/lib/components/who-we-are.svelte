<script>
  import CancerRibbon from '$lib/svg/cancer-ribbon.svelte';
</script>

<div class="bg-[#132442] px-4 py-8 sm:py-28 relative overflow-hidden sm:rounded-b-3xl z-10">
  <section class="flex sm:flex-row flex-col gap-0 sm:gap-4 max-w-[57rem] mx-auto relative">
    <div class="flex flex-col gap-4 sm:gap-8 sm:max-w-[80%] grow">
      <h2 class="font-manrope text-[#5ED8FF] font-bold">Who we are</h2>

      <p class="font-manrope text-[#F9FFF2] text-[2em] font-semibold leading-[1.1]">
        Our mission is to support and empower cancer patients and their families.
      </p>

      <p class="font-manrope text-[#E5FAFF] leading-[1.8]">
        Through comprehensive programs, Jarurat Care provides vital support to those affected by
        cancer. We offer emotional, financial, and practical assistance to help patients and their
        families navigate their cancer journey.
      </p>
    </div>

    <div class="grow flex items-center justify-center sm:justify-end mt-16 sm:mt-0">
      <CancerRibbon class="max-w-[6em]" />
    </div>
  </section>

  <div class="absolute inset-x-0 bottom-0 z-0">
    <div
      class="w-full mx-auto aspect-square max-w-[40rem] translate-y-[60%] rounded-full scale-125 sm:scale-100"
      style="background-image: radial-gradient(#046E9C, #0D3961); opacity: 0.25;"
    ></div>
  </div>
</div>
