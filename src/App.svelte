<script>
    // You can add logic for forms here if needed, such as handling the social login redirects.
  </script>
  
  <main class="flex justify-center items-center min-h-screen bg-white">
    <div class="flex flex-row max-w-screen-lg w-full bg-white rounded-lg  gap-x-10">
        <!-- Form Section -->
        <img src="/images/Jarurat_care_logo.png" alt="JaruratCareLogo" class="absolute top-15 left-20 w-44">
        <div class="flex-1 p-10 ml-10 mb-8 relative" style="margin-top: 40px;">
            <div class="mb-6 mr-20 ml-7">
                <h1 class="text-3xl text-indigo-900  mr-20">Sign In</h1>
            </div>
            <form class="flex flex-col w-full max-w-sm mx-auto">
              <label class="text-indigo-900 text-lg mb-2">Email Address</label>
              <input type="email" class="p-2 h-9 mb-3 border-2 border-indigo-900 rounded-lg focus:outline-none focus:border-blue-500" required />
              
              <label class="text-indigo-900 text-lg mb-1">Password</label>
              <input type="password" class="p-2 h-9 mb-2 border-2 border-indigo-900 rounded-lg focus:outline-none focus:border-blue-500" required />
          
              <div class="text-right mb-3">
                  <a href="#" class="text-blue-500 text-sm underline">Forgot Password</a>
              </div>
              <button type="submit" class="bg-indigo-900 text-white py-2 px-20 rounded-lg self-center hover:bg-orange-400 hover:text-black">Login</button>
          </form>
            <span class="text-center mt-6 text-indigo-900 block">or sign in with</span>
            <div class="flex justify-center gap-11 mt-4">
                <button class="bg-gray-300 w-24 h-12 rounded-lg flex justify-center items-center">
                    <img src="/images/google_img.png" alt="Google" class="w-6 h-6">
                </button>
                <button class="bg-gray-300 w-24 h-12 rounded-lg flex justify-center items-center">
                    <img src="/images/fb_img.png" alt="Facebook" class="w-6 h-6">
                </button>
                <button class="bg-gray-300 w-24 h-12 rounded-lg flex justify-center items-center">
                    <img src="/images/apple_img.png" alt="Apple" class="w-6 h-6">
                </button>
            </div>
            <div class="text-left mt-4 ml-8 text-indigo-900">
                Need an account? <span class="text-yellow-400 cursor-pointer underline">Sign Up here</span>
            </div>
        </div>
  
        <!-- Image Section -->
        <div class="flex-1 bg-white flex justify-center items-center" >
            <img src="/images/image.png" alt="TAPMI Image" class="object-contain w-full h-auto">
        </div>
    </div>
  </main>
  
  <style>
    /* Global Styles */
    body {
        font-family: Arial, sans-serif;
        margin:0;
        padding:0;
        color: white;
    }
  
    @media (min-width: 768px) {
      .flex-row {
        gap: 20px;
      }
    }
  </style>
  