import mongoose from 'mongoose';

const bladderCancerDataSchema = new mongoose.Schema({
  type: { type: String, required: true },
  title: String,
  definition: String,
  types: String,
  causes: {
    title: String,
    causeDesc_1: String,
    causeDesc_2: String
  },
  risks: {
    title: String,
    causeDesc_1: String,
    causeDesc_2: String
  },
  symptoms: {
    title: String,
    desc: String,
    commonSymptoms: [String],
    dangerSymptoms: [String]
  },
  screening: {
    title: String,
    definition: String,
    testsTitle: String,
    description: String,
    tests: [String]
  },
  stages: {
    title: String,
    desc_1: String,
    desc_2: String,
    stage_0: {
      title: String,
      desc: String,
      types: [String]
    },
    stage_1: {
      title: String,
      desc: String
    },
    stage_2: {
      title: String,
      desc: String
    },
    stage_3: {
      title: String,
      desc: String,
      type_1: { type: String, desc: String },
      type_2: { type: String, desc: String }
    },
    stage_4: {
      title: String,
      types: {
        type_1: [String],
        type_2: String
      }
    }
  }
});

const BladderCancerData = mongoose.model('BladderCancerData', bladderCancerDataSchema);
export default BladderCancerData;
