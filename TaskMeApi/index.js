
require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const mongoString = process.env.DATABASE_URL;
const routes = require('./routes/TaskMeRoutes');

mongoose.connect(mongoString);
const database = mongoose.connection;

database.on('error', (error) => {
    console.log(error);
});

database.once('connected', () => {
    console.log("Database connected");
});

const app = express()

app.use(express.json());
app.use('/api', routes);

app.listen(3000, () => {
    console.log('Server is running in port 3000');
});

process.on('SIGTERM', () => {
    server.close(() => {
        console.log('Process terminated');
    });
});

process.on('SIGINT', () => {
    server.close(() => {
        console.log('Process terminated');
    });
});