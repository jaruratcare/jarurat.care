import axios from 'axios';
import cheerio from 'cheerio';
import mongoose from 'mongoose';
import connectToDb from '../index.js';
import AllCancerData from './models/allTypeCancer.model.js';
import BladderCancerData from './models/bladderCancer.model.js';

console.log('Script is running');

const bladderCancerUrls = [
  { type: 'introduction', url: 'https://www.cancer.gov/types/bladder' },
  { type: 'causes&risk', url: 'https://www.cancer.gov/types/bladder/causes-risk-factors' },
  { type: 'symptoms', url: 'https://www.cancer.gov/types/bladder/symptoms' },
  { type: 'screening', url: 'https://www.cancer.gov/types/bladder/screening' },
  { type: 'stages', url: 'https://www.cancer.gov/types/bladder/stages' }
];

const allTypeOfCancer = [
  'https://www.cancer.gov/types/breast',
  'https://www.cancer.gov/types/colorectal',
  'https://www.cancer.gov/types/kidney',
  'https://www.cancer.gov/types/lung',
  'https://www.cancer.gov/types/lymphoma',
  'https://www.cancer.gov/types/pancreatic',
  'https://www.cancer.gov/types/prostate',
  'https://www.cancer.gov/types/skin',
  'https://www.cancer.gov/types/uterine',
  'https://www.cancer.gov/types/leukemia',
  'https://www.cancer.gov/types/adrenocortical',
  'https://www.cancer.gov/types/soft-tissue-sarcoma',
  'https://www.cancer.gov/types/anal',
  'https://www.cancer.gov/types/gi-neuroendocrine-tumors',
  'https://www.cancer.gov/types/brain',
  'https://www.cancer.gov/types/bone',
  'https://www.cancer.gov/types/unknown-primary',
  'https://www.cancer.gov/types/myeloproliferative',
  'https://www.cancer.gov/types/esophageal',
  'https://www.cancer.gov/types/head-and-neck',
  'https://www.cancer.gov/types/extracranial-germ-cell',
  'https://www.cancer.gov/types/eye',
  'https://www.cancer.gov/types/retinoblastoma',
  'https://www.cancer.gov/types/ovarian',
  'https://www.cancer.gov/types/testicular',
  'https://www.cancer.gov/types/gestational-trophoblastic',
  'https://www.cancer.gov/types/langerhans',
  'https://www.cancer.gov/types/mesothelioma',
  'https://www.cancer.gov/types/myeloma',
  'https://www.cancer.gov/types/neuroblastoma',
  'https://www.cancer.gov/types/pheochromocytoma',
  'https://www.cancer.gov/types/parathyroid',
  'https://www.cancer.gov/types/penile',
  'https://www.cancer.gov/types/pituitary',
  'https://www.cancer.gov/types/small-intestine',
  'https://www.cancer.gov/types/thymoma',
  'https://www.cancer.gov/types/thyroid',
  'https://www.cancer.gov/types/urethral',
  'https://www.cancer.gov/types/vaginal',
  'https://www.cancer.gov/types/soft-tissue-sarcoma/hp',
  'https://www.cancer.gov/types/vulvar'
];

let bladderCancerData = [];
let allCancerData = [];

