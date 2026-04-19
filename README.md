# 🎗️ Jarurat Care — Official Website

> **Jarurat Care** is a non-profit initiative dedicated to supporting cancer patients and their caregivers across India. This repository contains the source code for [jarurat.care](https://jarurat.care), built with **SvelteKit** and **TypeScript**.

---

## 📋 Table of Contents

- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running Locally](#running-locally)
- [Project Structure](#-project-structure)
- [Git Workflow](#-git-workflow)
  - [Branching Strategy](#branching-strategy)
  - [Step-by-Step: Pushing Your Code](#step-by-step-pushing-your-code)
- [Building for Production](#-building-for-production)
- [Contributing Guidelines](#-contributing-guidelines)

---

## 🛠 Tech Stack

| Technology | Purpose |
|---|---|
| [SvelteKit](https://kit.svelte.dev/) | Full-stack web framework |
| [TypeScript](https://www.typescriptlang.org/) | Type safety |
| [Tailwind CSS](https://tailwindcss.com/) | Utility-first styling |
| [Firebase](https://firebase.google.com/) | Backend / Database |
| [Vite](https://vitejs.dev/) | Build tool & dev server |

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed on your machine before proceeding:

- **Node.js** `v18+` — [Download here](https://nodejs.org/)
- **npm** `v9+` (comes with Node.js)
- **Git** — [Download here](https://git-scm.com/)

You can verify your installations by running:

```bash
node --version
npm --version
git --version
```

### Installation

**1. Fork the repository**

Click the **Fork** button at the top-right of this page on GitHub to create your own copy.

**2. Clone your fork locally**

```bash
git clone https://github.com/<your-username>/jarurat-care.git
cd jarurat-care
```

**3. Add the upstream remote** (so you can pull future updates from the main repo)

```bash
git remote add upstream https://github.com/jarurat-care/jarurat-care.git
```

**4. Install dependencies**

```bash
npm install
```

### Running Locally

Start the development server:

```bash
npm run dev
```

The app will be available at **http://localhost:5173** by default.

To open it automatically in your browser:

```bash
npm run dev -- --open
```

---

## 📁 Project Structure

```
jarurat-care/
│
├── src/
│   ├── lib/
│   │   ├── assets/             # Images, icons, and static media used in components
│   │   │   ├── community-member/
│   │   │   ├── donate/
│   │   │   ├── get-involved/
│   │   │   └── team-members/
│   │   │
│   │   ├── components/         # Reusable Svelte UI components
│   │   │   ├── cancer-research/    # Components for the cancer research section
│   │   │   ├── contact-us/         # Contact form and gallery
│   │   │   ├── donate/             # Donation flow components
│   │   │   ├── faq/                # FAQ page components
│   │   │   ├── get-involved/       # Community & volunteering components
│   │   │   ├── glossary/           # Medical glossary components
│   │   │   ├── join-us/            # Volunteer and internship components
│   │   │   └── ui/                 # Generic shared UI primitives (button, input, etc.)
│   │   │
│   │   ├── data/               # Static JSON data files (articles, FAQs, hospitals, etc.)
│   │   │
│   │   ├── svg/                # All SVG icons and illustrations as Svelte components
│   │   │
│   │   ├── firebase.ts         # Firebase client initialization & config
│   │   ├── index.ts            # Barrel exports for lib
│   │   └── utils.ts            # Shared utility/helper functions
│   │
│   ├── routes/                 # SvelteKit file-based routing (each folder = a page)
│   │   ├── about-us/
│   │   ├── api/payment/        # Server-side payment API endpoint
│   │   ├── articles/
│   │   ├── cancer-research/
│   │   ├── contact-us/
│   │   ├── donate/
│   │   ├── faq/
│   │   ├── get-involved/
│   │   ├── glossary/
│   │   ├── join-us/
│   │   ├── mission/
│   │   ├── privacy_policy/
│   │   ├── terms_and_conditions/
│   │   ├── terms_of_service/
│   │   ├── +layout.svelte      # Root layout (navbar, footer, etc.)
│   │   └── +page.svelte        # Homepage
│   │
│   ├── app.css                 # Global CSS / Tailwind base styles
│   ├── app.d.ts                # Global TypeScript declarations
│   └── app.html                # HTML shell template
│
├── static/                     # Files served as-is (favicon, images, sitemap, etc.)
│
├── tailwind.config.js          # Tailwind CSS configuration
├── svelte.config.js            # SvelteKit configuration
├── vite.config.ts              # Vite build configuration
├── tsconfig.json               # TypeScript configuration
└── package.json                # Project dependencies & scripts
```

> **Tip for newcomers:** Most of your day-to-day work will be inside `src/lib/components/` (UI building) and `src/routes/` (pages). The `src/lib/data/` folder holds JSON that powers dynamic content.

---

## 🌿 Git Workflow

### Branching Strategy

We follow a **trunk-based** approach with a protected `prod` branch:

```
feature/my-feature  ──┐
bugfix/fix-something  ─┼──► develop ──► prod ──► jarurat.care (live)
hotfix/urgent-fix  ────┘
```

| Branch | Purpose |
|---|---|
| `prod` | Live production — **never push directly here** |
| `develop` | Integration branch — all PRs target this |
| `features/*` | New features |
| `bugfixes/*` | Bug and issue fixes |
| `hotfixes/*` | Urgent production patches |

**Branch naming examples:**

```bash
features/add-donation-modal
bugfixes/fix-nav-mobile-overflow
hotfixes/payment-api-timeout
```

---

### Step-by-Step: Pushing Your Code

Follow these steps every time you work on something new:

**Step 1 — Sync your local `develop` with upstream before starting**

```bash
git checkout develop
git fetch upstream
git merge upstream/develop
```

**Step 2 — Create a new branch off `develop`**

```bash
git checkout -b features/your-feature-name
```

**Step 3 — Make your changes and stage them**

```bash
# Stage specific files
git add src/lib/components/your-component.svelte

# Or stage everything
git add .
```

**Step 4 — Write a clear, descriptive commit message**

```bash
git commit -m "feat: add donation modal with payment summary"
```

Follow this commit message style:

| Prefix | When to use |
|---|---|
| `feat:` | Adding a new feature |
| `fix:` | Fixing a bug |
| `chore:` | Config, dependency updates |
| `style:` | CSS/UI-only changes |
| `refactor:` | Code restructure without behavior change |
| `docs:` | Documentation updates |

**Step 5 — Push your branch to origin**

```bash
git push origin features/your-feature-name
```

If it's the first time pushing this branch:

```bash
git push --set-upstream origin features/your-feature-name
```

You can now merge your branch directly into `develop` on GitHub or via the command line:

```bash
git checkout develop
git merge features/your-feature-name
git push origin develop
```

---

## 🏗 Building for Production

To create an optimized production build:

```bash
npm run build
```

Preview the production build locally before deploying:

```bash
npm run preview
```

---

## 🤝 Contributing Guidelines

- Always branch off `develop`, never off `prod`
- Keep commits focused — one feature or fix per branch
- Run the dev server and test your changes before pushing
- Follow the existing code style (Prettier config is included)
- Be respectful and collaborative when reviewing others' code

---

