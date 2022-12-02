import {Choice} from "./choice";

export interface Question {
  questionId: number | null,
  description: string,
  numberOfChoices: number,
  choices: Choice[]
}
