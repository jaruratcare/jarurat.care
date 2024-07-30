<script>
  // @ts-nocheck

  import { onMount } from 'svelte';
  import axios from 'axios';
  import LocateIcon from '$lib/svg/locate-icon.svelte';
  import LocationArrow from '$lib/svg/location-arrow.svelte';
  import Map from '$lib/svg/map.svelte';
  import SearchIcon from '$lib/svg/search-icon.svelte';
  import dotenv from 'dotenv';

  const statesAndUnionTerritories = [
    { id: 'AP', state: 'Andhra Pradesh' },
    { id: 'AR', state: 'Arunachal Pradesh' },
    { id: 'AS', state: 'Assam' },
    { id: 'BR', state: 'Bihar' },
    { id: 'CG', state: 'Chhattisgarh' },
    { id: 'GA', state: 'Goa' },
    { id: 'GJ', state: 'Gujarat' },
    { id: 'HR', state: 'Haryana' },
    { id: 'HP', state: 'Himachal Pradesh' },
    { id: 'JH', state: 'Jharkhand' },
    { id: 'KA', state: 'Karnataka' },
    { id: 'KL', state: 'Kerala' },
    { id: 'MP', state: 'Madhya Pradesh' },
    { id: 'MH', state: 'Maharashtra' },
    { id: 'MN', state: 'Manipur' },
    { id: 'ML', state: 'Meghalaya' },
    { id: 'MZ', state: 'Mizoram' },
    { id: 'NL', state: 'Nagaland' },
    { id: 'OD', state: 'Odisha' },
    { id: 'PB', state: 'Punjab' },
    { id: 'RJ', state: 'Rajasthan' },
    { id: 'SK', state: 'Sikkim' },
    { id: 'TN', state: 'Tamil Nadu' },
    { id: 'TS', state: 'Telangana' },
    { id: 'TR', state: 'Tripura' },
    { id: 'UP', state: 'Uttar Pradesh' },
    { id: 'UK', state: 'Uttarakhand' },
    { id: 'WB', state: 'West Bengal' },
    { id: 'AN', state: 'Andaman and Nicobar Islands' },
    { id: 'CH', state: 'Chandigarh' },
    { id: 'DH', state: 'Dadra and Nagar Haveli and Daman and Diu' },
    { id: 'DL', state: 'Delhi' },
    { id: 'LD', state: 'Lakshadweep' },
    { id: 'PY', state: 'Puducherry' },
    { id: 'LA', state: 'Ladakh' },
    { id: 'JK', state: 'Jammu and Kashmir' }
  ];

  let stateName = 'Delhi';
  let token = '';
  let hospitals = [];
  let location_query = '';
  let searchResults = [];
  const API_KEY = '1a9e84b87cc24455a17b852fa1090e78';
  const API_URL = 'https://api.opencagedata.com/geocode/v1/json';

  onMount(async () => {
    // fetching all hospitals
    const response = await axios.get(`https://chat-backend-e7nr.onrender.com/hospitals`);
    hospitals = response.data;

    const states = document.querySelectorAll('.state');
    states.forEach((state) => {
      state.addEventListener('click', (ev) => {
        states.forEach((s) => (s.style.fill = '')); // remove prev state fill color
        ev.target.style.fill = '#0D2561'; // add fill color to clicked state
        const id = ev.target.id; // getting id of targeted state

        statesAndUnionTerritories.forEach((state) => {
          if (state.id === id) {
            stateName = state.state;
          }
        });
      });
    });

    return () => {
      states.forEach((state) => {
        state.removeEventListener('click', (ev) => {
          const id = ev.target.id;
        });
      });
    };
  });

  const handleSearch = async () => {
    try {
      const request_url = `${API_URL}?key=${API_KEY}&q=${encodeURIComponent(location_query)}&pretty=1&no_annotations=1`;
      const response = await axios.get(request_url);

      // Extract relevant data
      if (response.data.results.length > 0) {
        searchResults = response.data.results.map((result) => ({
          formatted: result.formatted,
          lat: result.geometry.lat,
          lng: result.geometry.lng,
          components: result.components,
          confidence: result.confidence
        }));
        console.log(searchResults);
      } else {
        searchResults = [];
        console.log('No results found');
      }
    } catch (error) {
      console.log('error in searching location: ', error);
    }
  };
</script>

<div class="w-full py-48">
  <div class="relative flex justify-center flex-col items-center">
    <div class="text-center">
      <h2
        class="text-[#0D2561] text-[2.5rem] font-semibold"
        style="font-family: 'Roboto', sans-serif;"
      >
        Locate Cancer Hospitals Near You
      </h2>
      <p class="text-[#0D2561] text-xl font-[400] mt-1" style="font-family: 'Roboto', sans-serif;">
        Finding Hope, One Location at a Time
      </p>
    </div>

    <div class="mt-12 flex justify-between items-center">
      <div class=" w-[25rem] mx-2 px-5 flex items-center bg-[#DBE1E6] rounded-full p-2">
        <span><SearchIcon /></span>
        <form on:submit|preventDefault={handleSearch} class="w-full flex items-center">
          <input
            type="text"
            bind:value={location_query}
            placeholder="Enter your location"
            class=" py-[.3rem] px-4 flex-1 bg-transparent border-none outline-none text-gray-700 placeholder-[#576171] placeholder:text-medium"
          />
          <button type="submit"><LocateIcon /></button>
        </form>
      </div>
      <div class="w-[25rem] mx-2 flex items-center bg-[#0155BD] rounded-full p-2">
        <select
          class="py-[.3rem] px-7 bg-[#0155BD] border-none outline-none text-white appearance-none w-full"
        >
          <option value="" selected disabled>Select your state</option>
          {#each statesAndUnionTerritories as state}
            <option value={state?.state} id={state?.id} class="state">{state?.state}</option>
          {/each}
        </select>
      </div>
    </div>

    <div class=" w-full h-full mt-32 flex">
      <div class="w-1/2 flex justify-center ml-20 mb-2">
        <div class="w-3/5">
          <div class="w-full">
            <p
              class="text-sm text-[#0155BD] bg-[#e3efff] py-1 px-3 border-l-2 border-[#0155BD] rounded-r-md"
            >
              Note: we might be missing some hospitals or maybe they are unregistered
            </p>
          </div>
          <div class=" mt-5 rounded-t-md overflow-hidden w-full">
            <p
              class="bg-[#0D2561] text-white p-2 text-2xl font-semibold"
              style="font-family: 'Roboto', sans-serif;"
            >
              {stateName}
            </p>
            <ul class="w-full">
              <li class="py-2 px-4 w-full">
                <p>Wockhardt Hospital</p>
                <p class="text-[#868B93] flex justify-between">
                  Kalawad Road Near St. Mary's High Scho... <LocationArrow />
                </p>
              </li>
              <li class="py-2 px-4 w-full">
                <p>Wockhardt Hospital</p>
                <p class="text-[#868B93] flex justify-between">
                  Kalawad Road Near St. Mary's High Scho... <LocationArrow />
                </p>
              </li>
              <li class="py-2 px-4 w-full">
                <p>Wockhardt Hospital</p>
                <p class="text-[#868B93] flex justify-between">
                  Kalawad Road Near St. Mary's High Scho... <LocationArrow />
                </p>
              </li>
              <li class="py-2 px-4 w-full">
                <p>Wockhardt Hospital</p>
                <p class="text-[#868B93] flex justify-between">
                  Kalawad Road Near St. Mary's High Scho... <LocationArrow />
                </p>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="w-1/2 mr-12 mb-2 flex justify-center">
        <Map />
      </div>
    </div>
  </div>
</div>
