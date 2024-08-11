import mongoose from 'mongoose';
import dotenv from 'dotenv';

dotenv.config({
  path: './.env'
});

const DB_URL = process.env.DB_URL;

const connectToDb = async () => {
  try {
    await mongoose.connect(DB_URL);
    console.log('Connected to MongoDB');
  } catch (error) {
    console.log('Failed to connect to DB:', error);
  }
};

connectToDb();

export default connectToDb;
