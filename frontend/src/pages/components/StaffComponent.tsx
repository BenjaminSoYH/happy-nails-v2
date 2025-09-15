import React from 'react'
import {NailTech} from "../interfaces/NailTech";

const StaffComponent = ({tech}:{tech:NailTech}) => {
  return (
      <div id="staff" className="p-3 text-black d-flex flex-row gap-3 align-items-center cursor-pointer">
          <div id="image" className="border border-secondary-subtle rounded-circle" style={{width:"4rem", height:"4rem"}}>
          </div>
          <div className="d-flex flex-column gap-0">
              <h5 className="">{tech.name}</h5>
              <h5 className="text-secondary fw-light">{tech.description}</h5>
          </div>

      </div>
  )
}

export default StaffComponent;