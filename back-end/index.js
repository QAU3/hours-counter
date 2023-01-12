const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const app = express();

// SETTINGS
const port = process.env.PORT || 3000;
require("dotenv").config();
const connectionParams = {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  };
  app.use(express.json());
  app.use(express.urlencoded({
    extended: true
  }))
  app.use(cors())

// ROUTES
  const logger = require('./routes/logger');
  
  
app.get('/', (req, res) => {
  res.send('hours-loger-api');
});

app.use("/api/logger", logger)



// START SERVER
mongoose
  .connect(process.env.URI, connectionParams) //process.env.URI comes locally from .env file.
  .then(() => {
    /// Start listening only if database connection succeds
    app.listen(process.env.PORT || port, () => {
      console.log(
        `Database connected and server running on port: ${
            process.env.PORT || port
          }` // If this line is displayed on your console you are ready o go.
      );
    });
  })
  .catch((err) => {
    console.log("Error connecting to the database", err);
  });
