import i18n from 'sveltekit-i18n';
import lang from './lang.json';


/** @type {import('sveltekit-i18n').Config} */
export const config = {
  translations: {
    en: { lang },
    hi: { lang }
  },
  loaders: [
    {
      locale: 'en',
      key: 'menu',
      loader: async () => (await import('./en/menu.json')).default
    },
    {
      locale: 'en',
      key: 'about',
      routes: ['/about'],
      loader: async () => (await import('./en/about.json')).default
    },
    {
      locale: 'en',
      key: 'home',
      routes: ['/'],
      loader: async () => (await import('./en/home.json')).default
    },
    {
      locale: 'hi',
      key: 'menu',
      loader: async () => (await import('./hi/menu.json')).default
    },
    {
      locale: 'hi',
      key: 'about',
      routes: ['/about'],
      loader: async () => (await import('./hi/about.json')).default
    },
    {
      locale: 'hi',
      key: 'home',
      routes: ['/'],
      loader: async () => (await import('./hi/home.json')).default
    }
  ]
};

export const {
  t,
  loading,
  locales,
  locale,
  loadTranslations,
  addTranslations,
  translations,
  setLocale,
  setRoute
} = new i18n(config);

loading.subscribe(
  ($loading) => $loading && console.log('Loading translations for the main instance...')
);
