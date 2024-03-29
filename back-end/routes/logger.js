const express = require('express');
const logger = express.Router();
const minutesController = require('../controllers/minutesController');

logger.get('/:username', async(req, res, next) => {
    console.log(req.params.username)
    try{
        let minutes = await minutesController.getAggregatedMinutes(req.params.username);
        res.json(minutes)
    }
    catch(err){
        console.log(err.message)
        res.status(500).json({message: err.message})
    }
});

logger.get('/all/:username', async(req, res, next) => {
    try{
        let minutes = await minutesController.getAllUserMinutes(req.params.username);
        res.json(minutes)
        console.log(minutes)
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
        res.status(500).json({message: err.message})
    }
});



logger.delete('/:id', async(req, res, next) => {
    try{
        await minutesController.deleteMinutes(req.params.id);
        res.json({response: "Minutes deleted"})
    }catch(err){
        console.log(err.message)
        res.status(500).json({message: err.message})
    }
});

logger.put('/', async(req, res, next) => {
    let {username, minutes, date} = req.body;
    try{
        let timestamp = new Date(date);
        await minutesController.updateMinutes({username, minutes, date:timestamp});
        res.json({response: "Minutes updated"})
    }catch(err){
        console.log(err.message)
        res.status(500).json({message: err.message})
    }
});

module.exports = logger;