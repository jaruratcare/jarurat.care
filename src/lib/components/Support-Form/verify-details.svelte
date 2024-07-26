<script>

  import FormInput from "./form-input.svelte";
import EditIcon from "$lib/svg/edit-icon.svelte";
</script>

<div class="flex flex-col items-center text-center">
  <h1 class="text-xl font-bold text-[#0D2561] tracking-widest">Verify Details</h1>
  <p class=" w-1/2 mt-2 text-slate-500">Please review all the information below carefully to ensure it is accurate and complete. Correct any errors and make sure all fields are filled in properly.</p>
</div>
<div class="grid grid-cols-3 gap-4   my-16   mx-32">
   <div class=" col-span-2 border-[#DBEDFD] border-2 rounded-xl ">
 <div class="bg-[#DBEDFD] flex justify-between items-center h-16 px-4">
  <h1 class="text-2xl text-[#0D2561] font-bold">Personal Details</h1>
  <div class="flex items-center space-x-2">
    <span class="text-blue-500 text-xl">Edit</span>
    <EditIcon />
  </div>
</div>
        <div class="grid grid-cols-2 gap-4 p-4">
          <FormInput data={{ name: "Full Name", value:"Aarthi Reddy", type: "text", required: true }} />
                <div class="grid grid-cols-2 gap-4">
            <FormInput data={{ name: "Gender", value:"Female", type: "text", required: true }} />
          <FormInput data={{ name: "Date of Birth", value:"26/07/1982", type: "date", required: true }} />
          </div>
            <FormInput data={{ name: "Email", value:"blue.sama007@gmail.com", type: "email", required: true }} />
          <FormInput data={{ name: "Phone no", value:"+91 7988908909", type: "tel", required: true }} />
          <FormInput data={{ name: "Address line 1", value:"TC3-120, Mg college, calicut", type: "text", required: true }} />
          <FormInput data={{ name: "Address line 2", value:"-", type: "text",  }} />
            <FormInput data={{ name: "Country", value:"India", type: "text", required: true }} />
                <div class="grid grid-cols-2 gap-4">
            <FormInput data={{ name: "City/Town", value:"Calicut", type: "text", required: true }} />
          <FormInput data={{ name: "Pincode", value:"120456", type: "text", required: true }} />
          </div>
        </div>
   </div>
   <div class="border col-span-1 border-[#DBEDFD] border-2 rounded-xl">
<div class="bg-[#DBEDFD] flex justify-between items-center h-16 px-4 ">
  <h1 class="text-2xl text-[#0D2561] font-bold">Personal Details</h1>
  <div class="flex items-center space-x-2">
    <span class="text-blue-500 text-xl">Edit</span>

     <EditIcon />

  </div>
</div>
<div class="p-4">
<label  for="" class=" mt-8 bg-gray-100 inline-flex justify-between w-full items-center rounded-lg p-4 border    has-[:checked]:bg-indigo-50 has-[:checked]:font-bold   cursor-pointer has-[:checked]:transition-all has-[:checked]:duration-500 duration-500 relative [&amp;_p]:has-[:checked]:translate-y-0 [&amp;_p]:has-[:checked]:transition-transform [&amp;_p]:has-[:checked]:duration-500 [&amp;_p]:has-[:checked]:opacity-100 overflow-hidden">
  <div class="inline-flex items-center justify-center gap-2 relative">
    <h1>Patient Advocacy</h1>
  </div>
</label>
<p class="mt-12 ">Brief description</p>
 <textarea class="bg-gray-100 w-full h-40 rounded-xl p-4" value="I am writing to request support for patient advocacy on behalf of my mother, who is currently undergoing treatment for cancer. We have encountered numerous challenges navigating the healthcare system, including understanding complex medical information" name="" id=""></textarea>

</div>
   </div>
</div>
<button type="submit" class="flex items-center justify-center rounded-3xl px-8 py-2 mx-auto my-8 text-white bg-[#0155BD] cursor-pointer font-bold transition-colors duration-300 ease-in-out hover:bg-[#0148a2]">
        <p>Submit</p>
</button>
