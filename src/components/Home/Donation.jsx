import HeartDonateIcon from "../../assets/svg/HeartDonateIcon.jsx";
import DonateBg from "../../assets/svg/Donatebg.jsx";
import SeekSupportIcon from "../../assets/svg/SeekSupportIcon.jsx";
import MemberIcon from "../../assets/svg/MemberIcon.jsx";
import Button from "./Button.jsx";

const SupportDonateMember = () => {
  return (
    <div className="relative w-full border border-[#0072C4] bg-white overflow-hidden mb-10">
      {/* Background */}
      <div className="absolute w-full h-full z-0 inset-0 overflow-hidden">
        <DonateBg />
      </div>

      {/* Content Grid */}
      <div className="relative z-10 grid grid-cols-1 md:grid-cols-3 text-center text-white">
        {/* Seek Support */}
        <div className="py-8 px-6 flex flex-col justify-center items-center border-b md:border-b-0">
          <span className="bg-white p-3 rounded-full">
            <SeekSupportIcon />
          </span>
          <h2 className="md:mt-6 md:mb-6 mt-4 mb-4 text-2xl md:text-4xl font-bold font-serif text-[#0155BD]">
            Seek Support
          </h2>
          <p className="text-gray-800 text-sm md:text-base w-[70%] md:w-[50%] mt-8 md:mt-12">
            Our NGO is here to help you through your cancer journey.{" "}
            <span className="font-bold">Reach out to us today.</span>
          </p>
          <Button text="Seek Support" />
        </div>

        {/* Donate */}
        <div className="py-8 px-6 flex flex-col justify-center items-center border-t md:border-t-0 md:border-x border-[#0072C4] border-b">
          <span className="bg-white p-3 rounded-full">
            <HeartDonateIcon />
          </span>
          <h2 className="md:mt-6 md:mb-6 mt-4 mb-4 text-2xl md:text-4xl font-bold font-serif text-[#0155BD]">
            Donate
          </h2>
          <p className="text-gray-800 text-sm md:text-base w-[70%] md:w-[50%] mt-8 md:mt-12">
            One Life at a Time. <span className="font-bold">Donate</span> today
            and be the light that brings hope and healing to cancer patients.
          </p>
          <Button text="Donate Now" />
        </div>

        {/* Become a Member */}
        <div className="py-8 px-6 flex flex-col justify-center items-center">
          <span className="bg-white p-3 rounded-full">
            <MemberIcon />
          </span>
          <h2 className="md:mt-6 md:mb-6 mt-4 mb-4 text-2xl md:text-4xl font-bold font-serif text-[#0155BD]">
            Become a Member
          </h2>
          <p className="text-gray-800 text-sm md:text-base w-[70%] md:w-[50%] mt-8 md:mt-12">
            Be a hero. <span className="font-bold">Volunteer</span> with us and
            provide support and hope to those affected by cancer.
          </p>
          <Button text="Get Started" />
        </div>
      </div>
    </div>
  );
};

export default SupportDonateMember;
