const Minutes = require('../models/Minute');
const {Month} = require('../utils/utils');

const getTotalMinutes = (username)  => {
    // let workingTime = Minutes.find({username})
    let workingTime = Minutes.aggregate([
        { $match: { username: username } },
        { $group: { _id: { month: { $month: "$createdAt" } }, totalMinutes: { $sum: "$minutes" } } }
    ])
    return workingTime
}

const addMinutes = (entry) => {
    return Minutes.create(entry)
}


const handleTime = (workingTime) =>{
    const currentMonth = new Date().getMonth();
    let month = Month[currentMonth];

};

module.exports = { getTotalMinutes, addMinutes }