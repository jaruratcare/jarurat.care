<script>
  import { page } from '$app/stores';
  import logo from '../../assets/logo/image.webp';

  $: routeId = $page.route.id;
</script>

<nav class="w-full h-[72px] flex justify-between items-center bg-white p-4 px-16">
  <div class="logo">
    <img src={logo} alt="logo" class="h-12" />
  </div>
  <ul class="flex list-none m-0 p-0 gap-8">
    <li class="">
      <a
        href="/"
        class:active={routeId === '/'}
        class="text-blue-900 no-underline"
        class:font-bold={routeId === '/'}>Home</a
      >
    </li>
    <li class="">
      <a
        href="/about"
        class:active={routeId === '/about'}
        class="text-blue-900 no-underline"
        class:font-bold={routeId === '/about'}>About Us</a
      >
    </li>
    <li class="">
      <a
        href="/news"
        class:active={routeId === '/news'}
        class="text-blue-900 no-underline"
        class:font-bold={routeId === '/news'}>News and Blogs</a
      >
    </li>
    <li class="">
      <a
        href="/getInvolved"
        class:active={routeId === '/getInvolved'}
        class="text-blue-900 no-underline"
        class:font-bold={routeId === '/getInvolved'}>Get Involved</a
      >
    </li>
    <li class="mx-2">
      <a
        href="#contactUs"
        class:active={routeId === '/contact'}
        class="text-blue-900 no-underline"
        class:font-bold={routeId === '/contact'}>Contact Us</a
      >
    </li>
  </ul>
  <div class="flex gap-3 mr-7">
    <div
      class="relative flex items-center justify-between px-3 text-blue-800 rounded-full border-2 border-blue-800"
    >
      <select class="w-full p-2 cursor-pointer bg-transparent outline-none" name="" id="lang">
        <option value="en">English</option>
        <option value="hi">हिन्दी</option>
      </select>
    </div>
    <div
      class="relative flex items-center justify-between px-3 bg-blue-800 text-white back rounded-full border-2 border-blue-800"
    >
      <select class="w-full p-2 cursor-pointer bg-transparent outline-none" name="" id="">
        <option value="donate">Donate</option>
      </select>
    </div>
  </div>
</nav>
