<script lang="ts">
  import OurKeyFeatureWave from "$lib/svg/our-key-featureWave.svelte";
  import Card from "$lib/components/LandingPageComponents/our-key-featuresCards.svelte";
  import img1 from "$lib/assets/landing-page/ourkeyfeature/img1.png";
  import img2 from "$lib/assets/landing-page/ourkeyfeature/img3.png";
  import img3 from "$lib/assets/landing-page/ourkeyfeature/img2.png";
</script>

<div class="relative w-full h-screen overflow-hidden">
  <!-- SVG or Wave Element -->
  <OurKeyFeatureWave class="relative w-full h-full" />

  <!-- Content to be positioned over the SVG -->
  <div class="components absolute top-16 left-0 w-full h-full flex flex-col justify-center items-center gap-8">
      <!-- Title Section -->
      <div class="h-[90px] flex-col justify-start items-center gap-2 inline-flex">
          <div class="text-center">
              <span class="text-[#0d2460] text-[40px] font-medium font-['Rubik'] leading-[55.20px]">Our Key </span>
              <span class="text-[#0054bd] text-[40px] font-medium font-['Rubik'] leading-[55.20px]">Features</span>
          </div>
          <!-- Subtitle Section -->
          <div class="text-center">
              <span class="text-[#676e73] text-xl font-normal font-['Manrope']">Unlock these features to </span>
              <span class="text-[#676e73] text-xl font-semibold font-['Manrope']">guide</span>
              <span class="text-[#676e73] text-xl font-normal font-['Manrope']">, </span>
              <span class="text-[#676e73] text-xl font-semibold font-['Manrope']">connect</span>
              <span class="text-[#676e73] text-xl font-normal font-['Manrope']">, and </span>
              <span class="text-[#676e73] text-xl font-semibold font-['Manrope']">empower</span>
              <span class="text-[#676e73] text-xl font-normal font-['Manrope']"> you every step of the way!</span>
          </div>
      </div>

      <!-- Cards Section -->
      <div class="cards flex justify-center items-center gap-4 p-10">
          <Card title="Cancer Connect" buttonText="Explore Now" imageSrc={img1} altText="xyz" />
          <Card title="Find the nearest cancer hostipal" buttonText="Explore Now" imageSrc={img2} altText="xyz" />
          <Card title="Research Paper Synopsis" buttonText="Explore Now" imageSrc={img3} altText="xyz" />
      </div>
  </div>
</div>

<style>
  /* Add your styles here if needed */
</style>
