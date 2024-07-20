<script>
   export let data
</script>

  <div style="box-shadow: 0 2rem 3rem rgba(132, 139, 200, 0.18); border: 2px solid rgba(132, 139, 200, 0.18);" class=" grid grid-cols-[1fr_1.5fr] border border-black max-w-4xl min-h-[20rem] mx-auto rounded-lg transform transition-transform hover:scale-105 ">
    <img class="w-full h-full object-cover rounded-l-lg" src={data?.img} alt="">
    <div class="content">
      <div class="avatar m-4 flex flex-col items-start">
        <img class="h-16 w-16 rounded-full" src={data?.img} alt="">
        <h2 class="text-darkblue mt-2">{data?.name}</h2>
        <span class="text-darkblue mt-1">{data?.cancerType}, {data?.location}</span>
      </div>
      <div class="story flex items-start relative mt-4">
        <svg class="h-10 w-10 ml-8 flex-shrink-0" height="40px" width="40px" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32" xml:space="preserve" fill="#000000" transform="rotate(180)">
          <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
          <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g>
          <g id="SVGRepo_iconCarrier">
            <g>
              <g id="right_x5F_quote">
                <g>
                  <path style="fill:#2d9a4e;" d="M0,4v12h8c0,4.41-3.586,8-8,8v4c6.617,0,12-5.383,12-12V4H0z"></path>
                  <path style="fill:#2d9a4e;" d="M20,4v12h8c0,4.41-3.586,8-8,8v4c6.617,0,12-5.383,12-12V4H20z"></path>
                </g>
              </g>
            </g>
          </g>
        </svg>
        <p class="text-darkblue ml-12 mr-8 flex-grow">{data?.description}</p>
      </div>
    </div>
  </div>
