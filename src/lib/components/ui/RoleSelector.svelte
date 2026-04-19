<script lang="ts">
  import DropDownArrow from "$lib/svg/drop-down-arrow.svelte";
  let selectedRole = "";
</script>

<style>
  select {
    -webkit-appearance: none; /* Removes default styling in WebKit browsers */
    -moz-appearance: none; /* Removes default styling in Firefox */
    appearance: none; /* Removes default styling in other browsers */

    border: 2px solid transparent; /* Make sure it doesn’t show an extra border */
    padding-right: 2.5rem; /* Space for custom arrow */
  }

  .select-wrapper {
    position: relative;
  }

  select:focus {
    background-color: #D3F2FC; /* Change to the desired background color when selected */
    border: 2px solid #0155BD;
  }
</style>

<div class="rounded-lg">
  <label for="roleSelect" class="text-gray-600 text-sm mb-1">
    Select Your Role
  </label>
  <div class="select-wrapper text-gray-500">
    <select
      id="roleSelect"
      bind:value={selectedRole}
      class="hover:border-[#0155BD] focus:outline-none focus:border-[#0155BD] relative w-full h-12 text-sm font-[600] bg-[white] md:bg-[#E8EBF1] text-[#0155BD] outline-none rounded-lg pl-4 pr-10"
    >
      <option value="">-- Select an option --</option>
      <option value="Advisor">Advisor</option>
      <option value="Doctor">Doctor</option>
      <option value="Patient">Patient</option>
    </select>
    <DropDownArrow/>
  </div>
</div>
