/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx,svelte}"],
  theme: {
    extend: {
      colors: {
        "custom-blue": "rgb(19 36 66 / var(--tw-bg-opacity, 1))",
      },
    },
  },
  plugins: [],
};
