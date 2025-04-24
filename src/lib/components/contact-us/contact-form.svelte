<script>
  import ContactUs from "$lib/svg/contact-us.svelte";
  import ContactNumber from "../contact-number.svelte";
  import FormInput from "../form-input.svelte";
  import RoleSelector from "../ui/RoleSelector.svelte";
  import BgImage from "./image.png";

  // Text variables
  let talk = "TALK ";
  let to = "TO";
  let us = "US";
  let withText = "with";
  let getInTouchHeading = "Get in Touch ";
  let getInTouchDescription = "Our team is just an email away and ready to answer your questions";
  let fillDetailsNote = "Please fill in your details correctly";
  let messageLabel = "Message";
  let messagePlaceholder = "What's on your Mind";
  let privacyNotice = "Your information will remain confidential and be used to provide you with personalized support.";
  let submitButtonLabel = "Submit";

  // Form data
  let fullName = "";
  let email = "";
  let message = "";
  let role = "";
  let phone = "";
  let acceptedPrivacy = false;
  let showToast = false;


  function resetForm() {
    fullName = "";
    email = "";
    message = "";
    role = "";
    phone = "";
    acceptedPrivacy = false;
  }

  function handleSubmit() {
    if (!acceptedPrivacy) {
      alert("Please accept the privacy policy before submitting.");
      return;
    }

    const formData = {
      fullName,
      email,
      message,
      role,
      phone
    };

    console.log("Submitting form:", formData);

    // Placeholder for API call
    

    // Simulate success
    showToast = true;
     resetForm();
    setTimeout(() => (showToast = false), 3000);
    
  }

  
</script>

<style>
  /* Toast animation (optional) */
  .toast {
    animation: slideUpFade 0.3s ease-out;
  }

  @keyframes slideUpFade {
    0% {
      opacity: 0;
      transform: translateY(10px);
    }
    100% {
      opacity: 1;
      transform: translateY(0);
    }
  }
</style>

<!-- Main Banner Section -->
<div class="bg-cover h-[60vh] w-full md:items-center flex items-end md:justify-center bg-center relative" style="background-image: url({BgImage});">
  <h1 class="text-white tracking-wider font-[600] z-20 text-[1.5rem] p-8 sm:text-[2.5rem] md:text-[5.25rem] md:p-0 md:font-[600]">
    {talk}<span class="text-[#FFBA41] md:text-white">{to}</span> <span class="text-[#78C520] md:text-white">{us}</span>
  </h1>
</div>

<!-- Wave and Form Sectionfunction resetForm() {
    fullName = "";
    email = "";
    message = "";
    role = "";
    phone = "";
    acceptedPrivacy = false;
  } -->
<div class="relative">
  <div class="relative bg-white">
    <ContactUs />

    <!-- Form Content Section -->
    <div class="absolute inset-0 flex flex-col items-center max-w-[50rem] md:mx-auto mt-20 mb-16">
      <div class="text-center md:mb-8 mb-0 mx-8 md:mx-0">
        <h1 class="text-[#0D2460] text-[1.75rem] md:text-[3rem] sm:w-1/2 w-2/3 md:w-full mx-auto font-[600]">
          {getInTouchHeading}<span class="text-[#0155BD]">{withText} {us}</span>
        </h1>
        <p class="text-[#24272A]">{getInTouchDescription}</p>
      </div>

      <div class="md:bg-white w-full md:mx-8 mx-0 p-4 rounded-xl">
        <p class="text-[#24272A] text-[1.25rem] text-left hidden md:block mb-4 mt-2">{fillDetailsNote}</p>

        <div class="md:grid md:grid-cols-2 gap-4">
          <FormInput bind:value={fullName} data={{ name: "Full Name", placeholder: "Enter your full name", type: "text", required: true }} />
          <RoleSelector bind:value={role} />
          <FormInput bind:value={email} data={{ name: "Email", placeholder: "Enter your Email", type: "email", required: true }} />
          <ContactNumber bind:value={phone} />

          <div class="col-span-2 mt-4 md:mt-0">
            <p class="text-sm mb-2 text-[#3B3E43]">{messageLabel}</p>
            <textarea
              bind:value={message}
              placeholder={messagePlaceholder}
              class="text-[#0155BD] hover:border-[#0155BD] focus:outline-none focus:border-[#0155BD] md:bg-[#E8EBF1] bg-white w-full md:h-40 h-28 rounded-xl p-4 border resize-none"
            ></textarea>
          </div>

          <div class="col-span-2 flex gap-2 mt-4 md:mt-0">
            <input bind:checked={acceptedPrivacy} class="h-4 w-4 mx-2 col-span-1 my-auto rounded-2xl" type="checkbox" />
            <p class="text-[1rem] my-auto text-[#24272A]">{privacyNotice}</p>
          </div>

          <div class="col-span-2 text-center mt-4 md:mt-0">
            <button on:click={handleSubmit} class="bg-[#0155BD] rounded-3xl mx-auto w-28 h-12 text-white">
              {submitButtonLabel}
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Toast -->
  {#if showToast}
    <div class="fixed bottom-6 right-6 bg-[#78C520] text-white px-4 py-2 rounded-xl shadow-lg z-50 toast">
       Message sent successfully!
    </div>
  {/if}
</div>
