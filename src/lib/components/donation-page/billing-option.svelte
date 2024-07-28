<script>
  import Ellipse from '$lib/svg/donate-page-svgs/ellipse.svelte';
  import WaveGradient from '$lib/svg/donate-page-svgs/wave-gradient.svelte';
  import DonorDetails from './payment/donor-details.svelte';
  import PaymentOption from './payment/payment-option.svelte';
</script>

<div id="donatenow" class="relative w-full py-16 flex flex-col justify-center items-center overflow-hidden">
  <div class="absolute top-0 z-10">
    <Ellipse/>
  </div>
  <div class="w-full flex flex-col justify-center items-center overflow-hidden z-20">
    <h1
      class="text-4xl text-[#0D2561] text-center font-semibold z-20"
      style="font-family: 'Rubic', sans-sarif;"
    >
      Help Us Fight Cancer
    </h1>
    <p class=" text-xl text-[#132442] font-400 mt-1" style="font-family: 'Manrope', sans-sarif;">
      Your donation can make a life-saving difference.
    </p>

    <PaymentOption />
    <!-- <DonorDetails /> -->
  </div>
  <div class="absolute bottom-0 z-0">
    <WaveGradient />
  </div>
</div>
