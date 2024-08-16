<script>
  import medicalRibbon from '../../assets/images/medical-ribbon.png';
  import { t } from '$lib/translations/translations.js'; // Adjust the import path as needed
</script>

<div class="relative bg-custom-blue text-white py-16 px-8">
  <div class="max-w-2xl mx-auto text-left">
    <p class="text-lg font-semibold" style="color:#5ED8FF">{$t('home.section.whoWeAre')}</p>
    <h1 class="text-3xl mt-5">
      {$t('home.section.mission')}
    </h1>
    <h2 class="mt-6 text-1xl">
      {$t('home.section.description')}
    </h2>
  </div>
  <img
    src={medicalRibbon}
    alt="Medical Ribbon"
    class="absolute right-40 top-1/2 transform -translate-y-1/2 w-32"
  />
</div>