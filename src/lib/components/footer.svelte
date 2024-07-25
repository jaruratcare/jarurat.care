<script>
    import  Icon from '@iconify/svelte';
    import instagramIcon from '@iconify-icons/mdi/instagram';
    import twitterIcon from '@iconify-icons/mdi/twitter';
    import facebookIcon from '@iconify-icons/mdi/facebook';
  </script>
  
    
    <footer class="bg-[#132442] font-sans py-5">
      <div class="flex items-center mx-24 justify-between">
        <div class="flex items-center">
          <div class="logo mx-7">
            <img src="./images/logo.svg" alt="logo" height="85px" />
          </div>
          <div>
            <h1 class="text-[#D6E7EE] text-3xl">
              Jaruratcare Foundation
            </h1>
            <p class="text-[#BFE9E8] text-base">Jaisi Jarurat, Vaisi Care</p>
          </div>
        </div>
        <div class="flex space-x-8">
          <button class="bg-[#132442] text-white  py-2 px-6 rounded-full border border-white cursor-pointer">Donate Now</button>
          <button class="bg-[#0155BD] text-white  py-2 px-5 rounded-full border-none cursor-pointer">Seek Support</button>
        </div>
      </div>
    
      <hr class="my-5 mx-24 border-gray-300" />
    
      <div class="flex text-white text-sm justify-end py-5 mx-24 gap-5">
        <div class="flex flex-col space-y-2">
          <p>Home</p>
          <p>Mission</p>
          <p>Volunteer</p>
          <p>Locate Hospitals</p>
          <p>Terms & Conditions</p>
        </div>
        <div class="flex flex-col space-y-2">
          <p>About Us</p>
          <p>Blogs</p>
          <p>News</p>
          <p>Contact Us</p>
        </div>
        <div class="flex flex-col space-y-2">
          <p>Connect with us</p>
          <div class="flex space-x-5">
            <Icon icon={instagramIcon} class="icon h-7 cursor-pointer" />
            <Icon icon={twitterIcon} class="icon h-7 cursor-pointer" />
            <Icon icon={facebookIcon} class="icon h-7 cursor-pointer" />
          </div>
        </div>
      </div>
    
      <hr class="my-5 mx-24 border-gray-300" />
    
      <div class="flex text-[#204D80] justify-between mx-24 py-5">
        <div class="flex space-x-2 cursor-pointer">
          <p>Privacy policy</p>
          <p>Terms of service</p>
        </div>
        <div>
          <p>&copy;2024 HANDSCART LCC. All right reserved</p>
        </div>
        <div class="text-[#D8E74E] cursor-pointer">
          <p>Back to Top &uarr;</p>
        </div>
      </div>
    </footer>