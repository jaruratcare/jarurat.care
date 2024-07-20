<script>
   export let data
</script>

    <div class="bg-neutral-200 h-96 w-3/5 mx-auto flex items-center justify-center">
        <div class="w-1/2 h-full bg-white relative  ">
            <div style="transform: translateX(-50%);" class="h-28 bg-white w-28 absolute top-12 rounded-full flex items-center justify-center">
                <svg class="h-16 w-16 p-4 rounded-full bg-green-600" height="40px" width="40px" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32" xml:space="preserve" fill="#000000" transform="rotate(180)">
                    <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                    <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g>
                    <g id="SVGRepo_iconCarrier">
                        <g>
                            <g id="right_x5F_quote">
                                <g>
                                    <path style="fill:white;" d="M0,4v12h8c0,4.41-3.586,8-8,8v4c6.617,0,12-5.383,12-12V4H0z"></path>
                                    <path style="fill:white;" d="M20,4v12h8c0,4.41-3.586,8-8,8v4c6.617,0,12-5.383,12-12V4H20z"></path>
                                </g>
                            </g>
                        </g>
                    </g>
                </svg>
            </div>
            <p class="absolute top-24 left-12" >{data.Message}</p>
            <p class="absolute bottom-16 left-12 " >{data.Name}</p>
            <p class="absolute bottom-10 left-12  " >{data.Designation}</p>
        </div>
    </div>
