<script>
	import Logo from '$lib/svg/logo.svelte';
	import { page } from '$app/stores';
	import Button from './ui/button.svelte';
	import { MenuIcon } from 'lucide-svelte';
	import { cn } from '$lib/utils';
	import { onMount } from 'svelte';

	let isMenuOpen = false;
	let isMobileScreen = false;

	onMount(() => {
		const listener = () => {
			isMobileScreen = window.innerWidth <= 768;
		};

		listener();
		window.addEventListener('resize', listener);
	});

	const pathname = $page.url.pathname;
	const navItems = [
		{ title: 'Home', href: '/' },
		{ title: 'About Us', href: '/about-us' },
		{ title: 'Get Involved', href: '/get-involved' },

		{ title: 'News & Blogs', href: '/#news' },
		{ title: 'Contact Us', href: 'mailto:jaruratcare@gmail.com' }
	];
</script>

<div class="fixed inset-x-0 z-50 text-[0.9em] bg-white flex items-center justify-between">
	<nav class="flex items-center font-rubik text-[#0D2561] px-2 md:px-4 py-1 md:py-2 grow">
		<span class="inline-block w-[5rem] sm:w-[10rem]">
			<Logo />
		</span>

		<ul
			class={cn(
				`gap-8 grow items-center justify-center fixed md:static inset-0 bg-white flex flex-col md:flex-row h-full`,
				'translate-x-full md:translate-x-0',
				isMobileScreen
					? isMenuOpen
						? 'translate-x-0 opacity-100'
						: 'translate-x-full opacity-0'
					: 'translate-x-0',
				'transition-all duration-300'
			)}
		>
			{#each navItems as item}
				<li class:font-bold={pathname === item.href}>
					<a
						href={item.href}
						on:click={() => {
							isMenuOpen = false;
						}}>{item.title}</a
					>
				</li>
			{/each}
		</ul>

		<a href="/donate" class="text-[0.8em] hidden md:block">
			<Button>Donate Now</Button>
		</a>
	</nav>

	<button
		class="block md:hidden z-50 px-4"
		on:click={() => {
			isMenuOpen = !isMenuOpen;
		}}
	>
		<MenuIcon />
	</button>
</div>
