import React, {useState} from 'react'
import {TrashIcon} from "@heroicons/react/16/solid";

const ServiceComponent = ({name, price, id, removeService} : {name:string, price:number, id:number, removeService:any}) => {
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
                  <TrashIcon style={{width:"1rem",
                      height: "1rem",
                      cursor:"pointer",
                      color: hover ? "red" : "black"}}
                              onMouseEnter={() => {setHover(true)}}
                              onMouseLeave={() => {setHover(false)}}
                              onClick={() => removeService(id)}
                  ></TrashIcon>
          </div>
      </div>
  )
}

export default ServiceComponent