import React, { useState } from 'react'
import TimeSlots from './components/TimeSlots';
import AppointmentSummary from './components/AppointmentSummary';
import Calendar from './components/Calendar';

const BookCalendar = () => {

    const[selectedDate, setSelectedDate] = useState<Date>(new Date());
    const services = [
        {id: 12, name: "Straight-Razor Shave", description: "", duration: 45, price: 40.00},
        {id: 13, name: "Haircut", description: "", duration: 45, price: 50.00},

    ]

    return (
        <>
            <div className="row">

                <div className="col-lg-5 col-md-12">
                    <Calendar selectedDate={selectedDate} setSelectedDate={setSelectedDate} />
                    <TimeSlots selectedDate={selectedDate} />
                </div>
                <div className="col-lg-7 col-md-12 d-flex justify-content-center mt-lg-5 mt-3">
                    <AppointmentSummary services={services}/>
                </div>

            </div>

        </>

    )
}

export default BookCalendar;