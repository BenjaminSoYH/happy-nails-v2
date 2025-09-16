export type ApptStatus =
  | 'PENDING'
  | 'CONFIRMED'
  | 'CANCELLED'
  | 'COMPLETED'
  | 'NO_SHOW'
  | 'RESCHEDULED';

export interface Appointment {
    id: number,
    startTime: string;  
    endTime: string;    
    status: ApptStatus,
}