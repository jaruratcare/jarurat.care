<script>
  import Button from './ui/button.svelte';
  import Header from './ui/header.svelte';
  import { t } from '$lib/translations/translations'; // Adjust path as necessary

  let language = 'en'; // Set the default language or get it from user settings
</script>

<div class="py-10 sm:py-32 px-2 bg-[#535963]">
  <div class="max-w-[45rem] w-full mx-auto px-4 py-8 sm:p-8 rounded-xl sm:rounded-3xl bg-white relative z-10">
    <Header title={$t('home.newsletter.title')} class="text-[0.8em] text-[#0D2561]" />

    <p class="max-w-[25rem] w-full mx-auto leading-[1.2] text-center text-[0.9em] text-[#6F737E]">
      {$t('home.newsletter.description')}
    </p>

    <form class="max-w-[30rem] mx-auto mt-12 flex items-stretch gap-2">
      <input
        type="email"
        placeholder={$t('home.newsletter.emailPlaceholder')}
        class="px-4 py-2 bg-[#EBF0F4] w-full grow text-manrope rounded-lg placeholder:text-[#9196A3] placeholder:text-manrope"
      />

      <Button class="text-[0.8em] rounded-lg px-4 py-2 min-w-[5rem] text-center">{$t('home.newsletter.submitButton')}</Button>
    </form>
  </div>
</div>
