<script>
  import { onMount, onDestroy } from "svelte";

  let advisoryBoard = [
    { name: "Dr. Milind Javle", role: "Department of Gastrointestinal Medical Oncology" },
    { name: "Prof. Usha Dutta", role: "Professor & HOD, Gastroenterology, PGIMER, Chandigarh" },
    { name: "Dr. Sangeeta Goswami", role: "Associate Professor, Genitourinary and Immunology, MD Anderson Cancer Center" },
    { name: "Dr. Darshit Shah", role: "Consultant Medical Oncology, Sir H. N. Reliance Foundation Hospital" },
    { name: "Dr. Vikas Ostwal", role: "GI Oncology, Tata Memorial Cancer Center, Mumbai" },
    { name: "Dr. Soumil Vyas", role: "Consultant Surgical Oncology, Sir H N Reliance Foundation Hospital" },
    { name: "Dr. Bhawna Sirohi", role: "Medical Director, Balco Medical Centre, Raipur" },
    { name: "Dr. Jill Koshiol", role: "Senior Investigator, National Cancer Institute (NCI)" },
    { name: "Dr. Vinay Kapoor", role: "Pro Vice Chancellor, MGUMST" },
    { name: "Stacie C. Lindsey", role: "Founder & CEO, Cholangiocarcinoma Foundation" },
    { name: "Dr. Sewanti Limaye", role: "Director Medical & Precision Oncology, Sir HN Reliance Hospital" },
    { name: "Dr. Ravi Kanan", role: "Surgical Oncologist & Director, Cachar Cancer Hospital & Research" },
    { name: "Dr. Shefali Agarwal", role: "Professor of Surgery, Senior Consultant, Indraprastha Apollo Hospitals" },
    { name: "Dr. Raman Sood", role: "Hematology and Oncology Care Provider, Brooks TLC" },
    { name: "Dr. Moushumi Suryavanshi", role: "HOD, Molecular Biology, Amrita Institute of Medical Sciences" },
    { name: "Dr. Vineet Gupta", role: "Oncologist, Fortis Hospital, New Delhi" },
    { name: "Dr. Chetan Arora", role: "Professor and Scientist, IIT Delhi" }
  ];

  function getInitials(name) {
    let cleanName = name.replace("Dr. ", "").replace("Prof. ", "").replace("Ms. ", "");
    let parts = cleanName.split(" ");
    return parts.length > 1 ? (parts[0][0] + parts[1][0]).toUpperCase() : parts[0][0].toUpperCase();
  }

  let scrollContainer;
  let autoScroll;

  const scroll = (direction) => {
    if (!scrollContainer) return;
    const cardWidth = scrollContainer.offsetWidth; // एका वेळी एक पूर्ण कार्ड सरकवण्यासाठी
    scrollContainer.scrollBy({
      left: direction === "next" ? cardWidth : -cardWidth,
      behavior: "smooth"
    });
  };

  onMount(() => {
    autoScroll = setInterval(() => {
      if (scrollContainer) {
        if (scrollContainer.scrollLeft + scrollContainer.clientWidth >= scrollContainer.scrollWidth - 10) {
          scrollContainer.scrollTo({ left: 0, behavior: "smooth" });
        } else {
          // मोबाईलवर क्लायंट विथनुसार स्क्रोल
          scrollContainer.scrollBy({ left: scrollContainer.clientWidth, behavior: "smooth" });
        }
      }
    }, 5000);
  });

  onDestroy(() => {
    clearInterval(autoScroll);
  });
</script>

<section class="py-12 md:py-20 px-0 md:px-6 bg-[#F8FAFF] overflow-hidden">
  <div class="max-w-7xl mx-auto flex flex-col items-center text-center">
    
    <div class="mb-8 md:mb-12 px-6">
      <div class="inline-block px-3 py-1 bg-blue-100 text-blue-600 text-[10px] font-bold uppercase tracking-widest rounded-full mb-4">
        Expert Network
      </div>
      <h2 class="text-3xl md:text-5xl font-black text-[#0D2561] tracking-tight">
        Advisory <span class="text-blue-600">Board</span>
      </h2>
      <p class="text-slate-500 text-sm md:text-base mt-4 font-medium opacity-80 max-w-2xl mx-auto">
        World-class medical leaders guiding our mission to transform cancer care pathways.
      </p>
    </div>

    <div class="flex gap-5 mb-10">
      <button on:click={() => scroll("prev")} aria-label="Previous" class="p-3 border border-blue-200 rounded-full bg-white text-[#0D2561] active:scale-90 hover:bg-[#0D2561] hover:text-white transition-all shadow-md">
        <svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2.5" d="M15 19l-7-7 7-7"/>
        </svg>
      </button>
      <button on:click={() => scroll("next")} aria-label="Next" class="p-3 border border-blue-200 rounded-full bg-white text-[#0D2561] active:scale-90 hover:bg-[#0D2561] hover:text-white transition-all shadow-md">
        <svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2.5" d="M9 5l7 7-7 7"/>
        </svg>
      </button>
    </div>

    <div
      bind:this={scrollContainer}
      class="flex overflow-x-auto snap-x snap-mandatory no-scrollbar scroll-smooth w-full"
      style="-webkit-overflow-scrolling: touch; scroll-behavior: smooth;"
    >
      {#each advisoryBoard as doctor}
        <div class="flex-shrink-0 w-full md:w-1/2 lg:w-1/3 flex justify-center px-6 md:px-4 snap-center">
          <div class="w-full max-w-[360px] group bg-white border border-slate-100 rounded-[2.5rem] p-8 h-[320px] flex flex-col items-center justify-between transition-all duration-300 hover:border-blue-400 hover:shadow-[0_20px_40px_rgba(13,37,97,0.1)]">
            
            <div class="flex flex-col items-center">
                <div class="w-20 h-20 rounded-[1.5rem] bg-gradient-to-br from-blue-600 to-blue-800 flex items-center justify-center text-white font-bold text-2xl shadow-lg mb-5 group-hover:scale-110 transition-transform duration-500">
                  {getInitials(doctor.name)}
                </div>

                <h3 class="text-[#0D2561] font-extrabold text-xl md:text-2xl mb-1 group-hover:text-blue-600 transition-colors text-center">
                  {doctor.name}
                </h3>
            </div>

            <div class="w-full pt-4 border-t border-slate-100 text-center">
              <p class="text-[10px] text-slate-400 font-bold uppercase tracking-[0.15em] mb-1">Affiliation & Role</p>
              <p class="text-[13px] md:text-[14px] text-slate-600 font-semibold leading-relaxed line-clamp-2">
                {doctor.role}
              </p>
            </div>
          </div>
        </div>
      {/each}
    </div>

    <div class="flex gap-2 mt-8">
        <div class="w-8 h-1.5 bg-blue-600 rounded-full"></div>
        <div class="w-2 h-1.5 bg-blue-200 rounded-full"></div>
        <div class="w-2 h-1.5 bg-blue-200 rounded-full"></div>
    </div>

  </div>
</section>

<style>
  /* Hide scrollbar completely */
  .no-scrollbar::-webkit-scrollbar {
    display: none;
  }
  .no-scrollbar {
    -ms-overflow-style: none;
    scrollbar-width: none;
  }

  /* Prevent text selection during fast swipe */
  section {
    -webkit-user-select: none;
    user-select: none;
  }
</style>