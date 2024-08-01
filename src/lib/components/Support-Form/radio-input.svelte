<script>
  let selectedGender = 'male'; // default value

  const handleChange = (event) => {
    selectedGender = event.target.value;
    // Handle the change event if needed
  };
</script>

<label
  for="gender"
  class="block mb-1 text-sm font-bold text-[#0D2561] transition-colors duration-300 ease-in-out"
>
  Gender
  <span class="text-red-500 ml-1">*</span>
</label>
<div class="flex items-center space-x-4 p-4">
  <label class="flex items-center space-x-2">
    <input
      type="radio"
      name="gender"
      value="male"
      bind:group={selectedGender}
      on:change={handleChange}
      class="form-radio h-4 w-4 text-blue-600"
    />
    <span>Male</span>
  </label>
  <label class="flex items-center space-x-2">
    <input
      type="radio"
      name="gender"
      value="female"
      bind:group={selectedGender}
      on:change={handleChange}
      class="form-radio h-4 w-4 text-pink-600"
    />
    <span>Female</span>
  </label>
  <label class="flex items-center space-x-2">
    <input
      type="radio"
      name="gender"
      value="others"
      bind:group={selectedGender}
      on:change={handleChange}
      class="form-radio h-4 w-4 text-gray-600"
    />
    <span>Others</span>
  </label>
</div>
