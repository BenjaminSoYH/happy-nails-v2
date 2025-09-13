import React from 'react';
import {useState} from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

type CalendarProps = {
    selectedDate: Date;
    setSelectedDate: (date: Date) => void;
}

const Calendar = ({ selectedDate, setSelectedDate }: CalendarProps) => {

    return (
        <>
            <div className="m-3">
                <h1 className="mb-3">
                    {selectedDate.toLocaleDateString("en-US", {
                        weekday:"long",
                        month: "long",
                        day: "numeric"
                    })}
                </h1>
                <div className="d-flex justify-content-center">
                    <DatePicker
                        selected={selectedDate}
                        onChange={(date) => date && setSelectedDate(date)}
                        inline
                        calendarClassName="shadow"
                    />
                    
                </div>
                <p className="d-flex justify-content-center mt-3 mx-3">
                    (Times below are shown in PST)
                </p>
            </div>

        </>
    )
}

export default Calendar;