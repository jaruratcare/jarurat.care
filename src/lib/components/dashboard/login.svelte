<script>
  import { slide } from 'svelte/transition';
  import '@splidejs/splide/dist/css/splide.min.css';
  import Form1 from "./form1.svelte";
  import Form2 from "./form2.svelte";
  import ReviewCarousel from "./review-carousel.svelte";
  import FormTracker from './form-tracker.svelte';
  import Form3 from './form3.svelte';
  import Form4 from './form4.svelte';
	import SuccessScreen from './successScreen.svelte';

  // reactive activeStep
  let activeStep = 1;

  function moveNext() {
    activeStep = activeStep + 1;
  }
  $:userData={
         firstName : '',
         lastName : '',
         email : '',
         password : '',
         location : '',
         recipent : '',
         supportType : '',
         primaryChallenge : '',
          careType : '',
          hospitalSchedule : '',
          fullTimeCare : '',
          specialNeeds : '',
          income : null,
          govSchemesUsing : '',
          govSchemesHelp : '',
          priceRange : null
  }
</script>

<style>
  .bg {
    background-image: url('./image.png');
  }
</style>

<div class="flex items-center justify-center min-h-screen bg-gray-100 lg:p-16 md:p-4 sm:p-8 p-2 ">
  <div class="w-full max-w-6xl grid grid-cols-1 md:grid-cols-2 bg-white rounded-lg shadow-lg overflow-hidden">
    <div class="hidden md:block bg-cover bg-center bg lg:px-12 px-4 py-8">
      <h2 class="text-2xl font-bold text-[#0D2460] mb-4">Jarurat Care</h2>
      <h3 class="text-xl lg:text-4xl w-2/3 text-[#0D2460] font-light my-6 leading-relaxed">
        Start Your Journey With Us
      </h3>
      <p class="text-[#0D2460] font-light mb-6 leading-relaxed">
        Together let’s build a better care experience.
      </p>
      <ReviewCarousel />
    </div>

    <div class="p-6 bg-gradient-to-b from-[#0D2460] to-[#1B4AC6] text-white rounded-r-lg">
      <div>
        <!-- Transition wrapper for the form content -->
        <div class="transition-wrapper">
          {#if activeStep === 1}
            <div key="form1" in:slide={{ duration: 500 }} out:slide={{ duration: 500 }}>
              <Form1 data={userData} moveNext={moveNext} activeStep={activeStep} />
            </div>
          {/if}

          {#if activeStep === 2}
            <div key="form2" in:slide={{ duration: 500 }} out:slide={{ duration: 500 }}>
              <Form2 data={userData} movenext={moveNext} activeStep={activeStep} />
            </div>
          {/if}

          {#if activeStep === 3}
            <div key="form3" in:slide={{ duration: 500 }} out:slide={{ duration: 500 }}>
              <Form3 data={userData} movenext={moveNext} activeStep={activeStep} />
            </div>
          {/if}

          {#if activeStep === 4}
            <div key="form4" in:slide={{ duration: 500 }} out:slide={{ duration: 500 }}>
              <Form4 data={userData} movenext={moveNext} activeStep={activeStep} />
            </div>
          {/if}
          {#if activeStep === 5}
            <div key="form4" in:slide={{ duration: 500 }} out:slide={{ duration: 500 }}>
              <SuccessScreen  />
            </div>
          {/if}
        </div>

        <FormTracker bind:activeStep={activeStep} />
      </div>
    </div>
  </div>
</div>
