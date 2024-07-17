<script>
  import Donate from '../../assets/svg/donate.svelte';
  import Member from '../../assets/svg/member.svelte';
  import SeekSupport from '../../assets/svg/seekSupport.svelte';
  import DonationBg from '../../assets/svg/donationBg.svelte';
</script>

<div class=" relative w-full flex justify-between border border-[#0072C4]">
  <div class="absolute w-full h-full z-0 overflow-hidden">
    <DonationBg />
  </div>

  <div class="w-1/3 py-12 px-32 flex flex-col justify-center items-center z-10">
    <span class="bg-white p-3 rounded-full"><SeekSupport /></span>
    <h2
      class="mt-6 mb-12 text-4xl font-bold font-serif text-center"
      style="font-family: 'Playfair Display', sans-serif;"
    >
      Seek Support
    </h2>
    <p class="mt-2 text-center text-sm">
      Our NGO is here to help you through your cancer journey. <span class="font-bold"
        >Reach out to us today.</span
      >
    </p>
    <button
      class="relative z-1 mt-8 px-8 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease"
      style="font-family: 'Roboto', sans-serif;"
    >
      <span
        class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
        style="transform: translateX(-50%), translateX(50%);"
      ></span>
      <span class="relative">Seek Support</span>
    </button>
  </div>

  <div
    class="w-1/3 py-12 px-32 flex flex-col justify-center items-center border-x border-[#0072C4] z-10"
  >
    <span class="bg-white p-3 rounded-full"><Donate /></span>
    <h2
      class="mt-6 mb-12 text-4xl font-bold font-serif text-center"
      style="font-family: 'Playfair Display', sans-serif;"
    >
      Donate
    </h2>
    <p class="mt-2 text-center text-sm">
      One Life at a Time "<span class="font-bold">Donate</span> today and be the light that brings hope
      and healing to cancer patients."
    </p>
    <button
      class="relative z-1 mt-8 px-8 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease"
      style="font-family: 'Roboto', sans-serif;"
    >
      <span
        class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
        style="transform: translateX(-50%), translateX(50%);"
      ></span>
      <span class="relative">Donate Now</span>
    </button>
  </div>

  <div class="w-1/3 py-12 px-32 flex flex-col justify-center items-center z-10">
    <span class="bg-white p-3 rounded-full"><Member /></span>
    <h2
      class="mt-6 mb-12 text-4xl font-bold font-serif text-center"
      style="font-family: 'Playfair Display', sans-serif;"
    >
      Become a Member
    </h2>
    <p class="mt-2 text-center text-sm">
      Be a hero. <span class="font-bold">Volunteer</span> with us and provide support and hope to those
      affected by cancer.
    </p>
    <button
      class="relative z-1 mt-8 px-8 py-3 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-blue-950 group flex items-center justify-center overflow-hidden duration-200 ease"
      style="font-family: 'Roboto', sans-serif;"
    >
      <span
        class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
        style="transform: translateX(-50%), translateX(50%);"
      ></span>
      <span class="relative">Get Started</span>
    </button>
  </div>
</div>
