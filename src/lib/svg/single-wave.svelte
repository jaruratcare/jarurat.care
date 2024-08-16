<script lang="ts">
  let className = '';

  export let fill = '#85daff';
  export { className as class };
</script>

<svg viewBox="0 0 500 30" version="1.1" xmlns="http://www.w3.org/2000/svg" class={className}>
  <path
    d="M 0 15 Q 50 0, 100 15 T 200 15 T 300 15 T 400 15 T 500 15 L 500 30 L 0 30 Z"
    {fill}
    stroke={fill}
  />
</svg>
