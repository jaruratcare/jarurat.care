<script>
  import { createEventDispatcher } from 'svelte';
  export let show = false;
  export let title = '';
  export let fields = [];

  const dispatch = createEventDispatcher();
  let submitted = false;

  function closeModal() {
    submitted = false; // reset when modal closes
    dispatch('close');
  }

  function handleSubmit(event) {
    event.preventDefault();
    submitted = true;
    // You can add backend submission logic here if needed
  }
</script>

{#if show}
  <div class="modal-overlay fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
    <div class="modal bg-white p-6 rounded-lg w-full max-w-md relative">
      <button class="absolute top-2 right-2 text-gray-500 hover:text-black" on:click={closeModal}>
        ✖
      </button>

      {#if submitted}
        <h2 class="text-xl font-bold mb-4 text-center">Thank you for your response!</h2>
        <p class="text-center mb-4">We’ll get back to you soon.</p>
        <div class="flex justify-center">
          <button class="bg-blue-500 text-white px-4 py-2 rounded" on:click={closeModal}>Close</button>
        </div>
      {:else}
        <h2 class="text-xl font-bold mb-4">{title}</h2>
        <form on:submit={handleSubmit}>
          {#each fields as field}
            <div class="mb-4">
              <label class="block font-semibold mb-1">{field.label}</label>
              {#if field.type === 'textarea'}
                <textarea class="w-full p-2 border rounded" rows="3" required></textarea>
              {:else}
                <input type={field.type} class="w-full p-2 border rounded" required />
              {/if}
            </div>
          {/each}
          <button type="submit" class="bg-blue-500 text-white py-2 px-4 my-3 border-[2px] border-double border-blue rounded-full">Submit</button>
        </form>
      {/if}
    </div>
  </div>
{/if}



<style>
	@keyframes fade-in {
		from { opacity: 0; transform: scale(0.9); }
		to { opacity: 1; transform: scale(1); }
	}
	.animate-fade-in {
		animation: fade-in 0.2s ease-out;
	}
</style>



