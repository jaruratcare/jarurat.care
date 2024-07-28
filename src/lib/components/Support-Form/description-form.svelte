<script>
  import ButtonRightArrowIcon from '$lib/svg/button-right-arrow-icon.svelte';

  const arr = [
    'Patient Advocacy',
    'Emotional Support',
    'Education Resources',
    'Cancer Connect',
    'Other'
  ];
</script>

<div class="flex justify-center items-center w-full my-16">
  <div class="w-full max-w-3xl">
    <div class="radio-section">
      <h1 class="text-xl font-bold text-[#0D2561] tracking-widest">Type of Support Needed</h1>
      <span class="tracking-tighter text-slate-500">Select the type of support you need</span>
      <div class="grid grid-cols-2 gap-8 my-16">
        {#each arr as data}
          <label
            class="inline-flex bg-blue-50 justify-between w-full items-center rounded-lg p-4 border border-transparent checked:border-indigo-500 checked:text-indigo-900 checked:bg-indigo-50 checked:font-bold hover:bg-slate-200 transition-all cursor-pointer duration-500 relative overflow-hidden"
          >
            <div class="inline-flex items-center justify-center gap-2 relative">
              <h1>{data}</h1>
            </div>
            <input
              class="checked:text-indigo-500 checked:ring-0 focus:ring-0"
              value={data}
              name="support"
              type="radio"
            />
          </label>
        {/each}
      </div>
      <h1 class="text-xl my-4 text-[#0D2561] font-bold">
        Brief Description <b class="text-slate-400 font-normal">(Optional)</b>
      </h1>
      <textarea
        placeholder="Describe your situation"
        class="p-2 border border-black rounded-xl w-full h-60"
      ></textarea>
      <button
        type="submit"
        class="mx-auto flex items-center justify-center rounded-3xl px-8 py-2 text-white bg-[#0155BD] cursor-pointer font-bold transition-colors duration-300 ease-in-out hover:bg-[#0148a2] my-12"
      >
        <p>Save and Continue</p>
        <ButtonRightArrowIcon />
      </button>
    </div>
  </div>
</div>
