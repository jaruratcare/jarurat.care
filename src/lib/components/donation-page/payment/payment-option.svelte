<script>
  import RightArrow from '$lib/svg/donate-page-svgs/right-arrow.svelte';
  import RightChekc from '$lib/svg/donate-page-svgs/right-chekc.svelte';
  const billingAmount = [
    {
      id: 1,
      amount: 200
    },
    {
      id: 1,
      amount: 500
    },
    {
      id: 1,
      amount: 1000
    },
    {
      id: 1,
      amount: 2000
    },
    {
      id: 1,
      amount: 5000
    },
    {
      id: 1,
      amount: 10000
    }
  ];
</script>

<div id="paymentOption" class="w-[37%] bg-white py-12 px-16 rounded-3xl mt-32 mb-2 shadow-md">
  <h2 class="text-[1.4rem] text-[#0D2561] font-semibold" style="font-family: 'Rubik', sans-sarif;">
    Billing Options
  </h2>
  <div class="w-full flex justify-between mt-3">
    <button
      class="w-[48%] py-2 text-[#0155BD] text-xl font-semibold bg-[#DBEDFD] flex justify-evenly items-center rounded-lg border border-[#0155BD]"
      >Pay Once
      <span><RightChekc /></span>
    </button>
    <button
      class="w-[48%] py-1 text-[#0155BD] text-xl font-semibold bg-[#DBEDFD] flex justify-evenly items-center rounded-lg border border-[#0155BD]"
      >Pay Monthly
      <span><RightChekc /></span>
    </button>
  </div>

  <div class="w-full mt-12">
    <h2
      class="text-[1.4rem] text-[#0D2561] font-semibold"
      style="font-family: 'Rubik', sans-sarif;"
    >
      Donation Amount
    </h2>
    <p class="  text-[#576171] font-400" style="font-family: 'Manrope', sans-sarif;">
      Choose the amount you would like to donate
    </p>
  </div>

  <div class="w-full flex flex-wrap mt-5">
    {#each billingAmount as amount}
      <button
        class="border border-[#0155BD] py-2 px-5 mr-4 my-2 font-semibold text-[#] bg-[#DBEDFD] rounded-lg"
        >₹{amount?.amount}</button
      >
    {/each}
    <button
      class="border-2 border-[#0155BD] py-2 px-5 mr-4 my-2 font-semibold text-[#0155BD] bg-[#DBEDFD] rounded-lg"
      >Custom Amount</button
    >
  </div>
  <div class="w-full flex justify-center items-center mt-11">
    <a href="/donor-details" class="w-[45%]"
      ><button
        class=" text-xl font-medium text-white py-1 px-3 w-full border-2 border-[#0155BD] bg-[#0155BD] rounded-full flex justify-evenly items-center"
        style="font-family: 'Monrope', sans-sarif;">Continue <span><RightArrow /></span></button
      ></a
    >
  </div>
</div>
