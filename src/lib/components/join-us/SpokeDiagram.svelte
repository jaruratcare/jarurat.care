<script>
    import { onMount } from "svelte";

    export let center = "Jarurat Care";
    export let items = [];

    $: spokes =
        items?.length > 0
            ? items.map((item) =>
                  typeof item === "string" ? { label: item } : item
              )
            : [
                  { label: "Healthcare Access" },
                  { label: "Patient Support" },
                  { label: "Caregiver Network" },
                  { label: "Mental Wellness" },
                  { label: "Home Care" },
                  { label: "Elder Support" },
                  { label: "Child Health" },
                  { label: "Nutrition" },
                  { label: "Rehabilitation" },
                  { label: "Awareness" },
                  { label: "Community Outreach" },
                  { label: "Digital Health" },
                  { label: "Research" },
                  { label: "Partnerships" }
              ];

    $: centerLabel = center;
    const centerSubtitle = "Our Focus Areas";

    let containerWidth = 1000;
    let containerEl;
    $: isMobile = containerWidth < 640;
    $: radius = isMobile
        ? Math.min(containerWidth * 0.32, 180)   // mobile
        : Math.min(containerWidth * 0.36, 220);  // desktop
    $: centerR = Math.min(containerWidth * 0.13, 72);
    $: nodeR = isMobile
        ? Math.min(containerWidth * 0.07, 40)
        : Math.min(containerWidth * 0.085, 52);
    $: svgSize = isMobile
        ? radius * 2 + nodeR * 1.5
        : radius * 2 + nodeR * 2 + 20;
    $: cx = svgSize / 2;
    $: cy = svgSize / 2;
    $: maxRadius = (svgSize / 2) - nodeR - 12;
    
    // Stable randomness
    $: variations = spokes.map((_, i) => {
        const rand = (seed) => {
            const x = Math.sin(seed * 9999) * 10000;
            return x - Math.floor(x);
        };

        return {
            radiusFactor: 0.75 + rand(i) * 0.4,
            sizeFactor: 0.7 + rand(i + 100) * 0.6,
            angleOffset: (rand(i + 200) - 0.5) * 0.4
        };
    });

    // New position function (angle-based)
    function getPos(angle, r) {
        return {
            x: cx + r * Math.cos(angle),
            y: cy + r * Math.sin(angle)
        };
    }

    // ✅ Collision-aware layout
    $: layout = (() => {
        const placed = [];

        return spokes.map((_, i) => {
            const v = variations[i];

            let angle =
                (2 * Math.PI * i) / spokes.length - Math.PI / 2 + v.angleOffset;

            let r = radius * v.radiusFactor;
            let localNodeR = nodeR * v.sizeFactor;

            let pos = getPos(angle, r);

            let attempts = 0;
            while (attempts < 20) {
                let collision = false;

                for (const p of placed) {
                    const dx = pos.x - p.x;
                    const dy = pos.y - p.y;
                    const dist = Math.sqrt(dx * dx + dy * dy);

                    const minDist = localNodeR + p.r + 12;

                    if (dist < minDist) {
                        collision = true;
                        r += 10; // push outward
                        pos = getPos(angle, r);
                        break;
                    }
                }

                if (!collision) break;
                attempts++;
            }

            const node = { ...pos, r: localNodeR };
            placed.push(node);
            return node;
        });
    })();

    onMount(() => {
        const observer = new ResizeObserver((entries) => {
            containerWidth = entries[0].contentRect.width;
        });

        if (containerEl) observer.observe(containerEl);
        containerWidth = containerEl?.offsetWidth || 600;

        return () => observer.disconnect();
    });

    let hoveredIndex = null;
</script>

<section class="py-16 bg-[#eef4ff]">
    <div class="max-w-6xl mx-auto px-6">

        <div class="text-center mb-10">
            <h2 class="font-extrabold text-3xl md:text-4xl text-primaryBlue">
                {centerSubtitle}
            </h2>

            <p class="mt-3 text-gray-600 max-w-xl mx-auto">
                Explore the many areas where Jarurat Care is making a difference.
            </p>
        </div>

        <div bind:this={containerEl} class="w-full flex items-center justify-center">

            <svg
                width={svgSize}
                height={svgSize}
                viewBox="0 0 {svgSize} {svgSize}"
                class="max-w-full"
                style="overflow: visible;"
            >
                <!-- Outer ring -->
                <circle
                    cx={cx}
                    cy={cy}
                    r={radius + nodeR + 8}
                    fill="none"
                    stroke="#dbeafe"
                    stroke-width="1.5"
                    stroke-dasharray="6 5"
                    opacity="0.7"
                />

                <!-- Spokes -->
                {#each spokes as _, i}
                    {@const pos = layout[i]}

                    <line
                        x1={cx}
                        y1={cy}
                        x2={pos.x}
                        y2={pos.y}
                        stroke={hoveredIndex === i ? "#2563eb" : "#93c5fd"}
                        stroke-width={hoveredIndex === i ? 2.5 : 1.5}
                        stroke-linecap="round"
                    />
                {/each}

                <!-- Nodes -->
                {#each spokes as spoke, i}
                    {@const pos = layout[i]}
                    {@const localNodeR = pos.r}
                    {@const words = spoke.label.split(" ")}

                    <g
                        on:mouseenter={() => (hoveredIndex = i)}
                        on:mouseleave={() => (hoveredIndex = null)}
                    >
                        <circle
                            cx={pos.x}
                            cy={pos.y}
                            r={hoveredIndex === i ? localNodeR + 3 : localNodeR}
                            fill={hoveredIndex === i ? "#2563eb" : "white"}
                            stroke={hoveredIndex === i ? "#1d4ed8" : "#bfdbfe"}
                            stroke-width="2"
                        />

                        <text
                            x={pos.x}
                            y={pos.y}
                            text-anchor="middle"
                            font-size={Math.max(localNodeR * 0.26, 8)}
                            font-weight="600"
                            fill={hoveredIndex === i ? "white" : "#1e3a8a"}
                            pointer-events="none"
                        >
                            {#each words as word, wi}
                                <tspan
                                    x={pos.x}
                                    dy={wi === 0 ? 0 : localNodeR * 0.32}
                                >
                                    {word}
                                </tspan>
                            {/each}
                        </text>
                    </g>
                {/each}

                <!-- Center -->
                <circle
                    cx={cx}
                    cy={cy}
                    r={centerR}
                    fill="#2563eb"
                    stroke="white"
                    stroke-width="3"
                />

                <text
                    x={cx}
                    y={cy}
                    text-anchor="middle"
                    font-size={Math.max(centerR * 0.3, 11)}
                    font-weight="800"
                    fill="white"
                >
                    {centerLabel}
                </text>
            </svg>
        </div>
    </div>
</section>