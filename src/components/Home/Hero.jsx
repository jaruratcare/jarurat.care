import { useEffect, useState } from "react";
import { motion, AnimatePresence } from "framer-motion";
import gradient from "../../assets/images/gradient.png";
import Button from "./Button";

const HeroSection = () => {
  const [showFinalScreen, setShowFinalScreen] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      setShowFinalScreen(true);
    }, 1500);
  }, []);

  return (
    <div
      id="home"
      className="relative h-screen w-screen flex items-center justify-center overflow-hidden px-6"
      style={{ background: `url(${gradient}) no-repeat center center / cover` }}
    >
      <AnimatePresence mode="wait">
        {!showFinalScreen ? (
          <motion.div
            key="placeholder"
            className="flex flex-col items-center gap-4 text-white"
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ x: -1000 }}
            transition={{ duration: 1 }}
          />
        ) : (
          <motion.div
            key="content"
            className="flex flex-col items-center text-center gap-4"
            initial={{ opacity: 0, y: 1000 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -1000 }}
            transition={{ duration: 1 }}
          >
            <h1 className="text-4xl md:text-6xl lg:text-8xl font-extrabold text-[#0464C4]">
              JARURAT CARE
            </h1>
            <h2 className="text-xl md:text-3xl lg:text-4xl font-semibold text-[#132F78]">
              Jaisi Jarurat Vaisi Care
            </h2>
            <p className="text-blue-900 text-sm md:text-lg lg:text-xl max-w-md md:max-w-lg lg:max-w-xl">
              Providing support, guidance, hope, and personalized care for{" "}
              <span className="underline font-bold">cancer patients</span> and
              their families. Here to ensure you never face your journey alone.
            </p>
            <Button text="Seek Cancer Support" />
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
};

export default HeroSection;
