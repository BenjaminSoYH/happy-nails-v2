import React, {useEffect, useState} from 'react'
import { ChevronUpIcon, ChevronDownIcon } from '@heroicons/react/24/outline';
import {Service} from '../interfaces/Service'
import ServiceComponent from "./ServiceComponent";

const AppointmentSummary = ({ services }: { services: Service[] }) => {
    const[listView, setListView] = useState(true);
    const[serviceList, setServiceList] = useState<Service[]>(services ?? []);
    const[price, setPrice] = useState(0);
    const[duration, setDuration] = useState(0);

    // Define a method that calculates the total price
    useEffect(() => {
        // Everytime serviceList is updated, recalculate the time and duration.
        let totalPrice = 0;
        let totalDuration = 0;
        serviceList.forEach(function(element){totalPrice += element.price; totalDuration += element.duration});
        // Set total price
        setPrice(totalPrice);
        // Set duration
        setDuration(totalDuration);
    }, [serviceList])

    const convertMins = (minutes : number) => {
        const hours = Math.floor(minutes / 60);
        const min = minutes % 60;
        // If hours is larger than 0
        return hours > 0 ? `${hours} hr ${min} min` : `${minutes} mins`;
    }

  return (
    <div className="d-flex flex-column gap-2">
        <h4 className="text-body fw-bold">Appointment summary</h4>
        <div id="summary-container">
            {serviceList.length == 0 ? <div className="border border-light p-4 rounded-2">No services added yet</div> :
                <div>

                    <div className="border border-secondary-subtle p-3 rounded-top-2 d-flex align-items-center justify-content-center">
                        <div className="d-flex align-items-center justify-content-between">

                            <div className="d-flex flex-row gap-3">
                                <image id="image" className="border border-secondary-subtle rounded-circle" style={{width:"4rem", height:"4rem"}}>
                                </image>
                                <div className="d-flex flex-column gap-2">
                                    <h5 className="text-body fw-semibold">{serviceList.length} Services</h5>
                                    <h6 className="text-secondary fw-semibold">{`$${price.toFixed(2)}`} <span>·</span> {convertMins(duration)}</h6>
                                </div>
                            </div>

                            <button type="button" className="btn btn-sm rounded-circle bg-secondary-subtle d-flex align-items-center justify-content-center" style={{width:"2rem", height:"2rem"}} onClick={() => {setListView(!listView)}}>
                                {listView ? <ChevronUpIcon></ChevronUpIcon> : <ChevronDownIcon></ChevronDownIcon>}
                            </button>

                        </div>
                    </div>



                    <div className="border-bottom border-start border-end border-secondary-subtle p-3 rounded-bottom-2 d-flex flex-column gap-3">
                        {listView && serviceList.map((service) => (
                            <ServiceComponent
                                key={service.name}
                                name={service.name}
                                description={service.description}
                                price={service.price}
                            />
                        ))}
                    </div>
                </div>}
        </div>

    </div>
  )
}

export default AppointmentSummary;