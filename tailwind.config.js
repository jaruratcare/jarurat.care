/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{html,js,svelte,ts}'],
  theme: { extend: {
    colors: {
      primaryBlue: " #0155BD",
    }
  } },
  plugins: [require('@tailwindcss/typography')]
  
};


