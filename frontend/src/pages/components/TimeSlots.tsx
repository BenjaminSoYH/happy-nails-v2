import React, { useEffect, useState } from 'react'; 

type TimeSlot = {
    time: string;
}

type TimeSlotsProps = {
    selectedDate: Date;
}

const slots: TimeSlot[] = [
    { time: "2025-09-29T08:00:00" },
    { time: "2025-09-29T09:00:00" },
    { time: "2025-09-29T10:00:00" },
    { time: "2025-09-29T12:00:00" },
    { time: "2025-09-29T12:00:00" },
    { time: "2025-09-29T13:00:00" },
    { time: "2025-09-29T14:00:00" },
    { time: "2025-09-29T16:00:00" },
    { time: "2025-09-29T16:30:00" }
]

function groupSlotsByTimePeriod(slots: TimeSlot[]) {
    const groups: Record<string, TimeSlot[]> = { Morning: [], Afternoon: [], Evening: [] };

    slots.forEach((timeSlot) => {
        const hour = new Date(timeSlot.time).getHours();

        if (hour < 12) {
            groups.Morning.push(timeSlot);
        } else if (hour < 17) {
            groups.Afternoon.push(timeSlot);
        } else {
            groups.Evening.push(timeSlot);
        }
    });
    
    return groups;
}



const TimeSlots = ({selectedDate}: TimeSlotsProps) => {

    const filteredSlots = slots.filter(
        (slot) => new Date(slot.time).toDateString() === selectedDate.toDateString()
    );

    const groups = groupSlotsByTimePeriod(filteredSlots);

    return (
        <>
            {Object.entries(groups).map(([period, slots]) => (
                <div key={period} className="m-3">
                    <h3>
                        {period}
                    </h3>
                    <div className="row">
                        {slots.map((s: TimeSlot, index: number) => (
                            <div className = "col-6 col-md-4 col-lg-3 mb-3" key ={index}>
                                <button className = "btn btn-outline-secondary "> 
                                    {new Date(s.time).toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'})}
                                </button> 
                            </div>
                        ))}
                    </div>
                </div>
                
            ))}
        </>
        
    )
    
}

export default TimeSlots;
