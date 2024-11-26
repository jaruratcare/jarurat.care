<script lang="ts">
  import OurKeyFeatureWave from "$lib/svg/our-key-featureWave.svelte";
  import Card from "$lib/components/LandingPageComponents/our-key-featuresCards.svelte";
  import img1 from "$lib/assets/landing-page/ourkeyfeature/img1.png";
  import img2 from "$lib/assets/landing-page/ourkeyfeature/img3.png";
  import img3 from "$lib/assets/landing-page/ourkeyfeature/img2.png";
  import Header from "$lib/components/ui/header.svelte";
</script>

<div class="relative w-full min-h-screen overflow-hidden bg-gray-50">
  <!-- SVG Wave Background -->
   <div class="absolute inset-0 w-full h-auto z-0">
  <OurKeyFeatureWave /></div>

  <!-- Content Positioned Over the SVG -->
  <div class="relative z-10 flex flex-col items-center gap-8 mt-20 px-4 py-10">
    <!-- Title Section -->
    <div class="flex flex-col items-center gap-4 text-center">
      <Header color='[#0d2460]' spantitleColor='[#0054bd]' title='Our Key' spantitle='Features' />
      <!-- Subtitle Section -->
      <p class="text-base sm:text-lg md:text-xl lg:text-2xl text-[#676e73] font-['Manrope'] leading-relaxed max-w-3xl">
        Unlock these features to 
        <span class="font-semibold">guide</span>, 
        <span class="font-semibold">connect</span>, and 
        <span class="font-semibold">empower</span> you every step of the way!
      </p>
    </div>

    <!-- Cards Section -->
    <div 
      class="grid gap-6 p-4 sm:p-10 w-full max-w-6xl"
      style="grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));"
    >
      <Card title="Cancer Connect" buttonText="Explore Now" imageSrc={img1} altText="Cancer Connect Image" />
      <Card title="Find the nearest cancer hospital" buttonText="Explore Now" imageSrc={img2} altText="Hospital Image" />
      <Card title="Research Paper Synopsis" buttonText="Explore Now" imageSrc={img3} altText="Research Paper Image" />
    </div>
  </div>
</div>

<style>
  /* Ensure SVG stays in the background */
  .absolute {
    position: absolute;
  }

  /* Z-index ensures content is above the SVG */
  .relative {
    position: relative;
  }
  
  /* Smooth hover animations for cards */
  .cards > div {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }
  .cards > div:hover {
    transform: translateY(-5px);
    box-shadow: 0px 10px 15px rgba(0, 0, 0, 0.1);
  }
</style>
