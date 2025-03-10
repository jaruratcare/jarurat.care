<script>
    let searchQuery = "";
    import Navbar from "$lib/components/online-community/navbar.svelte";
</script>

<div class="w-full">
    <Navbar blogs={true} />
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
    <div
			class="flex items-center  bg-white border-2 border-[#B0B6C0] rounded-2xl shadow-[0px_4px_8px_0px_rgba(0,123,255,0.4)] px-4 py-2 md:w-[480px] mt-24 md:ml-20 xl:ml-16 relative z-10 "
		>
        <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M24.6582 21.6162L19.79 16.748C19.5703 16.5283 19.2725 16.4062 18.96 16.4062H18.1641C19.5117 14.6826 20.3125 12.5146 20.3125 10.1562C20.3125 4.5459 15.7666 0 10.1562 0C4.5459 0 0 4.5459 0 10.1562C0 15.7666 4.5459 20.3125 10.1562 20.3125C12.5146 20.3125 14.6826 19.5117 16.4062 18.1641V18.96C16.4062 19.2725 16.5283 19.5703 16.748 19.79L21.6162 24.6582C22.0752 25.1172 22.8174 25.1172 23.2715 24.6582L24.6533 23.2764C25.1123 22.8174 25.1123 22.0752 24.6582 21.6162ZM10.1562 16.4062C6.7041 16.4062 3.90625 13.6133 3.90625 10.1562C3.90625 6.7041 6.69922 3.90625 10.1562 3.90625C13.6084 3.90625 16.4062 6.69922 16.4062 10.1562C16.4062 13.6084 13.6133 16.4062 10.1562 16.4062Z" fill="black"/>
            </svg>
            
			<input type="text" 
				class="w-full outline-none px-2 placeholder:font-bold placeholder:text-[#37393D] placeholder:text-md placeholder:pl-4"
                bind:value={searchQuery}
                placeholder="Search"
			/>
                
		</div>
</div>
<div class="min-h-screen bg-[#A4D9FB] mt-5">
    <div class="block  md:flex justify-between md:gap-x-10 xl:gap-x-0">
        <div class="md:w-[50%] md:h-[70%] xl:h-auto left-container  space-y-6 mt-5">
            <div class="first-card flex md:items-start md:space-x-4 h-[25%] bg-white rounded-2xl md:ml-10 xl:ml-16 p-4 shadow">
                <div class="image bg-[#D9D9D9] h-[80px] w-[100px] m-2 ml-4">
                    <img src="" alt="image" class="h-full w-full object-cover text-center"/>
                </div>
                <div class="w-full">
                    <p class="font-semibold  xl:w-[65%]"><span class="text-xs font-semibold block">Date</span> 
                        Lorem ipsum dolor sit amet,
                        consectetur adipiscing elit.Vivamus
                        lacinia odio vitae vestibulum 
                        vestibulum.Cras venenatis euismod 
                        malesuada."</p>
                </div>
                
            </div>
            <div class="xl:w-[88%] md:ml-[50px] xl:ml-[85px] border-b-2 border-[#0D2460]"></div>
            <div class="second-card flex md:items-start md:space-x-4 h-[25%] bg-white  rounded-2xl md:ml-10 xl:ml-16 p-4 shadow">
                <div class="image bg-[#D9D9D9] h-[80px] w-[100px] m-2 ml-4">
                    <img src="" alt="image" class="h-full w-full object-cover text-center"/>
                </div>
                <div class="w-full">
                    <p class="font-semibold  xl:w-[65%]"><span class="text-xs font-semibold block">Date</span> 
                        Lorem ipsum dolor sit amet,
                        consectetur adipiscing elit. Vivamus
                        lacinia odio vitae vestibulum 
                        vestibulum.Cras venenatis euismod 
                        malesuada."</p>
                </div>
                
            </div>
            <div class="xl:w-[88%] md:ml-[50px] xl:ml-[85px] border-b-2 border-[#0D2460]"></div>

            <div class="third-card flex md:items-start md:space-x-4 h-[25%] bg-white rounded-2xl md:ml-10 xl:ml-16 p-4 shadow">
                <div class="image bg-[#D9D9D9] h-[80px] w-[100px] m-2 ml-4">
                    <img src="" alt="image" class="h-full w-full object-cover text-center"/>
                </div>
                <div class="w-full">
                    <p class="font-semibold   xl:w-[65%]"><span class="text-xs font-semibold block">Date</span> 
                        Lorem ipsum dolor sit amet,
                        consectetur adipiscing elit. Vivamus
                        lacinia odio vitae vestibulum 
                        vestibulum.Cras venenatis euismod 
                        malesuada."</p>
                </div>
                
            </div>
            <div class="xl:w-[88%] md:ml-[50px] xl:ml-[85px] border-b-2 border-[#0D2460]"></div>

        </div>
        <div class="xl:w-2/5 h-2/5 right-container mt-5 bg-white rounded-xl md:mr-16">
            <img src="" alt="image" class="w-[70%] mx-auto bg-gray-200 h-48 mt-5"/>
            <p class="italic  font-semibold xl:w-[80%] xl:ml-16">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum.Cras venenatis euismod 
                malesuada."</p>   
        </div>
    </div>
    <div class="absolute top-[100px] left-0 md:left-auto md:right-[15%] md:top-[90%] xl:right-[28%] xl:top-[90%]">
        <img src="/blog_leaf_2.png" alt="image" class="h-[200px] w-[100px] md:h-[400px] md:w-[320px]"/> 
    </div>
    <div class="absolute top-[100px] right-0 md:right-[5%] md:top-[90%] xl:right-[20%] xl:top-[105%]">
        <img src="/blog_leaf.png" alt="image" class="h-[200px] w-[100px] md:h-[300px] md:w-[208px]"/>
            
    </div>      
</div>