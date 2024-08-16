<script lang="ts">
  import { Plus } from 'lucide-svelte';
  import { onMount } from 'svelte';
  import { writable } from 'svelte/store';

  export let question = '';
  export let answer = '';

  let isOpen = writable(false);

  let container: HTMLDivElement | null = null;
  let contentHeight = 0;

  onMount(() => {
    if (container) contentHeight = container.scrollHeight;
  });
</script>

<div class="bg-white sm:px-4 py-3 sm:rounded-lg border-b sm:border">
  <button
    class="flex items-center justify-between cursor-pointer w-full text-[#0D2561] font-medium text-[0.9em] text-left gap-4"
    on:click={() => isOpen.set(!$isOpen)}
  >
    {question}
    <Plus class={`text-[#0D2561] ${$isOpen ? 'rotate-180' : ''} transition-transform`} />
  </button>

  <div
    bind:this={container}
    style="height: {$isOpen ? contentHeight : 0}px"
    class="overflow-hidden transition-all text-gray-700 text-[0.8em]"
  >
    <p class="mt-2 pt-2 border-t">{answer}</p>
  </div>
</div>
