import React, { useState } from "react";
import { motion, AnimatePresence } from "framer-motion";
import PlusIcon from "../../assets/svg/PlusIcon.jsx";
import bg from "../../assets/images/Frame111.png";
import "../../css/home/FAQSection.css";
import Button from "./Button.jsx";

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
      className="faq-section"
      style={{ backgroundImage: `url(${bg})` }}
    >
      {/* Header Section */}
      <div className="faq-header">
        <h2>Frequently Asked Questions</h2>
        <p>Look through some of our frequently answered questions</p>
      </div>

      {/* FAQ Items Section */}
      <div className="faq-items">
        {faqs.map((faq) => (
          <div key={faq.id} className="faq-item">
            <button
              onClick={() => handleClick(faq.id)}
              className="faq-question"
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
                  className="faq-answer"
                >
                  {faq.answer}
                </motion.div>
              )}
            </AnimatePresence>
          </div>
        ))}
      </div>

      {/* Contact Section */}
      <div className="faq-contact">
        <h2>Still Have Questions?</h2>
        <p>Feel free to contact us</p>
        {/* <button className="contact-button">
          <span className="button-overlay"></span>
          <span className="button-text">Contact Us</span>
        </button> */}
        <div className="faqButton">
          <Button text={"Contact Us"} />
        </div>
      </div>
    </section>
  );
};

export default FAQSection;
