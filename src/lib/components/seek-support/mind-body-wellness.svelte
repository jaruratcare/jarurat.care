<script>
    import PinkWave from "$lib/svg/seek-support/pink-wave.svelte";
    let BooksSelected = false;
    
  const expertRecs = [
    {
      title: "The Cancer Survivor's Companion",
      author: "Lucy Atkins & Dr. Frances Goodhart",
      image: "https://m.media-amazon.com/images/I/71-UqgbffTL._SL1500_.jpg",
      link: "https://www.amazon.in/-/hi/Lucy-Atkins/dp/074995485X",
    },
    {
      title: "Anti-Cancer: A New Way of Life",
      author: "David Servan-Schreiber",
      image: "https://m.media-amazon.com/images/I/51QkGdn1XIL._SL1200_.jpg",
      link: "https://www.amazon.in/Anticancer-New-Way-Life/dp/0670021644",
    },
    {
      title: "Radical Remission",
      author: "Kelly A. Turner",
      image: "https://m.media-amazon.com/images/I/61jleYB+y+L._SY466_.jpg",
      link: "",
    },
  ];

  const doctorRecs = [
    {
      title: "When Breath Becomes Air",
      author: "Paul Kalanith",
      image: "https://m.media-amazon.com/images/I/31VxAv+hdAL._SY445_SX342_.jpg",
      link: "http://amazon.in/When-Breath-Becomes-Paul-Kalanithi/dp/081298840X",
    },
    {
      title: " Healed: How Cancer Gave Me a New Life",
      author: "Manisha Koirala",
      image: "https://m.media-amazon.com/images/I/41NHo8KrjcL._SY445_SX342_.jpg",
      link: "https://www.amazon.in/Healed-How-Cancer-Gave-Life/dp/0143457209",
    },
    {
      title: "After Cancer Care",
      author: "Gerald Lemole, Pallav Mehta, & Dwight McKee",
      image: "https://m.media-amazon.com/images/I/71WAEvHCHBL._SY466_.jpg",
      link: "https://www.amazon.in/After-Cancer-Care-Definitive-Self-Care/dp/1623365023",
    },
  ];



  </script>
  
  <PinkWave />
  
  <div class="bg-[#FFC3CF] h-[1500px] w-[100%] flex justify-center">
    <div class={`h-[86%] w-[86%] ${BooksSelected ? 'bg-[#FFEBEB]' : 'bg-white'} rounded-[30px] border border-[#FF9BB9] shadow-sm flex flex-col items-center`}>
      <h1 class="text-center text-3xl font-bold pt-10">Mind and <span class="text-[#FC3C55]">Body Wellness</span></h1>
  
      <p class="w-[65%] text-center text-sm pt-4">
        Your well-being is important. We offer simple tools to help you relax, stay strong, and feel better during your cancer journey. Whether it's through calming meditations, gentle exercises, or healthy eating tips, these resources are here to support you. Take a moment for yourself—you deserve it.
      </p>
  
      <!-- buttons -->
      <div class="flex flex-row p-5">
        <button class="w-[227px] h-[50px] rounded-l-full rounded-r-full bg-[#FFBA41] text-white mr-3 transform transition-transform hover:scale-105 hover:bg-[#FFA500]" on:click={() => BooksSelected = false}>Yoga/Exercise</button>
        <button class="w-[150px] h-[50px] rounded-l-full rounded-r-full bg-[#FC3C55] text-white transform transition-transform hover:scale-105 hover:bg-[#FF5A75]" on:click={() => BooksSelected = true}>Books</button>
      </div>
  
      <!-- Conditional Content -->
      {#if !BooksSelected}
      <!--videos links to be added -->
        <div class="h-[80%] w-[80%] flex flex-col items-start mt-9">
          <h1 class="font-bold text-lg">Light Yoga</h1>
  
          
        </div>
      {:else}


      <!--books recommendations-->
        <div class="h-[80%] w-[80%] flex flex-col items-start mt-9">
          <h2 class="text-2xl font-semibold mb-4">Expert Recommendations</h2>
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-10">

      {#each expertRecs as book}
      <div class="bg-white p-4 rounded-xl shadow hover:shadow-lg flex flex-col items-center">
        <img src={book.image} alt={book.title + " cover"} class="h-48 w-[136px] rounded-t-md" />
        {#if book.link}
          <a href={book.link} class="mt-4 text-lg font-bold text-[#0155BD]">{book.title}</a>
        {:else}
          <h3 class="mt-4 text-lg font-bold text-[#0155BD]">{book.title}</h3>
        {/if}
        <p class="text-sm text-gray-600">By {book.author}</p>
      </div>
      
    {/each}
      </div>

      <h2 class="text-2xl font-semibold mb-4">Doctors Choice</h2>
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-10">

      {#each doctorRecs as book}
      <div class="bg-white p-4 rounded-xl shadow hover:shadow-lg flex flex-col items-center">
        <img src={book.image} alt={book.title + " cover"} class="h-48 w-[136px] rounded-t-md" />
        {#if book.link}
          <a href={book.link} class="mt-4 text-lg font-bold text-[#0155BD]">{book.title}</a>
        {:else}
          <h3 class="mt-4 text-lg font-bold text-[#0155BD]">{book.title}</h3>
        {/if}
        <p class="text-sm text-gray-600">By {book.author}</p>
      </div>
      
    {/each}
      </div>


     
    </div>


    
      {/if}
    </div>
  </div>
  