import React, { useEffect, useState } from "react";
import LocateIcon from "../../assets/svg/LocateIcon";
import LocationArrow from "../../assets/svg/LocationArrow";
import Map from "../../assets/svg/Map";
import SearchIcon from "../../assets/svg/SearchIcon";
import "../../css/home/HospitalLocation.css";

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

      if (state.id === selectedState) {
        state.style.fill = "#0D2561";
      } else {
        state.style.fill = "#ffffff";
      }

      const handleMouseEnter = () => {
        if (state.id !== selectedState) {
          state.style.fill = "#0D2561";
        }
      };

      const handleMouseLeave = () => {
        if (state.id !== selectedState) {
          state.style.fill = "#ffffff";
        }
      };

      const handleClick = (event) => {
        setSelectedState(event.target.id);
        setStateName(
          statesAndUnionTerritories.find((s) => s.id === event.target.id)?.state
        );
      };

      state.addEventListener("mouseenter", handleMouseEnter);
      state.addEventListener("mouseleave", handleMouseLeave);
      state.addEventListener("click", handleClick);

      return () => {
        state.removeEventListener("mouseenter", handleMouseEnter);
        state.removeEventListener("mouseleave", handleMouseLeave);
        state.removeEventListener("click", handleClick);
      };
    });
  }, [selectedState]);

  return (
    <div className="locate-container">
      <div className="locate-header">
        <h2>Locate Cancer Hospitals Near You</h2>
        <p>Finding Hope, One Location at a Time</p>
      </div>

      <div className="locate-search-container">
        <div className="search-input">
          <SearchIcon className="icon" />
          <input type="text" placeholder="Enter your location" />
          <LocateIcon className="icon" />
        </div>
        <div className="state-dropdown">
          <select
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

      <div className="locate-content">
        <div className="hospital-list">
          <p className="note">
            Note: We might be missing some hospitals or they may be
            unregistered.
          </p>
          <div className="hospital-card">
            <p className="hospital-header">{stateName}</p>
            <ul>
              {[...Array(4)].map((_, i) => (
                <li key={i}>
                  <p>Wockhardt Hospital</p>
                  <p className="hospital-address">
                    Kalawad Road Near St. Mary's High Scho...
                    <LocationArrow className="icon" />
                  </p>
                </li>
              ))}
            </ul>
          </div>
        </div>

        <div className="map-container">
          <Map className="map" />
        </div>
      </div>
    </div>
  );
};

export default LocateHospitals;
