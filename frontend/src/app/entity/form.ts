import { Question } from "./question";

export interface Form {
    formId: number | null;
    description: string;
    questions: Question[];
}
