import { useEffect, useState } from "react";
import { motion, AnimatePresence } from "framer-motion";
import gradient from "../../assets/images/gradient.png";
import Button from "./Button";
import "../../css/home/Hero.css";

const HeroSection = () => {
  const [showFinalScreen, setShowFinalScreen] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      setShowFinalScreen(true);
    }, 1500);
  }, []);

  return (
    <div id="home" className="hero-section">
      <AnimatePresence mode="wait">
        {!showFinalScreen ? (
          <motion.div
            key="placeholder"
            className="placeholder"
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ x: -1000 }}
            transition={{ duration: 1 }}
          />
        ) : (
          <motion.div
            key="content"
            className="hero-content"
            initial={{ opacity: 0, y: 1000 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -1000 }}
            transition={{ duration: 1 }}
          >
            <h1 className="hero-title">JARURAT CARE</h1>
            <h2 className="hero-subtitle">Jaisi Jarurat Vaisi Care</h2>
            <p className="hero-description">
              Providing support, guidance, hope, and personalized care for{" "}
              <span className="highlight">cancer patients</span> and their
              families. Here to ensure you never face your journey alone.
            </p>
            <Button text="Seek Cancer Support" />
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
};

export default HeroSection;
