<script>
  export let activeStep
  export let movenext
  export let data 

	import FormInput from "../form-input.svelte";
	import InputLogin from "./input-login.svelte";

</script>
  <div class={`${activeStep===4?"block":"hidden"}`}>
    <h2 class={`text-xl lg:text-2xl font-bold mb-6 text-center `}>Regarding Caregiving</h2>
         <form on:submit={()=>{
            console.log("final Submit",data);
             movenext()}}>
            <div class="grid gap-4 mb-6">
        <InputLogin bind:value={data.careType}  data={{color:true, name: "What type of care does your loved one require?", placeholder: "Care-Type", type: "text", required: true }} />
        <InputLogin bind:value={data.hospitalSchedule} data={{color:true, name: "How often do you visit hospitals?", placeholder: "Hospital Schedule", type: "text", required: true }} />
        <InputLogin bind:value={data.fullTimeCare} data={{color:true, name: "Are you available for caregiving full-time?", placeholder: "Yes/No", type: "text", required: true }} />
        <InputLogin bind:value={data.specialNeeds} data={{color:true, name: "Do you/your recipient have any special needs?", placeholder: "Needs Of Care Recipents", type: "text", required: true }} />
         </div>


      <button type="submit" class="w-full bg-[#D9D9D9] h-10 flex justify-center items-center text-black rounded-lg">
        Continue
      </button>
         </form>
  </div>