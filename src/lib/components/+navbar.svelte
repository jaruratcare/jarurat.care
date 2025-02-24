<script>
    let languages = ["English", "Hindi", "Spanish"];
    let selectedLanguage = "English";
  </script>
  
  <nav class="bg-white shadow-md p-4">
    <div class="max-w-6xl mx-auto flex justify-between items-center">
      <div class="flex items-center space-x-3">
        <img src="/JC_Logo.png" alt="Jarurat Care Foundation" class="h-8 w-auto">
      </div>
      <ul class="hidden md:flex space-x-6">
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">About Us</a></li>
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">Cancer Info</a></li>
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">Get Involved</a></li>
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">News & Blogs</a></li>
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">Seek Support</a></li>
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">Contact Us</a></li>
      </ul>
  
      <div class="flex items-center space-x-4">
        <div class="relative group">
          <select bind:value={selectedLanguage} class="text-[#0155BD] appearance-none rounded-3xl px-3 py-1 hover:bg-[#0155BD] hover:text-white transition duration-500 pr-10">
            {#each languages as lang}
              <option value={lang} class="bg-white text-[#0155BD] hover:bg-white">{lang}</option>
            {/each}
          </select>
          <svg 
            class="absolute right-2 top-1/2 transform -translate-y-1/2 w-4 h-4 text-[#0155BD] transition-transform duration-300 ease-in-out group-hover:rotate-180 group-hover:text-white"
            xmlns="http://www.w3.org/2000/svg" 
            viewBox="0 0 20 20" 
            fill="currentColor">
            <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
          </svg>
        </div>
        <a href="#" class="bg-[#0155BD] text-white px-4 py-1 rounded-3xl hover:bg-[#FFD700] transition duration-500">Donate Now</a>
      </div>
      
    </div>
  </nav>
  
  