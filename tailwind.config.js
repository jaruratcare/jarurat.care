/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{html,js,svelte,ts}'],
  theme: { extend: {} },
  // eslint-disable-next-line
  plugins: [require('@tailwindcss/typography')]
};
