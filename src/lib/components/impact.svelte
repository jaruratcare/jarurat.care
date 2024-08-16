<script>
    import { t } from '$lib/translations/translations.js';
</script>

<section class="py-12 bg-gradient-to-r from-blue-50 to-blue-100">
  <div class="container mx-auto text-center">
    <h2 class="text-3xl font-bold mb-4 text-blue-950">{$t('home.impact.title')}</h2>
    <p class="text-lg mb-12 text-blue-950">
     {$t('home.impact.description')}
    </p>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
      <div class="bg-white p-6 rounded-lg shadow-md">
        <p class="text-5xl font-bold text-blue-950 mb-2 text-left">{$t('home.impact.value1')}</p>
        <p class="text-gray-600 text-left">{$t('home.impact.label1')}</p>
      </div>
      <div class="bg-white p-6 rounded-lg shadow-md">
        <p class="text-5xl font-bold text-blue-950 mb-2 text-left">{$t('home.impact.value2')}</p>
        <p class="text-gray-600 text-left">{$t('home.impact.label2')}</p>
      </div>
      <div class="bg-white p-6 rounded-lg shadow-md">
        <p class="text-5xl font-bold text-blue-950 mb-2 text-left">{$t('home.impact.value3')}</p>
        <p class="text-gray-600 text-left">{$t('home.impact.label3')}</p>
      </div>
      <div class="bg-white p-6 rounded-lg shadow-md">
        <p class="text-5xl font-bold text-blue-950 mb-2 text-left">{$t('home.impact.value4')}</p>
        <p class="text-gray-600 text-left">{$t('home.impact.label4')}</p>
      </div>
      <div class="bg-white p-6 rounded-lg shadow-md">
        <p class="text-5xl font-bold text-blue-950 mb-2 text-left">{$t('home.impact.value5')}</p>
        <p class="text-gray-600 text-left">{$t('home.impact.label5')}</p>
      </div>
      <div class="bg-white p-6 rounded-lg shadow-md">
        <p class="text-5xl font-bold text-blue-950 mb-2 text-left">{$t('home.impact.value6')}</p>
        <p class="text-gray-600 text-left">{$t('home.impact.label6')}</p>
      </div>
      <div class="bg-white p-6 rounded-lg shadow-md">
        <p class="text-5xl font-bold text-blue-950 mb-2 text-left">{$t('home.impact.value7')}</p>
        <p class="text-gray-600 text-left">{$t('home.impact.label7')}</p>
      </div>
      <div class="bg-white p-6 rounded-lg shadow-md">
        <p class="text-5xl font-bold text-blue-950 mb-2 text-left">{$t('home.impact.value8')}</p>
        <p class="text-gray-600 text-left">{$t('home.impact.label8')}</p>
      </div>
    </div>
    <button
      class="mt-12 px-6 py-2 bg-blue-600 text-white font-bold rounded-lg shadow-md hover:bg-blue-700"
      >{$t('home.impact.buttonText')}</button
    >
  </div>
</section>

<style>
  .container {
    max-width: 1280px;
    margin: 0 auto;
    padding: 0 1rem;
  }
</style>
