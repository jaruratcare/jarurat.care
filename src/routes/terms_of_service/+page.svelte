<script>
	import Nav from '$lib/components/nav.svelte';
	import Footer from '$lib/components/footer.svelte';

	const lastUpdated = 'March 15, 2026';

	const organisationName = 'Jarurat Care Foundation';
	const contactEmail = 'Priyanka.joshi@jarurat.care';

	const organisationAddress = `
Jarurat Care Foundation
C/O M.C. Joshi, Aastha Vihar, Polysheet
Haldwani, Amritpur, Nainital
Uttarakhand – 263126
India
`;
</script>

<Nav />

<section class="bg-[#F8FBFF] min-h-screen text-[#0D2561]">
	<div class="max-w-4xl mx-auto px-6 sm:px-8 py-16">
		<div class="bg-white shadow-md rounded-xl p-8 sm:p-12">
			<h1 class="text-3xl font-bold mb-2">Terms of Service</h1>
			<p class="text-sm text-slate-500 mb-8">Last updated: {lastUpdated}</p>

			<div class="prose prose-slate max-w-none">

				<h2>1. Scope</h2>
				<p>
					These Terms of Service ("Terms") govern your access to and use of the
					online services, resources and content made available by {organisationName}
					at <strong>jarurat.care</strong> and any related subdomains or services
					(collectively, the "Services").
				</p>

				<h2>2. Acceptance</h2>
				<p>
					By using the Services you agree to these Terms. If you do not agree
					with these Terms, please discontinue use of the Services.
				</p>

				<h2>3. Accounts & Access</h2>
				<p>
					Some features may require registration. You are responsible for
					maintaining the confidentiality of your account credentials and
					for activities performed using your account.
				</p>

				<h2>4. User Obligations</h2>
				<ul>
					<li>Provide accurate and truthful information.</li>
					<li>Use the platform only for lawful purposes.</li>
					<li>Respect other users and the community.</li>
				</ul>

				<h2>5. Prohibited Conduct</h2>
				<ul>
					<li>Engaging in illegal or fraudulent activity.</li>
					<li>Attempting to gain unauthorized access to systems.</li>
					<li>Uploading malware or harmful software.</li>
					<li>Harassment, abusive behavior, or misinformation.</li>
				</ul>

				<h2>6. User Content</h2>
				<p>
					If you submit content such as comments, feedback, or information,
					you grant {organisationName} permission to use that content
					for operating and improving the platform.
				</p>

				<h2>7. Donations</h2>
				<p>
					If donations are made through the platform, they are processed
					through secure third-party payment providers. Donation related
					questions may be directed to
					<a href={"mailto:" + contactEmail} class="text-[#1E40AF] hover:underline">
						{contactEmail}
					</a>.
				</p>

				<h2>8. Third-Party Services</h2>
				<p>
					The Services may include links to third-party websites or tools.
					{organisationName} is not responsible for the content or
					practices of these external services.
				</p>

				<h2>9. Intellectual Property</h2>
				<p>
					All content including text, graphics, logos, and website design
					is owned by or licensed to {organisationName}. Content may not
					be copied or distributed without permission.
				</p>

				<h2>10. Privacy</h2>
				<p>
					Your use of the Services is also governed by our Privacy Policy,
					which explains how personal information is collected and used.
				</p>

				<h2>11. Disclaimer</h2>
				<p>
					The Services are provided "as is". We do not guarantee that the
					platform will always be available, secure, or free from errors.
				</p>

				<h2>12. Limitation of Liability</h2>
				<p>
					To the maximum extent permitted by law, {organisationName}
					will not be liable for indirect or consequential damages
					arising from the use of the Services.
				</p>

				<h2>13. Termination</h2>
				<p>
					We may suspend or terminate access to the Services if users
					violate these Terms or engage in harmful activities.
				</p>

				<h2>14. Changes to Terms</h2>
				<p>
					These Terms may be updated periodically. Updated versions
					will be posted on this page with a revised "Last updated" date.
				</p>

				<h2>15. Contact</h2>
				<p>
					If you have questions about these Terms of Service, please contact:
				</p>

				<p>
					<strong>Email:</strong>
					<a href={"mailto:" + contactEmail} class="text-[#1E40AF] hover:underline">
						{contactEmail}
					</a>
				</p>

				<p class="whitespace-pre-line">
					<strong>Address:</strong>
					{organisationAddress}
				</p>

			</div>
		</div>
	</div>
</section>