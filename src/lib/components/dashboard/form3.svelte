<script>
  export let activeStep
  export let movenext
export let data

	import FormInput from "../form-input.svelte";
	import InputLogin from "./input-login.svelte";
	import InputNumber from "./input-number.svelte";

</script>
  <div class={`${activeStep===3?"block":"hidden"}`}>
    <h2 class={`text-xl lg:text-2xl font-bold mb-6 text-center `}>Financial Situation</h2>
    <form  on:submit={()=>{ movenext()}}>
         <div class="grid gap-4 mb-6">
        <InputNumber bind:value={data.income} data={{color:true, name: "What is your income level?", placeholder: "Monthly Income", type: "number", required: true }} />
        <InputLogin  bind:value={data.govSchemesUsing} data={{color:true, name: "Do you use government schemes?", placeholder: "Yes/No", type: "text", required: true }} />
        <InputLogin  bind:value={data.govSchemesHelp} data={{color:true, name: "Do you need help applying for the schemes?", placeholder: "Yes/No", type: "text", required: true }} />
        <InputNumber bind:value={data.priceRange} data={{color:true, name: "What is the preferred price range?", placeholder: "Price Range", type: "text", required: true }} />
         </div>


      <button type="submit" class="w-full bg-[#D9D9D9] h-10 flex justify-center items-center text-black rounded-lg">
        Continue
      </button>
   </form>
  </div>