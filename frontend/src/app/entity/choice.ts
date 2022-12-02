import { InterviewedChoice } from "./interviewed-choice";

export interface Choice {
  choiceId: number | null;
  description: string;
  interviewedChoices: InterviewedChoice[] | null;
}
