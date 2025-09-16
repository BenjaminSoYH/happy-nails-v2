import React, {useEffect, useState} from 'react'
import { ChevronUpIcon, ChevronDownIcon } from '@heroicons/react/24/outline';
import {Service} from '../interfaces/Service'
import ServiceComponent from "./ServiceComponent";

const AppointmentSummary = ({ services, removeService }: { services: Service[], removeService: any }) => {
    const[listView, setListView] = useState(true);
    const[serviceList, setServiceList] = useState<Service[]>(services ?? []);
    const[price, setPrice] = useState(0);
    const[duration, setDuration] = useState(0);

    useEffect(() => {
        // Everytime chosenServices is updated...
        // Update serviceList
        setServiceList(services);
        // Recalculate the price and total duration
        let totalPrice = 0;
        let totalDuration = 0;
        services.forEach(function(element){totalPrice += element.price; totalDuration += element.duration});
        setPrice(totalPrice);
        setDuration(totalDuration);
    }, [services]);

    const convertMins = (minutes : number) => {
        const hours = Math.floor(minutes / 60);
        const min = minutes % 60;
        return hours > 0 ? `${hours} hr ${min} min` : `${minutes} mins`;
    }

  return (
    <div className="d-flex flex-column gap-2 w-100" style={{ maxWidth:"30rem" }}>
        <h4 className="text-body fw-bold">Appointment summary</h4>
        <div id="summary-container">
            {serviceList.length === 0 ? <div className="border border-secondary-subtle p-3 rounded-2">No services added yet</div> :
                <div>
                    <div className="border border-secondary-subtle p-3 rounded-top-2 d-flex align-items-center justify-content-center">
                        <div className="d-flex align-items-center justify-content-between w-100">
                            <div className="d-flex flex-row gap-3">
                                {/*<div id="image" className="border border-secondary-subtle rounded-circle" style={{width:"4rem", height:"4rem"}}>*/}
                                {/*</div>*/}
                                <div className="d-flex flex-column gap-1">
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
                                id={service.id}
                                name={service.name}
                                price={service.price}
                                removeService={removeService}
                            />
                        ))}
                    </div>
                </div>}
        </div>
    </div>
  )
}

export default AppointmentSummary;