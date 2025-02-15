import React from "react";
import HeroSection from "../components/Home/Hero";
import MissionSection from "../components/Home/Mission";
import SupportDonateMember from "../components/Home/Donation";
import StorySection from "../components/Home/StorySection";
import ImpactSection from "../components/Home/Impact";
import SupportSection from "../components/Home/Support";
import MentorSection from "../components/Home/MentorSection";
import LocateHospitals from "../components/Home/HospitalLocation";
import ArticlesCarousel from "../components/Home/CancerResearch";
import VoicesOfHope from "../components/Home/VoiceOfHope";
import TeamMessageCarousel from "../components/Home/TeamMessageCarousel";
import FAQSection from "../components/Home/FAQSection";

const Home = () => {
  return (
    <div>
      <HeroSection />
      <MissionSection />
      <SupportDonateMember />
      <StorySection />
      <ImpactSection />
      <SupportSection />
      <MentorSection />
      <LocateHospitals />
      <ArticlesCarousel />
      <VoicesOfHope />
      <TeamMessageCarousel />
      <FAQSection />
    </div>
  );
};

export default Home;
