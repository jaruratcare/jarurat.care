<script>
  import { onMount, onDestroy } from "svelte";
  import ContactUs from "$lib/svg/contact-us.svelte";
  import ContactNumber from "../contact-number.svelte";
  import FormInput from "../form-input.svelte";
  import RoleSelector from "../ui/RoleSelector.svelte";
  import BgImage from "./image.png";

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

  // form values
  let fullName = "";
  let role = "";
  let email = "";
  let phone = "";
  let message = "";
  let agreed = false;

  let sending = false;
  let submitted = false;
  let globalError = "";

  let fieldErrors = {
    fullName: "",
    email: "",
    message: "",
    agreed: ""
  };

  const FORMSPREE_ENDPOINT = "https://formspree.io/f/xzdjkpde";

  // DOM wrappers (we will query real inputs inside children)
  let fullNameWrap;
  let roleWrap;
  let emailWrap;
  let phoneWrap;

  // keep references to listeners to remove onDestroy
  const _listeners = [];

  // helper: attach listener to first input/select/textarea found inside wrapper
  function attachInputListener(wrapper, setter) {
    if (!wrapper) return;
    const el = wrapper.querySelector("input, textarea, select");
    if (!el) return;
    // set initial value
    setter(el.value ?? "");
    const handler = (e) => {
      setter(e.target.value);
    };
    el.addEventListener("input", handler);
    _listeners.push({ el, handler });
  }

  onMount(() => {
    // attach listeners for each wrapper. Works even if components don't dispatch events or bind:value.
    attachInputListener(fullNameWrap, (v) => (fullName = v));
    attachInputListener(emailWrap, (v) => (email = v));
    attachInputListener(roleWrap, (v) => (role = v));
    attachInputListener(phoneWrap, (v) => (phone = v));
  });

  onDestroy(() => {
    // cleanup
    for (const { el, handler } of _listeners) {
      try { el.removeEventListener("input", handler); } catch {}
    }
  });

  // fallback read from wrappers (useful right before submit)
  function readFromWrappers() {
    try {
      const fnEl = fullNameWrap?.querySelector("input,textarea,select");
      if (fnEl) fullName = fnEl.value ?? fullName;

      const emEl = emailWrap?.querySelector("input,textarea,select");
      if (emEl) email = emEl.value ?? email;

      const rEl = roleWrap?.querySelector("input,select,textarea");
      if (rEl) role = rEl.value ?? role;

      const phEl = phoneWrap?.querySelector("input,textarea,select");
      if (phEl) phone = phEl.value ?? phone;
    } catch (e) {
      // ignore
    }
  }

  function validate() {
    fieldErrors = { fullName: "", email: "", message: "", agreed: "" };
    let ok = true;

    if (!fullName || !fullName.trim()) {
      fieldErrors.fullName = "Please enter your full name.";
      ok = false;
    }

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!email || !email.trim()) {
      fieldErrors.email = "Please enter your email.";
      ok = false;
    } else if (!emailPattern.test(email.trim())) {
      fieldErrors.email = "Enter a valid email.";
      ok = false;
    }

    if (!message || !message.trim()) {
      fieldErrors.message = "Please enter a message.";
      ok = false;
    } else if (message.trim().length < 10) {
      fieldErrors.message = "Message must be at least 10 characters.";
      ok = false;
    }

    if (!agreed) {
      fieldErrors.agreed = "Please accept privacy policy.";
      ok = false;
    }

    return ok;
  }

  async function handleSubmit(e) {
    e.preventDefault();
    globalError = "";
    submitted = false;

    // ensure we pulled the latest values from child DOMs
    readFromWrappers();

    if (!validate()) {
      // If validation failed, focus the first invalid field (nice UX)
      if (fieldErrors.fullName && fullNameWrap) {
        const el = fullNameWrap.querySelector("input,textarea,select");
        el?.focus();
      } else if (fieldErrors.email && emailWrap) {
        const el = emailWrap.querySelector("input,textarea,select");
        el?.focus();
      } else if (fieldErrors.message) {
        // message is native textarea below; we can focus it
        const m = document.querySelector("textarea[placeholder='" + messagePlaceholder + "']");
        m?.focus();
      }
      return;
    }

    sending = true;

    // double-read right before send (last-resort)
    readFromWrappers();

    const fd = new FormData();
    fd.append("full_name", fullName.trim());
    fd.append("role", role);
    fd.append("email", email.trim());
    fd.append("phone", phone.trim());
    fd.append("message", message.trim());
    fd.append("agreed", agreed ? "yes" : "no");

    try {
      const res = await fetch(FORMSPREE_ENDPOINT, {
        method: "POST",
        body: fd,
        headers: { Accept: "application/json" }
      });

      if (res.ok) {
        submitted = true;
        // show success briefly then reload
        setTimeout(() => location.reload(), 900);
      } else {
        // try parse
        let json = null;
        try { json = await res.json(); } catch {}
        globalError = (json && json.error) ? json.error : "Submission failed. Please try again later.";
      }
    } catch (err) {
      console.error(err);
      globalError = "Network error. Please check your connection and try again.";
    } finally {
      sending = false;
    }
  }
