import { loadTranslations, locale } from '$lib/translations/translations';

export async function load({ params, url }) {
	const pathSegments = url.pathname.split('/').filter(Boolean);
	const initLocale = pathSegments[0] === 'en' || pathSegments[0] === 'hi' ? pathSegments[0] : 'en';

	await loadTranslations(initLocale, url.pathname);
	locale.set(initLocale);

	return {
		props: {
			lang: initLocale
		}
	};
}
