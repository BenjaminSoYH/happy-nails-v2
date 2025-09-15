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
            <div className="d-flex flex-column gap-2 mt-4" style={{ width: "30rem" }}>
                <h4 className="text-body fw-bold">
                    {selectedDate.toLocaleDateString("en-US", {
                        weekday:"long",
                        month: "long",
                        day: "numeric"
                    })}
                </h4>
                <div className="d-flex justify-content-center">
                    <DatePicker
                        selected={selectedDate}
                        onChange={(date) => date && setSelectedDate(date)}
                        inline
                        calendarClassName="shadow"
                    />
                    
                </div>
                <p className="text-secondary fst-italic text-center">
                    (Times below are shown in PST)
                </p>
            </div>

        </>
    )
}

export default Calendar;