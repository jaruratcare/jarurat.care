<script>
  import { Instagram, Linkedin, Twitter } from 'lucide-svelte';

  const socialLinks = {
    instagram: "#",
    linkedin: "https://www.linkedin.com/company/jaruratcare/",
    twitter: "https://x.com/JaruratCare?s=20"
  };
</script>

<section class="w-full min-h-[80vh] bg-[linear-gradient(180deg,#FFFFFF_0%,#F4F8FF_100%)] flex items-center justify-center px-4 py-20 border-t border-blue-50">
  
  <div class="max-w-6xl w-full">

    <div class="flex justify-center mb-20">
      <div class="bg-white/90 border-y border-[#2563EB]/20 px-12 py-4 shadow-md relative">
        
        <div class="absolute left-0 top-0 h-full w-[2px] bg-[#2563EB]"></div>
        <div class="absolute right-0 top-0 h-full w-[2px] bg-[#2563EB]"></div>

        <h2 class="text-[#0D2561] text-[13px] md:text-[15px] font-extrabold tracking-[0.32em] text-center uppercase">
          RE-WRITING THE NARRATIVE,
          <span class="italic font-semibold opacity-90 normal-case ml-2 text-[#2563EB]">
            one life at a time.
          </span>
        </h2>

      </div>
    </div>

    <div class="relative bg-white/40 backdrop-blur-md p-12 md:p-20 rounded-[4rem] border border-white shadow-2xl overflow-hidden mb-16">
      
      <div class="absolute top-8 left-12 opacity-5">
        <svg width="120" height="120" viewBox="0 0 45 34" fill="#2563EB">
          <path d="M12.5 0C5.6 0 0 5.6 0 12.5C0 25.3 10.1 33.1 19.3 33.1V26.2C13.5 26.2 9.4 21.6 8.8 15.9H19.3V0H12.5ZM38.2 0C31.3 0 25.7 5.6 25.7 12.5C25.7 25.3 35.8 33.1 45 33.1V26.2C39.2 26.2 35.1 21.6 34.5 15.9H45V0H38.2Z"/>
        </svg>
      </div>

      <div class="relative z-10 text-center space-y-10">
        <p class="text-xl md:text-2xl font-medium italic leading-[1.6] text-[#0D2561] max-w-2xl mx-auto tracking-tight">
          “Alone we can do so little, <br/> 
          <span class="text-[#2563EB] not-italic font-bold">together</span> we can do so much.”
        </p>

        <div class="flex flex-col items-center">
          <div class="w-8 h-[2px] bg-[#2563EB] mb-4 opacity-50"></div>
          <p class="font-bold text-sm text-[#0D2561] uppercase tracking-[0.2em]">Priyanka Joshi</p>
          <p class="text-slate-400 text-[9px] font-bold uppercase tracking-[0.3em] mt-2">
            Founder, Jarurat Care Foundation
          </p>
        </div>
      </div>
    </div>

    <div class="flex flex-col md:flex-row justify-between items-center gap-8 px-6">

      <div class="flex items-center gap-8">
        
        <span class="text-[15px] font-extrabold text-[#0D2561] uppercase tracking-[0.25em] hidden sm:block">
          Connect
        </span>

        <div class="flex gap-4">
          {#each [
            { link: socialLinks.instagram, icon: Instagram },
            { link: socialLinks.linkedin, icon: Linkedin },
            { link: socialLinks.twitter, icon: Twitter }
          ] as social}
            <a
              href={social.link}
              target="_blank"
              rel="noopener noreferrer"
              class="w-11 h-11 rounded-full bg-white text-[#0D2561] flex items-center justify-center hover:bg-[#2563EB] hover:text-white transition-all duration-500 border border-slate-200 shadow-md hover:scale-110"
            >
              <svelte:component this={social.icon} size={18} strokeWidth={1.8} />
            </a>
          {/each}
        </div>

      </div>

      <a href="/contact-us" class="group">
        <button
          class="bg-[#0D2561] text-white px-12 py-5 rounded-full text-[12px] font-bold shadow-xl group-hover:bg-[#2563EB] group-hover:-translate-y-1 transition-all duration-500 uppercase tracking-[0.25em]"
        >
          Join Our Advisory Board
        </button>
      </a>

    </div>

  </div>
</section>