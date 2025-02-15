import medicalRibbon from "../../assets/images/medical-ribbon.png";

const MissionSection = () => {
  return (
    <div className="relative bg-custom-blue text-white py-16 px-8">
      <div className="max-w-2xl mx-auto text-left">
        <p className="text-lg font-semibold text-[#5ED8FF]">Who we are</p>
        <h1 className="text-3xl mt-5">
          Our mission is to support and empower cancer patients and their
          families.
        </h1>
        <h2 className="mt-6 text-l">
          Through comprehensive programs, Jarurat Care provides vital support to
          those affected by cancer. We offer emotional, financial, and practical
          assistance to help patients and their families navigate their cancer
          journey.
        </h2>
      </div>
      <img
        src={medicalRibbon}
        alt="Medical Ribbon"
        className="absolute right-40 top-1/2 transform -translate-y-1/2 w-32 hidden lg:block"
      />
    </div>
  );
};

export default MissionSection;
