<script>

  import Send from "$lib/svg/send.svelte";
  import EllipseImage from '../../assets/images/Ellipse 64.png'
</script>

<div class="relative min-h-screen flex flex-col items-center mt-20">
  <img src={EllipseImage} alt="" class="absolute top-0 left-0 w-full h-auto z-[-1]"/>
  <div class="flex flex-col items-center relative z-10 w-full mt-4">
    <div class="text-center mb-12">
      <h1 class="text-4xl font-bold mb-4 heading pt-[90px]" style="color: #0D2561;">How We Support You</h1>
      <p class="text-xl mb-8" style="color: #0D2561;">Comprehensive care and support throughout your cancer journey</p>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-4 gap-8 mb-12">
      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96 relative overflow-hidden" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <Send/>
        </div>
        <h2 class="text-3xl font-semibold text-center mb-4" style="color: #0D2561;">Patient Advocacy</h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          We stand by your side,<br/>
          helping navigate treatment<br/>
          options and healthcare systems
        </p>
        <div class="text-center">
          <button class="relative inline-block px-4 py-2 text-[#2C72B9] font-bold underline underline-offset-2 rounded-full overflow-hidden">
            <span class="relative z-10">Know More</span>
            <span class="absolute inset-0 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-500"></span>
          </button>
        </div>
      </div>
      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96 relative overflow-hidden" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <Send/>
        </div>
        <h2 class="text-3xl font-semibold text-center mb-4" style="color: #0D2561;">Emotional Support</h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          Access to counseling<br/>
          and support groups<br/>
          to help you cope with your diagnosis
        </p>
        <div class="text-center">
          <button class="relative inline-block px-4 py-2 text-[#2C72B9] font-bold underline underline-offset-2 rounded-full overflow-hidden">
            <span class="relative z-10">Know More</span>
            <span class="absolute inset-0 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-500"></span>
          </button>
        </div>
      </div>
      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96 relative overflow-hidden" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <Send/>
        </div>
        <h2 class="text-3xl font-semibold text-center mb-4" style="color: #0D2561;">Education Resources</h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          Up-to-date information<br/>
          on treatments, clinical trials,<br/>
          and cancer management
        </p>
        <div class="text-center">
          <button class="relative inline-block px-4 py-2 text-[#2C72B9] font-bold underline underline-offset-2 rounded-full overflow-hidden">
            <span class="relative z-10">Know More</span>
            <span class="absolute inset-0 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-500"></span>
          </button>
        </div>
      </div>
      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96 relative overflow-hidden" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <Send/>
        </div>
        <h2 class="text-3xl font-semibold text-center mb-4" style="color: #0D2561;">Cancer Connect</h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          A community of dedicated <br/>caregivers who will help you<br/> navigate in your uncertain times
        </p>
        <div class="text-center">
          <button class="relative inline-block px-4 py-2 text-[#2C72B9] font-bold underline underline-offset-2 rounded-full overflow-hidden">
            <span class="relative z-10">Know More</span>
            <span class="absolute inset-0 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-500"></span>
          </button>
        </div>
      </div>
    </div>

    <div class="text-center">
      <button class="inline-block px-6 py-3 font-bold text-white rounded-full bg-[#0155BD]">
        Know More
      </button>
    </div>
  </div>
</div>

<style>
  .card:hover {
    background: radial-gradient(circle, #e8dcff 0%, #d9edfe 50%, #ebe1ff 100%);
  }

  .card:hover .relative::before {
    width: 200%;
    height: 200%;
    left: 50%;
    top: 50%;
  }

  .card:hover .relative {
    background-color: #ffffff;
    color: #0D2561;
    border-radius: 20px;
    padding: 10px 20px;
  }

  .relative .relative:hover {
    color: #0D2561; 
  }
</style>
