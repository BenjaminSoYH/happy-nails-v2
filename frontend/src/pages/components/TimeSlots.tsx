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

    const noSlotsAvailable = Object.values(groups).every(
        (group) => group.length === 0
    );


    return (
        <div className="d-flex flex-column gap-3" style={{ width: "30rem" }}>
            {noSlotsAvailable ? (
                <div className="mx-4">
                    <p className="border border-secondary-subtle p-3 rounded-2 text-center fst-italic text-muted">
                        No availability for this date 
                    </p>
                </div>
            ) : (
                Object.entries(groups).map(([period, slots]) => (
                    slots.length > 0 && (
                        <div key={period}>
                            <h5 className="fw-semibold">
                                {period}
                            </h5>
                            <div className="row">
                                {slots.map((s: TimeSlot, index: number) => (
                                    <div className = "col-6 col-md-4 col-lg-3 mb-3" key ={index}>
                                        <button className = "btn btn-outline-secondary w-100"> 
                                            {new Date(s.time).toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'})}
                                        </button> 
                                    </div>
                                ))}
                            </div>
                        </div>
                    )
                ))
            )}
            
        </div>
        
    )
    
}

export default TimeSlots;
