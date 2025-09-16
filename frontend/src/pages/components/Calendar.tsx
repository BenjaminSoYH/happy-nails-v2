import React, { useState } from 'react';
import "react-datepicker/dist/react-datepicker.css";

type CalendarProps = {
  selectedDate: Date;
  setSelectedDate: (date: Date) => void;
};

function getStartOfWeek(date: Date) {
  const d = new Date(date);
  const day = d.getDay(); // 0 = Sunday
  d.setDate(d.getDate() - day);
  d.setHours(0, 0, 0, 0);
  return d;
}

function getWeekDates(start: Date) {
  return Array.from({ length: 7 }, (_, i) => {
    const d = new Date(start);
    d.setDate(start.getDate() + i);
    return d;
  });
}

const Calendar = ({ selectedDate, setSelectedDate }: CalendarProps) => {
  const [currentWeekStart, setCurrentWeekStart] = useState(getStartOfWeek(new Date()));
  const weekDates = getWeekDates(currentWeekStart);

  return (
    <div className="d-flex flex-column gap-4 mt-4 w-100" style={{ maxWidth: "30rem" }}>
      <div>
        <h4 className="text-body fw-bold">
          {selectedDate.toLocaleDateString("en-US", {
            weekday: "long",
            month: "long",
            day: "numeric",
          })}
        </h4>
        {/* <div className="d-flex justify-content-center">
          <DatePicker
            selected={selectedDate}
            onChange={(date) => date && setSelectedDate(date)}
            inline
            calendarClassName="shadow"
            minDate={new Date()}
          />
        </div> */}
      </div>

      {/* Week calendar  */}
      <div>
        <div className="d-flex align-items-center mb-3">
          <h5 className="flex-grow-1 text-body fw-bold">
            {currentWeekStart.toLocaleString("default", { month: "long", year: "numeric" })}
          </h5>
          <button
            className="btn btn-outline-secondary me-2"
            onClick={() =>
              setCurrentWeekStart(prev => new Date(prev.getFullYear(), prev.getMonth(), prev.getDate() - 7))
            }
          >
            &lt;
          </button>
          <button
            className="btn btn-outline-secondary"
            onClick={() =>
              setCurrentWeekStart(prev => new Date(prev.getFullYear(), prev.getMonth(), prev.getDate() + 7))
            }
          >
            &gt;
          </button>
        </div>

        <div className="d-flex justify-content-between">
          {weekDates.map((date, i) => {
            const isPast = date.getTime() < new Date().setHours(0, 0, 0, 0);
            const isSelected = date.toDateString() === selectedDate.toDateString();
            return (
              <button
                key={i}
                className={`btn flex-fill mx-1 ${
                  isSelected
                    ? "btn-primary text-white"
                    : isPast
                    ? "btn-light text-decoration-line-through"
                    : "btn-outline-primary"
                }`}
                disabled={isPast}
                onClick={() => setSelectedDate(date)}
              >
                <div>{date.toLocaleDateString("en-US", { weekday: "short" })}</div>
                <div>{date.getDate()}</div>
              </button>
            );
          })}
        </div>
      </div>

      <p className="text-secondary fst-italic text-center mt-2">
        (Times below are shown in PST)
      </p>
    </div>
  );
};

export default Calendar;
