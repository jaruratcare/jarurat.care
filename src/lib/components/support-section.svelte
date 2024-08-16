<script lang="ts">
  import { t } from '$lib/translations/translations.js'; // Ensure svelte-i18n is configured and imported correctly
  import Send from '$lib/svg/send.svelte';
  import EllipseImage from '../../assets/images/Ellipse 64.png';

  let mainmainButton = $t('home.supportType.mainButton');
</script>

<div class="relative min-h-screen flex flex-col items-center mt-20">
  <img src={EllipseImage} alt="" class="absolute top-0 left-0 w-full h-auto z-[-1]" />
  <div class="flex flex-col items-center relative z-10 w-full mt-4">
    <div class="text-center mb-12">
      <h1 class="text-4xl font-bold mb-4 heading pt-[90px]" style="color: #0D2561;">
        {$t('home.supportType.title-main')}
      </h1>
      <p class="text-xl mb-8" style="color: #0D2561;">
        {$t('home.supportType.description-main')}
      </p>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-4 gap-8 mb-12">
      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96 relative overflow-hidden" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <Send />
        </div>
        <h2 class="text-3xl font-semibold text-center mb-4" style="color: #0D2561;">
          {$t('home.supportType.title1')}
        </h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          {$t('home.supportType.description1')}
        </p>
        <div class="text-center">
          <button class="relative inline-block px-4 py-2 text-[#2C72B9] font-bold underline underline-offset-2 rounded-full overflow-hidden">
            <span class="relative z-10">{$t('home.supportType.mainButton')}</span>
            <span class="absolute inset-0 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-500"></span>
          </button>
        </div>
      </div>

      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96 relative overflow-hidden" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <Send />
        </div>
        <h2 class="text-3xl font-semibold text-center mb-4" style="color: #0D2561;">
          {$t('home.supportType.title2')}
        </h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          {$t('home.supportType.description2')}
        </p>
        <div class="text-center">
          <button class="relative inline-block px-4 py-2 text-[#2C72B9] font-bold underline underline-offset-2 rounded-full overflow-hidden">
            <span class="relative z-10">{$t('home.supportType.mainButton')}</span>
            <span class="absolute inset-0 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-500"></span>
          </button>
        </div>
      </div>

      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96 relative overflow-hidden" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <Send />
        </div>
        <h2 class="text-3xl font-semibold text-center mb-4" style="color: #0D2561;">
          {$t('home.supportType.title3')}
        </h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          {$t('home.supportType.description3')}
        </p>
        <div class="text-center">
          <button class="relative inline-block px-4 py-2 text-[#2C72B9] font-bold underline underline-offset-2 rounded-full overflow-hidden">
            <span class="relative z-10">{$t('home.supportType.mainButton')}</span>
            <span class="absolute inset-0 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-500"></span>
          </button>
        </div>
      </div>

      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96 relative overflow-hidden" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <Send />
        </div>
        <h2 class="text-3xl font-semibold text-center mb-4" style="color: #0D2561;">
          {$t('home.supportType.title4')}
        </h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          {$t('home.supportType.description4')}
        </p>
        <div class="text-center">
          <button class="relative inline-block px-4 py-2 text-[#2C72B9] font-bold underline underline-offset-2 rounded-full overflow-hidden">
            <span class="relative z-10">{$t('home.supportType.mainButton')}</span>
            <span class="absolute inset-0 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-500"></span>
          </button>
        </div>
      </div>
    </div>

    <div class="text-center">
      <button class="inline-block px-6 py-3 font-bold text-white rounded-full bg-[#0155BD]">
        {$t('home.supportType.mainButton')}
      </button>
    </div>
  </div>
</div>

<style>
  .card:hover {
    background: radial-gradient(circle, #e8dcff 0%, #d9edfe 50%, #ebe1ff 100%);
  }

  .card:hover .relative::before {
    width: 200%;
    height: 200%;
    left: 50%;
    top: 50%;
  }

  .card:hover .relative {
    background-color: #ffffff;
    color: #0d2561;
    border-radius: 20px;
    padding: 10px 20px;
  }

  .relative .relative:hover {
    color: #0d2561;
  }
</style>