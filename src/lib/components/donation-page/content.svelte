<script>
  import Ellipse2 from '$lib/svg/donate-page-svgs/ellipse2.svelte';
  import Ellipse3 from '$lib/svg/donate-page-svgs/ellipse3.svelte';
  import RightArrow from '$lib/svg/donate-page-svgs/right-arrow.svelte';
  import childImage from '../../../assets/images/child-image.png';
  import FundraiseImage from '../../../assets/images/fundraise-image.png';
  import Qrcode from '$lib/svg/donate-page-svgs/qrcode.svelte';
</script>

<div class="">
  <div class="relative w-full h-[100vh] px-48 flex justify-center items-center">
    <div class="absolute right-0">
      <Ellipse2 />
    </div>
    <div class="w-1/2 pr-28 z-20">
      <h1
        class="text-[2.5rem] text-[#0D2561] font-semibold"
        style="font-family: 'Rubik', sans-sarif;"
      >
        Sponsor
      </h1>
      <h2
        class="text-[1.6rem] text-[#0155BD] font-semibold"
        style="font-family: 'Rubik', sans-sarif;"
      >
        A Cancer Patient’s Care
      </h2>
      <p class="text-[#132442] font-medium mt-6" style="font-family: 'Manrope', sans-sarif;">
        Help improve the life of a cancer patient by sponsoring their care. Your donation provides
        essential support, medications, and therapies to those battling cancer. Every rupee counts.
        Make a difference and give hope to someone fighting cancer today.
      </p>
      <button
        class=" py-1 px-7 mt-8 text-xl text-white font-semibold bg-[#0155BD] rounded-full flex justify-between items-center border border-[#0155BD]"
        style="font-family: 'Manrope', sans-sarif;">Continue <span><RightArrow /></span></button
      >
    </div>
    <div class="w-1/2 flex justify-end z-20">
      <img src={childImage} width="81%" alt="child in hospital" />
    </div>
  </div>

  <!-------Donate Options------->
  <div class="w-full px-48 flex justify-between items-center">
    <!-- donate through UPI -->
    <div
      class="w-[48%] py-7 px-9 mb-0.5 border-2 border-[#DBEDFD] flex justify-center items-center shadow-md rounded-xl"
    >
      <div class="w-1/2">
        <p
          class="text-[#0D2561] text-[1.6rem] font-semibold"
          style="font-family: 'Rubik', sans-sarif;"
        >
          Donate
        </p>
        <p
          class="-mt-2 text-[#0155BD] text-[1.4rem] font-semibold"
          style="font-family: 'Rubik', sans-sarif;"
        >
          Through UPI
        </p>
        <p class="my-3 text-[#0D2561] text-[0.9rem]" style="font-family: 'Manrope', sans-sarif;">
          Make a difference today! Your UPI donation to Zarurat Care can help provide essential
          resources and services to those in need. Every rupee counts.
        </p>
        <p
          class="text-[0.9rem] font-semibold text-red-500"
          style="font-family: 'Manrope', sans-sarif;"
        >
          UPI donation limit is ₹1000
        </p>
        <button class="text-[#0155BD] py-1 px-4 mt-4 border border-[#0155BD] rounded-full"
          >How to Donate</button
        >
      </div>
      <div class="w-1/2 pl-6 flex flex-col items-center">
        <Qrcode />
        <span
          class="text-[#0D2561] text-[0.8rem] font-semibold mt-3"
          style="font-family: 'Manrope', sans-sarif;">Scan and pay with any UPI app</span
        >
      </div>
    </div>

    <!-- donate offline -->
    <div
      class="w-[48%] py-7 px-9 border-2 border-[#DBEDFD] flex justify-center items-center shadow-md rounded-xl"
    >
      <div class="">
        <p
          class="text-[#0D2561] text-[1.6rem] font-semibold"
          style="font-family: 'Rubik', sans-sarif;"
        >
          Donate Offline
        </p>
        <p
          class="-mt-2 text-[#0155BD] text-[1.4rem] font-semibold"
          style="font-family: 'Rubik', sans-sarif;"
        >
          Through Mail
        </p>
        <p class="my-3 text-[#0D2561] text-[0.95rem]" style="font-family: 'Manrope', sans-sarif;">
          Send a check or money order directly to our office.
        </p>
        <ol class="">
          <li class="text-[#0D2561] text-[0.85rem]" style="font-family: 'Manrope', sans-sarif;">
            1. Make your check or money order payable to "Zarurat Care."
          </li>
          <li class="text-[#0D2561] text-[0.85rem]" style="font-family: 'Manrope', sans-sarif;">
            2. Include a brief note with your name, address, and any special instructions.
          </li>
          <li class="text-[#0D2561] text-[0.85rem]" style="font-family: 'Manrope', sans-sarif;">
            3. Send your donation to the address below.
          </li>
        </ol>
        <div
          class="mt-3.5 flex flex-col text-[#0D2561] text-[0.85rem]"
          style="font-family: 'Manrope', sans-sarif;"
        >
          <span>JaruratCare</span>
          <span>123 Wellness Avenue, Sector 42</span>
          <span>Near Lotus Park, Gurugram, Haryana 122003</span>
          <span>India</span>
        </div>
      </div>
    </div>
  </div>

  <!-- ----fund raiser---- -->
  <div class="relative w-full h-[100vh] px-48 flex justify-center items-center overflow-hidden">
    <div class="absolute left-0 -bottom-28 z-0">
      <Ellipse3 />
    </div>

    <div class="w-1/2 flex justify-start z-20">
      <img src={FundraiseImage} width="81%" alt="child in hospital" />
    </div>

    <div class="w-1/2 pl-24 pr-6 z-20">
      <h1
        class="text-[2.5rem] text-[#0D2561] font-semibold"
        style="font-family: 'Rubik', sans-sarif;"
      >
        Fundraiser
      </h1>
      <h2
        class="text-[1.6rem] text-[#0155BD] font-semibold"
        style="font-family: 'Rubik', sans-sarif;"
      >
        A Cancer Patient’s Care
      </h2>
      <p class="text-[#132442] text-[1.05rem] mt-5" style="font-family: 'Manrope', sans-sarif;">
        Help save lives by contributing to a fundraising event. Whether it's a charity run, a bake
        sale, a community auction, or a themed party, your event can raise vital funds for people in
        need of life-saving treatments. Every contribution counts.
      </p>

      <div class="flex items-center mt-7">
        <a href="#donatenow">
          <button
            class=" py-2.5 px-8 mr-2 text-xl text-[#0155BD] font-semibold rounded-full flex justify-between items-center border-2 border-[#0155BD]"
            style="font-family: 'Manrope', sans-sarif;"
            >Make a donation
          </button>
        </a>
        <button
          class=" py-2.5 px-8 text-xl text-white font-semibold bg-[#0155BD] rounded-full flex justify-between items-center border border-[#0155BD]"
          style="font-family: 'Manrope', sans-sarif;"
          >Contact Us
        </button>
      </div>
    </div>
  </div>
</div>
