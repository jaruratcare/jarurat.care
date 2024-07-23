<script>
  import bgImage from '../../assets/images/Rectangle 52.png';
  import PrevButtonIcon from '$lib/svg/prev-button-icon.svelte';
  import NextButtonIcon from '$lib/svg/next-button-icon.svelte';
  const storyInfo = {
    bgImage: bgImage,
    title: 'Where it all started',
    descripton:
      "Rekha's last trip before she started her battle with Cholangiocarcinoma and succumbed on 25th December2023",
    content:
      'Our story commenced in December 2023, marking a pivotal moment in our lives. It wasthen that we faced the profound loss of our cherished mother, whoebravely battledCholangiocarcinoma, a form of bile duct cancer, for seven months. Motivated by ouraexperiences and driven by the samedetermination any devoted son or daughter wouldpossess, we established the Jarurat Care Foundation with the intent to rectify thesesystemic shortcomings and improve the healthcare landscape for others..'
  };

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
        style="background-image: url({storyInfo?.bgImage});"
      >
        <h1 class="text-5xl font-semibold text-white" style=" font-family: 'Public Sans', serif;">
          {storyInfo?.title}
        </h1>
        <span
          class="text-[#e9e9e9] absolute bottom-4 left-4 w-[250px] text-[13px] leading-4"
          style="font-family: 'Roboto-100', sans-serif">{storyInfo?.descripton}</span
        >
      </div>
      <div
        class="min-w-full min-h-full bg-cover bg-no-repeat bg-center"
        style="background-image: url({storyInfo?.bgImage});"
      >
        <div class="w-[100%] h-[100%] bg-[#00000071] flex justify-center items-center">
          <p
            class=" w-[50%] break-break text-[13px] text-[#d7d7d7] [word-spacing: 50px]"
            style="font-family: 'Roboto, sans-serif';"
          >
            {storyInfo?.content}
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
