<script>
  export let content;
</script>

<div class="relative w-full flex justify-center items-center">
  <!-- Back Layer -->
  <div
    class="back bg-[#0D2561] -rotate-1 rounded-2xl w-[90%] sm:w-[80%] h-[12rem] sm:h-[16rem] lg:h-[18rem]"
  ></div>

  <!-- Front Layer -->
  <div
    class="front bg-white absolute bottom-2 sm:bottom-4 left-1 sm:left-2 rounded-2xl w-[90%] sm:w-[80%] h-[12rem] sm:h-[16rem] lg:h-[18rem] flex justify-center items-center text-center px-6 py-4 shadow-md overflow-hidden"
  >
    <p class="text-sm sm:text-base lg:text-lg font-medium text-[#0D2561] break-words overflow-wrap">
      {content}
    </p>
  </div>
</div>
