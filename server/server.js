import express from 'express';
import cors from 'cors';
import axios from 'axios';

const app = express();
const PORT = process.env.PORT || 5000;

app.use(cors());
app.use(express.json());
let token = '9163446c-1aa0-4a82-8d7e-6d0b3c813443';

app.post('/api/token', async (req, res) => {
  const clientId =
    '96dHZVzsAuuXluc72nfI5Fwaz_ZFDosXLb92b-noHubpjkuxZA0Jyp7HKE6HymQJFPH3rPD2vFihwP6YSuZnOw==';
  const clientSecret =
    'lrFxI-iSEg-ndISzJ-aWcJ5O5y2ZTqjI9lVy-1cNX_5X4X3B5Kl8eyZjlVOrpQNn8ooO7LWPWaYZV9xEBAeqD-sXW5nudhmq';

  const authHeader = `Basic ${Buffer.from(`${clientId}:${clientSecret}`).toString('base64')}`;

  try {
    const response = await axios.post(
      'https://outpost.mappls.com/api/security/oauth/token',
      new URLSearchParams({
        grant_type: 'client_credentials'
      }),
      {
        headers: {
          Authorization: authHeader,
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }
    );
    token = response.data.access_token;
    res.json(response.data);
  } catch (error) {
    console.error('Error fetching token:', error.message);
    if (error.response) {
      console.error('Response data:', error.response.data);
      console.error('Response status:', error.response.status);
      console.error('Response headers:', error.response.headers);
      res.status(error.response.status).send(error.response.data);
    } else if (error.request) {
      console.error('No response received:', error.request);
      res.status(500).send('No response received from the server');
    } else {
      console.error('Error setting up request:', error.message);
      res.status(500).send('Error setting up request');
    }
  }
});

app.post('/api/geocode', async (req, res) => {
  const { address } = req.body;
  console.log('Token:', token);
  console.log('Address:', address);
  try {
    const response = await axios.get('https://atlas.mappls.com/api/places/geocode', {
      params: {
        region: 'ind',
        address,
        itemCount: 5,
        bias: 0
      },
      headers: {
        Authorization: `Bearer ${token}`
      }
    });

    console.log(response.data);
    res.status(200).json(response.data);
  } catch (error) {
    console.error('Error fetching geocode:', error.message);
    res.status(500).json({ error: error.message });
  }
});

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
