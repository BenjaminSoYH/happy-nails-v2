import React, { useEffect, useState } from 'react'; 

type TimeSlot = {
    time: string;
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

const groups = groupSlotsByTimePeriod(slots);

const TimeSlots = () => {
    return (
        <>
            <h1 className="mt-3 mx-3">
                Today, September 9th
            </h1>
            <p className="mb-3 mx-3">
                (Times are shown in PST)
            </p>
            {Object.entries(groups).map(([period, slots]) => (
                <div key={period} className="m-3">
                    <h3>
                        {period}
                    </h3>
                    <div className="row">
                        {slots.map((s: TimeSlot, index: number) => (
                            <div className = "col-3 mb-3" key ={index}>
                                <button className = "btn btn-outline-secondary ">    {/* w-100 add here */}
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

// const TimeSlots = () => {
//   return (
//     <div className = "container mt-3">
//         <div className = "row justify-content-left">
//             <div className = "col-4">
//                 <h1 className = "mb-5">
//                     Today, September 9th
//                 </h1>
//                 <div className = "row">
//                     <div className = "col-3 mb-3">
//                         <button type="button" className="btn btn-outline-secondary text-dark me-4">
//                             1:30
//                         </button>
//                     </div>
//                     <div className = "col-3 mb-3">
//                         <button type="button" className="btn btn-outline-secondary text-dark me-4">
//                             2:30
//                         </button>
//                     </div>
//                     <div className = "col-3 mb-3">
//                         <button type="button" className="btn btn-outline-secondary text-dark me-4">
//                             3:30
//                         </button>
//                     </div>
//                     <div className = "col-3 mb-3">
//                         <button type="button" className="btn btn-outline-secondary text-dark me-4">
//                             4:30
//                         </button>
//                     </div>
//                     <div className = "col-3 mb-3">
//                         <button type="button" className="btn btn-outline-secondary text-dark me-4">
//                             5:30
//                         </button>
//                     </div>
//                     <div className = "col-3 mb-3">
//                         <button type="button" className="btn btn-outline-secondary text-dark me-4">
//                             6:30
//                         </button>
//                     </div>
//                     <div className = "col-3 mb-3">
//                         <button type="button" className="btn btn-outline-secondary text-dark me-4">
//                             7:30
//                         </button>
//                     </div>
//                     <div className = "col-3 mb-3">
//                         <button type="button" className="btn btn-outline-secondary text-dark me-4">
//                             8:30
//                         </button>
//                     </div>
//                     <div className = "col-3 mb-3">
//                         <button type="button" className="btn btn-outline-secondary text-dark me-4">
//                             9:30
//                         </button>
//                     </div>
//                 </div>
                
//             </div>
//         </div>
//     </div>
//   )
// }

// export default TimeSlots;