import React, {useState} from 'react'
import {PencilIcon} from "@heroicons/react/16/solid";

const ServiceComponent = ({name, description, price} : {name:string, description:string, price:number}) => {
    const[hover, setHover] = useState(false);
  return (
      <div className="d-flex justify-content-between align-items-center">
          <div className="d-flex flex-column">
              <h6 className="text-body fw-semibold m-0">{name}</h6>
          </div>
          <div className="d-flex flex-row gap-2 align-items-center">
              <h6 className="text-body fw-light m-0">
                  <span>$</span>{price.toFixed(2)}
              </h6>
                  <PencilIcon style={{width:"1rem",
                      height: "1rem",
                      cursor:"pointer",
                      color: hover ? "blue" : "black"}}
                              onMouseEnter={() => {setHover(true)}}
                              onMouseLeave={() => {setHover(false)}}
                  ></PencilIcon>
          </div>
      </div>
  )
}

export default ServiceComponent