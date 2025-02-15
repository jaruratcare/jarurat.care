import { useState, useEffect } from "react";
import Button from "./Button";

const ImpactSection = () => {
  const [impactData, setImpactData] = useState([
    { label: "Mentor", value: 0 },
    { label: "Doctors", value: 0 },
    { label: "People", value: 0 },
    { label: "Early Treatments Initiated", value: "0%" },
    { label: "Patients Assisted", value: 0 },
    { label: "People Reached", value: 0 },
    { label: "Emotional Support Services", value: 0 },
    { label: "Early Treatments Initiated", value: "0%" },
  ]);

  // Simulate fetching data
  useEffect(() => {
    setTimeout(() => {
      setImpactData([
        { label: "Mentor", value: 25 },
        { label: "Doctors", value: 50 },
        { label: "People", value: 200 },
        { label: "Early Treatments Initiated", value: "75%" },
        { label: "Patients Assisted", value: 120 },
        { label: "People Reached", value: 5000 },
        { label: "Emotional Support Services", value: 300 },
        { label: "Early Treatments Initiated", value: "85%" },
      ]);
    }, 2000); // Simulate API delay
  }, []);

  return (
    <section className="py-12 bg-gradient-to-r from-blue-50 to-blue-100 px-4">
      <div className="max-w-7xl mx-auto text-center">
        <h2 className="text-3xl font-bold mb-4 text-blue-950">Our Impact</h2>
        <p className="text-lg mb-12 text-blue-950">
          Explore the real-world difference we're making in cancer care.
        </p>
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {impactData.map((item, index) => (
            <div key={index} className="bg-white p-6 rounded-lg shadow-md">
              <p className="text-5xl font-bold text-blue-950 mb-2 text-left">
                {item.value}
              </p>
              <p className="text-gray-600 text-left">{item.label}</p>
            </div>
          ))}
        </div>
        <div className="flex justify-center items-center mt-6">
          <Button text="Seek Support" />
        </div>
      </div>
    </section>
  );
};

export default ImpactSection;
