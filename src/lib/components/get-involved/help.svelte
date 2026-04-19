<script>
  import { Users, Stethoscope, MonitorPlay, HandHeart, ArrowRight } from "lucide-svelte";
  
  // VERCEL FIX: Image import kelyamule build nantar path chukat nahi
  import scanToJoinImg from '$lib/assets/scantojoin.png';

  let helpItems = [
    {
      title: "Join the Ecosystem",
      desc: "Be part of India's most inclusive cancer support network and connect with experts.",
      icon: Users
    },
    {
      title: "Medical Opinions",
      desc: "Provide critical secondary medical insights to strengthen patient treatment pathways.",
      icon: Stethoscope
    },
    {
      title: "Co-Host Webinars",
      desc: "Lead educational webinars and screening initiatives to spread awareness across India.",
      icon: MonitorPlay
    },
    {
      title: "Mentor & Guide",
      desc: "Empower caregivers and early-career oncologists through your clinical experience.",
      icon: HandHeart
    }
  ];
</script>

<section class="relative py-12 md:py-20 px-4 md:px-6 bg-[linear-gradient(180deg,#FFFFFF_0%,#F0F7FF_50%,#E6F0F5_100%)] font-bold overflow-hidden">
  
  <div class="absolute top-0 left-0 w-full h-full overflow-hidden pointer-events-none opacity-20">
    <div class="absolute -top-24 -right-24 w-96 h-96 bg-blue-200 rounded-full blur-3xl"></div>
    <div class="absolute top-1/2 -left-48 w-80 h-80 bg-blue-100 rounded-full blur-3xl"></div>
  </div>

  <div class="max-w-7xl mx-auto relative">

    <div class="flex flex-col items-center text-center mb-12 md:mb-16">
      <div class="inline-flex items-center gap-2 px-4 py-1.5 bg-white border border-blue-100 rounded-full shadow-sm mb-6">
        <span class="flex h-2 w-2 rounded-full bg-blue-600 animate-pulse"></span>
        <span class="text-blue-600 font-bold text-[10px] tracking-[0.2em] uppercase">Impact Opportunities</span>
      </div>
      
      <h2 class="text-3xl md:text-4xl font-black text-[#0D2561] mb-4 tracking-tight px-2">
        How You Can <span class="text-transparent bg-clip-text bg-gradient-to-r from-blue-600 to-blue-800">Get Involved</span>
      </h2>
      
      <p class="max-w-2xl mx-auto text-slate-500 text-sm md:text-base leading-relaxed font-medium opacity-80 px-4">
        Join a movement that's redefining cancer care in India. Your expertise can be the light for someone's journey.
      </p>
    </div>

    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 mb-16 md:mb-24">
      {#each helpItems as item}
        <div class="group relative bg-white border border-slate-100 rounded-[2rem] p-8 shadow-sm transition-all duration-300 hover:border-blue-400 hover:shadow-[0_0_30px_rgba(37,99,235,0.15)] cursor-pointer">
          <div class="w-12 h-12 md:w-14 md:h-14 rounded-xl bg-slate-50 flex items-center justify-center mb-6 border border-slate-100 transition-all duration-300 group-hover:bg-blue-600">
            <svelte:component this={item.icon} size={22} class="text-blue-600 transition-colors duration-300 group-hover:text-white" />
          </div>
          <h3 class="text-[#0D2561] font-bold text-lg mb-3 transition-colors duration-300 group-hover:text-blue-600">{item.title}</h3>
          <p class="text-slate-500 text-sm leading-relaxed mb-6 font-medium">{item.desc}</p>
          <div class="flex items-center text-blue-600 font-bold text-[10px] uppercase tracking-widest opacity-60 group-hover:opacity-100 transition-opacity">
            Learn More <ArrowRight size={14} class="ml-2" />
          </div>
        </div>
      {/each}
    </div>

    <div id="join-qr" class="relative bg-[#0D2561] rounded-[2rem] md:rounded-[2.5rem] overflow-hidden shadow-2xl max-w-6xl mx-auto">
      <div class="absolute top-0 right-0 w-64 h-64 bg-blue-500/10 rounded-full blur-[80px] -mr-32 -mt-32"></div>

      <div class="relative z-10 grid lg:grid-cols-12 items-stretch">
        
        <div class="lg:col-span-7 p-8 md:p-12 lg:pl-16 text-white flex flex-col justify-center">
          <h3 class="text-2xl md:text-4xl font-black mb-4 leading-tight tracking-tight">
            Ready to make a <br class="hidden md:block"/> 
            <span class="text-transparent bg-clip-text bg-gradient-to-r from-blue-400 to-blue-200">Difference?</span>
          </h3>
          
          <p class="text-blue-100/70 mb-6 md:mb-8 text-sm md:text-lg max-w-lg font-medium leading-relaxed">
            Scan the code to join our exclusive medical network and start your journey with Jarurat Care.
          </p>
          
          <div class="flex flex-col sm:flex-row items-start sm:items-center gap-6">
            <div class="relative p-1 transition-transform duration-500 hover:scale-105">
              <div class="absolute top-0 left-0 w-5 h-5 border-t-2 border-l-2 border-blue-400 rounded-tl-lg"></div>
              <div class="absolute top-0 right-0 w-5 h-5 border-t-2 border-r-2 border-blue-400 rounded-tr-lg"></div>
              <div class="absolute bottom-0 left-0 w-5 h-5 border-b-2 border-l-2 border-blue-400 rounded-bl-lg"></div>
              <div class="absolute bottom-0 right-0 w-5 h-5 border-b-2 border-r-2 border-blue-400 rounded-br-lg"></div>
              
              <div class="bg-white p-2 rounded-lg shadow-2xl">
                <img
                  src="https://api.qrserver.com/v1/create-qr-code/?size=120x120&data=https://jaruratcare.org/volunteer"
                  alt="QR Code"
                  class="w-20 h-20 md:w-24 md:h-24"
                />
              </div>
            </div>

            <div class="space-y-1">
              <div class="text-lg md:text-2xl font-bold text-white tracking-tight">Scan to Register</div>
              <div class="flex items-center gap-2">
                <span class="w-1.5 h-1.5 rounded-full bg-blue-400 animate-pulse"></span>
                <span class="text-blue-400 text-[9px] md:text-xs tracking-[0.2em] uppercase font-bold">Quick Onboarding</span>
              </div>
            </div>
          </div>
        </div>

        <div class="lg:col-span-5 relative h-56 sm:h-72 lg:h-auto overflow-hidden">
          <img 
            src={scanToJoinImg} 
            alt="Medical Team" 
            class="absolute inset-0 w-full h-full object-cover grayscale-[10%]"
          />
          <div class="absolute inset-0 bg-gradient-to-t from-[#0D2561] via-transparent to-transparent lg:bg-gradient-to-r lg:from-[#0D2561] lg:via-[#0D2561]/30"></div>
        </div>
      </div>
    </div>

  </div>
</section>

<style>
  :global(html) {
    scroll-behavior: smooth;
  }
  #join-qr {
    scroll-margin-top: 80px;
  }
</style>