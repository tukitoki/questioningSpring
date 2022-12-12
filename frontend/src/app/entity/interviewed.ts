import { Form } from "./form";
import { InterviewedChoice } from "./interviewed-choice";

export interface Interviewed {
    form: Form | null;
    interviewedId: number | null;
    interviewedDate: Date;
    interviewedIp: string;
    interviewedChoices: InterviewedChoice[];
    allInterviewedChoices: InterviewedChoice[];
}