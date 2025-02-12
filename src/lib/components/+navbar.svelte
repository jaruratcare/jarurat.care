<script>
    let languages = ["English", "Hindi", "Spanish"];
    let selectedLanguage = "English";
  </script>
  
  <nav class="bg-white shadow-md p-4">
    <div class="max-w-6xl mx-auto flex justify-between items-center">
      <!-- Logo -->
      <div class="flex items-center space-x-3">
        <img src="/JC_Logo.png" alt="Jarurat Care Foundation" class="h-8 w-auto">
      </div>
  
      <!-- Navigation Links -->
      <ul class="hidden md:flex space-x-6">
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">About Us</a></li>
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">Cancer Info</a></li>
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">Get Involved</a></li>
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">News & Blogs</a></li>
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">Seek Support</a></li>
        <li><a href="#" class="text-[#0D2460] font-bold hover:text-blue-500">Contact Us</a></li>
      </ul>
  
      <div class="flex items-center space-x-4">
        <select  bind:value={selectedLanguage} class="text-[#0155BD]  rounded-3xl px-3 py-1 hover:bg-[#0155BD] hover:text-white trasnition duration-500">
          {#each languages as lang}
            <option value={lang} class="bg-white text-[#0155BD] hover:bg-white">{lang}</option>
          {/each}
        </select>
        <a href="#" class="bg-[#0155BD] text-white px-4 py-1 rounded-3xl hover:bg-[#FFD700]">Donate Now</a>
      </div>
    </div>
  </nav>
  
  