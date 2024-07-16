<script>
    import { page } from '$app/stores';
    import logo from "../../assets/logo/image.png"
   
    $: routeId = $page.route.id
    
</script>

<nav class="nav_container">
    <div class="logo">
        <img src={logo} alt="logo">
    </div>
    <ul>
        <li><a href="/" class:active={routeId === '/'}>Home</a></li>
        <li><a href="/about" class:active={routeId === '/about'}>About Us</a></li>
        <li><a href="/news" class:active={routeId === '/news'}>News and Blogs</a></li>
        <li><a href="/getInvolved" class:active={routeId === '/getInvolved'}>Get Involved</a></li>
        <li><a href="#contactUs" class:active={routeId === '/contact'}>Contact Us</a></li>
    </ul>
    <div class="select-group">
        <div class="select_container language">
            <select class="lang" name="" id="lang">
                <option value="en">English</option>
                <option value="hi">हिन्दी</option>
            </select>
        </div>
        <div class="select_container donation">
            <select class="donate" name="" id="">
                <option value="donate">Donate</option>
            </select>
        </div>
    </div>
</nav>

<style>
    .nav_container {
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #ffffff;
        padding: 10px 4rem;
    }

    .logo img {
        height: 50px;
    }

    ul {
        display: flex;
        list-style: none;
        margin: 0;
        padding: 0;
    }

    li {
        margin: 0 10px;
    }

    a {
        text-decoration: none;
        color: #0D2561;
    }

    .active {
        font-weight: bold;
    }

    .select-group {
        display: flex;
        gap: 10px;
    }

    .select-group .select_container {
        position: relative;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 15px;
        border-radius: 25px;
        border: 2px solid;
        cursor: pointer;
        border: 2px solid #0155BD;
    }

    .select-group select {
        width: 100%;
        padding: 8px 10px;
        cursor:pointer;
        background: none;
        border: none;
        outline: none;
    }

    .language {
        background: none;
        color: #0155BD;
    }

    .donation {
        background-color: #0155BD;
        color: white;
    }

</style>
