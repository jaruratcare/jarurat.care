<script>
  import { HeartHandshake, Users, HandHeart } from "lucide-svelte";
  import patient from '$lib/assets/patient.jpeg';
</script>

<section class="relative w-full pt-28 pb-20 bg-[#F4F8FF] overflow-hidden">
  <div class="max-w-7xl mx-auto px-4 md:px-6">

    <div class="grid md:grid-cols-2 items-center gap-10 md:gap-8">

      <!-- LEFT -->
      <div class="space-y-4">
        <h1 class="text-3xl md:text-[2.8rem] font-bold text-[#0D2561] leading-tight">
          Jarurat Care Foundation
        </h1>
        <h2 class="text-lg md:text-xl text-[#2563EB] italic font-medium">
          Jaisi Jarurat Vaisi Care
        </h2>
        <p class="text-[#475569] text-base md:text-lg max-w-lg leading-relaxed">
          Providing support to cancer patients and their families. 
          Ensuring no one faces the journey alone.
        </p>
        <div class="flex flex-wrap gap-3 pt-2">
          <a href="mailto:Priyanka.joshi@jarurat.care?subject=Seek%20Support%20For%20Cancer">
            <button class="bg-[#1E40AF] text-white px-6 py-3 rounded-full text-sm font-medium shadow-md hover:bg-white hover:text-[#1E40AF] hover:border hover:border-[#1E40AF] transition-colors duration-300">
              Seek Cancer Support
            </button>
          </a>
          <a href="/donate">
            <button class="border border-[#1E40AF] text-[#1E40AF] px-6 py-3 rounded-full text-sm font-medium hover:bg-[#1E40AF] hover:text-white transition-colors duration-300">
              Donate
            </button>
          </a>
        </div>
      </div>

      <!-- RIGHT IMAGE -->
      <div class="relative flex justify-center md:justify-end">
        <div class="relative w-full md:w-[850px] min-h-[260px] md:min-h-[400px]">
          <img src={patient} alt="Jarurat Care Foundation" class="w-full h-full object-cover" />

          <div
            class="absolute inset-0 pointer-events-none"
            style="
              background:
                linear-gradient(to right, #F4F8FF 0%, rgba(244,248,255,0) 10%),
                linear-gradient(to left, #F4F8FF 0%, rgba(244,248,255,0) 10%),
                linear-gradient(to bottom, #F4F8FF 0%, rgba(244,248,255,0) 30%),
                linear-gradient(to top, #F4F8FF 0%, rgba(244,248,255,0) 8%);
            ">
          </div>
        </div>
      </div>

    </div>
  </div>
</section>