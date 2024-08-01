<script>
  import SupportUsGr from '$lib/svg/donate-page-svgs/support-us-gr.svelte';
</script>

<div class="relative w-full py-24 flex justify-center items-center">
  <div class="absolute w-full h-full -z-10">
    <SupportUsGr />
  </div>

  <div class=" w-2/3 flex justify-center items-center">
    <div class="w-[55%] pl-16 z-20 flex flex-col justify-center">
      <h1
        class="text-[#0155BD] text-[2.3rem] font-semibold"
        style="font-family: 'Rubik', sans-serif;"
      >
        Support Us
      </h1>
      <p
        class="mt-3.5 pr-4 text-[#0D2561] text-[1.15rem] leading-7 font-medium"
        style="font-family: 'Rubik', sans-serif;"
      >
        All donations to JaruratCare Foundation are eligible for 50% tax exemption under section 80G
        of the Income Tax Act.
      </p>
    </div>

    <div class="w-[45%] z-20 font-medium flex items-center">
      <a href="#donatenow">
        <button
          class="px-8 py-2.5 mt-3 ml-52 text-[1.3rem] text-[#0155BD] font-medium rounded-full border border-[#0155BD] z-10"
          >Donate Now</button
        >
      </a>
    </div>
  </div>
</div>
