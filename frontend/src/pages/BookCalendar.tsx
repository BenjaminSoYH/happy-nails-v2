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
            <div className="container-fluid px-3"> 
                <div className="row justify-content-center">
                    
                    <div className="col-xxl-4 col-xl-5 col-lg-6 col-md-8">
                        <Calendar selectedDate={selectedDate} setSelectedDate={setSelectedDate} />
                        <TimeSlots selectedDate={selectedDate} />
                    </div>
                    <div className="col-xxl-3 col-xl-4 col-lg-6 col-md-8 mt-3">
                        <AppointmentSummary services={services}/>
                    </div>

                </div>

            </div>
        </>

    )
}

export default BookCalendar;