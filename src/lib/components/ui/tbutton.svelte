<script lang="ts">
    import { cn } from '$lib/utils';
    import { createEventDispatcher } from 'svelte';
  
    let className = '';
    export let color = "white"; // Default button color as white
    export { className as class };
  
    const dispatch = createEventDispatcher();
  
    function onClick(ev: MouseEvent & { currentTarget: EventTarget & HTMLButtonElement }) {
      dispatch('click', ev);
    }
  </script>
  
  <button
    on:click={onClick}
    class={cn(
      'py-3 px-6 rounded-full font-semibold inline-block relative overflow-hidden',
      className
    )}
    style="color: {color}; border-color: {color};"
  >
    <slot />
  </button>
  
  <style>
    button {
      position: relative;
      overflow: hidden; /* Prevents overflow during hover animation */
      border-width: 2px;
      border-radius: 50px;
      width: 100%;
      height: 100%;
      font-size: 1rem;
      cursor: pointer;
      background-color: transparent;
    }
  
    button::before {
      display: block;
      background-color: transparent; /* Ensure no background color */
      width: 100%;
      height: 100%;
      content: '';
      position: absolute;
      z-index: -20;
      top: 0;
      left: 0;
      border-radius: 50px;
    }
  
    button::after {
      display: block;
      background-color: #ffba41; /* Default hover animation color */
      width: 100%;
      height: 100%;
      content: '';
      position: absolute;
      z-index: -10;
      top: 0;
      left: 0;
      border-radius: 10rem; /* Ensure rounded shape */
      transition: transform 0.3s cubic-bezier(0.5, 0.36, 0.22, 1);
      transform: scale(0);
      transform-origin: center;
    }
  
    button:hover::after {
      transform: scale(2); /* Scale animation on hover */
    }
  
    button:hover {
      color: #ffffff; /* Optional: Change text color on hover */
    }
  </style>
  