const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const MinuteSchema = new Schema({
    username: {type: String, required: true},
    minutes: {type: Number, required: true}
}, {timestamps: true});


module.exports = mongoose.model('Minute', MinuteSchema);