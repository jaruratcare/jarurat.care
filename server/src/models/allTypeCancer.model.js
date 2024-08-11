import mongoose from 'mongoose';

const allCancerDataSchema = new mongoose.Schema({
  url: { type: String, required: true },
  mainHead: String,
  overview: {
    title: String,
    description: String
  },
  treatment: {
    title: String,
    subTitle: String,
    list: [
      {
        text: String,
        link: String
      }
    ]
  },
  causesNpreventions: {
    title: String,
    subTitle: String,
    list: [
      {
        text: String,
        link: String
      }
    ]
  },
  screening: {
    title: String,
    subTitle: String,
    list: [
      {
        text: String,
        link: String
      }
    ]
  }
});

const AllCancerData = mongoose.model('AllCancerData', allCancerDataSchema);
export default AllCancerData;
