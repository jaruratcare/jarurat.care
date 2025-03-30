import { useState, useEffect } from "react";
import Button from "./Button";
import "../../css/home/Impact.css";

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
    }, 2000);
  }, []);

  return (
    <section className="impact-section">
      <div className="impact-container">
        <h2 className="impact-title">Our Impact</h2>
        <p className="impact-description">
          Explore the real-world difference we're making in cancer care.
        </p>
        <div className="impact-grid">
          {impactData.map((item, index) => (
            <div key={index} className="impact-card">
              <p className="impact-value">{item.value}</p>
              <p className="impact-label">{item.label}</p>
            </div>
          ))}
        </div>
        <div className="impact-button-container">
          <Button text="Seek Support" />
        </div>
      </div>
    </section>
  );
};

export default ImpactSection;
