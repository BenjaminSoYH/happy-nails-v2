import React from 'react'

const AppointmentSummary = () => {
  return (
    <>

      <div className="col-auto">
        <h3 className="text-secondary">Appointment Summary</h3>
        {/* we have to account for a list of services, not just 1  */}
        <div className="card shadow-sm border-0 rounded-3" style={{ width: "100%", maxWidth: "300px" }}>
          <div className="card-body">
            <div className="d-flex justify-content-between align-items-center mb-3">
              <p className="fw-semibold mb-0">Service Type here</p>
            </div>

            <hr className="my-2" />

            <div className="d-flex justify-content-between align-items-center">
              <p className="fw-medium mb-0">Service Description here</p>
              <p className="fw-semibold mb-0">$Price here · duration here</p>
            </div>
          </div>
        </div>
      </div>

    </>
   
  )
}

export default AppointmentSummary;