<script>
  import Send from "../../assets/images/send.png";
  import EllipseImage from '../../assets/images/Ellipse 64.png'
</script>

<div class="relative min-h-screen flex flex-col items-center mt-20">
  <!-- svelte-ignore a11y-img-redundant-alt -->
  <img src={EllipseImage} alt="Ellipse Image" class="ellipse-image"/>
  <div class="flex flex-col items-center relative z-10 w-full mt-4">
    <div class="text-center mb-12">
      <h1 class="text-4xl font-bold mb-4 heading" style="color: #0D2561; padding-top: 90px;">How We Support You</h1>
      <p class="text-xl mb-8" style="color: #0D2561;">Comprehensive care and support throughout your cancer journey</p>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-4 gap-8 mb-12">
      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <img src={Send} alt="Send Icon" class="send-icon"/>
        </div>
        <h2 class="title text-3xl font-semibold text-center mb-4">Patient Advocacy</h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          We stand by your side,<br/>
          helping navigate treatment<br/>
          options and healthcare systems
        </p>
        <div class="text-center">
          <button class="know-more-group">
            <span class="know-more">Know More</span>
          </button>
        </div>
      </div>
      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <img src={Send} alt="Send Icon" class="send-icon"/>
        </div>
        <h2 class="title text-3xl font-semibold text-center mb-4">Emotional Support</h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          Access to counseling<br/>
          and support groups<br/>
          to help you cope with your diagnosis
        </p>
        <div class="text-center">
          <button class="know-more-group">
            <span class="know-more">Know More</span>
          </button>
        </div>
      </div>
      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <img src={Send} alt="Send Icon" class="send-icon"/>
        </div>
        <h2 class="title text-3xl font-semibold text-center mb-4">Education Resources</h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          Up-to-date information<br/>
          on treatments, clinical trials,<br/>
          and cancer management
        </p>
        <div class="text-center">
          <button class="know-more-group">
            <span class="know-more">Know More</span>
          </button>
        </div>
      </div>
      <div class="card p-6 border-2 rounded-lg shadow-md bg-white h-96" style="border-color: #2C72B9;">
        <div class="flex justify-center mb-8 mt-4">
          <img src={Send} alt="Send Icon" class="send-icon"/>
        </div>
        <h2 class="title text-3xl font-semibold text-center mb-4">Cancer Connect</h2>
        <p class="text-center mb-6" style="color: #0D2561;">
          A community of dedicated <br/>caregivers who will help you<br/> navigate in your uncertain times
        </p>
        <div class="text-center">
          <button class="know-more-group">
            <span class="know-more">Know More</span>
          </button>
        </div>
      </div>
    </div>

    <div class="text-center">
      <button class="additional-know-more">
        Know More
      </button>
    </div>
  </div>
</div>

<style>
  .ellipse-image {
    position: absolute; 
    top: 0;
    left: 0;
    width: 100%;
    height: auto;
    z-index: -1; 
  }

  .relative {
    position: relative;
  }

  .flex {
    display: flex;
  }

  .min-h-screen {
    min-height: 100vh;
  }

  .w-full {
    width: 100%;
  }

  .heading {
    margin-bottom: 20px; 
  }

  .card {
    position: relative;
    overflow: hidden;
  }

  .card:hover {
    background: radial-gradient(circle, #e8dcff 0%, #d9edfe 50%, #ebe1ff 100%);
  }

  .send-icon {
    height: 48px; 
    width: 48px; 
  }

  .title {
    color: #0D2561; 
  }

  .know-more-group {
    position: relative;
    display: inline-block;
    padding: 8px 16px;
    color: #2C72B9;
    text-decoration: underline;
    text-decoration-thickness: 1px;
    font-weight: bold;
    overflow: hidden;
    border-radius: 20px;
  }

  .know-more-group::before {
    content: '';
    position: absolute;
    top: 50%;
    left: 50%;
    width: 0;
    height: 0;
    background: #DBEAFE; 
    border-radius: 50%;
    transform: translate(-50%, -50%);
    z-index: -1;
  }

  .know-more-group:hover::before {
    width: 200%;
    height: 200%;
    left: 50%;
    top: 50%;
  }

  .card:hover .know-more-group {
    background-color: #ffffff;
    color: #0D2561;
    border-radius: 20px;
    padding: 10px 20px;
  }

  .know-more {
    position: relative;
    z-index: 1; 
  }

  .know-more:hover {
    color: #0D2561; 
  }

  .additional-know-more {
    display: inline-block;
    padding: 12px 24px;
    text-decoration: none;
    font-weight: bold;
    border-radius: 30px; 
    background-color: #0155BD;
    color: #ffffff;
  }
</style>
