<script>
	let searchQuery = '';
	let subGroups = ['Sub Group 1', 'Sub Group 2', 'Sub Group 3', 'Sub Group 4'];
	import Navbar from '$lib/components/online-community/navbar.svelte';
</script>

<div class="w-full mx-auto p-6">
	<Navbar chatsAndgroupPage={true} />
	<div class="absolute z-0 top-0 left-0">
		<svg
			width="277"
			height="255"
			viewBox="0 0 277 255"
			fill="none"
			xmlns="http://www.w3.org/2000/svg"
		>
			<ellipse cx="46" cy="23" rx="231" ry="232" fill="#A4D9FB" fill-opacity="0.71" />
		</svg>
	</div>

	<div class="block md:flex md:justify-evenly mt-[90px] md:mt-[110px] lg:mt-[90px] md:space-x-32 lg:ml-[4rem]">
		<h1 class="text-2xl font-bold text-[#0D2460] mb-4 items-center">Chats and Group</h1>

	

		<div
			class="flex items-center  bg-white border-2 border-[#B0B6C0] rounded-2xl shadow-[0px_4px_8px_0px_rgba(0,123,255,0.4)] px-4 py-2 md:w-[480px] mb-6"
		>
        <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M24.6582 21.6162L19.79 16.748C19.5703 16.5283 19.2725 16.4062 18.96 16.4062H18.1641C19.5117 14.6826 20.3125 12.5146 20.3125 10.1562C20.3125 4.5459 15.7666 0 10.1562 0C4.5459 0 0 4.5459 0 10.1562C0 15.7666 4.5459 20.3125 10.1562 20.3125C12.5146 20.3125 14.6826 19.5117 16.4062 18.1641V18.96C16.4062 19.2725 16.5283 19.5703 16.748 19.79L21.6162 24.6582C22.0752 25.1172 22.8174 25.1172 23.2715 24.6582L24.6533 23.2764C25.1123 22.8174 25.1123 22.0752 24.6582 21.6162ZM10.1562 16.4062C6.7041 16.4062 3.90625 13.6133 3.90625 10.1562C3.90625 6.7041 6.69922 3.90625 10.1562 3.90625C13.6084 3.90625 16.4062 6.69922 16.4062 10.1562C16.4062 13.6084 13.6133 16.4062 10.1562 16.4062Z" fill="black"/>
            </svg>
            
			<input type="text" 
				class="w-full outline-none px-2  placeholder:font-bold placeholder:text-[#37393D] placeholder:text-md placeholder:pl-4"
                bind:value={searchQuery}
                placeholder="Search"
			/>
                
		</div>
	</div>

	
</div>
<div class="w-full middle bg-blue-100 py-10 flex justify-center items-center min-h-[150vh] md:min-h-screen rounded-lg">
    <div class="bg-white w-[70%] md:w-[80%] h-auto lg:h-[90vh]  p-6  shadow-lg">
        <div class="block md:flex md:flex-row lg:gap-20 md:items-center  md:space-x-5">
            <div class="flex-1 flex flex-col justify-center items-center text-center">
                <div class="bg-[#D9D9D9] w-40 h-40 md:w-60 md:h-60 lg:w-80 lg:h-80 rounded-full flex items-center justify-center text-lg font-semibold">
                    main group
                </div>
                
                <p class="text-blue-700 italic mt-6 font-bold md:w-50 lg:w-80">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae
                    vestibulum vestibulum. Cras venenatis euismod malesuada."
                </p>
        
                <button class="bg-[#065AC0] text-white font-semibold py-2 px-6 rounded-lg mt-4 shadow-md">
                    Join Now
                </button> 
            </div>
        
            <div class="flex-1 flex flex-col gap-4 mt-20 md:mt-auto">
                {#each subGroups as group}
                    <div class="block place-items-center md:flex md:items-center bg-white border-2 border-[#B0B6C0] shadow-[0px_4px_8px_0px_rgba(0,123,255,0.4)] rounded-lg p-4 lg:w-[480px] lg:ml-4">
                        <div class="bg-[#D9D9D9] w-20 h-20 rounded-full mr-3 flex-shrink-0"></div>
                        <div class="md:flex-1 lg:ml-12"> 
                            <span class="text-gray-700 font-medium">{group}</span>
                        </div>
                    </div>
                {/each}
            </div>
        </div>
        
    </div>
</div>
