<script>
  import Icon from '@iconify/svelte';


import { t, setLocale } from '$lib/translations/translations.js'
   // Reactive variable to store the current route


</script>

<footer class="bg-[#132442] font-sans py-5">
  <div class="flex items-center justify-between mx-24">
    <div class="logo">
      <img src="./images/logo.svg" alt="logo" height="85px" />
    </div>
    <div>
      <h1 class="text-white text-3xl font-bold">
        {$t('home.footer.foundation1')} <span class="text-[#D8E74E]"> {$t('home.footer.foundation2')}</span>
      </h1>
      <p class="text-white text-base">{$t('home.footer.tagline')}</p>
    </div>
    <div class="flex space-x-8">
      <button
        class="bg-[#D8E74E] text-black font-bold py-2 px-5 rounded-full border-none cursor-pointer"
      >{$t('home.footer.seekSupport')}</button>
      <button
        class="bg-[#2C72B9] text-white font-bold py-2 px-5 rounded-full border-none cursor-pointer"
      >{$t('home.footer.donate')}</button>
    </div>
  </div>

  <hr class="my-5 mx-24 border-gray-300" />

  <div class="flex text-white text-sm justify-end py-5 mx-24 gap-5">
    <div class="flex flex-col space-y-2">
      <p>{$t('home.footer.sections.home')}</p>
      <p>{$t('home.footer.sections.mission')}</p>
      <p>{$t('home.footer.sections.volunteer')}</p>
      <p>{$t('home.footer.sections.locateHospitals')}</p>
      <p>{$t('home.footer.sections.termsConditions')}</p>
    </div>
    <div class="flex flex-col space-y-2">
      <p>{$t('home.footer.sections.aboutUs')}</p>
      <p>{$t('home.footer.sections.blogs')}</p>
      <p>{$t('home.footer.sections.news')}</p>
      <p>{$t('home.footer.sections.contactUs')}</p>
    </div>
    <div class="flex flex-col space-y-2">
      <p>{$t('home.footer.sections.connect')}</p>
      <div class="flex space-x-5">
        <Icon icon="mdi:instagram" class="icon h-7 cursor-pointer" />
        <Icon icon="mdi:twitter" class="icon h-7 cursor-pointer" />
        <Icon icon="mdi:facebook" class="icon h-7 cursor-pointer" />
      </div>
    </div>
  </div>

  <hr class="my-5 mx-24 border-gray-300" />

  <div class="flex text-[#204D80] justify-between mx-24 py-5">
    <div class="flex space-x-2 cursor-pointer">
      <p>{$t('home.footer.privacyPolicy')}</p>
      <p>{$t('home.footer.termsOfService')}</p>
    </div>
    <div>
      <p>&copy;2024 HANDSCART LLC. {$t('home.footer.allRightsReserved')}</p>
    </div>
    <div class="text-[#D8E74E] cursor-pointer">
      <a href="#navbar">{$t('home.footer.backToTop')} &uarr;</a>
    </div>
  </div>
</footer>

