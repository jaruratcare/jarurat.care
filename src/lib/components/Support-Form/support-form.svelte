<script>
  import SupportFormQuestionCard from "./support-form-question-card.svelte";
  import ContactNumber from "./contact-number.svelte";
  import FormInput from "./form-input.svelte";
  import RadioInput from "./radio-input.svelte";
  import ButtonRightArrowIcon from "$lib/svg/button-right-arrow-icon.svelte";
  
  const questions = [
    {
      question: "How is my privacy protected?",
      answer: "Your personal information is securely stored and only accessed by authorized support staff."
    },
    {
      question: "When can I expect updates on my support request?",
      answer: "Our team reviews requests promptly. You'll be notified within 48 hours about next steps or any additional information needed.",
    },
    {
      question: "Can I connect with other cancer fighters or caregivers?",
      answer: "Yes, we offer peer support groups and mentorship programs. Indicate your interest in your profile, and we'll provide options while respecting everyone's privacy.",
    }
  ];
</script>



<div class="grid grid-cols-3 gap-8 m-16 text-[#0D2561]">
  <div class="col-span-2 ml-40 mr-16">
    <h1 class="text-2xl font-semibold">Personal Details</h1>
    <span>Please fill in your details correctly</span>
    <form class="my-8">
      <FormInput data={{ name: "Full Name", placeholder: "Enter your full name", type: "text", required: true }} />
      <RadioInput />
      <FormInput data={{ name: "Email", placeholder: "Enter your Email", type: "email", required: true }} />
      <FormInput data={{ name: "Date of Birth", placeholder: "MM/DD/YYYY", type: "date", required: true }} />
      <ContactNumber />
      <FormInput data={{ name: "Address line 1", placeholder: "Street address", type: "text", required: true }} />
      <FormInput data={{ name: "Address line 2", placeholder: "Street address", type: "text", required: false }} />
      <FormInput data={{ name: "Country", value: "India", type: "text", required: true }} />
      <div class="grid grid-cols-2 gap-8 border-b border-b-blue-800 pb-12">
        <FormInput data={{ name: "City/Town", placeholder: "Enter City/town", type: "text", required: true }} />
        <FormInput data={{ name: "Pincode", type: "text", required: true }} />
      </div>

      <div class="grid grid-cols-12 my-8">
        <input class="h-8 w-8 col-span-1 my-auto" type="checkbox" name="" id="">
        <p class="col-span-11">By submitting this form, I agree to Zarurat Care using and verifying my information for support. I understand my information will be kept confidential.</p>
      </div>
      <div class="grid grid-cols-12 my-8">
        <input checked class="h-8 w-8 col-span-1 my-auto" type="checkbox" name="" id="">
        <p class="col-span-11">Subscribe to Zaruratcare foundation newsletter</p>
      </div>
      <button type="submit" class="flex items-center justify-center rounded-3xl px-8 py-2 text-white bg-[#0155BD] cursor-pointer font-bold transition-colors duration-300 ease-in-out hover:bg-[#0148a2]">
        <p>Save and Continue</p>
       <ButtonRightArrowIcon />
      </button>
    </form>
  </div>
  <div class="col-span-1 lg:col-span-1 ">
    <div class=" border-4 border-[#DBEDFD] rounded-xl ">
    {#each questions as data}
      <SupportFormQuestionCard data={data} />
    {/each}
    </div>
  </div>
</div>