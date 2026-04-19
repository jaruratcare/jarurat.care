<script>
	import Nav from '$lib/components/nav.svelte';
	import Footer from '$lib/components/footer.svelte';

	const lastUpdated = 'March 15, 2026';
	const contactEmail = 'Priyanka.joshi@jarurat.care';
</script>

<Nav />

<section class="bg-[#F8FBFF] min-h-screen text-[#0D2561]">
	<div class="max-w-4xl mx-auto px-6 sm:px-8 py-16">
		<div class="bg-white shadow-md rounded-xl p-8 sm:p-12">
			<h1 class="text-3xl font-bold mb-2">Privacy Policy</h1>
			<p class="text-sm text-slate-500 mb-8">Last updated: {lastUpdated}</p>

			<div class="prose prose-slate max-w-none">

				<h2>Overview</h2>
				<p>
					Jarurat Care Foundation is committed to protecting your privacy and handling
					your personal data transparently and securely. This Privacy Policy explains
					what information we collect, why we collect it, how we use it, who we share
					it with, and how you can exercise your rights.
				</p>

				<h2>Scope</h2>
				<p>
					This Policy applies to information collected through our website, services,
					forms, emails, phone calls, and other interactions with Jarurat Care Foundation.
					It covers users, donors, volunteers, healthcare partners and visitors.
				</p>

				<h2>Legal framework</h2>
				<p>
					We process personal data in accordance with applicable law, including the
					Digital Personal Data Protection Act, 2023 where relevant, and other local
					regulations. We rely on lawful bases such as consent, contract performance,
					legal obligation, and legitimate interests depending on the processing activity.
				</p>

				<h2>Information we collect</h2>
				<ul>
					<li><strong>Identity & contact data:</strong> name, email, phone number, postal address.</li>
					<li><strong>Health & case data:</strong> medical queries, medical history, appointment notes — only when you share them to receive a service.</li>
					<li><strong>Transactional data:</strong> donation records, payment receipts, billing address.</li>
					<li><strong>Support & communications:</strong> messages, case notes submitted to support teams.</li>
					<li><strong>Technical data:</strong> IP address, browser, device, operating system, pages visited, referral URL, cookies and usage analytics.</li>
				</ul>

				<h2>How we collect information</h2>
				<ul>
					<li>Directly from you when you provide it (forms, registrations, emails, calls).</li>
					<li>Automatically when you use the website (cookies and server logs).</li>
					<li>From third parties where you have given permission or where permitted by law (e.g., payment processors, healthcare partners).</li>
				</ul>

				<h2>How we use your personal data</h2>
				<p>Typical uses include:</p>
				<ul>
					<li>Providing requested services (appointments, teleconsultations, follow-ups).</li>
					<li>Processing donations and sending receipts.</li>
					<li>Responding to queries, support requests, and volunteer applications.</li>
					<li>Improving our services, research, and analytics (using aggregated/anonymized data where possible).</li>
					<li>Sending service-related communications, updates and where consent is given, occasional marketing.</li>
					<li>Meeting legal and regulatory obligations and preventing fraud or abuse.</li>
				</ul>

				<h2>Lawful basis & consent</h2>
				<p>
					Where required we will ask for your explicit consent to process sensitive
					data (for example, health information). You can withdraw consent at any time
					by contacting us at the email below; withdrawal will not affect processing carried
					out while consent was in effect.
				</p>

				<h2>Cookies and tracking</h2>
				<p>
					We use cookies and similar technologies for site functionality, analytics and
					preference storage. You can manage cookie preferences via your browser or the
					cookie banner on our site. Disabling certain cookies may affect site features.
				</p>

				<h2>Sharing & third parties</h2>
				<p>
					We do not sell personal data. We may share personal data with:
				</p>
				<ul>
					<li>Service providers that support our platform (hosting, analytics, payment processors).</li>
					<li>Healthcare providers and partners when needed to deliver care or services you requested.</li>
					<li>Legal or regulatory authorities where required (e.g., court orders, public health emergencies).</li>
				</ul>
				<p>
					Where third parties process data on our behalf we use contracts and technical measures
					to require them to keep data secure and only act under our instructions.
				</p>

				<h2>Cross-border transfers</h2>
				<p>
					Some processing may occur outside your country. When we transfer data internationally
					we use appropriate safeguards such as contractual protections or ensure transfers are
					permitted under applicable law.
				</p>

				<h2>Retention</h2>
				<p>
					We keep personal data only as long as necessary for the purposes described or to
					meet legal obligations. Example retention periods:
				</p>
				<ul>
					<li>Donation & payment records: 7 years (for accounting and tax).</li>
					<li>Support tickets & case notes: retained for the duration of the case + 2 years.</li>
					<li>Account information: until account deletion + reasonable grace period.</li>
				</ul>

				<h2>Security</h2>
				<p>
					We maintain administrative, technical and physical safeguards such as:
				</p>
				<ul>
					<li>Encryption of data in transit and at rest where applicable.</li>
					<li>Access controls and least-privilege for staff and processors.</li>
					<li>Periodic security reviews, vulnerability scanning and employee training.</li>
				</ul>
				<p>
					No system is completely secure; if a data breach occurs we will notify affected users
					and authorities as required by law.
				</p>

				<h2>Automated decision-making</h2>
				<p>
					We do not use solely automated decision-making that produces legal or similarly
					significant effects about you. If this changes we will provide clear information
					and options to request human review.
				</p>

				<h2>Your rights</h2>
				<p>
					Subject to applicable law you may have the right to:
				</p>
				<ul>
					<li>Access personal data we hold about you.</li>
					<li>Request correction, restriction, portability or deletion of your personal data.</li>
					<li>Withdraw consent where processing is based on consent.</li>
					<li>Object to processing based on legitimate interests.</li>
				</ul>
				<p>
					To exercise your rights, please email
					<a href={"mailto:" + contactEmail} class="text-[#1E40AF] hover:underline">{contactEmail}</a>.
					We aim to respond to requests promptly and in accordance with applicable law (typically within 30 days).
				</p>

				<h2>Data breach notifications</h2>
				<p>
					In the unlikely event of a data breach that creates a risk to your rights, we will
					notify you and the relevant regulator as required by law and provide guidance on steps
					to protect yourself.
				</p>

				<h2>Children</h2>
				<p>
					Our services are not intended for children under 18 without parental consent. We do not knowingly
					collect data from children; if we learn we have, we will promptly delete it (subject to verification).
				</p>

				<h2>Marketing and communications</h2>
				<p>
					We may send occasional updates or newsletters where you have opted in. You can unsubscribe
					from marketing communications at any time using the link in those emails or by contacting us.
				</p>

				<h2>Privacy concerns</h2>
				<p>
					If you have any questions, concerns, or complaints regarding this Privacy Policy or the way
					your personal information is handled, please contact us:
				</p>
				<p>
					<strong>Email:</strong>
					<a href={"mailto:" + contactEmail} class="text-[#1E40AF] hover:underline">{contactEmail}</a>
				</p>

				<h2>Third-party links</h2>
				<p>
					Our site may contain links to third-party websites. We are not responsible for their privacy practices;
					review their policies before sharing personal information.
				</p>

				<h2>Changes to this Policy</h2>
				<p>
					We may update this Policy from time to time. Material changes will be posted with a new "Last updated" date.
					We encourage you to review this page periodically.
				</p>

				<h2>Contact</h2>
				<p>
					For privacy questions, rights requests or other inquiries contact:
				</p>
				<p>
					<strong>Email:</strong>
					<a href={"mailto:" + contactEmail} class="text-[#1E40AF] hover:underline">{contactEmail}</a>
				</p>

				<p class="text-sm text-slate-500 mt-6">
					<strong>Note:</strong> This page uses a single contact email for all privacy and policy inquiries.
				</p>

			</div>
		</div>
	</div>
</section>
