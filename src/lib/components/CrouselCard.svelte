<script>
   export let data
</script>
<style>
  

  .card {
    display: grid;
    grid-template-columns: 1fr 1.5fr;
border: 1px solid black;
    max-width:50rem ;
    min-height: 20rem;
    grid-column: 2;
    margin: 0 auto;
    border-radius: 15px;
  }

  .card img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-top-left-radius: 15px;
    border-bottom-left-radius: 15px;
  }

  .avatar {
    margin: 1rem;
  }

  .avatar img {
    height: 4rem;
    width: 4rem;
    border-radius: 50%;
  }

  .avatar h2, .avatar span {
    color: darkblue;
    margin: 0.5rem;
  }

  .story {
    display: flex;
    align-items: flex-start;
    position: relative;
	 margin: 1rem 0;
  }

  .story svg {
    height: 40px;
    width: 40px;
    margin-left: 2rem;
    flex-shrink: 0;
  }

  .story p {
    color: darkblue;
    margin: 0 2rem 0 5rem;
    flex-grow: 1;
  }
</style>
<div class="carousel">
  <div class="card">
    <img src={data.img} alt="">
    <div class="content">
     <div class="avatar">
		   <img  src={data.img} alt="">
		  <h2>{data.name}</h2>
		  <span>{data.cancerType}, {data.location}</span>
	  </div>
	  <div class="story"> 
     <svg  height="40px" width="40px" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32" xml:space="preserve" fill="#000000" transform="rotate(180)"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g id="right_x5F_quote"> <g> <path style="fill:#2d9a4e;" d="M0,4v12h8c0,4.41-3.586,8-8,8v4c6.617,0,12-5.383,12-12V4H0z"></path> <path style="fill:#2d9a4e;" d="M20,4v12h8c0,4.41-3.586,8-8,8v4c6.617,0,12-5.383,12-12V4H20z"></path> </g> </g> </g> </g></svg>
	  <p style="margin: 0 2rem;">{data.description}</p>
	  </div>
    </div>
  </div>
</div>