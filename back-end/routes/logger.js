const express = require('express');
const logger = express.Router();
const minutesController = require('../controllers/minutesController');

logger.get('/:username', async(req, res, next) => {
    console.log(req.params.username)
    try{
        let minutes = await minutesController.getTotalMinutes(req.params.username);
        res.json(minutes)
    }
    catch(err){
        console.log(err.message)
        res.status(500).json({message: err.message})
    }
});

logger.post('/', async(req, res, next) => {
    let {username, minutes, date} = req.body;
    try{
        let timestamp = new Date(date);
        await minutesController.addMinutes({username, minutes, date:timestamp});
        res.json({response: "Minutes added"})
    }catch(err){
        console.log(err.message)
        res.json({response: err.message})
    }
});

module.exports = logger;