(async () => {
  // connecting to database
  await connectToDb();
  console.log('Database connected successfully');

  for (const { url, type } of bladderCancerUrls) {
    try {
      const { data } = await axios.get(url);
      const $ = cheerio.load(data);
      let extractedData = {};

      switch (type) {
        case 'introduction':
          extractedData = {
            type,
            title: $('.resize-content > h1:nth-child(1)').text(),
            definition: $('#_2').text(),
            types: $('#_6').text()
          };
          break;

        case 'causes&risk':
          extractedData = {
            type,
            causes: {
              title: $('.resize-content > h1:nth-child(1)').text(),
              causeDesc_1: $('#_2').text(),
              causeDesc_2: $('#_3').text()
            },
            risks: {
              title: $('#_4 > h2:nth-child(1)').text(),
              causeDesc_1: $('#_5').text(),
              causeDesc_2: $('#_6').text()
            }
          };
          break;

        case 'symptoms':
          extractedData = {
            type,
            title: $('.resize-content > h1:nth-child(1)').text(),
            desc: $('#_2').text(),
            commonSymptoms: $('#_4 li')
              .map((index, element) => {
                return $(element).text().trim();
              })
              .get(),
            dangerSymptoms: $('#_10 li')
              .map((index, element) => {
                return $(element).text().trim();
              })
              .get()
          };
          break;

        case 'screening':
          extractedData = {
            type,
            title: $('.resize-content > h1:nth-child(1)').text(),
            definition: $('#_2').text(),
            testsTitle: $('#_4 > h2:nth-child(1)').text(),
            description: $('#_42').text(),
            tests: $('#_section_4 p:nth-child(n+2)')
              .map((index, element) => {
                return $(element).text().trim();
              })
              .get()
          };
          break;

        case 'stages':
          extractedData = {
            type,
            title: $('.resize-content > h1:nth-child(1)').text(),
            desc_1: $('#_2').text(),
            desc_2: $('#_3').text(),
            stage_0: {
              title: $('#_6 > h2:nth-child(1)').text(),
              desc: $('#_8').text(),
              types: $('#_9 li')
                .map((i, element) => {
                  return $(element).text().trim();
                })
                .get()
            },
            stage_1: {
              title: $('#_11 > h2:nth-child(1)').text(),
              desc: $('#_12').text()
            },
            stage_2: {
              title: $('#_15 > h2:nth-child(1)').text(),
              desc: $('#_16').text()
            },
            stage_3: {
              title: $('#_19 > h2:nth-child(1)').text(),
              desc: $('#_20').text(),
              type_1: { type: 'stage IIIA ', desc: $('#_22 > li:nth-child(1)').text() },
              type_2: {
                type: 'stage IIIB ',
                desc: $('#figure_24 > figcaption:nth-child(3)').text()
              }
            },
            stage_4: {
              title: $('#_26 > h2:nth-child(1)').text(),
              types: {
                type_1: $('#_29')
                  .map((i, element) => {
                    return $(element).text();
                  })
                  .get(),
                type_2: $('#_31').text()
              }
            }
          };
          break;
      }

      const existingData = await BladderCancerData.findOne({ type });
      if (!existingData) {
        bladderCancerData.push(extractedData);
      }
    } catch (error) {
      console.log(`Error fetching ${type}:`, error.message);
    }
  }

  for (const url of allTypeOfCancer) {
    try {
      const { data } = await axios.get(url);
      const $ = cheerio.load(data);
      let extractedData = {};

      extractedData = {
        url,
        mainHead: $('.large-12 > h1:nth-child(1)').text(),
        overview: {
          title: $('.cthp-overview > h2:nth-child(1)').text(),
          description: $('.cthp-overview > div:nth-child(2) > p:nth-child(1)').text()
        },
        treatment: {
          title: $('.cthp-treatment > h2:nth-child(1)').text(),
          subTitle: $('.cthp-treatment > div:nth-child(2) > h4:nth-child(1)').text().trim(),
          list: $('.cthp-treatment > div:nth-child(2) > ul > li > a')
            .map((index, element) => {
              return {
                text: $(element).text().trim(),
                link: $(element).attr('href')
              };
            })
            .get()
        },
        causesNpreventions: {
          title: $('.cthp-causes > h2:nth-child(1)').text().trim(),
          subTitle: $('.cthp-causes > div:nth-child(2) > h4:nth-child(1)').text().trim(),
          list: $('.cthp-causes > div:nth-child(2) > ul:nth-child(2) > li > a')
            .map((index, element) => {
              return {
                text: $(element).text().trim(),
                link: $(element).attr('href')
              };
            })
            .get()
        },
        screening: {
          title: $('.cthp-screening > h2:nth-child(1)').text().trim(),
          subTitle: $('.cthp-screening > div:nth-child(2) > h4:nth-child(1)').text().trim(),
          list: $('.cthp-screening > div:nth-child(2) > ul:nth-child(2) > li > a')
            .map((index, element) => {
              return {
                text: $(element).text().trim(),
                link: $(element).attr('href')
              };
            })
            .get()
        }
      };

      // Check if data already exists in the database
      const existingData = await AllCancerData.findOne({ url });
      if (!existingData) {
        allCancerData.push(extractedData);
      }
      // console.log(extractedData);
    } catch (error) {
      console.log(`Error fetching data from ${url}:`, error.message);
    }
  }

  for (const data of bladderCancerData) {
    const bladderCancerEntry = new BladderCancerData(data);
    await bladderCancerEntry.save();
  }

  // Save all cancer data to the database
  for (const data of allCancerData) {
    const allCancerEntry = new AllCancerData(data);
    await allCancerEntry.save();
  }

  mongoose.connection.close();
})();
