import React, { useEffect, useState } from "react";
import LocateIcon from "../../assets/svg/LocateIcon";
import LocationArrow from "../../assets/svg/LocationArrow";
import Map from "../../assets/svg/Map";
import SearchIcon from "../../assets/svg/SearchIcon";

const statesAndUnionTerritories = [
  { id: "AP", state: "Andhra Pradesh" },
  { id: "AR", state: "Arunachal Pradesh" },
  { id: "AS", state: "Assam" },
  { id: "BR", state: "Bihar" },
  { id: "CG", state: "Chhattisgarh" },
  { id: "GA", state: "Goa" },
  { id: "GJ", state: "Gujarat" },
  { id: "HR", state: "Haryana" },
  { id: "HP", state: "Himachal Pradesh" },
  { id: "JH", state: "Jharkhand" },
  { id: "KA", state: "Karnataka" },
  { id: "KL", state: "Kerala" },
  { id: "MP", state: "Madhya Pradesh" },
  { id: "MH", state: "Maharashtra" },
  { id: "MN", state: "Manipur" },
  { id: "ML", state: "Meghalaya" },
  { id: "MZ", state: "Mizoram" },
  { id: "NL", state: "Nagaland" },
  { id: "OD", state: "Odisha" },
  { id: "PB", state: "Punjab" },
  { id: "RJ", state: "Rajasthan" },
  { id: "SK", state: "Sikkim" },
  { id: "TN", state: "Tamil Nadu" },
  { id: "TS", state: "Telangana" },
  { id: "TR", state: "Tripura" },
  { id: "UP", state: "Uttar Pradesh" },
  { id: "UK", state: "Uttarakhand" },
  { id: "WB", state: "West Bengal" },
  { id: "DL", state: "Delhi" },
  { id: "JK", state: "Jammu and Kashmir" },
];

const LocateHospitals = () => {
  const [stateName, setStateName] = useState("Delhi");
  const [selectedState, setSelectedState] = useState(null);

  useEffect(() => {
    const states = document.querySelectorAll(".state");

    states.forEach((state) => {
      state.style.cursor = "pointer";

      // Reset all states to white, except selected
      if (state.id === selectedState) {
        state.style.fill = "#0D2561"; // Keep selected state hovered color
      } else {
        state.style.fill = "#ffffff"; // Default white
      }

      // Hover Effect
      const handleMouseEnter = () => {
        if (state.id !== selectedState) {
          state.style.fill = "#0D2561"; // Highlight on hover
        }
      };

      const handleMouseLeave = () => {
        if (state.id !== selectedState) {
          state.style.fill = "#ffffff"; // Reset if not selected
        }
      };

      // Click to select state
      const handleClick = (event) => {
        setSelectedState(event.target.id); // Update selected state
        setStateName(
          statesAndUnionTerritories.find((s) => s.id === event.target.id)?.state
        );
      };

      state.addEventListener("mouseenter", handleMouseEnter);
      state.addEventListener("mouseleave", handleMouseLeave);
      state.addEventListener("click", handleClick);

      // Cleanup event listeners
      return () => {
        state.removeEventListener("mouseenter", handleMouseEnter);
        state.removeEventListener("mouseleave", handleMouseLeave);
        state.removeEventListener("click", handleClick);
      };
    });
  }, [selectedState]);

  return (
    <div className="w-full py-24 px-4 md:px-16">
      <div className="text-center">
        <h2 className="text-[#0D2561] text-3xl md:text-4xl font-semibold">
          Locate Cancer Hospitals Near You
        </h2>
        <p className="text-[#0D2561] text-lg md:text-xl font-medium mt-1">
          Finding Hope, One Location at a Time
        </p>
      </div>

      <div className="mt-8 flex flex-col md:flex-row items-center justify-center gap-4">
        <div className="w-full md:w-[25rem] px-5 flex items-center bg-[#DBE1E6] rounded-full p-2">
          <SearchIcon className="w-5 h-5" />
          <input
            type="text"
            placeholder="Enter your location"
            className="flex-1 bg-transparent border-none outline-none text-gray-700 px-4"
          />
          <LocateIcon className="w-5 h-5" />
        </div>
        <div className="w-full md:w-[25rem] flex items-center bg-[#0155BD] rounded-full p-2">
          <select
            className="w-full bg-[#0155BD] border-none outline-none text-white px-4 py-2"
            onChange={(e) => {
              const selected = statesAndUnionTerritories.find(
                (state) => state.state === e.target.value
              );
              if (selected) setSelectedState(selected.id);
              setStateName(e.target.value);
            }}
          >
            <option value="" disabled>
              Select your state
            </option>
            {statesAndUnionTerritories.map((state) => (
              <option key={state.id} value={state.state} id={state.id}>
                {state.state}
              </option>
            ))}
          </select>
        </div>
      </div>

      <div className="w-full mt-16 flex flex-col md:flex-row items-start">
        <div className="w-full md:w-1/2 flex justify-center mb-8 md:mb-0">
          <div className="w-3/5">
            <p className="text-sm text-[#0155BD] bg-[#e3efff] py-2 px-4 border-l-4 border-[#0155BD] rounded-r-md">
              Note: We might be missing some hospitals or they may be
              unregistered.
            </p>
            <div className="mt-5 rounded-t-md overflow-hidden">
              <p className="bg-[#0D2561] text-white p-3 text-lg md:text-2xl font-semibold">
                {stateName}
              </p>
              <ul>
                {[...Array(4)].map((_, i) => (
                  <li key={i} className="py-2 px-4 border-b">
                    <p>Wockhardt Hospital</p>
                    <p className="text-[#868B93] flex justify-between">
                      Kalawad Road Near St. Mary's High Scho...{" "}
                      <LocationArrow className="w-4 h-4" />
                    </p>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>

        <div className="w-full md:w-1/2 flex justify-center">
          <Map className="w-full max-w-md md:max-w-lg" />
        </div>
      </div>
    </div>
  );
};

export default LocateHospitals;
