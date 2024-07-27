<script>
   import img from "../../../assets/images/care-visual.png";
   import bgImg from "../../../assets/images/Frame 1171275545.png";
  import NewVoiceOfHope from "../new-voice-of-hope.svelte";
   import SupportSection from "../support-section.svelte";
   import VoiceOfHope from "../voice-of-hope.svelte";
   import FormInput from "./form-input.svelte";
</script>

<div class="grid grid-cols-5  h-full"> <!-- Setting the height to full screen for demonstration -->
  <div class="col-span-3  h-full flex items-center">
    <img class="h-full w-full object-cover brightness-110" src={img} alt="Care Visual">
  </div>
  <div 
    class="col-span-2 h-full px-12 py-8 flex flex-col justify-center"
    style="background: url({bgImg}) no-repeat center center; background-size: cover;"
  >
    <h1 class="text-3xl font-bold text-[#0D2561] tracking-widest">Seek Support</h1>
    <p class="mt-2 mb-6">Guidance, Support, and Resources for Your Cancer Journey.</p>
    <div class="bg-white h-full w-full rounded-xl p-4 overflow-y-auto">
      <div class="grid grid-cols-2 gap-4">
        <FormInput data={{ name: "First Name", placeholder: "Enter your first name", value:"Ayush", type: "text", required: true }} />
        <FormInput data={{ name: "Middle Name", placeholder: "Enter your full name", type: "text", required: false }} />
      </div>
      <FormInput data={{ name: "Last Name", placeholder: "Enter your Last name", value:"Singh", type: "text", required: true }} />
      <FormInput data={{ name: "Phone number", placeholder: "Enter your Phone Number", value:"+91 1234567890", type: "tel", required: true }} />
      <FormInput data={{ name: "Email", placeholder: "Enter your Email", type: "email", required: true }} />
      <FormInput data={{ name: "Type of Assistance", placeholder: "Enter the type of support needed", value:"Emotional Support", type: "email", required: true }} />
      <p>Brief description</p>
      <textarea 
        class="bg-gray-100 w-full h-40 rounded-xl p-4" 
        placeholder="You can describe your situation with us..."
      ></textarea>
      <button type="submit" class="mx-auto flex items-center h-16 justify-center rounded-xl px-8 py-2 w-full text-white bg-[#0155BD] cursor-pointer font-bold transition-colors duration-300 ease-in-out hover:bg-[#0148a2] my-4">
        <p>Join Jarurat Care</p>
      </button>
    </div>
  </div>
</div>
<SupportSection />
<NewVoiceOfHope />