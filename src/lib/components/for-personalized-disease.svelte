<script>
    import Phone from "$lib/svg/icons/phone.svelte";
    import Message from "$lib/svg/icons/message.svelte";
    import Mail from "$lib/svg/icons/mail.svelte";
  </script>
  
  <div class="w-full max-w-full h-auto px-4 sm:px-8 md:px-16 py-8 md:py-16 bg-[#040e36] flex flex-col justify-center items-center">
      <div class="w-full flex-col justify-start items-center gap-6 md:gap-8 flex">
          <div class="text-center text-[#2cbfe2] text-sm sm:text-base md:text-lg lg:text-xl font-normal font-['Manrope']">
              For personalized disease, treatment or support information, <br class="hidden sm:inline" />
              please contact one of our Information Specialists:
          </div>
          <div class="flex flex-col md:flex-row justify-center items-center gap-4">
              <div class="px-5 py-3 md:px-7 md:py-4 rounded-full flex justify-center items-center gap-2 ">
                  <div class="w-6 h-6 sm:w-8 sm:h-8"><Phone/></div>
                  <div class="text-white text-sm sm:text-lg md:text-xl font-medium font-['Poppins']">+91-1234567890</div>
              </div>
              <div class="px-5 py-3 md:px-7 md:py-4 rounded-full flex justify-center items-center gap-2 ">
                  <div class="w-6 h-6 sm:w-8 sm:h-8"><Message/></div>
                  <div class="text-white text-sm sm:text-lg md:text-xl font-medium font-['Poppins']">Live Chat</div>
              </div>
              <div class="px-5 py-3 md:px-7 md:py-4 rounded-full flex justify-center items-center gap-2">
                  <div class="w-6 h-6 sm:w-8 sm:h-8"><Mail/></div>
                  <div class="text-white text-sm sm:text-lg md:text-xl font-medium font-['Poppins']">Email</div>
              </div>
          </div>
      </div>
  </div>
  