<script>
  import LogoSquare from '$lib/svg/logo-square.svelte';
  import { ArrowUp } from 'lucide-svelte';
  import Button from './ui/button.svelte';
</script>

<div class="bg-[#0D2561] text-white p-4 sm:p-12 flex flex-col gap-8">
  <div class="flex items-center">
    <LogoSquare class="max-w-[2.5em] sm:max-w-[3.5em] w-full" />
    <div class="flex flex-col gap-1 leading-[1] text-[1.2em] md:text-[2em] ml-4">
      <span class="font-semibold">Jaruratcare Foundation</span>
      <small class="text-[0.7em] text-[#BFE9E8]">Jaisi Jarurat, Vaisi Care</small>
    </div>

    <div class="grow"></div>

    <div class="relative z-10 text-[0.9em] gap-2 hidden sm:flex">
      <Button>Donate Now</Button>
      <Button>Seek Support</Button>
    </div>
  </div>

  <div class="">
    <div
      class="w-full h-0.5 opacity-30"
      style="background-image: linear-gradient(90deg, #00C8F4 0%, #24D0F5 33%, #D6B7FF 69%, #FFFFFF 94%);"
    ></div>

    <div
      class="text-[#2C72B9] mt-4 text-[0.8em] font-rubik flex flex-col sm:flex-row gap-2 sm:items-center"
    >
      <a href="/" target="_blank">Privacy Policy</a>
      <a href="/" target="_blank">Terms of service</a>
      <div class="grow"></div>
      <span>©2024 Jarurat Care</span>
      <div class="grow"></div>
      <button
        class="flex gap-1 text-[#FFBA41] justify-end"
        on:click={() => window.scrollTo({ top: 0, left: 0, behavior: 'smooth' })}
      >
        Back to top <ArrowUp />
      </button>
    </div>
  </div>
</div>
