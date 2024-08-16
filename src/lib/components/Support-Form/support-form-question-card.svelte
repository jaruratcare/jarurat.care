<script>
  export let data;
  import CheckPrivacyIcon from '$lib/svg/check-privacy-icon.svelte';
</script>

<div class="mx-8 mt-8 border-b-2 pb-8 border-[#DBEDFD]">
  <CheckPrivacyIcon />
  <h1 class="mb-4 tracking-widest w-11/12">{data.question}</h1>
  <p class=" w-11/12 text-left">
    {data.answer}
  </p>
</div>
<!-- 
When can I expect updates on my support request?
Our team reviews requests promptly. You'll be notified within 48 hours about next steps or any additional information needed.
Can I connect with other cancer fighters or caregivers?
Yes, we offer peer support groups and mentorship programs. Indicate your interest in your profile, and we'll provide options while respecting everyone's privacy. -->
