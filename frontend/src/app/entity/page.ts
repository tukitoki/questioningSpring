import { Form } from "./form"

export interface Page<T> {
    content: T[],
    pageable: {
        sort: {
            empty: boolean,
            sorted: boolean,
            unsorted: boolean
        },
        offset: number,
        pageSize: number,
        pageNumber: number,
        unpaged: boolean,
        paged: boolean
    },
    totalPages: number,
    totalElements: number,
    last: boolean,
    size: number,
    number: number,
    sort: {
        empty: boolean,
        sorted: boolean,
        unsorted: boolean
    },
    first: boolean,
    numberOfElements: number,
    empty: boolean
}