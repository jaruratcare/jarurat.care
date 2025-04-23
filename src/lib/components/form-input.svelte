<script>
  export let data;
  export let value;

  let showIcon = data.name === "Address line 2";

  // Compute the input type to prevent direct binding to `type`
  let inputType = data.type || 'text'; // Default to 'text' if no type is specified
</script>

<style>
  .input-group:hover .label,
  .input:focus {
    background-color: #D3F2FC;
    border: 1px solid #0155BD;
  }

  input::placeholder {
    color: #3B3E43;
    font-weight: 600;
  }

  input:focus {
    background-color: #D3F2FC;
    border: 2px solid #0155BD;
  }

  @media (max-width: 600px) {
    input::placeholder {
      color: #676E73;
      font-weight: 400;
    }
  }

  input:focus::placeholder, textarea:focus::placeholder {
    color: #0155BD;
  }
</style>

<div class="relative my-4 md:my-0">
  <label for={data.name} class="block mb-1 text-sm font-[400] text-[#676E73] transition-colors duration-300 ease-in-out">
    {data.name}
    {#if data.required}
      <span class="text-red-500 ml-1">*</span>
    {/if}
  </label>
  <input
    id={data.name}
    bind:value={value} 
    required={data.required}
    placeholder={data.placeholder}
    {inputType} 
    class={`w-full text-[#0155BD] md:text-[1rem] text-[1.25rem] h-12 md:bg-[#E8EBF1] rounded-lg px-4 ${showIcon ? 'pr-12' : ''} border-2 border-transparent text-base transition-all duration-300 ease-in-out hover:border-[#0155BD] focus:outline-none focus:border-[#0155BD]`}
  />
</div>
