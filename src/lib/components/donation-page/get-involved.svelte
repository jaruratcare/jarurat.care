<script>
  import ConnectionIcon from '$lib/svg/donate-page-svgs/connection-icon.svelte';
  import GetInvolvedGr1 from '$lib/svg/donate-page-svgs/get-involved-gr1.svelte';
  import GetInvolvedGr2 from '$lib/svg/donate-page-svgs/get-involved-gr2.svelte';
  import HandConnectIcon from '$lib/svg/donate-page-svgs/hand-connect-icon.svelte';
  import HandPlusIcon from '$lib/svg/donate-page-svgs/hand-plus-icon.svelte';
</script>

<div class="relative w-full py-28 px-24 flex flex-col justify-center items-center overflow-hidden">
  <div class="absolute w-full h-full top-0"><GetInvolvedGr1 /></div>
  <div class="absolute w-full h-full bottom-0"><GetInvolvedGr2 /></div>

  <div class="mb-16 z-20">
    <span class="text-[2.1rem] text-[#0D2561] font-bold" style="font-family: 'Rubik', sans-serif;"
      >Get</span
    >
    <span class="text-[2.1rem] text-[#0155BD] font-bold" style="font-family: 'Rubik', sans-serif;"
      >Involved</span
    >
  </div>

  <div class="flex items-center z-20">
    <div class="w-1/3 py-8 mx-2 bg-white flex flex-col items-center rounded-2xl">
      <span><ConnectionIcon /></span>
      <div class="text-center mt-5">
        <p
          class="text-[2.1rem] text-[#132442] leading-10 font-semibold"
          style="font-family: 'Playfair Display Variable', serif;"
        >
          Join Our
        </p>
        <p
          class="text-[2.1rem] text-[#132442] leading-10 font-semibold"
          style="font-family: 'Playfair Display Variable', serif;"
        >
          Team
        </p>
      </div>
      <p
        class="px-16 my-6 text-[#132442] text-[0.9rem] font-medium text-center"
        style="font-family: 'Manrope', sans-serif;"
      >
        Our NGO is here to help you through your cancer journey. <b>Reach out</b> to us today.
      </p>
      <button
        class="px-8 py-2.5 mt-3 bg-[#0155BD] text-white font-medium rounded-full border border-[#0155BD]"
        >Apply Now</button
      >
    </div>

    <div class="w-1/3 py-8 mx-2 bg-white flex flex-col items-center rounded-2xl">
      <span><HandPlusIcon /></span>
      <div class="text-center mt-5">
        <p
          class="text-[2.1rem] text-[#132442] leading-10 font-semibold"
          style="font-family: 'Playfair Display Variable', serif;"
        >
          Become a
        </p>
        <p
          class="text-[2.1rem] text-[#132442] leading-10 font-semibold"
          style="font-family: 'Playfair Display Variable', serif;"
        >
          caregiver
        </p>
      </div>
      <p
        class="px-16 my-6 text-[#132442] text-[0.9rem] font-medium text-center"
        style="font-family: 'Manrope', sans-serif;"
      >
        Our NGO is here to help you through your cancer journey. <b>Reach out</b> to us today.
      </p>
      <button
        class="px-8 py-2.5 mt-3 bg-[#0155BD] text-white font-medium rounded-full border border-[#0155BD]"
        >Apply Now</button
      >
    </div>

    <div class="w-1/3 py-8 mx-2 bg-white flex flex-col items-center rounded-2xl">
      <span><HandConnectIcon /></span>
      <div class="text-center mt-5">
        <p
          class="text-[2.1rem] text-[#132442] leading-10 font-semibold"
          style="font-family: 'Playfair Display Variable', serif;"
        >
          Become a
        </p>
        <p
          class="text-[2.1rem] text-[#132442] leading-10 font-semibold"
          style="font-family: 'Playfair Display Variable', serif;"
        >
          Mentor
        </p>
      </div>
      <p
        class="px-16 my-6 text-[#132442] text-[0.9rem] font-medium text-center"
        style="font-family: 'Manrope', sans-serif;"
      >
        Our NGO is here to help you through your cancer journey. <b>Reach out</b> to us today.
      </p>
      <button
        class="px-8 py-2.5 mt-3 bg-[#0155BD] text-white font-medium rounded-full border border-[#0155BD]"
        >Apply Now</button
      >
    </div>
  </div>
</div>
