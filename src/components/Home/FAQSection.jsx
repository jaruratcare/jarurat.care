import React, { useState } from "react";
import { motion, AnimatePresence } from "framer-motion"; // For animations
import PlusIcon from "../../assets/svg/PlusIcon.jsx";
import bg from "../../assets/images/Frame111.png";

const faqs = [
  {
    id: 1,
    question: "What is Mentor?",
    answer:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.",
  },
  {
    id: 2,
    question: "How do I apply for assistance?",
    answer:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.",
  },
  {
    id: 3,
    question: "What areas or regions does Jarurat Care operate in?",
    answer:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.",
  },
  {
    id: 4,
    question: "How can I apply for assistance from Jarurat Care?",
    answer:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.",
  },
  {
    id: 5,
    question: "Who is eligible for support?",
    answer:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.",
  },
  {
    id: 6,
    question: "Are the services free?",
    answer:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.",
  },
  {
    id: 7,
    question: "How can I volunteer or donate?",
    answer:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.",
  },
  {
    id: 8,
    question: "How can I volunteer or donate?",
    answer:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.",
  },
];

const FAQSection = () => {
  const [openId, setOpenId] = useState(null);

  const handleClick = (id) => {
    setOpenId(openId === id ? null : id);
  };

  return (
    <section
      id="FAQs"
      className="w-full flex flex-col justify-center items-center py-20 bg-cover bg-no-repeat bg-center px-6 md:px-16"
      style={{ backgroundImage: `url(${bg})` }}
    >
      <div className="text-center w-full max-w-3xl mx-auto">
        <h2 className="text-[2rem] font-medium text-[#0D2561] font-rubik leading-tight">
          Frequently Asked Questions
        </h2>
        <p className="text-[#596274] text-xl font-medium font-manrope mt-2">
          Look through some of our frequently answered questions
        </p>
      </div>

      <div className="my-24 w-full max-w-4xl flex flex-wrap justify-center gap-6">
        {faqs.map((faq) => (
          <div
            key={faq.id}
            className="w-full md:w-[48.5%] bg-white rounded-md shadow-md overflow-hidden"
          >
            <button
              onClick={() => handleClick(faq.id)}
              className="w-full flex justify-between items-center py-6 px-5 text-[#0D2561] font-medium text-sm"
            >
              <span>{faq.question}</span>
              <span>
                <PlusIcon width={25} height={25} />
              </span>
            </button>
            <AnimatePresence>
              {openId === faq.id && (
                <motion.div
                  initial={{ opacity: 0, height: 0 }}
                  animate={{ opacity: 1, height: "auto" }}
                  exit={{ opacity: 0, height: 0 }}
                  transition={{ duration: 0.4, ease: "easeInOut" }}
                  className="px-5 pb-3 text-[#596274] text-base leading-relaxed overflow-hidden"
                >
                  {faq.answer}
                </motion.div>
              )}
            </AnimatePresence>
          </div>
        ))}
      </div>

      <div className="flex flex-col justify-center items-center text-center mt-12">
        <h2 className="text-[2rem] font-medium text-[#0D2561] font-rubik leading-tight">
          Still Have Questions?
        </h2>
        <p className="text-[#596274] text-xl font-medium font-manrope mt-2">
          Feel free to contact us
        </p>
        <button className="relative z-1 mt-12 px-9 py-2.5 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-[#0155BD] group flex items-center justify-center overflow-hidden duration-200 ease">
          <span
            className="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
            style={{ transform: "translateX(-50%)" }}
          ></span>
          <span className="relative">Contact Us</span>
        </button>
      </div>
    </section>
  );
};

export default FAQSection;