</script>

<style>
  .error {
    color: #c0392b;
    font-size: 0.85rem;
    margin-top: 4px;
  }

  .success {
    color: #1e7a2e;
    font-size: 1rem;
    font-weight: 500;
  }

  .input-like {
    padding: 0.75rem;
    border-radius: 0.75rem;
    border: 1px solid #d1d5db;
    width: 100%;
    transition: all 0.2s ease;
  }

  .input-like:focus {
    outline: none;
    border-color: #0155BD;
    box-shadow: 0 0 0 2px rgba(1, 85, 189, 0.15);
  }

  /* 🔴 error state */
  .input-error {
    border-color: #c0392b !important;
    background-color: #fff5f5;
  }

  /* 🟢 success state */
  .input-success {
    border-color: hsl(106, 100%, 50%) !important;
  }

  .btn {
    background: #0155BD;
    color: white;
    padding: 0.6rem 1.25rem;
    border-radius: 999px;
    transition: all 0.2s ease;
  }

  .btn:hover {
    background: #0144a0;
  }

  .btn:disabled {
    opacity: 0.6;
    cursor: not-allowed;
  }
</style>

<!-- Banner -->
<div class="bg-cover bg-center w-full flex items-end md:items-center md:justify-center h-[50vh]"
  style={`background-image: url(${BgImage})`}>
  <h1 class="text-white text-3xl md:text-6xl">
    {talk}
    <span class="text-[#FFBA41]">{to}</span>
    <span class="text-[#78C520]">{us}</span>
  </h1>
</div>

<div class="relative bg-white">
  <ContactUs />

  <div class="absolute inset-0 flex flex-col items-center max-w-[50rem] mx-auto mt-20">
    
    <h1 class="text-3xl text-center mb-4">
      {getInTouchHeading}
      <span class="text-[#0155BD]">{withText} {us}</span>
    </h1>

    {#if submitted}
      <div class="success mb-4 text-center">✅ Message sent successfully. Reloading…</div>
    {:else if globalError}
      <div class="error mb-4 text-center">❌ {globalError}</div>
    {/if}

    <form class="md:grid md:grid-cols-2 gap-4 w-full" on:submit|preventDefault={handleSubmit}>

      <!-- Full Name: wrapper ensures we can find the inner input -->
      <div bind:this={fullNameWrap}>
        <FormInput data={{ name: "Full Name", placeholder: "Enter your full name", type: "text", required: true }} />
        {#if fieldErrors.fullName}
          <div class="error mt-1">{fieldErrors.fullName}</div>
        {/if}
      </div>

      <!-- Role -->
      <div bind:this={roleWrap}>
        <RoleSelector />
      </div>

      <!-- Email -->
      <div bind:this={emailWrap}>
        <FormInput data={{ name: "Email", placeholder: "Enter your email", type: "email", required: true }} />
        {#if fieldErrors.email}
          <div class="error mt-1">{fieldErrors.email}</div>
        {/if}
      </div>

      <!-- Phone -->
      <div bind:this={phoneWrap}>
        <ContactNumber />
      </div>

      <!-- Message -->
      <div class="col-span-2 mt-4 md:mt-0">
        <p class="text-sm mb-2 text-[#3B3E43]">{messageLabel}</p>
        <textarea
          placeholder={messagePlaceholder}
          class="text-[#0155BD] hover:border-[#0155BD] focus:outline-none focus:border-[#0155BD] md:bg-[#E8EBF1] bg-white w-full md:h-40 h-28 rounded-xl p-4 border resize-none"
          bind:value={message}
          name="message"
          required
        ></textarea>
        {#if fieldErrors.message}
          <div class="error mt-1">{fieldErrors.message}</div>
        {/if}
      </div>

      <!-- Privacy checkbox -->
      <div class="col-span-2 flex gap-2 mt-4 md:mt-0 items-start">
        <input class="h-4 w-4 mx-2 col-span-1 my-auto rounded-2xl" type="checkbox" bind:checked={agreed} />
        <p class="text-[1rem] my-auto text-[#24272A]">{privacyNotice}</p>
      </div>
      {#if fieldErrors.agreed}
        <div class="col-span-2 error">{fieldErrors.agreed}</div>
      {/if}

      <!-- Submit -->
      <div class="col-span-2 text-center mt-4 md:mt-0">
        <button class="btn" type="submit" disabled={sending || submitted}>
          {#if sending}Sending...{:else}{submitButtonLabel}{/if}
        </button>
      </div>

    </form>
  </div>
</div>