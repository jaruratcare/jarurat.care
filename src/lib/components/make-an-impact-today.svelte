<script>
  import bg from "$lib/assets/landing-page/makeanimpacttoday/bg.png"
  import Button from "./ui/cardbutton.svelte";
  import MakeAnImpactTodaywave from "$lib/svg/make-an-impact-todaywave.svelte";
</script>

<div class="w-full h-fit bg-cover bg-center overflow-hidden" style="background-image: url({bg});">
    <!-- Main content section -->
    <div class="h-fit flex flex-col justify-start items-center gap-10 p-4 md:p-8">
      <!-- Title with responsive text sizes -->
      <div class="self-stretch text-center text-3xl md:text-5xl font-semibold leading-tight md:leading-[50.40px] font-['Rubik'] m-5">
        <span class="text-[#0155bd]">Make an </span>
        <span class="text-[#0d2460]">Impact </span>
        <span class="text-white"></span>
        <span class="text-[#0155bd]">Today</span>
      </div>
  
      <!-- Donation amount buttons -->
      <div class="flex flex-wrap justify-center items-center gap-3 md:gap-5">
        <button class="px-6 py-3 md:px-9 md:py-4 bg-[#0054bd] rounded-[10px] border border-[#d3f2fc] text-[#d3f2fc] text-xl md:text-2xl font-semibold font-['Manrope']">₹200</button>
        <button class="px-6 py-3 md:px-9 md:py-4 rounded-[10px] border border-[#004089] text-[#004089] text-xl md:text-2xl font-semibold">₹500</button>
        <button class="px-6 py-3 md:px-9 md:py-4 rounded-[10px] border border-[#004089] text-[#004089] text-xl md:text-2xl font-semibold">₹1000</button>
        <button class="px-6 py-3 md:px-9 md:py-4 rounded-[10px] border border-[#004089] text-[#004089] text-xl md:text-2xl font-semibold">₹2000</button>
        <button class="px-6 py-3 md:px-9 md:py-4 rounded-[10px] border border-[#004089] text-[#004089] text-xl md:text-2xl font-semibold">₹5000</button>
        <button class="px-6 py-3 md:px-9 md:py-4 rounded-[10px] border border-[#004089] text-[#004089] text-xl md:text-2xl font-semibold">₹10000</button>
        <button class="w-fit h-auto px-6 py-3 md:px-9 md:py-4 rounded-[10px] border border-[#004089] text-[#004089] text-xl md:text-2xl font-semibold">Custom Amount</button>
      </div>
  
      <!-- Donate button -->
      <Button class="mt-6 bg-[#0155BD] text-white px-6 py-3 md:px-9 md:py-4 transition transform duration-300 ease-in-out">
        Donate Today
      </Button>
    </div>
  
    <!-- SVG wave, ensures it's responsive -->
    <div class="w-full h-auto">
      <MakeAnImpactTodaywave class="w-full h-[200px] md:h-[250px]"/>
    </div>
  </div>
  
