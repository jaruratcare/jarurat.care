<script>
  export let moveNext
  export let activeStep
  export let data
  import { goto } from '$app/navigation';
	import Apple from '$lib/svg/apple.svelte';
	import Facebook from '$lib/svg/facebook.svelte';
	import Google from '$lib/svg/google.svelte';
	import InputEmail from './input-email.svelte';
	import InputLogin from './input-login.svelte';
	import InputPassword from './input-password.svelte';

 
 
  // Function to set query params on form submission
  // function setQueryParams() {
  //   console.log('Navigating to:', `/login?firstName=${data.firstName}&lastName=${data.lastName}&email=${data.email}&password=${data.password}`);
  //   goto(`/login?firstName=${data.firstName}&lastName=${data.lastName}&email=${data.email}&password=${data.password}`);
  // }

  // Function to handle form submission
  function handleSubmit(e) {
    e.preventDefault(); // Prevent default form submission
    console.log(data);
    moveNext()
  }
</script>

<div class="my-4">
  <h2 class="text-xl lg:text-2xl font-bold mb-6">Sign Up</h2>

  <form on:submit={handleSubmit}>
    <!-- First Name and Last Name -->
    <div class="grid grid-cols-2 gap-4 mb-6">
      <InputLogin
        bind:value={data.firstName}
        data={{
          color: true,
          name: "First Name", 
          placeholder: "Full name", 
          type: "text", 
          required: true
        }}
      />
      <InputLogin
        bind:value={data.lastName}
        data={{
          color: true,
          name: "Last Name", 
          placeholder: "Last name", 
          type: "text", 
          required: true
        }}
      />
    </div>

    <!-- Email and Password -->
    <div class="grid gap-4 mb-6">
      <InputEmail
        bind:value={data.email}
        data={{
          color: true, 
          name: "Email", 
          placeholder: "Email", 
          type: "email", 
          required: true
        }}
      />
      <InputPassword
        bind:value={data.password}
        data={{
          color: true,
          name: "Password", 
          placeholder: "Password", 
          type: "password", 
          required: true
        }}
      />
    </div>
        <a href="#" class="block text-center text-sm font-light underline mb-4">Or sign up with</a>
     <div class="grid grid-cols-3 w-2/3 mx-auto gap-4 mb-6">
      <button class="w-full bg-slate-100 h-10 flex justify-center items-center rounded-lg">
        <Google />
      </button>
      <button class="w-full bg-slate-100 h-10 flex justify-center items-center rounded-lg">
        <Facebook />
      </button>
      <button class="w-full bg-slate-100 h-10 flex justify-center items-center rounded-lg">
        <Apple />
      </button>
    </div>


    <button type="submit" class="w-full bg-yellow-400 h-10 flex justify-center items-center text-black rounded-lg">
      Continue
    </button>
  </form>

  <a href="#" class="block text-center text-sm font-light underline mt-4">
    Already have an account? <span class="text-yellow-400">Log in</span>
  </a>
</div>
