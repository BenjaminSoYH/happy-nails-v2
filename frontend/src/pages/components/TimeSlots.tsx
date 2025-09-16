import React, { useEffect, useState } from 'react'; 
import { Availability } from '../interfaces/Availability';

type TimeSlot = {
    startTime: string,
    endTime: string
}

type TimeSlotsProps = {
    selectedDate: Date;
}

const useAvailabilities = (nailTechId: number) => {
    const [availabilities, setAvailabilities] = useState<Availability[]>([]);

    useEffect(() => {
        const getAvailabilities = async () => {
            try {
                const url = `http://localhost:8080/api/nailtechs/${nailTechId}/availabilities`;
                const response = await fetch(url);
                const json = await response.json();
                const data = json; // json instead of json.content backend doesn't wrap it in content object
                setAvailabilities(data ?? []);
                console.log("Fetched availabilities:", data);
                console.log("Availabilities fetched successfully.")
            } catch (e) {
                console.log("Failed to fetch availabilities");
            }
        }
        getAvailabilities();
    }, [nailTechId])

    return availabilities;
}

function groupSlotsByTimePeriod(slots: TimeSlot[]) {
    const groups: Record<string, TimeSlot[]> = { Morning: [], Afternoon: [], Evening: [] };

    slots.forEach((timeSlot) => {
        const hour = new Date(timeSlot.startTime).getHours();

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

    const availabilities = useAvailabilities(34); //techId

    const slots: TimeSlot[] = availabilities.map((a) => ({
        startTime: a.startTime,
        endTime: a.endTime,
    }));

    const filteredSlots = slots.filter(
        (slot) => new Date(slot.startTime).toDateString() === selectedDate.toDateString()
    );

    const groups = groupSlotsByTimePeriod(filteredSlots);

    const noSlotsAvailable = Object.values(groups).every(
        (group) => group.length === 0
    );


    return (
        <div className="d-flex flex-column gap-3" style={{ width: "30rem" }}>
            {noSlotsAvailable ? (
                <div className="mx-4">
                    <p className="border 
                                  border-secondary-subtle 
                                  p-3 
                                  rounded-2 
                                  text-center 
                                  fst-italic 
                                  text-muted">
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
                                            {new Date(s.startTime).toLocaleTimeString([], 
                                                    {hour: '2-digit', minute: '2-digit'})}
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
