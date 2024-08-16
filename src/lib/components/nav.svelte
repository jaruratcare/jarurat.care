<script>
  import { onMount } from 'svelte';
  import { page } from '$app/stores';
  import { t, setLocale, locale } from '$lib/translations/translations.js';
  import logo from '../../assets/logo/image.webp';

  // Set a default locale (e.g., 'en')
  let defaultLocale = 'en';

  // Initialize currentLocale with the default value
  let currentLocale = defaultLocale;

  // Reactive variable for the current route
  $: routeId = $page.route.id;

  // Function to handle language change
  function handleLanguageChange(event) {
    const selectedLanguage = event.target.value;
    setLocale(selectedLanguage);

    // Save the selected language to local storage
    if (typeof window !== 'undefined') {
      localStorage.setItem('selectedLanguage', selectedLanguage);
    }

    // Update the currentLocale to reflect the change
    currentLocale = selectedLanguage;
  }

  // Load the language from local storage if available
  onMount(() => {
    const savedLanguage = localStorage.getItem('selectedLanguage');
    if (savedLanguage) {
      setLocale(savedLanguage);
      currentLocale = savedLanguage; // Update the currentLocale to avoid blank select
    } else {
      // If no language is saved, set the default locale
      setLocale(defaultLocale);
    }
  });
</script>

<nav id="navbar" class="w-full h-[72px] flex justify-between items-center bg-white p-4 px-16">
  <div class="logo">
    <img src={logo} alt="logo" class="h-12" />
  </div>
  <ul class="flex list-none m-0 p-0 gap-8">
    <li>
      <a
        href="/"
        class:active={routeId === '/'}
        class="text-blue-900 no-underline"
        class:font-bold={routeId === '/'}>{$t('home.nav.home')}</a>
    </li>
    <li>
      <a
        href="/about"
        class:active={routeId === '/about'}
        class="text-blue-900 no-underline"
        class:font-bold={routeId === '/about'}>{$t('home.nav.about')}</a>
    </li>
    <li>
      <a
        href="/news"
        class:active={routeId === '/news'}
        class="text-blue-900 no-underline"
        class:font-bold={routeId === '/news'}>{$t('home.nav.news')}</a>
    </li>
    <li>
      <a
        href="/getInvolved"
        class:active={routeId === '/getInvolved'}
        class="text-blue-900 no-underline"
        class:font-bold={routeId === '/getInvolved'}>{$t('home.nav.getInvolved')}</a>
    </li>
    <li class="mx-2">
      <a
        href="#contactUs"
        class:active={routeId === '/contact'}
        class="text-blue-900 no-underline"
        class:font-bold={routeId === '/contact'}>{$t('home.nav.contactUs')}</a>
    </li>
  </ul>
  <div class="flex gap-3 mr-7">
    <div
      class="relative flex items-center justify-between px-3 text-blue-800 rounded-full border-2 border-blue-800"
    >
      <select
        class="w-full p-2 cursor-pointer bg-transparent outline-none"
        name="lang"
        id="lang"
        on:change={handleLanguageChange}
        bind:value={currentLocale}
      >
        <option value="en">English</option>
        <option value="hi">हिन्दी</option>
      </select>
    </div>
    <div
      class="relative flex items-center justify-between px-3 bg-blue-800 text-white rounded-full border-2 border-blue-800"
    >
      <select class="w-full p-2 cursor-pointer bg-transparent outline-none" name="" id="">
        <option value="donate">{$t('home.nav.donate')}</option>
      </select>
    </div>
  </div>
</nav>
