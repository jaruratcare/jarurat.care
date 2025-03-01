<script>
    import Navbar from "$lib/components/+navbar.svelte";
    let location = "";
    let fundingOptions = [
      {
        name: "Global Hope Foundation",
        city: "Mumbai",
        type: "Grants",
        email: "care@cancercarefoundation.org",
        badge: "Non Profit",
        badgecolor: "#C6A8E4"
      },
      {
        name: "Education Empowerment Trust",
        city: "Delhi",
        type: "Sponsorship",
        email: "info@visionindiafoundation.org.in",
        badge: "Public/Private Trust",
        badgecolor: "#FDD5A7"
      },
      {
        name: "Community Development Fund",
        city: "Bhuban",
        type: "Grants",
        email: "team@udidfoundation.com",
        badge: "Govt Schemes",
        badgecolor: "#A8E6A3"

      },
      {
        name: "Corporate Social Responsibility",
        city: "Mumbai",
        type: "Grants",
        email: "csr@corporatefoundation.com",
        badge: "Pro-bono Treatment",
        badgecolor: "#FF736E"

      },
      {
        name: "Global Hope Foundation",
        city: "Mumbai",
        type: "Grants",
        email: "care@cancercarefoundation.org",
        badge: "Non Profit",
        badgecolor: "#C6A8E4"
      },
      {
        name: "Education Empowerment Trust",
        city: "Delhi",
        type: "Sponsorship",
        email: "info@visionindiafoundation.org.in",
        badge: "Public/Private Trust",
        badgecolor: "#FDD5A7"
      },
      {
        name: "Community Development Fund",
        city: "Bhuban",
        type: "Grants",
        email: "team@udidfoundation.com",
        badge: "Govt Schemes",
        badgecolor: "#A8E6A3"

      },
      {
        name: "Corporate Social Responsibility",
        city: "Mumbai",
        type: "Grants",
        email: "csr@corporatefoundation.com",
        badge: "Pro-bono Treatment",
        badgecolor: "#FF736E"

      },
      {
        name: "Global Hope Foundation",
        city: "Mumbai",
        type: "Grants",
        email: "care@cancercarefoundation.org",
        badge: "Non Profit",
        badgecolor: "#C6A8E4"
      },
      {
        name: "Education Empowerment Trust",
        city: "Delhi",
        type: "Sponsorship",
        email: "info@visionindiafoundation.org.in",
        badge: "Public/Private Trust",
        badgecolor: "#FDD5A7"
      },
      {
        name: "Community Development Fund",
        city: "Bhuban",
        type: "Grants",
        email: "team@udidfoundation.com",
        badge: "Govt Schemes",
        badgecolor: "#A8E6A3"

      },
      {
        name: "Corporate Social Responsibility",
        city: "Mumbai",
        type: "Grants",
        email: "csr@corporatefoundation.com",
        badge: "Pro-bono Treatment",
        badgecolor: "#FF736E"

      }
    ];

  let isDropdownOpen = false;
  function toggleDropDown(){
    isDropdownOpen = !isDropdownOpen;
    document.getElementById("dropdown")?.classList.toggle("hidden");
  }
  let isDropdownOpen1 = false;
  function toggleDropDown1(){
    isDropdownOpen1 = !isDropdownOpen1;
    document.getElementById("dropdown1")?.classList.toggle("hidden");
  }
  
  </script>
  
  <div class="min-h-screen bg-white">
    
    <Navbar />
    <div class="max-w-5xl mx-auto  bg-[#F1FCFF] mt-24 p-6 rounded-xl shadow-md">
      <div class="flex justify-center items-center mt-12 mb-6 relative">
        <h1 class="text-[50px]  font-extrabold text-center text-[#0D2460] mb-3">Explore Funding Opportunities</h1>
        <button class="absolute right-0 flex items-center font-bold text-[#37393D]">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M6.5 4L3 7L6.5 10.5" stroke="#37393D" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            <path d="M3 7H14.497C17.9385 7 20.861 9.81 20.995 13.25C21.137 16.885 18.1335 20 14.497 20H5.999" stroke="#37393D" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            </svg><span class="ml-2">Back</span> </button>
      </div>
      
      <div class="flex justify-center items-center mb-6 relative">
          
          <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg" class="size-6 absolute left-20 ml-10  text-[#1055BD]">
            <path d="M13.1988 11.6083H12.3648L12.0691 11.3234C12.7289 10.5574 13.2112 9.65499 13.4813 8.6809C13.7514 7.70682 13.8028 6.6851 13.6317 5.68886C13.1355 2.7555 10.6861 0.413034 7.72986 0.0542773C6.69056 -0.0771269 5.63496 0.0308227 4.64383 0.369866C3.6527 0.708909 2.75231 1.27006 2.01156 2.01038C1.2708 2.75069 0.709324 3.65056 0.370083 4.64111C0.0308408 5.63166 -0.0771721 6.68664 0.0543092 7.72533C0.413276 10.6798 2.75712 13.1278 5.6922 13.6237C6.68902 13.7947 7.71134 13.7434 8.68599 13.4734C9.66065 13.2034 10.5635 12.7215 11.3301 12.0621L11.6151 12.3575V13.1911L16.1022 17.6755C16.5351 18.1082 17.2425 18.1082 17.6753 17.6755C18.1082 17.2429 18.1082 16.536 17.6753 16.1033L13.1988 11.6083ZM6.86412 11.6083C4.23521 11.6083 2.11309 9.48746 2.11309 6.86009C2.11309 4.23273 4.23521 2.11185 6.86412 2.11185C9.49302 2.11185 11.6151 4.23273 11.6151 6.86009C11.6151 9.48746 9.49302 11.6083 6.86412 11.6083Z" fill="#1055BD"/>
            </svg>
        <input 
          type="text" 
          bind:value={location} 
          placeholder="Enter Your Location"
          class="w-[75%] mr-12 p-2  border-2 border-blue-600 rounded-3xl font-bold text-center focus:outline-none"
        />
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6 absolute right-0 mr-40 font-bold text-[#1055BD]">
          <path stroke-linecap="round" stroke-linejoin="round" d="M15 10.5a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
          <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 10.5c0 7.142-7.5 11.25-7.5 11.25S4.5 17.642 4.5 10.5a7.5 7.5 0 1 1 15 0Z" />
        </svg>
      </div>
      
      <div class="flex space-y-4">
        <div class="w-full lg:w-[20%] p-4 pt-5 ml-[10%]">
          
          <div class="flex items-center space-x-2 mb-4">
            <svg width="16" height="15" viewBox="0 0 16 15" fill="none" xmlns="http://www.w3.org/2000/svg" class="size-5 font-semibold text-[#5D5F64]">
              <path opacity="0.5" d="M15 7.82359H5.65027M2.35005 7.82359H1M2.35005 7.82359C2.35005 7.41059 2.52386 7.01451 2.83325 6.72247C3.14263 6.43044 3.56225 6.26637 3.99978 6.26637C4.43732 6.26637 4.85693 6.43044 5.16632 6.72247C5.4757 7.01451 5.64951 7.41059 5.64951 7.82359C5.64951 8.23659 5.4757 8.63267 5.16632 8.9247C4.85693 9.21674 4.43732 9.3808 3.99978 9.3808C3.56225 9.3808 3.14263 9.21674 2.83325 8.9247C2.52386 8.63267 2.35005 8.23659 2.35005 7.82359ZM15 12.5431H10.6502M10.6502 12.5431C10.6502 12.9562 10.4759 13.3527 10.1665 13.6448C9.85702 13.9369 9.43731 14.101 8.99968 14.101C8.56214 14.101 8.14253 13.9362 7.83314 13.6442C7.52376 13.3522 7.34995 12.9561 7.34995 12.5431M10.6502 12.5431C10.6502 12.13 10.4759 11.7342 10.1665 11.4421C9.85702 11.15 9.43731 10.9859 8.99968 10.9859C8.56214 10.9859 8.14253 11.1499 7.83314 11.442C7.52376 11.734 7.34995 12.1301 7.34995 12.5431M7.34995 12.5431H1M15 3.10409H12.6503M9.35005 3.10409H1M9.35005 3.10409C9.35005 2.69109 9.52386 2.29501 9.83325 2.00297C10.1426 1.71094 10.5622 1.54688 10.9998 1.54688C11.2164 1.54687 11.431 1.58715 11.6311 1.66541C11.8313 1.74367 12.0131 1.85837 12.1663 2.00297C12.3195 2.14757 12.441 2.31924 12.5239 2.50817C12.6068 2.6971 12.6495 2.89959 12.6495 3.10409C12.6495 3.30858 12.6068 3.51108 12.5239 3.70001C12.441 3.88894 12.3195 4.0606 12.1663 4.2052C12.0131 4.3498 11.8313 4.46451 11.6311 4.54277C11.431 4.62102 11.2164 4.6613 10.9998 4.6613C10.5622 4.6613 10.1426 4.49724 9.83325 4.2052C9.52386 3.91317 9.35005 3.51709 9.35005 3.10409Z" stroke="#37393D" stroke-opacity="0.75" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
              </svg>
              
            <h2 class="text-lg font-semibold text-[#b7b9bf]">Filter</h2>
          </div>
          <div class="pt-5 relative" id="dropdownButton">
            <div class="flex gap-11">
              <div  class="block font-semibold text-[#5D5F64] mb-3 mr-1">Location</div>
              <svg  width="8" height="6" viewBox="0 0 8 6" fill="none" xmlns="http://www.w3.org/2000/svg" class="size-3 items-center mt-2 cursor-pointer transition-tran" class:rotate-180={isDropdownOpen} on:click={toggleDropDown}>
                <path d="M4.36719 6L0.903086 0H7.83129L4.36719 6Z" fill="#5D5F64"/>
                </svg>
            </div>
            <div id="dropdown" class="rounded ml-4 hidden">
              <div class="flex">
                <div class="border-[1px] w-2 h-2 border-[#5D5F64] mt-2 mr-2 "></div>
                <div class="text-[#5D5F64] text-sm font-medium cursor-pointer">PAN India</div>
              </div>
              <div class="flex">
                <div class="border-[1px] w-2 h-2 border-[#5D5F64] mt-2 mr-2"></div>
                <div class="text-[#5D5F64] text-sm font-medium cursor-pointer">State Wise</div>
              </div>
            </div>
            <div class="w-[180px] border-b-[2px] border-[#5D5F64] mt-3"></div>
          </div>
          

          
          <div class="relative" id="dropdownButton1">
            <div class="flex gap-13">
              <div class="block  font-semibold text-[#5D5F64] mt-3">Funding Type</div>
              <svg width="8" height="6" viewBox="0 0 8 6" fill="none" xmlns="http://www.w3.org/2000/svg" class="items-center mt-5 ml-3.5 size-3 cursor-pointer transition-tran" class:rotate-180={isDropdownOpen1} on:click={toggleDropDown1}>
                <path d="M4.36719 6L0.903086 0H7.83129L4.36719 6Z" fill="#5D5F64"/>
                </svg>
            </div>
            <div  id="dropdown1" class="rounded ml-4 hidden">
              <div class="flex">
                <div class="border-[1px] w-2 h-2 border-[#5D5F64] mt-2 mr-2 "></div>
                <div class="text-[#5D5F64] text-xs font-medium cursor-pointer mt-1">Non Profit</div>
              </div>
              <div class="flex">
                <div class="border-[1px] w-2 h-2 border-[#5D5F64] mt-2 mr-1"></div>
                <div class="text-[#5D5F64] text-xs font-medium cursor-pointer mt-1 ml-1">Public/Private Trust</div>
              </div>
              <div class="flex">
                <div class="border-[1px] w-2 h-2 border-[#5D5F64] mt-2 mr-1"></div>
                <div class="text-[#5D5F64] text-xs font-medium cursor-pointer mt-1 ml-1">Government Schemes</div>
              </div>
              <div class="flex">
                <div class="border-[1px] w-2 h-2 border-[#5D5F64] mt-2 mr-1"></div>
                <div class="text-[#5D5F64]  text-xs font-medium cursor-pointer mt-1 ml-1">Pro-bono Treatment</div>
              </div>
              <div class="flex">
                <div class="border-[1px] w-2 h-2 border-[#5D5F64] mt-2 mr-1"></div>
                <div class="text-[#5D5F64] text-xs font-medium cursor-pointer mt-1 ml-1">Crowd Funding</div>
              </div>
            </div>
            <div class="w-[180px] border-b-[2px] border-[#5D5F64] mt-5"></div>
          </div>

        </div>
        <div class="funding-available">
          <div class="w-full lg:w-[77%] mt-6 lg:mt-0 lg:ml-6">
            <h1 class="text-2xl font-bold text-[#5D5F64]">Funding Available</h1>
            
            <div class="w-[130%] mt-4 space-y-4 overflow-y-auto h-[600px] pr-10 custom-scrollbar">
              {#each fundingOptions as fund}
              <div class="parent">
                <svg width="83" height="30" viewBox="0 0 83 30" fill="none" xmlns="http://www.w3.org/2000/svg" class="mr-1 mx-auto">
                  <path d="M0.873965 0.857001C0.554221 0.543463 0.776218 0 1.22404 0H65.6442C65.7652 0 65.8821 0.0438681 65.9732 0.123471L82.569 14.6235C82.7971 14.8227 82.7971 15.1773 82.569 15.3765L65.9732 29.8765C65.8821 29.9561 65.7652 30 65.6442 30H1.22404C0.776221 30 0.554222 29.4565 0.873966 29.143L14.9329 15.357C15.1328 15.161 15.1328 14.839 14.9329 14.643L0.873965 0.857001Z" fill="{fund.badgecolor}"/>
                  </svg>
              
                <div class="bg-white shadow-md p-4 rounded-lg flex flex-col lg:flex-row justify-between">
                  <div>
                    <div class="flex items-center">
                      <h3 class="text-lg font-bold text-[#1055BD]">{fund.name}</h3>
                      <svg width="14" height="14" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg" class="size-4 ml-2">
                        <path d="M9.40928 8.62605L9.40928 8.62603L9.40749 8.62547C9.23667 8.57155 9.05433 8.56544 8.88028 8.60781C8.70623 8.65018 8.5471 8.7394 8.42017 8.86581L8.42002 8.86595L7.63318 9.65279C6.25017 8.88226 5.10931 7.74139 4.33877 6.35838L5.11891 5.57824C5.3856 5.3239 5.48237 4.94582 5.36593 4.58356L5.36568 4.5828C5.15915 3.95746 5.05573 3.29724 5.05573 2.625C5.05573 2.36641 4.95301 2.11842 4.77016 1.93557C4.58731 1.75272 4.33931 1.65 4.08073 1.65H2.03906C1.78048 1.65 1.53248 1.75272 1.34963 1.93557C1.16679 2.11842 1.06406 2.36641 1.06406 2.625C1.06406 8.30939 5.688 12.9333 11.3724 12.9333C11.631 12.9333 11.879 12.8306 12.0618 12.6478C12.2447 12.4649 12.3474 12.2169 12.3474 11.9583V9.91667C12.3474 9.65808 12.2447 9.41008 12.0618 9.22724C11.879 9.04439 11.631 8.94167 11.3724 8.94167C10.7002 8.94167 10.0402 8.83826 9.40928 8.62605ZM4.62154 4.82614L4.6215 4.82615L4.62239 4.82863C4.6406 4.87963 4.6378 4.95395 4.57593 5.01588C4.5759 5.0159 4.57588 5.01593 4.57585 5.01596L3.42687 6.15911L3.37517 6.21055L3.40834 6.27549C4.38044 8.17845 5.80731 9.60522 7.71575 10.5889L7.78099 10.6225L7.83263 10.5703L8.96977 9.42154C8.96984 9.42148 8.9699 9.42141 8.96997 9.42135C9.02353 9.36792 9.09528 9.3514 9.16757 9.37074C9.87716 9.60696 10.6161 9.725 11.3724 9.725C11.4232 9.725 11.472 9.74519 11.5079 9.78114C11.5439 9.81708 11.5641 9.86583 11.5641 9.91667V11.9583C11.5641 12.0092 11.5439 12.0579 11.5079 12.0939C11.472 12.1298 11.4232 12.15 11.3724 12.15C6.11929 12.15 1.8474 7.8781 1.8474 2.625C1.8474 2.57417 1.86759 2.52542 1.90353 2.48947C1.93948 2.45353 1.98823 2.43333 2.03906 2.43333H4.08073C4.13156 2.43333 4.18031 2.45353 4.21626 2.48947C4.2522 2.52542 4.2724 2.57417 4.2724 2.625C4.2724 3.38215 4.39071 4.12183 4.62154 4.82614Z" fill="#1055BD" stroke="#1055BD" stroke-width="0.2"/>
                        </svg>
                        
                    </div>
                    <div class="flex items-center mr-1 gap-1">
                      <svg width="8" height="13" viewBox="0 0 8 13" fill="none" xmlns="http://www.w3.org/2000/svg" class="size-4">
                        <path d="M7.18308 5.72001L7.15427 5.78612L4 12.3887L0.845725 5.78611L0.816919 5.72001C0.613807 5.25394 0.5 4.73492 0.5 4.18643C0.5 2.13033 2.08972 0.5 4 0.5C5.91028 0.5 7.5 2.13033 7.5 4.18643C7.5 4.73492 7.38619 5.25394 7.18308 5.72001ZM4 1.77305C2.87477 1.77305 1.99995 2.72213 1.99995 3.84302C1.99995 4.9639 2.87477 5.91298 4 5.91298C5.12523 5.91298 6.00005 4.9639 6.00005 3.84302C6.00005 2.72213 5.12523 1.77305 4 1.77305Z" stroke="#1055BD"/>
                        </svg>
                      <p class="text-[#1055BD] font-bold">{fund.city}</p>
                    </div>
                    <div class="flex items-center mr-1 gap-1">
                      <svg width="11" height="11" viewBox="0 0 11 11" fill="none" xmlns="http://www.w3.org/2000/svg" class="size-4">
                        <path d="M5.4974 10.0807C8.0287 10.0807 10.0807 8.0287 10.0807 5.4974C10.0807 2.96609 8.0287 0.914062 5.4974 0.914062C2.96609 0.914062 0.914062 2.96609 0.914062 5.4974C0.914062 8.0287 2.96609 10.0807 5.4974 10.0807Z" stroke="#1055BD" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M0.914062 5.4974H10.0807M5.4974 0.914062C4.3205 2.1498 3.66406 3.7909 3.66406 5.4974C3.66406 7.20389 4.3205 8.84499 5.4974 10.0807C6.67429 8.84499 7.33073 7.20389 7.33073 5.4974C7.33073 3.7909 6.67429 2.1498 5.4974 0.914062Z" stroke="#1055BD" stroke-linecap="round" stroke-linejoin="round"/>
                        </svg>
                        <p class="text-[#1055BD] font-bold">
                          {fund.type}</p>
                    </div>
                    <div class="flex items-center mr-1 gap-1">
                      <svg width="10" height="9" viewBox="0 0 10 9" fill="none" xmlns="http://www.w3.org/2000/svg" class="size-4">
                        <g clip-path="url(#clip0_4602_22155)">
                        <path d="M0.9375 8.225H9.0625C9.63648 8.225 10.1 7.76148 10.1 7.1875V1.5625C10.1 0.988522 9.63648 0.525 9.0625 0.525H0.9375C0.363522 0.525 -0.1 0.988522 -0.1 1.5625V7.1875C-0.1 7.76148 0.363522 8.225 0.9375 8.225ZM0.725 1.5625C0.725 1.44273 0.817728 1.35 0.9375 1.35H9.0625C9.18227 1.35 9.275 1.44273 9.275 1.5625V7.1875C9.275 7.30727 9.18227 7.4 9.0625 7.4H0.9375C0.817728 7.4 0.725 7.30727 0.725 7.1875V1.5625Z" fill="#1055BD" stroke="#1055BD" stroke-width="0.2"/>
                        <path d="M3.7859 5.171L3.78591 5.17102C4.0816 5.48518 4.46992 5.66854 4.89623 5.69333V5.70248H4.99623C5.46165 5.70248 5.88789 5.51584 6.20655 5.17727L6.20656 5.17725L9.48748 1.6901C9.48752 1.69006 9.48756 1.69002 9.48759 1.68998C9.64471 1.52446 9.63588 1.26128 9.47135 1.10498C9.30584 0.947739 9.04257 0.95654 8.88623 1.1211L8.88596 1.12139L5.60471 4.60264L5.60441 4.60295C5.28769 4.9419 4.70477 4.9419 4.38805 4.60295L4.38781 4.6027L1.10656 1.1152L1.10656 1.1152L1.10623 1.11485C0.949893 0.95029 0.68662 0.941489 0.521104 1.09873C0.356601 1.25501 0.347745 1.51814 0.504804 1.68367C0.504862 1.68373 0.504921 1.68379 0.504979 1.68385L3.7859 5.171Z" fill="#1055BD" stroke="#1055BD" stroke-width="0.2"/>
                        </g>
                        <defs>
                        <clipPath id="clip0_4602_22155">
                        <rect width="10" height="8.75" fill="white"/>
                        </clipPath>
                        </defs>
                        </svg>
                        <a href="mailto:{fund.email}" class="text-[#1055BD] font-bold">
                          {fund.email}</a>
                    </div>
                  </div>
                  
                  <svg width="24" height="14" viewBox="0 0 24 14" fill="none" xmlns="http://www.w3.org/2000/svg" class="size-8 mt-8">
                    <g opacity="0.5">
                    <path d="M2 4.82031C1.0335 4.82031 0.25 5.60381 0.25 6.57031C0.25 7.53681 1.0335 8.32031 2 8.32031V4.82031ZM2 8.32031H14V4.82031H2V8.32031Z" fill="#5D5F64"/>
                    <path d="M15.3488 3.79413L20.7868 7L15.3488 10.2059V3.79413Z" fill="#D9D9D9" stroke="#5D5F64" stroke-width="3.26367"/>
                    </g>
                    </svg>
                </div>
                </div>
              {/each}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


  