<script>
  import medicalRibbon from '../../assets/images/medical-ribbon.png';
  import talkToHope from '../../assets/images/talk-to-hope.png';
</script>

<div class="relative bg-custom-blue text-white py-16 px-8">
  <div class="max-w-2xl mx-auto text-left">
    <p class="text-lg font-semibold" style="color:#5ED8FF">Who we are</p>
    <h1 class="text-3xl mt-5">
      Our mission is to support and empower cancer patients and their families.
    </h1>
    <h2 class="mt-6 text-1xl">
      Through comprehensive programs, Jarurat Care provides vital support to those affected by
      cancer. We offer emotional, financial, and practical assistance to help patients and their
      families navigate their cancer journey.
    </h2>
  </div>
  <img
    src={medicalRibbon}
    alt="Medical Ribbon"
    class="absolute right-40 top-1/2 transform -translate-y-1/2 w-32"
  />
  <img src={talkToHope} alt="Talk to Hope" class="absolute top-4 right-0 cursor-pointer w-40" />
</div>
