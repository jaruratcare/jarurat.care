<script>
  import bgImage from '../../assets/images/Story-bg.png';
  import PrevButtonIcon from '$lib/svg/prev-button-icon.svelte';
  import NextButtonIcon from '$lib/svg/next-button-icon.svelte';
   import { t } from '$lib/translations/translations.js';
  let currentIndex = 0;

  const nextSlide = () => {
    currentIndex = (currentIndex + 1) % 2;
    console.log(currentIndex);
  };

  const prevSlide = () => {
    if (currentIndex > 0) {
      currentIndex = (currentIndex - 1) % 2;
    } else {
      currentIndex = 0;
    }
    console.log(currentIndex);
  };
</script>

<div class="relative w-[100%] h-[100vh] flex justify-center items-center">
  <div class="relative w-[800px] h-[366px] overflow-hidden rounded-xl">
    <div
      class=" w-full h-full flex transition-transform duration-500"
      style="transform: translateX(-{currentIndex * 100}%);"
    >
      <div
        class="min-w-full min-h-full bg-cover bg-no-repeat bg-center flex justify-center items-center"
        style="background-image: url({bgImage});"
      >
        <h1 class="text-5xl font-semibold text-white" style=" font-family: 'Public Sans', serif;">
          {$t('home.story.title')}
        </h1>
        <span
          class="text-[#e9e9e9] absolute bottom-4 left-4 w-[250px] text-[13px] leading-4"
          style="font-family: 'Roboto-100', sans-serif">{$t('home.story.description')}</span
        >
      </div>
      <div
        class="min-w-full min-h-full bg-cover bg-no-repeat bg-center"
        style="background-image: url({bgImage});"
      >
        <div class="w-[100%] h-[100%] bg-[#00000071] flex justify-center items-center">
          <p
            class=" w-[50%] break-break text-[13px] text-[#d7d7d7] [word-spacing: 50px]"
            style="font-family: 'Roboto, sans-serif';"
          >
            {$t('home.story.content')}
          </p>
        </div>
      </div>
    </div>
  </div>
  <div class="absolute bottom-24 w-full flex justify-center transform -translate-y-1/2">
    <button
      class=" text-[grey] py-[10px] px-[16px] mx-2 border-2 border-[#474747] rounded-full"
      on:click={prevSlide}><PrevButtonIcon /></button
    >
    <button
      class=" text-[grey] py-[10px] px-[16px] mx-2 border-2 border-[#474747] rounded-full"
      on:click={nextSlide}><NextButtonIcon /></button
    >
  </div>
</div>