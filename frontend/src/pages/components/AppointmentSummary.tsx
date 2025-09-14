import React from 'react'

const AppointmentSummary = () => {
  return (
    <>
      <h3 className="text-secondary mb-3">Appointment Summary</h3>
      {/* we have to account for a list of services, not just 1  */}
      <div className="card shadow-sm border-0 rounded-3" style={{ width: "100%", maxWidth: "300px" }}>
        <div className="card-body">
          <div className="d-flex align-items-center mb-3">
            <i className="bi bi-calendar-check me-2 text-secondary"></i> {/* placeholder for a little icon*/}
            <div>
              <p className="fw-semibold mb-0">Service Type here</p>
              <small>$50 · 45 min</small>
            </div>
          </div>

          <hr className="my-2" />

          <div className="">
            <p className="fw-medium mb-0">Service type here </p>
            <small>staff here </small>
          </div>
        </div>
      </div>

    </>
   
  )
}

export default AppointmentSummary;