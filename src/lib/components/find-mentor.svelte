<script>
  import image2 from '../../assets/images/care-visual.png';
  import imageBlur from '../../assets/images/image-blur.png'; 
</script>

<div class="relative bg-white rounded-lg overflow-hidden shadow-lg max-w-6xl mx-auto mt-20">
  <div class="absolute inset-0 flex items-center">
    <div class=" w-1/2 h-full bg-[#132442]"></div>
  </div>
  
  <div class="relative z-10 flex">
    <div class="p-8 w-1/2">
      <h1 class="text-4xl font-bold text-white">Find a Mentor for Your <br/><span style="color: #D6B7FF;">Cancer</span> Journey</h1>
      <p class="mt-4 text-white text-xl">You Don't Have to Face Cancer Alone</p>
      <p class="mt-2 text-white">Connect with a compassionate mentor who understands your challenges and can offer personal support and guidance.</p>
      <button class="mt-6 px-4 py-2 font-semibold text-#132442 rounded-full bg-[#D6B7FF]">Get a mentor</button>
    </div>
    
    <div class="w-1/2 flex items-center justify-center">
      <img src={imageBlur} alt="Blurred effect" class="object-cover w-full h-full" />
    </div>
  </div>
  
  <div class="absolute inset-y-0 right-0 w-1/2 h-full">
    <img src={image2} alt="Doctor with patient" class="object-cover w-full h-full" />
  </div>
</div>

