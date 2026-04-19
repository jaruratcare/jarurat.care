<script>
	import Logo from '$lib/svg/logo.svelte';
	import { page } from '$app/stores';
	import { MenuIcon, X } from 'lucide-svelte';
	import { cn } from '$lib/utils';

	let isMenuOpen = false;

	$: pathname = $page.url.pathname;

	const navItems = [
		{ title: 'Home', href: '/' },
		{ title: 'About Us', href: '/about-us' },
		{ title: 'Get Involved', href: '/get-involved' },
		{ title: 'News & Blogs', href: '/articles' },
		{ title: 'Contact Us', href: '/contact-us' }
	];
</script>

<header class="fixed inset-x-0 top-0 z-50 bg-white shadow-sm">
	<nav class="max-w-7xl mx-auto flex items-center justify-between px-4 md:px-6 py-3">

		<!-- Logo -->
		<a href="/" class="flex items-center">
			<Logo class="h-8 md:h-10 w-auto" />
		</a>

		<!-- Desktop Menu -->
		<ul class="hidden md:flex items-center gap-8 font-rubik text-[#0D2561] text-[0.95rem]">
			{#each navItems as item}
				<li>
					<a
						href={item.href}
						class={cn(
							"transition-all duration-200 hover:text-[#1E4ED8]",
							pathname === item.href
								? "font-semibold border-b-2 border-[#1E4ED8] pb-1"
								: ""
						)}
					>
						{item.title}
					</a>
				</li>
			{/each}
		</ul>

		<!-- Donate Button Desktop -->
		<a href="/donate" class="hidden md:block">
			<button class="bg-[#1E4ED8] text-white px-6 py-2 rounded-full text-sm font-medium shadow-md hover:shadow-lg transition">
				Donate Now
			</button>
		</a>

		<!-- Mobile Toggle -->
		<button
			class="md:hidden p-2"
			on:click={() => (isMenuOpen = !isMenuOpen)}
		>
			{#if isMenuOpen}
				<X size={26} />
			{:else}
				<MenuIcon size={26} />
			{/if}
		</button>
	</nav>

	<!-- Mobile Menu -->
	{#if isMenuOpen}
		<div class="md:hidden absolute top-full left-0 w-full bg-white shadow-lg border-t">
			<div class="flex flex-col px-6 py-6 space-y-5 text-[#0D2561]">

				{#each navItems as item}
					<a
						href={item.href}
						class="text-base font-medium py-2 border-b"
						on:click={() => (isMenuOpen = false)}
					>
						{item.title}
					</a>
				{/each}

				<a href="/donate">
					<button class="w-full bg-[#1E4ED8] text-white py-3 rounded-full font-medium mt-4">
						Donate Now
					</button>
				</a>

			</div>
		</div>
	{/if}
</header>