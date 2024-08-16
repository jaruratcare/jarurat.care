<script>
  import '@fontsource/roboto/300.css';
  import '@fontsource/playfair-display/600.css';
  import Donate from '$lib/svg/donate.svelte';
  import Member from '$lib/svg/member.svelte';
  import DonationBg from '$lib/svg/donation-bg.svelte';
  import SeekSupport from '$lib/svg/seek-support.svelte';
  import { t } from '$lib/translations/translations.js'; // Adjust the import path as needed
</script>

<div class="relative w-full flex justify-between border border-[#0072C4]">
  <div class="absolute w-full h-full z-0 inset-x-0 overflow-hidden">
    <DonationBg class="w-full" />
  </div>

  <div class="w-1/3 py-11 px-32 flex flex-col justify-center items-center z-10">
    <span class="bg-white p-3 rounded-full"><SeekSupport /></span>
    <h2
      class="mt-6 mb-12 text-4xl font-bold font-serif text-center"
      style="font-family: 'Playfair Display', serif;"
    >
      {$t('home.support.seekSupport')}
    </h2>
    <p class="mt-2 py-1.5 px-5 text-center text-[15px]">
      {$t('home.support.seekSupportDescription')}
    </p>
    <button
      class="relative z-1 mt-8 px-8 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease"
      style="font-family: 'Roboto', sans-serif;"
    >
      <span
        class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
        style="transform: translateX(-50%), translateX(50%);"
      ></span>
      <span class="relative">{$t('home.support.seekSupportButton')}</span>
    </button>
  </div>

  <div
    class="w-1/3 py-12 px-32 flex flex-col justify-center items-center border-x border-[#0072C4] z-10"
  >
    <span class="bg-white p-3 rounded-full"><Donate /></span>
    <h2
      class="mt-6 mb-12 text-4xl font-bold font-serif text-center"
      style="font-family: 'Playfair Display', serif;"
    >
      {$t('home.support.donate')}
    </h2>
    <p class="mt-2 py-1.5 px-5 text-center text-[15px]">
      {$t('home.support.donateDescription')}
    </p>
    <button
      class="relative z-1 mt-8 px-8 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease"
      style="font-family: 'Roboto', sans-serif;"
    >
      <span
        class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
        style="transform: translateX(-50%), translateX(50%);"
      ></span>
      <span class="relative">{$t('home.support.donateButton')}</span>
    </button>
  </div>

  <div class="w-1/3 py-11 px-32 flex flex-col justify-center items-center z-10">
    <span class="bg-white p-3 rounded-full"><Member /></span>
    <h2
      class="mt-6 mb-12 text-4xl font-bold font-serif text-center"
      style="font-family: 'Playfair Display', serif;"
    >
      {$t('home.support.becomeMember')}
    </h2>
    <p class="mt-2 py-1.5 px-5 text-center text-[15px]">
      {$t('home.support.memberDescription')}
    </p>
    <button
      class="relative z-1 mt-8 px-8 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease"
      style="font-family: 'Roboto', sans-serif;"
    >
      <span
        class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
        style="transform: translateX(-50%), translateX(50%);"
      ></span>
      <span class="relative">{$t('home.support.memberButton')}</span>
    </button>
  </div>
</div>