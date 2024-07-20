/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{html,js,svelte,ts}'],
  theme: { extend: {
    colors:{
      'custom-blue':'#132442',
    },
  },
 },
  // eslint-disable-next-line
  plugins: [require('@tailwindcss/typography')]
};
