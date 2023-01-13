const Minutes = require('../models/Minute');
const { CURRENT_YEAR, CURRENT_MONTH, CURRENT_WEEK } = require('../utils/utils');

const getTotalMinutes = (username)  => {
    let workingTime =[
        {$match: {username}},
        {$facet: 
            {
                year:[{$group: {_id: {$year: "$date"}, minutes: {$sum: "$minutes"}}}],
                month:[{$group: {_id: {$month: "$date"}, minutes: {$sum: "$minutes"}}}],
                week:[{$group: {_id: {$week: "$date"}, minutes: {$sum: "$minutes"}}}]
            }   
        },
        {$unwind: '$year'},
        {$unwind: '$month'},
        {$unwind: '$week'},
        {$match: {'year._id': {$eq:CURRENT_YEAR}}},
        {$match: {'month._id': {$eq:CURRENT_MONTH+1}}},
        {$match: {'week._id': {$eq:CURRENT_WEEK}}},

    ]
    let json = Minutes.aggregate(workingTime)
    return json
}

const addMinutes = (entry) => {
    console.log(entry)
    return Minutes.create(entry)
}

module.exports = { getTotalMinutes, addMinutes }