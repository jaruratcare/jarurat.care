<script>
  import Phone from "$lib/svg/icons/phone.svelte";
  import Message from "$lib/svg/icons/message.svelte";
  import Mail from "$lib/svg/icons/mail.svelte";
</script>
<div class="w-full max-w-[1920px] h-[264px] px-16 py-16 bg-[#040e36] flex flex-col justify-center items-center">
    <div class="self-stretch flex-col justify-start items-center gap-[38px]">
        <div class="text-center text-[#2cbfe2] text-xl font-normal font-['Manrope']">
            For personalized disease, treatment or support information, <br />
            please contact one of our Information Specialists:
        </div>
        <div class="flex justify-center items-start gap-4">
            <div class="px-7 py-4 rounded-[45px] flex justify-center items-center gap-2">
                <div class="w-8 h-8 relative"><Phone/></div>
                <div class="text-white text-xl font-medium font-['Poppins']">+91-1234567890</div>
            </div>
            <div class="px-7 py-4 rounded-[45px]  flex justify-center items-center gap-2">
                <div class="w-8 h-8 relative"><Message/></div>
                <div class="text-white text-xl font-medium font-['Poppins']">Live Chat</div>
            </div>
            <div class="px-7 py-4 rounded-[45px]  flex justify-center items-center gap-2">
                <div class="w-8 h-8 relative"><Mail/></div>
                <div class="text-white text-xl font-medium font-['Poppins']">Email</div>
            </div>
        </div>
    </div>
</div>
