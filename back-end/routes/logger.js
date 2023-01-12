const { json } = require('express');
const express = require('express');
const logger = express.Router();
const minutesController = require('../controllers/minutesController');

logger.get('/', async(req, res, next) => {
    try{
        let minutes = await minutesController.getTotalMinutes("cuauhtli");
        res.json(minutes)
    }
    catch(err){
        console.log(err.message)
        res.status(500).json({message: err.message})
    }
});

logger.post('/', async(req, res, next) => {
    let {username, minutes} = req.body;
    try{
        let query = await minutesController.addMinutes({username, minutes});
        res.json(query)
    }catch(err){
        console.log(err.message)
        res.status(500).json({message: err.message})
    }
});

module.exports = logger;