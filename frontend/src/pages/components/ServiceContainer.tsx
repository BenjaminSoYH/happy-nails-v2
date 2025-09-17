import React, {useEffect, useState} from 'react'
import {Service} from "../interfaces/Service";
import ServiceItem from "./ServiceItem";

const ServiceContainer = ({services, addService, chosenServices} : {services: Service[], addService: any, chosenServices: Service[]}) => {
  const[serviceList, setList] = useState<Service[]>(services ?? []);
  useEffect(() => {
    setList(services);
  }, [services])

  // selected checks whether an element inside chosen service has the same id as current service
  // id

  return (
    <div>
      {serviceList.map((service, index) => (
          <div key={service.id}>
            <ServiceItem
                addService={addService}
                service={service}
                selected={chosenServices.some(s => s.id === service.id)}></ServiceItem>
            {index < serviceList.length - 1 && <hr></hr>}
          </div>
      ))}
    </div>
  )
}

export default ServiceContainer;