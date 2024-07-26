<script>
  import LeftArrow from '$lib/svg/donate-page-svgs/left-arrow.svelte';
  import RightArrow from '$lib/svg/donate-page-svgs/right-arrow.svelte';
</script>

<div id="donor-details" class="relative w-[37%] bg-white rounded-3xl px-16 mt-32 mb-2 shadow-md">
  <div class="absolute top-3.5 left-5 cursor-pointer">
    <a href="/paymentOption"><LeftArrow /></a>
  </div>

  <div class="w-full flex justify-between items-center mt-20">
    <button class="w-[48%] text-lg py-3 px-3 bg-blue-200 rounded-lg">Pay Once</button>
    <button class="w-[48%] text-lg py-3 px-3 bg-blue-200 rounded-lg">₹ 200</button>
  </div>

  <form>
    <div class="mt-8">
      <h1 class="text-[#0D2561] text-2xl font-semibold" style="font-family: 'Rubik', sans-sarif;">
        Personal Details
      </h1>
      <p
        class="text-[#576171] text-[0.9rem] font-medium"
        style="font-family: 'Manrope', sans-sarif;"
      >
        Please fill in your details correctly
      </p>
    </div>

    <div class="w-full flex flex-col mt-7">
      <label
        for="name"
        class="text-medium mb-1 text-[#A1A6B5] font-medium after:content-['*'] after:text-red-500"
        style="font-family: 'Manrope', sans-sarif;">Full Name {' '}</label
      >
      <input
        type="text"
        name="name"
        id="name"
        placeholder="Enter your full name"
        required
        class=" bg-[#EBF0F4] py-3.5 px-5 rounded-lg mb-3"
      />

      <label
        for="email"
        class="text-medium mb-1 text-[#A1A6B5] font-medium after:content-['*'] after:text-red-500"
        style="font-family: 'Manrope', sans-sarif;">Email {' '}</label
      >
      <input
        type="email"
        name="email"
        id="email"
        placeholder="Enter your email"
        required
        class=" bg-[#EBF0F4] py-3.5 px-5 rounded-lg mb-3"
      />

      <label
        for="phone"
        class="text-medium mb-1 text-[#A1A6B5] font-medium after:content-['*'] after:text-red-500"
        style="font-family: 'Manrope', sans-sarif;">Phone No {' '}</label
      >
      <input
        type="number"
        name="phone"
        id="phone"
        placeholder="Mobile No."
        required
        class=" bg-[#EBF0F4] py-3.5 px-5 rounded-lg mb-3"
      />

      <label
        for="pan"
        class="text-medium mb-1 text-[#A1A6B5] font-medium after:content-['*'] after:text-red-500"
        style="font-family: 'Manrope', sans-sarif;">Pan {' '}</label
      >
      <input
        type="text"
        name="pan"
        id="pan"
        placeholder="Enter your Pan Number"
        required
        class=" bg-[#EBF0F4] py-3.5 px-5 rounded-lg mb-3"
      />

      <label
        for="amount"
        class="mb-1 text-[#0D2561] font-semibold"
        style="font-family: 'Manrope', sans-sarif;">Amount</label
      >
      <input
        type="text"
        value="₹200"
        name="amount"
        id="amount"
        placeholder="abcd"
        class="text-[#0D2561] text-lg font-semibold py-3 px-5 mb-3 outline-none border border-black rounded-lg"
      />

      <label
        for="message"
        class="text-medium mb-1 text-[#A1A6B5] font-medium"
        style="font-family: 'Manrope', sans-sarif;">Message</label
      >
      <textarea
        name="message"
        id="message"
        rows="6"
        placeholder="your message here "
        class="bg-[#EBF0F4] py-3.5 px-5 rounded-lg mb-3"
      ></textarea>
    </div>
  </form>

  <div class="w-full flex justify-between items-center mt-10 mb-12">
    <a href="/paymentOption"
      ><button
        class=" py-2.5 px-11 text-xl text-[#0155BD] font-semibold rounded-full border border-[#0155BD] transition ease 1 hover:bg-[#0155BD] hover:text-white"
        style="font-family: 'Manrope', sans-sarif;">Go back</button
      ></a
    >
    <button
      class=" py-1 px-5 text-xl text-white font-semibold bg-[#0155BD] rounded-full flex justify-center items-center border border-[#0155BD] transition ease 1 hover:bg-[#0222CD]"
      style="font-family: 'Manrope', sans-sarif;"
      >Proceed to Donate <span><RightArrow /></span></button
    >
  </div>
</div>
