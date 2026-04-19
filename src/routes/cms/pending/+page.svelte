```svelte
<script>
import Nav from "$lib/components/nav.svelte";
import { fly, fade } from "svelte/transition";
</script>

<Nav />

<div class="page">

  <div class="card" in:fly={{ y: 50, duration: 500 }}>

    <div class="status-icon"></div>

    <h1>Waiting for Admin Approval</h1>

    <p class="desc">
      Your account has been created successfully.  
      Access to the CMS will be enabled once an administrator reviews
      and assigns your role.
    </p>

    <div class="loading" in:fade>
      <span></span>
      <span></span>
      <span></span>
    </div>

    <div class="info-box">
      <p>
        If your approval is taking longer than expected, you may contact
        the system author or administrator for assistance.
      </p>
    </div>

  </div>

</div>

<style>

.page{
margin-top:40px;
min-height:90vh;
display:flex;
align-items:center;
justify-content:center;
background:linear-gradient(135deg,#f5f7fb,#eef2ff);
padding:40px 20px;
}

.card{
max-width:600px;
background:white;
padding:45px;
border-radius:14px;
box-shadow:0 15px 40px rgba(0,0,0,0.08);
text-align:center;
animation:floatCard 4s ease-in-out infinite;
}

@keyframes floatCard{
0%{transform:translateY(0px);}
50%{transform:translateY(-6px);}
100%{transform:translateY(0px);}
}

.status-icon{
width:60px;
height:60px;
margin:auto;
border-radius:50%;
background:#2f80ed;
margin-bottom:20px;
position:relative;
}

.status-icon::after{
content:"";
position:absolute;
width:16px;
height:16px;
border-radius:50%;
background:white;
top:50%;
left:50%;
transform:translate(-50%,-50%);
}

h1{
margin-bottom:12px;
color:#2c3e50;
}

.desc{
color:#666;
font-size:15px;
line-height:1.6;
margin-bottom:25px;
}

.loading{
display:flex;
justify-content:center;
gap:8px;
margin-bottom:25px;
}

.loading span{
width:8px;
height:8px;
background:#2f80ed;
border-radius:50%;
animation:bounce 1.2s infinite;
}

.loading span:nth-child(2){
animation-delay:0.2s;
}

.loading span:nth-child(3){
animation-delay:0.4s;
}

@keyframes bounce{
0%,80%,100%{transform:scale(0);}
40%{transform:scale(1);}
}

.info-box{
background:#f8f9fc;
padding:18px;
border-radius:8px;
font-size:14px;
color:#555;
}

</style>
```
