<script>
  
  export let value;

 </script>

<style>
  input:focus {
    background-color: #D3F2FC; /* Change to the desired background color when selected */
       border: 2px solid #0155BD;
  }
  
   

  input:focus::placeholder, textarea:focus::placeholder {
    color: #0155BD; /* Change placeholder color when input is focused */
  }
  @media (max-width: 600px){
    input::placeholder {
    color: #676E73;
    font-weight: 400;
  }
  }
  input:focus::placeholder, textarea:focus::placeholder {
    color: #0155BD; /* Change placeholder color when input is focused */
  }
</style>
<div class=" rounded-lg">
            <label class="text-[#3B3E43] text-sm mb-1 ">
                Phone number
            </label>
             <span class="text-red-500 ml-1">*</span>
            <div class="relative  text-[#3B3E43]">
                <input bind:value={value} 
                type="tel"  placeholder="Enter Your Phone Number" class="w-full pl-[0.5rem] pr-3 py-[0.75rem] md:text-[1rem] text-[1.25rem] appearance-none md:bg-[#E8EBF1] bg-white outline-none border focus:border-slate-600 shadow-sm rounded-lg">
            </div>
        </div>