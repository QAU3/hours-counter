const Minutes = require('../models/Minute');
const { CURRENT_YEAR, CURRENT_MONTH, CURRENT_WEEK } = require('../utils/utils');

const getAggregatedMinutes = (username)  => {
    // TODO: Fix not existing week
    const WEEK_AGGREGATION =[
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
        {$match: {'week._id': {$eq:CURRENT_WEEK}}}
    ]
    
    return  Minutes.aggregate(WEEK_AGGREGATION)
}

const getAllUserMinutes = (username) => {
    return Minutes.find({username}).exec()
}

const addMinutes = (entry) => {
    console.log(entry)
    return Minutes.create(entry)
}

const deleteMinutes = (id) => { 
    return Minutes.findByIdAndDelete(id).exec()
}       

const updateMinutes = (entry) => {
    return Minutes.findOneAndUpdate
}        

module.exports = { getAggregatedMinutes, getAllUserMinutes, addMinutes, deleteMinutes }