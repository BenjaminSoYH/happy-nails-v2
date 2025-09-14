import React, { useState } from 'react'
import TimeSlots from './components/TimeSlots';
import AppointmentSummary from './components/AppointmentSummary';
import Calendar from './components/Calendar';

const BookCalendar = () => {

    const[selectedDate, setSelectedDate] = useState<Date>(new Date());

    

    return (
        <>
            <div className="row ">
                
                <div className="col-lg-4 col-md-12 offset-lg-2">
                    <Calendar selectedDate={selectedDate} setSelectedDate={setSelectedDate} />
                    <TimeSlots selectedDate={selectedDate} />
                </div>
                <div className="col-lg-3 col-md-12 mt-lg-4 mt-3">
                    <AppointmentSummary/>
                </div>
                
            </div>
            
        </>
        
    )
}

export default BookCalendar;