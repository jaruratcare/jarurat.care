<script>
  export let activeStep
  export let movenext
  export let data
	import InputLogin from "./input-login.svelte";

</script>
  <div class={`${activeStep===2?"block":"hidden"}`}>
    <h2 class={`text-xl lg:text-2xl font-bold mb-6 text-center `}>Basic Information</h2>
    <form  on:submit={()=>{
      console.log(data);
      movenext()}} >
         <div class="grid gap-4 mb-6">
        <InputLogin bind:value={data.location} data={{color:true, name: "Where are you located?", placeholder: "Location", type: "text", required: true }} />
        <InputLogin bind:value={data.recipent} data={{color:true, name: "Who are you caring for?", placeholder: "Care-Recipent", type: "text", required: true }} />
        <InputLogin bind:value={data.supportType} data={{color:true, name: "What type of support do you need?", placeholder: "Support Type", type: "text", required: true }} />
        <InputLogin bind:value={data.primaryChallenge} data={{color:true, name: "What is your primary challenge?", placeholder: "Primary Challenge", type: "text", required: true }} />
         </div>


      <button type="submit" class="w-full bg-[#D9D9D9] h-10 flex justify-center items-center text-black rounded-lg">
        Continue
      </button>
      </form>
  </div>